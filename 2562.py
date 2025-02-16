n=9
arr = []

for i in range(n):
    num = int(input())
    arr.append(num)

print(max(arr), arr.index(max(arr))+1)

