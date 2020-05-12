//-----------------------------------------------------------------------------
// DictionaryTest.c
// Test client for the Dictionary ADT

// Compile: gcc -std=c99 -o DictionaryTest DictionaryTest.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"1","2","3","4","5","6","7"};
   char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};

   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));

   insert(A, word1[0], word2[0]);
   insert(A, word1[1], word2[1]);
   insert(A, word1[2], word2[2]);

   delete(A, "2");
   delete(A, "3");

   //delete(A, "one");  // error: key not found

   k = "6";
   v = lookup(A, "6");
   printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);

   //insert(A, "1", "bro"); // error: key collision

   insert(A, word1[3], word2[3]);
   insert(A, word1[4], word2[4]);

   printDictionary(stdout, A);
   printf("%d\n", size(A));

   makeEmpty(A);


   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));

   printDictionary(stdout, A);

   
   freeDictionary(&A);


   return(EXIT_SUCCESS);
}
