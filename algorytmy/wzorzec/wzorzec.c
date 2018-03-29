#include<stdio.h>
#include<string.h>
#define d 256
void naiwny(char* P, char* T, int l1, int l2){
        printf("\n------naiwny-------\n");
    int s=0;
    int i=0;
    int l=0;
    for(s=0; s<l2-l1; s++){
        for(i=0;i<l1;i++){
            if(P[i]==T[s+i]) l++;// tu porównujemy m znaków (w pętli)
        }
        if(l==l1) printf("\nZnaleziono wzorzec w %d",s);
        l=0;
    }

}
void rabinkrap(char* P, char* T,int l1, int l2, int q)
{
        printf("\n------rabin-karp-------\n");
    int M = l1;
    int N = l2;
    int i, j;
    int p = 0; //wartosc hash dla wzoru
    int t = 0; // wartosc hash dla  tekstu
    int h = 1;

    for (i = 0; i < M-1; i++)
        h = (h*d)%q;

    for (i = 0; i < M; i++)
    {
        p = (d*p + P[i])%q;
        t = (d*t + T[i])%q;
    }

    for (i = 0; i <= N - M; i++)
    {
        if ( p == t )
        {
            for (j = 0; j < M; j++)
            {
                if (T[i+j] != P[j])
                    break;
            }

            if (j == M)
                printf("\nZnaleziono wzorzec w %d", i);
        }

        if ( i < N-M )
        {
            t = (d*(t - T[i]*h) + T[i+M])%q;

            if (t < 0)
            t = (t + q);
        }
    }
}
int *compute_prefix_function(char *pattern, int psize)
{
	int k = -1;
	int i = 1;
	int *pi = malloc(sizeof(int)*psize);

	pi[0] = k;
	for (i = 1; i < psize; i++) {
		while (k > -1 && pattern[k+1] != pattern[i])
			k = pi[k];
		if (pattern[i] == pattern[k+1])
			k++;
		pi[i] = k;
	}
	return pi;
}
int kmp(char *target, int tsize, char *pattern, int psize)
{
    printf("\n------kmp-------\n");
	int i;
	int *pi = compute_prefix_function(pattern, psize);
	int k = -1;
	for (i = 0; i < tsize; i++) {
		while (k > -1 && pattern[k+1] != target[i])
			k = pi[k];
		if (target[i] == pattern[k+1])
			k++;
		if (k == psize - 1) {
			printf("\nZnaleziono wzorzec w %d",i-k);
		}
	}
}

int getNextState(char *pat, int M, int state, int x)
{
  if (state < M && x == pat[state])
  return state+1;

  int ns, i;

  for (ns = state; ns > 0; ns--)
  {
  if(pat[ns-1] == x)
  {
  for(i = 0; i < ns-1; i++)
  {
  if (pat[i] != pat[state-ns+1+i])
  break;
  }
  if (i == ns-1)
  return ns;
  }
  }

  return 0;
}

void computeTF(char *pat, int M, int TF[][d])
{
  int state, x;
  for (state = 0; state <= M; ++state)
  for (x = 0; x < d; ++x)
  TF[state][x] = getNextState(pat, M, state, x);
}

void search(char *pat, char *txt, int l1, int l2)
{
            printf("\n------automat skonczony-------\n");
  int M = l1;
  int N = l2;

  int TF[M+1][d];

  computeTF(pat, M, TF);

  int i, state=0;
  for (i = 0; i < N; i++)
  {
  state = TF[state][txt[i]];
  if (state == M)
  {
  printf ("\n Znaleziono wzorzec w %d", i-M+1);
  }
  }
}

int main(){
    FILE *fp;
    char str1[999]={0};
    char cc;
    fp = fopen("wzorzec-tekst.txt", "r");
     fscanf(fp, "%s",str1);
      fscanf(fp, "%s",str1);
      int l1 = strlen(str1);
      fscanf(fp, "%s",str1);
      int l2=0;
    while(cc!=EOF){
            l2++;
             fscanf(fp, "%s",str1);
             l2=l2+strlen(str1);
            cc = getc(fp);
    }
    fclose(fp);
    fp = fopen("wzorzec-tekst.txt", "r");
    char P[l1];
    char T[l2];
    fscanf(fp, "%s",str1);
    fscanf(fp, "%s",P);
    fscanf(fp, "%s",str1);
    int i=0;
    cc=getc(fp);
    cc=getc(fp);
    while(cc!=EOF){
        cc=getc(fp);
        if(i<=l2) T[i]=cc;
        i++;
    }
    naiwny(P,T,l1,l2);
    rabinkrap(P,T,l1,l2,27077);
    kmp(T, l2, P, l1);
    search(P, T, l1,l2);
    return 0;
}
