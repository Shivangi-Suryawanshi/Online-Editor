public class Main{
  public static void main(String args[]){
    
   String str = args[0], reverseStr = "";
    
   int strLength = str.length();

    for (int i = (strLength - 1); i >=0; --i) {
      reverseStr = reverseStr + str.charAt(i);
    }

    if (str.equals(reverseStr)) {
      System.out.println(reverseStr);
    }
    else {
      System.out.println(reverseStr);
    }
  }
}