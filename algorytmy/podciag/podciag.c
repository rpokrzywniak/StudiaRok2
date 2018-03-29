#include <stdio.h>
#include <string.h>
#include <stdlib.h>
char lcs[20];
int licznik =0;
char backtrack(int** c,char* x,char* y,int i,int j){
    if(i==0 || j==0){
        return;
    }
    else if (x[i]==y[j]){
        lcs[licznik]=x[i];
        licznik++;
        return backtrack(c, x, y, i-1, j-1);
    }
    else{
        if(c[i][j-1] > c[i-1][j]) return backtrack(c,x,y,i,j-1);
        else return backtrack(c,x,y,i-1,j);
    }
}

void lcslength(char* x, char* y){
    int m = strlen(x) -1;
    int n = strlen(y) -1;
    int i;
    int j;
    int **c;
    c=(int**)malloc((m+1)*sizeof(int*));
    size_t pom = 0;
    for(pom = 0; pom < (m+1); ++pom) c[pom] = (int*)malloc((n+1) * sizeof(int));
    for(i = 0; i<m; i++){
        c[i][0] = 0;
    }
    for(i = 0; i<n; i++){
        c[0][i] = 0;
    }
    for(i = 1; i<m; i++){
        for(j = 1; j<n; j++){
            if(x[i]==y[j]){
                c[i][j] = c[i-1][j-1]+1;
            }
            else if (c[i-1][j] >= c[i][j-1]){
                c[i][j] = c[i-1][j];
            }
            else{
                c[i][j] = c[i][j-1];
            }
        }
    }
    char znak=backtrack(c,x,y,m,n);
}

int main(){
    char* x = " alamakota";
    char* y = " alamapsa";
    lcslength(x,y);
    int i;
    int k=strlen(lcs);
    for(i=0;i<k;i++){
        printf("%c",lcs[k-1-i]);
    }
    return 0;
}

