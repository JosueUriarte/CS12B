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
   Node top;
   int numItems;
} DictionaryObj;

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->top = NULL;
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Stack type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      if( !isEmpty(*pD) ) makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
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

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N = D->top;
   while(N!=NULL && strcmp(N->key,k) != 0){
      N = N->next;
   }

   if(N == NULL) return NULL;

   return N->value;


}

// Insert()
// Inserts a pair of characters
// pre: none
void insert(Dictionary D, char* x, char* y){
   if( D==NULL ){
      fprintf(stderr, "Stack Error: calling Insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }

   if( D->top == NULL){
      D->top = newNode(x,y);
   }

   
   else{

      Node N = D->top;
      if(strcmp(N->key,x) == 0){
            fprintf(stderr, "error: key collision\n");
            exit(EXIT_FAILURE);  
      }

      while(N->next != NULL){

         if(strcmp(N->key,x) == 0){
            fprintf(stderr, "error: key collision\n");
            exit(EXIT_FAILURE);  
         }

         N = N->next;
         
      }

      N->next = newNode(x,y);
   }

   D->numItems++;
}

// delete()
// deletes and returns item at top of S
// pre: !isEmpty(S)
void delete(Dictionary D, char* k){
   Node N = D->top;
   Node V = D->top;
   if( D==NULL ){
      fprintf(stderr, "Stack Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems==0 ){
      fprintf(stderr, "Stack Error: calling delete() on empty Dictionary\n");
      exit(EXIT_FAILURE);
   }
 
   if(D->top != NULL && strcmp(N->key,k) == 0 ){
      D->top = N->next;
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
   Node N = D->top;
   Node V = D->top;
   if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling makeEmpty() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems==0 ){
      fprintf(stderr, "Stack Error: calling makeEmpty() on empty Stack\n");
      exit(EXIT_FAILURE);
   }
   while(V != NULL){
      N = V->next;
      freeNode(&V);
      V = N;
   }
   D->numItems = 0;
   D->top = NULL;
}

// printDictionary()
// prints a text representation of D to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling printStack() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->top; N!=NULL; N=N->next){
      fprintf(out, "%s %s", N->key, N->value);
      if(N != NULL){
         fprintf(out, "\n");
      }
   }
   freeNode(&N);
}


