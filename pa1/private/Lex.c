//-----------------------------------------------------------------------------
// Lex.c
// Sorts an input file into alphabetical order
//-----------------------------------------------------------------------------

#include <stdbool.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "List.h"

#define MAX_LEN 255

int main(int argc, char * argv[]){

   int n, count=0;
   FILE *in, *out;

   char line[MAX_LEN];

   // check command line for correct number of arguments
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(1);
   }

   // open files for reading and writing 
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   if( in==NULL ){
      printf("Unable to open file %s for reading\n", argv[1]);
      exit(1);
   }
   if( out==NULL ){
      printf("Unable to open file %s for writing\n", argv[2]);
      exit(1);
   }

   /* read each line of input file and count it*/
   while( fgets(line, MAX_LEN, in) != NULL)  {
      count++;
   }

   char Lines[count][MAX_LEN];
   rewind(in);

   while( fgets(line, MAX_LEN, in) != NULL)  {
      strcpy(Lines[n], line);
      n++;
   }

   List L = newList();
   append(L,0);

   for(n = 1; n < count; n++){
      moveFront(L);

      while(index(L) >= 0){
         char *array = Lines[n];

         if(strncmp(Lines[get(L)], array, MAX_LEN) > 0){
            insertBefore(L, n);
            break;
         }

         moveNext(L);
      }

      if(index(L) < 0){
         append(L,n);
      }
   }

   moveFront(L);

   //Print Array
   n = 0;
   while(n < count){
      fprintf(out, "%s", Lines[get(L)]);
      moveNext(L);
      n++;
   }

   /* close files */
   fclose(in);
   fclose(out);
   freeList(&L);

   return(0);
}