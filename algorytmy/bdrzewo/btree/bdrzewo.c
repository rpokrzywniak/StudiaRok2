#include <stdio.h>
#include <stdlib.h>
#define T 3   // stopien B-drzewa
static int mini=T-1;
static int maxi=(2*T)-1;
static int t=T;
typedef struct Wezel {
    short n; //ilosc kluczy (-1 oznacza wezel usuniety)
    short leaf; // czy lisc
    int k[2 * T]; // klucze
    int c[2 * T + 1]; // wskazniki do synow (pozycje w pliku: 0,1,2 ...)
    // int info[2 * T - 1]; // wskazniki do informacji skojarzonej z kluczem
    // (pozycje w innym pliku); tutaj nie beda uzyte
    int pozycjaWDrzewie;
    struct Wezel *d[2*T+1];
} Wezel;
Wezel TR;
int ROOT = 0;
int rozmiarw = sizeof (Wezel); // rozmiar wezla w bajtach
FILE *drzewo; // plik drzewa (zawierajacy wezly)
int POZYCJAWPLIKU = 0; // numer ostatnio zapisanego wiersza w pliku;

void bdrzewa_usuwanie(Wezel *x,int k);
void usuwanie(Wezel *x,int k);

void zapisz(int i, Wezel *w) {
    // zapisuje *w jako i-ty zapis w pliku drzewa
    fseek(drzewo, (long) i*rozmiarw, SEEK_SET);
    fwrite(w, rozmiarw, 1, drzewo);
    //  printf("z%d ",i);
}

void odczytaj(int i, Wezel *w) {
    // odczytuje i-ty zapis w pliku drzewa i wpisuje do *w
    fseek(drzewo, (long) i*rozmiarw, SEEK_SET);
    fread(w, rozmiarw, 1, drzewo);
    //  printf("o%d ",i);
}

void zerujKluczeWezla(Wezel *w) {
    int i;
    for (i = 0; i <= 2 * T - 1; i++) {
        w->k[i] = 0;
        w->c[i] = 0;
    }
    w->c[i] = 0;
}

void drukuj() {
    int i = 0,j;
    Wezel w;
    for (i = 0; i < POZYCJAWPLIKU; i++) {
        odczytaj(i, &w);
        printf("%d. WEZEL n = %d leaf = %d\n", i, w.n, w.leaf);
        printf("               klucze = ");
        for(j=1;j<= w.n;j++)
        printf("%d ",w.k[j]);
        printf("\n");
        printf("\n\n");
    }
}

void drukujZawartoscWezla(Wezel *w) {
    printf("WEZEL n = %d leaf = %d\n", w->n, w->leaf);
    printf("               klucze= [1] %d [2] %d [3] %d [4] %d [5] %d\n", w->k[1], w->k[2], w->k[3], w->k[4], w->k[5]);
    printf("wskazniki do synow= [1] %d [2] %d [3] %d [4] %d [5] %d [6] %d\n", w->c[1], w->c[2], w->c[3], w->c[4], w->c[5], w->c[6]);
    printf("-------------------------------\n");

}

void BTreeSplitChild(
        Wezel *x, //NIE pełny węzeł wewnętrzny
        int i, //index
        Wezel *y //Pęłny węzeł y (syn x)
        ) {
    Wezel z;
    zerujKluczeWezla(&z);
    z.leaf = y->leaf;
    z.n = T - 1; // ?-1
    int j;
    for (j = 1; j <= T - 1; j++) { //?!?!adaptacja T-1 skrajnie prawych kluczy z wezla y do wezla Z
        z.k[j] = y->k[j + T ];
    }
    //drukujZawartoscWezla(&z);
    if (!y->leaf) { //?               //jezeli y NIE był lisciem to przepisuj tez wskazniki do synow (pozycje w pliku 0,1,2..)
        for (j = 1; j <= T; j++) {
            z.c[j] = y->c[j + T];
        }
    }
    //drukujZawartoscWezla(&z);
    y->n = T - 1; // - 1; //wezel y nie jest juz pełen
    for (j = x->n + 1; j >= i + 1; j--)  // przesuniecie wskaznikow do synow x'sa aby zrobic miejsce na nowy wskaznik do nowo przyjetego klucza z wezla y
        x->c[j + 1] = x->c[j];

    x->c[i + 1] = POZYCJAWPLIKU; // zapisem pod ktory wiersz ma byc wpisany wezel z do pliku
    z.pozycjaWDrzewie = POZYCJAWPLIKU; //zamiana
    zapisz(POZYCJAWPLIKU++, &z); //z wstawic na kolejna pozycje w pliku ktory bd synem x
    for (j = x->n; j >= i; j--) {
        x->k[j + 1] = x->k[j];
    }
    x->k[i] = y->k[T]; //dodanie srodkowego klucza z wezla Y do węzła X
    x->n = x->n + 1;
   zapisz(y->pozycjaWDrzewie, y);//disk-write(y)
    zapisz(x->pozycjaWDrzewie, x);//disk-write(x)
}


