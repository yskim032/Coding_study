t=28
arr = [0] * 28
result=[]

for _ in range(t):
    arr.append(int(input()))

for i in range(1,31):
    if i not in arr:
        result.append(i)

print(result[0])
print(result[1])
