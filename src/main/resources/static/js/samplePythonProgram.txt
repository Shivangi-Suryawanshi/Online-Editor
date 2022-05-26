import sys
n = len(sys.argv)
for i in range(1, n):
    m = sys.argv[1]
num=int(m)
temp=num
rev=0
while num > 0:
    dig=num%10
    rev=rev*10+dig
    num=num//10
if temp==rev :
 print("The number is palindrome!")
else:
    print("The number is not palindrome!")     
       