void BTreeInsertNonFull(
        Wezel *x, //musi byc Wezlem bo do niego? bd wstawionny nowy klucz k
        int k // klucz który bd wstawiony
        ) {
    int i = x->n; //ilosc kluczy w wezle x
    if (x->leaf) {
        while (i >= 1 && k < x->k[i]) {
            x->k[i + 1] = x->k[i];
            i = i - 1;
        }

        x->k[i + 1] = k;
        x->n = x->n + 1;
        zapisz(x->pozycjaWDrzewie, x);//DiskWrite(x); //Zapisz zmiany (czyli dodanie klucza k)
    } else {
        while (i >= 1 && k < x->k[i])
            i = i - 1;
        i = i + 1;
        Wezel tmp;
        odczytaj(x->c[i], &tmp);
        if (tmp.n == 2 * T - 1) {
            BTreeSplitChild(x, i, &tmp); // na i-tej pozycji wstaw wskaznik do wezla  TMP
            if (k > x->k[i])
                i = i + 1;
        }
        odczytaj(x->c[i], &tmp);
        BTreeInsertNonFull(&tmp, k);
    }
}

void BTreeInsert(Wezel TT, int k) {
    Wezel r;
    odczytaj(ROOT, &r); //r=root[T]?
    if (r.n == (2 * T - 1)) {
        Wezel s; //mozliwe allokacja pamieci; s ma zostac nowym korzeniem
        zerujKluczeWezla(&s);
        s.pozycjaWDrzewie = POZYCJAWPLIKU;
        ROOT = POZYCJAWPLIKU;
        s.leaf = 0;
        s.n = 0;
        s.c[1] = r.pozycjaWDrzewie; //podobna sytuacja jak wyzej, w s.c[1] ma byc numer wierszu w kótrym znajduje sie zapisana tam wartosc r
        zapisz(POZYCJAWPLIKU++, &s);
        BTreeSplitChild(&s, 1, &r);
        //wczytac ponownie S?
        //odczytaj(s.pozycjaWDrzewie,&s);
        BTreeInsertNonFull(&s, k); //te s nie wie o zmianach w f BTreeSplitChild
    } else BTreeInsertNonFull(&r, k); //do wezla r dodajemy klucz k
}

Wezel BTreeCreate() {
    Wezel x;
    x.leaf = 1;
    x.n = 0;
    x.pozycjaWDrzewie = POZYCJAWPLIKU;   //root[T]=x
    zerujKluczeWezla(&x);
    ROOT = POZYCJAWPLIKU;
    zapisz(POZYCJAWPLIKU++, &x);    //DiskWrite(X)
    return x;
}

void drukujZawartoscPliku() {
    int i = 0;
    Wezel w;
    //for(i=0;i<POZYCJAWPLIKU;i++){
    for (i = 0; i < 3; i++) {
        odczytaj(i, &w);
        printf("%d. WEZEL n = %d leaf = %d\n", i, w.n, w.leaf);
        printf("            klucze= [0] %d [1] %d [2] %d [3] %d [4] %d\n", w.k[0], w.k[1], w.k[2], w.k[3], w.k[4]);
        printf("wskazniki do synow= [0] %d [1] %d [2] %d [3] %d [4] %d [5] %d\n", w.c[0], w.c[1], w.c[2], w.c[3], w.c[4], w.c[5]);
        printf("*******************************\n");
    }
}

Wezel BTreeSearch(Wezel x, int k) {
    int i = 1;
    while (i <= x.n && k > x.k[i])
        i++;
    if (i <= x.n && k == x.k[i]) {
        printf("Znalazlem wezel posiadajacy klucz k = %d  i = %d\n", k, i);
        return x;
    }
    if (x.leaf) {
        printf("NIE Znalazlem wezela posiadajacego klucza k = %d ;(\n", k);
        return;
    } else {
        Wezel tmp;
        odczytaj(x.c[i], &tmp);
        return BTreeSearch(tmp, k);
    }
}
void bdrzewa_usuwanie(Wezel *x ,int e)
{
    int i;
    if(!x->leaf && x->n==1 && x->d[1]->n==mini && x->d[2]->n==mini)
    {
        Wezel *y = x->d[1];
        Wezel *z = x->d[2];
        y->k[t]= x->k[1];
        for(i=1; i<t; i++)
        {
            y->k[t+i]= z->k[i];
            y->d[t+i]= z->d[i];
        }
        y->d[t+i]= z->d[i];
        y->n= maxi;
        x= y;
        free(x);
        usuwanie(y,e);
    }
    else usuwanie(x,e);
    return;
}

