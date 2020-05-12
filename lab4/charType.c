#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alphabet; // string holding all alpha-numeric chars
   char* digits;
   char* punct;
   char* whiteSpace;
   char characters[] = "\0";
   int theLine = 0;

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alphabet = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   digits = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punct = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   whiteSpace = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL && alphabet!=NULL && digits!=NULL && punct!=NULL && whiteSpace!=NULL);

   // read each line in input file, extract alpha-numeric characters 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      theLine++;
      fprintf(out, "\nLine %d contains:\n", theLine);
      extract_chars(line, alphabet, digits, punct, whiteSpace);

      if(strlen(alphabet) == 0 || strlen(alphabet) > 1 ) characters[0] = 's';
      fprintf(out, "%lu alphabetic character%s: %s\n", strlen(alphabet), characters, alphabet);
      characters[0] = '\0';

      if(strlen(alphabet) == 0 || strlen(alphabet) > 1 ) characters[0] = 's';
      fprintf(out, "%lu numeric character%s: %s\n", strlen(digits), characters, digits);
      characters[0] = '\0';

      if(strlen(alphabet) == 0 || strlen(alphabet) > 1 ) characters[0] = 's';
      fprintf(out, "%lu punctuation character%s: %s\n", strlen(punct), characters, punct);
      characters[0] = '\0';

      if(strlen(whiteSpace) == 0 || strlen(whiteSpace) > 1 ) characters[0] = 's';
      fprintf(out, "%lu whitespace character%s: %s", strlen(whiteSpace), characters, whiteSpace);
      characters[0] = '\0';
      
   }
   fprintf(out, "\n");

   // free heap memory 
   free(line);
   free(alphabet);
   free(digits);
   free(punct);
   free(whiteSpace);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0;

   while(s[i]!='\0' && i < MAX_STRING_LENGTH){
      if( isalpha( (int) s[i]) ) a[j++] = s[i];
      i++;
   }
   a[j] = '\0';

   i=0, j=0;
   while(s[i]!='\0' && i < MAX_STRING_LENGTH){
      if( isdigit( (int) s[i]) ) d[j++] = s[i];
      i++;
   }
   d[j] = '\0';

   i=0, j=0;
   while(s[i]!='\0' && i < MAX_STRING_LENGTH){
      if( ispunct( (int) s[i]) ) p[j++] = s[i];
      i++;
   }
   p[j] = '\0';

   i=0, j=0;
   while(s[i]!='\0' && i < MAX_STRING_LENGTH){
      if( isspace( (int) s[i]) ) w[j++] = s[i];
      i++;
   }
   w[j] = '\0';

}
