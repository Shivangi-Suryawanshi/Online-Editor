#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 int main(int argc, char *argv[]){       
//int n=atoi(argv[1]);
 char temp;
   char *str;
   int i, j;
   str = argv[1]; // 2nd argument will the string to be reversed
   i = 0; // Initialize i at start 
   j = strlen(str) - 1; // Initialize j with end of string length
 
   /* Swap characters from start and end */
   while (i < j) {
      temp = str[i];
      str[i] = str[j];
      str[j] = temp;
      i++;
      j--;
   }
 
   // Print reversed string 
   printf(str);
   
   
return 1;  
}