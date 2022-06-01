
import sys
# total arguments
n = len(sys.argv)
# print("\nName of Python script:", sys.argv[0])
# for i in range(1, n):
#     m = sys.argv[1]
#     print(m)
m = 0
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
 print(rev)
