 #include <iostream>
#include<stdlib.h>
using namespace std;
  int main(int argc, char** argv) {    
      int array[10];
     int argv_offset = 0;
int num_numbers = argc - argv_offset;
for( int i = argv_offset; i < argc; i++ ) {
    array[i-argv_offset] = atoi(argv[i]);
  
} 
int i, j, a;
	int n[10];
//	n=array;
 for (i = 0; i < argc; ++i){
      for (j = i + 1; j < argc; ++j){
         if (array[i] > array[j]){
            a = array[i];
            array[i] = array[j];
            array[j] = a;
         }
      }
   }
    for (i = 0; i < argc; ++i){
    	printf("%d ", array[i]);
    }
 
        return 0;
      
      
  }      
 