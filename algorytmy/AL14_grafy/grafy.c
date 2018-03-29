#include<stdio.h>

int wezly;

struct wsk{
        char kolor;
        int pi;
}* wezel;

void dfs1(int i, struct wsk l[], int p[][wezly]){
        l[i].kolor = 's';
        int j = 0;
        for(j = 0; j < wezly; j++){
                if(p[i][j] == 1){
                        if(l[j].kolor == 'b'){
                                l[j].pi = i;
                        }
                }
        }
}

void dfs(struct wsk l[], int p[][wezly]){
        int i;
        for(i = 0; i < wezly; i++){
                l[i].kolor = 'b';
                l[i].pi = -1;
        }

        for(i = 0; i < wezly; i++){
                if(l[i].kolor == 'b'){
                        dfs1(i, l, p);
                }
        }
}

int main(){

        FILE* plik = fopen("graf.txt", "r");
        wezly = 0;
        fscanf(plik, "%i", &wezly);
        printf("\nwezly: %i\n", wezly);



        int sasiedztwa[wezly][wezly];

        printf("\nmacierz sasiedztwa:\n");
        int i,j;
        for(i = 0; i < wezly; i++){
                for(j = 0; j < wezly; j++){
                        fscanf(plik, "%i", &sasiedztwa[i][j]);
                        printf("%i ", sasiedztwa[i][j]);
                } printf("\n");
        }
        fclose(plik);

        printf("\n");

        struct wsk l[wezly];

        dfs(l, sasiedztwa);

        for(i = 0; i < wezly; i++){
                printf("%i", i+1);
                printf(" %i\n", l[i].pi+1);
        }

        return 0;
}

