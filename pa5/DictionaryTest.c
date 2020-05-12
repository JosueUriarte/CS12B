//-----------------------------------------------------------------------------
// DictionaryClient.c
// Test client for the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"
#include"Dictionary.c"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"one","two","three","four","five","six","seven"};
   char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};

   insert(A, word1[0], word2[0]);
   insert(A, word1[1], word2[1]);
   insert(A, word1[2], word2[2]);
   delete(A, "one");

   printDictionary(stdout, A);

   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));

   v = lookup(A, "aa");
   printf("key=\"%s\" %s\"%s\"\n", "aa", (v==NULL?"not found ":"value="), v);
   v = lookup(A, "two");
   printf("key=\"%s\" %s\"%s\"\n", "two", (v==NULL?"not found ":"value="), v);
   v = lookup(A, "three");
   printf("key=\"%s\" %s\"%s\"\n", "three", (v==NULL?"not found ":"value="), v);

   makeEmpty(A);

   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));

   freeDictionary(&A);

   return(EXIT_SUCCESS);
}
