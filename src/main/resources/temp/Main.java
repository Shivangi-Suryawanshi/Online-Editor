public class Main{
  public static void main(String args[]){
int n = Integer.parseInt(args[0]);
 int r,sum=0,temp;
temp=n;    
  while(n>0){    
   r=n%10;  //getting remainder  
   sum=(sum*10)+r;    
   n=n/10;    
  }    
  if(temp==sum)    
   System.out.println(sum);    
  else    
   System.out.println(sum); 
 
}
}