#include<stdio.h>
#include<stdlib.h>

#define T 2 //stopień drzewa
//x - węzeł B-drzewa

static int mini=T-1;
static int maxi=(2*T)-1;
static int t=T;

struct bdrzewo
{
    int n; //ilosc kluczy
    int klucz[2*T];
    int lisc;
    struct bdrzewo *c[2*T+1]; //tab wskaz do synow
};

struct szukaj
{
    struct bdrzewo *p;
    int i;
};

struct bdrzewo *przydziel_pamiec(void)
{
    return (struct bdrzewo *)malloc(sizeof(struct bdrzewo));
}

struct bdrzewo *korzen;
void bdrzewa_usuwanie(struct bdrzewo **x,int k);
void usuwanie(struct bdrzewo *x,int k);

void wyswietl_bdrzewo(struct bdrzewo *x)
{
    int i;
    for(i=1; i<x->n+1; i++)
        printf(" %d ", x->klucz[i]);
    printf("\n");
    if(x->lisc != 1)
        for(i=1; i<x->n+2; i++)
            wyswietl_bdrzewo(x->c[i]);
}

void bdrzewa_usuwanie(struct bdrzewo **pocz,int k)
{
    struct bdrzewo *x= *pocz;
    int i;
    if(!x->lisc && x->n==1 && x->c[1]->n==mini && x->c[2]->n==mini)
    {
        struct bdrzewo *y= x->c[1];
        struct bdrzewo *z= x->c[2];
        y->klucz[t]= x->klucz[1];
        for(i=1; i<t; i++)
        {
            y->klucz[t+i]= z->klucz[i];
            y->c[t+i]= z->c[i];
        }
        y->c[t+i]= z->c[i];
        y->n= maxi;
        *pocz= y;
        free(x);
        usuwanie(y,k);
    }
    else usuwanie(x,k);
    return;
}

void usuwanie(struct bdrzewo *x,int k)
{
    int i=1;
    int j;
    while(i<(x->n+1) && k>x->klucz[i]) i= i+1;
    if(i<(x->n+1) && k==x->klucz[i]) //jesli znaleziony zostal klucz w wezle x
    {
        if(x->lisc)//jesli x lisciem
        {
            for(j=i; j<x->n; j++)
            x->klucz[j]= x->klucz[j+1];
            x->n-=1;
            printf("\nKlucz %d usunieto\n", k);
        }
        else//jesli x wezlem wewnetrznym
        {
            struct bdrzewo *y= x->c[i];
            struct bdrzewo *z= x->c[i+1];
            if(y->n > mini)
            {
                int kk= y->klucz[y->n];
                x->klucz[i]= kk;
                bdrzewa_usuwanie(&y,kk);
            }
            else
            if(z->n > mini)
            {
                int kk = z->klucz[1];
                x->klucz[i]= kk;
                bdrzewa_usuwanie(&z,kk);
            }
            else
            {
                y->klucz[t]= k;
                for(j=1; j<t; j++)
                {
                    y->klucz[t+j]= z->klucz[j];
                    y->c[t+j]= z->c[j];
                }
                y->c[2*t]=z->c[t];
                y->n= maxi;
                for(j=i; j<x->n; j++)
                {
                    x->klucz[i]= x->klucz[i+1];
                    x->c[i+1]= x->c[i+2];
                }
                x->n-=1;
                bdrzewa_usuwanie(&y,k);
            }
        }
    }
    else if(x->lisc)//jesli x lisciem i nie ma klucza
        {
            printf("\nNie ma klucza %d w B-drzewie\n",k);
            return;
        }
        else//jesli k nie jest w x i x jest wezlem wewnetrznym
        {
            if(x->c[i]->n > mini) bdrzewa_usuwanie(&(x->c[i]), k);
            else if((i>1) && (x->c[i-1]->n > mini))
                {
                    struct bdrzewo *b= x->c[i-1];
                    struct bdrzewo *ten= x->c[i];
                    for(j=mini;j>0;j--)
                    {
                        ten->klucz[j+1]=ten->klucz[j];
                        ten->c[j+2]= ten->c[j+1];
                    }
                    ten->c[2]= ten->c[1];
                    ten->n+=1;
                    ten->c[1]= b->c[b->n+1];
                    ten->klucz[1]= x->klucz[i-1];
                    x->klucz[i-1]= b->klucz[b->n];
                    b->n-=1;
                    bdrzewa_usuwanie(&ten,k);
                }
                else if((i < x->n+1) && (x->c[i+1]->n >mini))
                    {
                        struct bdrzewo *b= x->c[i+1];
                        struct bdrzewo *ten= x->c[i];
                        ten->n+=1;
                        ten->klucz[t]= x->klucz[i];
                        ten->c[t+1]= b->c[1];
                        x->klucz[i]= b->klucz[1];
                        for(j=1; j<b->n; j++)
                        {
                            b->klucz[j]= b->klucz[j+1];
                            b->c[j]= b->c[j+1];
                        }
                        b->c[b->n]= b->c[b->n+1];
                        b->n-=1;
                        bdrzewa_usuwanie(&ten,k);
                    }
                    else
                    {
                        struct bdrzewo *y= NULL;
                        struct bdrzewo *z= NULL;
                        if(i < x->n+1 && x->c[i+1]->n==mini)
                        {
                            y= x->c[i];
                            z= x->c[i+1];
                            y->klucz[t]= x->klucz[i];
                            for(j=i; j<x->n; j++)
                            {
                                x->klucz[j]= x->klucz[j+1];
                                x->c[j+1]= x->c[j+2];
                            }
                        }
                        else
                        {
                            y= x->c[i-1];
                            z= x->c[i];
                            y->klucz[t]= x->klucz[i-1];
                            for(j=i-1; j<x->n; j++)
                            {
                                x->klucz[j]= x->klucz[j+1];
                                x->c[j+1]= x->c[j+2];
                            }
                        }
                        x->n-=1;
                        for(j=1; j<t; j++)
                        {
                            y->klucz[t+j]= z->klucz[j];
                            y->c[t+j]= z->c[j];
                        }
                        y->c[t+j]= z->c[j];
                        y->n= maxi;
                        free(z);
                        usuwanie(y,k);
                    }
        }
        return;
}

