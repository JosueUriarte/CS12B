//-----------------------------------------------------------------------------
//   Dictionary.c
//   Implementation file for Dictionary ADT

//   Compile: gcc -std=c99 -o Dictionary Dictionary.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

const int tableSize=101; // or some prime other than 101

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;


// newNode()
// constructor of the Node type
Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = x;
   N->value = y;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// StackObj
typedef struct DictionaryObj{
   Node* array;
   int numItems;
} DictionaryObj;

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->array = calloc(tableSize, sizeof(Node));
   assert(D->array != NULL);
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Stack type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      if( !isEmpty(*pD) ) makeEmpty(*pD);
      free((*pD)->array);
      free(*pD);
      *pD = NULL;
   }
}


// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
   return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
   unsigned int result = 0xBAE86554;
   while (*input) {
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

int size(Dictionary D){
   return (D->numItems);
}

//findKey()
//Will find the node that k is located in
Node findKey(Dictionary D, char* k){

   Node N = D->array[hash(k)];
   while(N!=NULL && strcmp(N->key,k) != 0){
      N = N->next;
   }

   if(N == NULL) return NULL;

   return N;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N = findKey(D,k);

   if(N == NULL) return NULL;

   return N->value;
}

// Insert()
// Inserts a pair of characters
// pre: none
void insert(Dictionary D, char* x, char* y){
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary\n");
      exit(EXIT_FAILURE);
   }

   if( findKey(D,x) != NULL){
      fprintf(stderr, "Dictionary Error: calling insert() on pre-existing key\n");
      exit(EXIT_FAILURE);
   }

   int index = hash(x);

   if( D->array[index] == NULL){
      D->array[index] = newNode(x,y);
   }
   
   else{
      Node N = newNode(x,y);
      N->next = D->array[index];
      D->array[index] = N;
   }
   D->numItems++;
}

// delete()
// deletes and returns item at top of S
// pre: !isEmpty(S)
void delete(Dictionary D, char* k){
   int index = hash(k);
   Node N = D->array[index];
   Node V = D->array[index];

   if( D==NULL ){
      fprintf(stderr, "Stack Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems==0 ){
      fprintf(stderr, "Stack Error: calling delete() on empty Dictionary\n");
      exit(EXIT_FAILURE);
   }
 
   if(N != NULL && strcmp(N->key,k) == 0 ){
      D->array[index] = N->next;
      freeNode(&N);
      D->numItems--;
      return;
   }


   while(N != NULL && strcmp(N->key,k) != 0 ){
      V = N;
      N = N->next;
   }

   if (N == NULL){
      fprintf(stderr, "error: key not found\n");
      exit(EXIT_FAILURE);
   }

   V->next = N->next;
   freeNode(&N);
   D->numItems--;
   
}


// makeEmpty()
// resets D to the empty state
// pre: !isEmpty(S)
void makeEmpty(Dictionary D){
   int index = 0;

   if( D==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling makeEmpty() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }

   while(index < tableSize){
      Node N = D->array[index];
      Node V = D->array[index];

      while(V != NULL){
         N = V->next;
         freeNode(&V);
         V = N;
      }

      D->numItems = 0;
      D->array[index] = NULL;
      index++;
   }

}

// printDictionary()
// prints a text representation of D to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){

   if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling printStack() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }

   int index = 0;

   while(index < tableSize){
      Node N = D->array[index];
      for(; N!=NULL; N=N->next){
         fprintf(out, "%s %s", N->key, N->value);
         if(N != NULL){
            fprintf(out, "\n");
         }
      }
      freeNode(&N);
      index++;
   }

}