void usuwanie(Wezel *x,int e)
{
    int i=1;
    int j;
    while(i<(x->n+1) && e>x->k[i]) i= i+1;
    if(i<(x->n+1) && e==x->k[i]) //jesli znaleziony zostal klucz w wezle x
    {
        if(x->leaf)//jesli x lisciem
        {
            for(j=i; j<x->n; j++)
            x->k[j]= x->k[j+1];
            x->n-=1;
            printf("\nKlucz %d usunieto\n", e);
        }
        else//jesli x wezlem wewnetrznym
        {
            Wezel *y= x->d[i];
            Wezel *z= x->d[i+1];
            if(y->n > mini)
            {
                int kk= y->k[y->n];
                x->k[i]= kk;
                bdrzewa_usuwanie(y,kk);
            }
            else
            if(z->n > mini)
            {
                int kk = z->k[1];
                x->k[i]= kk;
                bdrzewa_usuwanie(z,kk);
            }
            else
            {
                y->k[t]= e;
                for(j=1; j<t; j++)
                {
                    y->k[t+j]= z->k[j];
                    y->d[t+j]= z->d[j];
                }
                y->d[2*t]=z->d[t];
                y->n= maxi;
                for(j=i; j<x->n; j++)
                {
                    x->k[i]= x->k[i+1];
                    x->d[i+1]= x->d[i+2];
                }
                x->n-=1;
                bdrzewa_usuwanie(y,e);
            }
        }
    }
    else if(x->leaf)//jesli x lisciem i nie ma klucza
        {
            printf("\nNie ma klucza %d w B-drzewie\n",e);
            return;
        }
        else//jesli k nie jest w x i x jest wezlem wewnetrznym
        {
            if(x->d[i]->n > mini) bdrzewa_usuwanie((x->d[i]), e);
            else if((i>1) && (x->d[i-1]->n > mini))
                {
                    Wezel *b= x->d[i-1];
                    Wezel *ten= x->d[i];
                    for(j=mini;j>0;j--)
                    {
                        ten->k[j+1]=ten->k[j];
                        ten->d[j+2]= ten->d[j+1];
                    }
                    ten->d[2]= ten->d[1];
                    ten->n+=1;
                    ten->d[1]= b->d[b->n+1];
                    ten->k[1]= x->k[i-1];
                    x->k[i-1]= b->k[b->n];
                    b->n-=1;
                    bdrzewa_usuwanie(ten,e);
                }
                else if((i < x->n+1) && (x->d[i+1]->n >mini))
                    {
                        Wezel *b= x->d[i+1];
                        Wezel *ten= x->d[i];
                        ten->n+=1;
                        ten->k[t]= x->k[i];
                        ten->d[t+1]= b->d[1];
                        x->k[i]= b->k[1];
                        for(j=1; j<b->n; j++)
                        {
                            b->k[j]= b->k[j+1];
                            b->d[j]= b->d[j+1];
                        }
                        b->d[b->n]= b->d[b->n+1];
                        b->n-=1;
                        bdrzewa_usuwanie(ten,e);
                    }
                    else
                    {
                        Wezel *y= NULL;
                        Wezel *z= NULL;
                        if(i < x->n+1 && x->d[i+1]->n==mini)
                        {
                            y= x->d[i];
                            z= x->d[i+1];
                            y->k[t]= x->k[i];
                            for(j=i; j<x->n; j++)
                            {
                                x->k[j]= x->k[j+1];
                                x->d[j+1]= x->d[j+2];
                            }
                        }
                        else
                        {
                            y= x->d[i-1];
                            z= x->d[i];
                            y->k[t]= x->k[i-1];
                            for(j=i-1; j<x->n; j++)
                            {
                                x->k[j]= x->k[j+1];
                                x->d[j+1]= x->d[j+2];
                            }
                        }
                        x->n-=1;
                        for(j=1; j<t; j++)
                        {
                            y->k[t+j]= z->k[j];
                            y->d[t+j]= z->d[j];
                        }
                        y->d[t+j]= z->d[j];
                        y->n= maxi;
                        free(z);
                        usuwanie(y,e);
                    }
        }
        return;
}
int main() {

    drzewo = fopen("bdrzewo.txt", "w+");
    TR = BTreeCreate();

    int i;
    int k;
    printf("1 - DODAJ\n");
    printf("2 - DRUKUJ\n");
    printf("3 - SZUKAJ\n");
    printf("4 - USUN\n");
    printf("5 - WYJDZ\n");
    while(i!=5){
              scanf("%d",&i);
        if(i==1){
            printf("Podaj klucz: ");
            scanf("%d",&k);
            BTreeInsert(TR, k);
        }
        else if(i==2){
            printf("--------------------------\n");
            drukuj();
            printf("--------------------------\n");
        }
         else if(i==3){
           printf("Szukany klucz: ");
           scanf("%d",&k);
           Wezel r;
            odczytaj(ROOT, &r);
            BTreeSearch(r, k);
        }
         else if(i==4){
            printf("Usuwany klucz: ");
            scanf("%d",&k);
            odczytaj(ROOT, &TR);
            bdrzewa_usuwanie(&TR,k);
            odczytaj(ROOT, &TR);
        }
         else if(i==5){

        }
        else printf("\nWybrales zla opcje");
    }
    fclose(drzewo);
    printf("POZYCJAWPLIKU = %d ROOT = %d\n", POZYCJAWPLIKU, ROOT);
    return EXIT_SUCCESS;
}