void wstawianie_podzial_synow(struct bdrzewo *x,int i,struct bdrzewo *y)
{
    struct bdrzewo *z=przydziel_pamiec();
    int j;
    z->lisc= y->lisc;
    z->n= mini;
    for(j=1; j<t; j++)
        z->klucz[j]= y->klucz[t+j];
    if(!y->lisc)
    {
        for(j=1; j<t+1; j++)
            z->c[j]= y->c[t+j];
    }
    y->n=mini;
    for(j=(x->n+1); j>i; j--)
        x->c[j+1]= x->c[j];
    x->c[i+1]= z;
    for(j=x->n; j>i-1; j--)
        x->klucz[j+1]= x->klucz[j];
    x->klucz[i]= y->klucz[t];
    x->n+=1;
    return;
}

void wstawianie_niep(struct bdrzewo *x, int k)
{
    int i = x->n,g;
    if(x->lisc)
    {
        while(i>0 && k<x->klucz[i])
        {
            x->klucz[i+1]= x->klucz[i];
            i-=1;
        }
        x->klucz[i+1]= k;
        x->n+=1;
        printf("\nklucz %d zostal wstawiony.\n",k);
        g=getchar();
        g=getchar();
    }
    else
    {
        while(i>0 && k<x->klucz[i])
        i-=1;
        i+=1;
        if(x->c[i]->n==maxi)
        {
            wstawianie_podzial_synow(x,i,x->c[i]);
            if(k>x->klucz[i]) i=i+1;
        }
        wstawianie_niep(x->c[i],k);
    }
    return;
}

void wstawianie(struct bdrzewo **pocz, int k)
{
    struct bdrzewo *r=*pocz;
    if(r->n==maxi)
    {
        struct bdrzewo *s=przydziel_pamiec();
        *pocz= s;
        s->lisc= 0;
        s->n= 0;
        s->c[1]= r;
        wstawianie_podzial_synow(s,1,r);
        wstawianie_niep(s,k);
    }
    else
    wstawianie_niep(r,k);
    return;
}

void tworzenie_bdrzewa(struct bdrzewo **pocz)
{
    struct bdrzewo *x= przydziel_pamiec();
    x->lisc= 1;
    x->n= 0;
    *pocz= x;
    return;
}

struct szukaj wyszukaj_element(struct bdrzewo *x, int k)
{
    int i = 1;
    while(i<(x->n+1) && k>x->klucz[i])
        i= i+1;
    if(i<(x->n+1) && k==x->klucz[i])
    {
        struct szukaj s;
        s.p= x;
        s.i= i;
        return s;
    }
    if(x->lisc)
    {
        struct szukaj s= {NULL,0};
        return s;
    }
    else
    return wyszukaj_element(x->c[i],k);
}

int menu()
{
    int c;
    int g;
    int wejscie;
    system("clear");
    printf("\tB-DRZEWA\n\nPodaj dzialanie:\n\n");
    printf("1--wstaw element\n");
    printf("2--znajdz klucz\n");
    printf("3--wypisz klucze\n");
    printf("4--usun element\n");
    printf("q--Koniec\n\n");
    c=getchar();
    switch (c)
    {
        case '1':
        {
            printf("\npodaj element : ");
            scanf("%d",&wejscie);
            wstawianie(&korzen,wejscie);
            menu();
            break;
        }
        case '2':
        {
            struct szukaj s;
            printf("\npodaj element do wyszukania : ");
            scanf("%d",&wejscie);
            s=wyszukaj_element(korzen,wejscie);
            if (s.p)
            {
                int i=1;
                printf("\n\nwyswietlanie:\n\n");
                for(;i<(s.p->n+1);i++)
                    printf("%d ",s.p->klucz[i]);
                    printf("\n");
            }
            else
                printf("\nnie odnaleziono klucza %d.\n", wejscie);
            g=getchar();
            g=getchar();
            menu();
            break;
        }
        case '3':
        {
            printf("\n\nwyswietlanie:\n\n");
            wyswietl_bdrzewo(korzen);
            g=getchar();
            g=getchar();
            menu();
            break;
        }
        case '4':
        {
            printf("\npodaj element do usuniecia: ");
            scanf("%d",&wejscie);
            bdrzewa_usuwanie(&korzen,wejscie);
            menu();
            break;
        }
        case 'q': return 0;
        default :
        menu();
    }
    return 0;
}

int main()
{
    korzen=NULL;
    tworzenie_bdrzewa(&korzen);
    menu();
}
