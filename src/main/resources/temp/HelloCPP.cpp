#include <iostream>
#include<stdlib.h>
using namespace std;
  int main(int argc, char** argv) {
    // int n=atoi(argv[1]);
    // cout<<n; 
     
     int n = atoi(argv[1]);
     int num = atoi(argv[1]);
     int digit, rev = 0;
     n = num;

     do
     {
         digit = num % 10;
         rev = (rev * 10) + digit;
         num = num / 10;
     } while (num != 0);

     cout << " The reverse of the number is: " << rev << endl;

     if (n == rev)
         cout << " The number is a palindrome.";
     else
         cout << " The number is not a palindrome.";

    return 0;
      
      
  }      
      
      
      
      
      
      
      
      
      
      
      
  