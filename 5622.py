
s=input()

time=0
for i in range(len(s)):

    n=ord(s[i])-64
    if s[i]=='S' or s[i]=='V' or s[i]=='Y':
        n=n-1
    elif s[i]=='Z': 
        n=n-2
        
    m= -(-n//3)



    time+=m+2
    
print(time)


# for i in range(10):
#     s=input()
#     n=ord(s)-64
#     m= -(-n//3)
#     print(m)

