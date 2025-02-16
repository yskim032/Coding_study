n=int(input())
arr=list(map(int,input().split()))

max_score = max(arr)

new_arr=[]

for i in range(n):
    new_arr.append(arr[i]/max_score*100)

print(sum(new_arr)/n)

         








