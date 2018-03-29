#include <stdio.h>
#include <stdlib.h>

//struktura krawedzi grafu
struct Krawedz
{
    int src, dest;
};

// struktura grafu
struct Graf
{
    // V ilosc wierzcholkow, E ilosc krawedzi
    int V, E;

    // graf jest reprezentowany krawedziami
    struct Krawedz* krawedz;
};

struct podzbior
{
    int rodzic;
    int ranga;
};

// tworzy graf
struct Graf* stworzGraf(int V, int E)
{
    struct Graf* graf = (struct Graf*) malloc( sizeof(struct Graf) );
    graf->V = V;
    graf->E = E;

    graf->krawedz = (struct Krawedz*) malloc( graf->E * sizeof( struct Krawedz ) );

    return graf;
}

// findset elementu i
int find(struct podzbior podzbiory[], int i)
{
    // znajduje korzen i robi korzen rodzicem i   kompresja sciezki
    if (podzbiory[i].rodzic != i)
        podzbiory[i].rodzic = find(podzbiory, podzbiory[i].rodzic);

    return podzbiory[i].rodzic;
}

struct podzbior* makeSet(int V){
            struct podzbior *podzbiory =
        (struct podzbior*) malloc( V * sizeof(struct podzbior) );
        int v;
    for (v = 0; v < V; ++v)
    {
        podzbiory[v].rodzic = v;
        podzbiory[v].ranga = 0;
    }
    return podzbiory;
}

void Union(struct podzbior podzbiory[], int x, int y)
{
    int xroot = find(podzbiory, x);
    int yroot = find(podzbiory, y);

    // Dolacza mniejsze ranga drzewo do korzenia wiekszego
    if (podzbiory[xroot].ranga < podzbiory[yroot].ranga)
        podzbiory[xroot].rodzic = yroot;
    else if (podzbiory[xroot].ranga > podzbiory[yroot].ranga)
        podzbiory[yroot].rodzic = xroot;

    // Jesli rangi sa takie same
    else
    {
        podzbiory[yroot].rodzic = xroot;
        podzbiory[xroot].ranga++;
    }
}

// funkcja sprawdzajaca
int jestCykl( struct Graf* graf )
{
    int V = graf->V;
    int E = graf->E;

    struct podzbior *podzbiory = makeSet(V);

    // Przejdz przez wszystkie krawedzie grafu, znajdz sety obu
    // wierzcholkow kazdej krawedzi, jesli sety sa takie same, wtedy jest cykl
    int e;
    for(e = 0; e < E; ++e)
    {
        int x = find(podzbiory, graf->krawedz[e].src);
        int y = find(podzbiory, graf->krawedz[e].dest);

        if (x == y) return 1;

        Union(podzbiory, x, y);
    }
    return 0;
}

int main()
{
    int V = 2, E = 1;
    struct Graf* graf = stworzGraf(V, E);

    graf->krawedz[0].src = 0;
    graf->krawedz[0].dest = 1;

    if (jestCykl(graf)) printf( "Graf zawiera cykl" );
    else printf( "Graf nie zawiera cyklu" );

    return 0;
}
