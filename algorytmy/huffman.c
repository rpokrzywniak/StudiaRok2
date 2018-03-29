#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_TREE_HT 100

int huffmanB[100][100]={0};
char huffmanZ[100]={0};
int l=0;

struct MinHeapNode
{
	char data;
	unsigned freq;
	struct MinHeapNode *left, *right;
};

struct MinHeap
{
	unsigned size;
	unsigned capacity;
	struct MinHeapNode **array;
};

struct MinHeapNode* newNode(char data, unsigned freq)
{
	struct MinHeapNode* temp =
		(struct MinHeapNode*) malloc(sizeof(struct MinHeapNode));
	temp->left = temp->right = NULL;
	temp->data = data;
	temp->freq = freq;
	return temp;
}

struct MinHeap* createMinHeap(unsigned capacity)
{
	struct MinHeap* minHeap =
		(struct MinHeap*) malloc(sizeof(struct MinHeap));
	minHeap->size = 0;
	minHeap->capacity = capacity;
	minHeap->array =
	(struct MinHeapNode**)malloc(minHeap->capacity * sizeof(struct MinHeapNode*));
	return minHeap;
}

void swapMinHeapNode(struct MinHeapNode** a, struct MinHeapNode** b)
{
	struct MinHeapNode* t = *a;
	*a = *b;
	*b = t;
}

void minHeapify(struct MinHeap* minHeap, int idx)
{
	int smallest = idx;
	int left = 2 * idx + 1;
	int right = 2 * idx + 2;

	if (left < minHeap->size &&
		minHeap->array[left]->freq < minHeap->array[smallest]->freq)
	smallest = left;

	if (right < minHeap->size &&
		minHeap->array[right]->freq < minHeap->array[smallest]->freq)
	smallest = right;

	if (smallest != idx)
	{
		swapMinHeapNode(&minHeap->array[smallest], &minHeap->array[idx]);
		minHeapify(minHeap, smallest);
	}
}

int isSizeOne(struct MinHeap* minHeap)
{
	return (minHeap->size == 1);
}

struct MinHeapNode* extractMin(struct MinHeap* minHeap)
{
	struct MinHeapNode* temp = minHeap->array[0];
	minHeap->array[0] = minHeap->array[minHeap->size - 1];
	--minHeap->size;
	minHeapify(minHeap, 0);
	return temp;
}

void insertMinHeap(struct MinHeap* minHeap, struct MinHeapNode* minHeapNode)
{
	++minHeap->size;
	int i = minHeap->size - 1;
	while (i && minHeapNode->freq < minHeap->array[(i - 1)/2]->freq)
	{
		minHeap->array[i] = minHeap->array[(i - 1)/2];
		i = (i - 1)/2;
	}
	minHeap->array[i] = minHeapNode;
}

void buildMinHeap(struct MinHeap* minHeap)
{
	int n = minHeap->size - 1;
	int i;
	for (i = (n - 1) / 2; i >= 0; --i)
		minHeapify(minHeap, i);
}

void printArr(int arr[], int n)
{
	int i;
	for (i = 0; i < n; ++i){
		printf("%d", arr[i]);
        huffmanB[l][i]=arr[i];
	}
	huffmanB[l][i]=2;
	printf("\n");
	l++;
}

int isLeaf(struct MinHeapNode* root)
{
	return !(root->left) && !(root->right) ;
}

struct MinHeap* createAndBuildMinHeap(char data[], int freq[], int size)
{
	struct MinHeap* minHeap = createMinHeap(size);
	int i;
	for (i = 0; i < size; ++i)
		minHeap->array[i] = newNode(data[i], freq[i]);
	minHeap->size = size;
	buildMinHeap(minHeap);
	return minHeap;
}

struct MinHeapNode* buildHuffmanTree(char data[], int freq[], int size)
{
	struct MinHeapNode *left, *right, *top;

	struct MinHeap* minHeap = createAndBuildMinHeap(data, freq, size);

	while (!isSizeOne(minHeap))
	{
		left = extractMin(minHeap);
		right = extractMin(minHeap);

		top = newNode('$', left->freq + right->freq);
		top->left = left;
		top->right = right;
		insertMinHeap(minHeap, top);
	}

	return extractMin(minHeap);
}

void printCodes(struct MinHeapNode* root, int arr[], int top)
{
	if (root->left)
	{
		arr[top] = 0;
		printCodes(root->left, arr, top + 1);
	}

	if (root->right)
	{
		arr[top] = 1;
		printCodes(root->right, arr, top + 1);
	}

	if (isLeaf(root))
	{
		printf("%c: ", root->data);
		huffmanZ[l]=root->data;
		printArr(arr, top);
	}
}


void HuffmanCodes(char data[], int freq[], int size)
{

struct MinHeapNode* root = buildHuffmanTree(data, freq, size);

int arr[MAX_TREE_HT], top = 0;
printCodes(root, arr, top);
}

int main()
{
    FILE *fp;
    fp = fopen("huffman.txt", "r");
    char arr[100]={0};
    int freq[100]={0};
    char znak;
    int exists=0;
    int licznik=0;
    znak = getc(fp);
    int i;
    while(znak!=EOF){
        for(i=0;i<licznik;i++){
            if(arr[i]==znak) {
                freq[i]++;
                exists=1;
            }
         }
         if(exists==0){
            arr[licznik]=znak;
            freq[licznik]++;
         }
         else{
            exists=0;
            licznik--;
         }
        znak = getc(fp);
        licznik++;
    }
    fclose(fp);
	HuffmanCodes(arr, freq, licznik);
	fp = fopen("huffman.txt", "r");
	FILE *fs;
	fs = fopen("huffman1.txt", "w");
    znak = getc(fp);
    int j=0;
    while(znak!=EOF){
        for(i=0;i<l;i++){
            if(znak==huffmanZ[i]){
                while(huffmanB[i][j]!=2){
                    fprintf(fs,"%d",huffmanB[i][j]);
                    j++;
                }
                j=0;
            }
        }
        znak=getc(fp);
    }
    fclose(fp);
    fclose(fs);
    fp = fopen("huffman1.txt", "r");
    znak=getc(fp);
    licznik=0;
    int checkH[100]={0};
    i=0;
    j=0;
    while(znak!=EOF){
        while(licznik!=1){
            for(i=0;i<l;i++){
                if(huffmanB[i][j]==(((int)znak)-48) && j==0 ) {licznik++; checkH[i]=1;}
                else if(checkH[i]==1 && huffmanB[i][j]!=(((int)znak)-48)) {checkH[i]=0; licznik--;}
            }
            znak=getc(fp);
            j++;
        }
        licznik=0;
        j=0;
        printf("\n");
        for(i=0;i<l;i++){
            if(checkH[i]==1) printf("%c",huffmanZ[i]);
        }
        memset(checkH,0,100);
    }
    fclose(fp);
	return 0;
}
