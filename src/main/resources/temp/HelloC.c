#include <stdio.h>
#include <stdlib.h>
 int main(int argc, char *argv[]){       


    char string1[20];
    string1=atoi(argv[1]);
    int i, length;
    int flag = 0;
    
    printf("Enter a string:");
    scanf("%s", string1);
    
    length = strlen(string1);
    
    for(i=0;i < length ;i++){
        if(string1[i] != string1[length-i-1]){
             printf("%c ", string1[i]);
             printf("%c ", string1[length-i-1]);
            flag = 1;
            break;
           }
        }
    
    if (flag) {
        printf("%s is not a palindrome", string1);
    }    
    else {
        printf("%s is a palindrome", string1);
    }



 
 
return 1;  
}