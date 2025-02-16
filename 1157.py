from collections import Counter

s=input().upper()
s_sort=sorted(s)

counter=Counter(s_sort)


max_value=max(counter.values())

print(max_value)
print(list(counter.values()))
print(list(counter.values()).count(max_value))


# if list(counter.values()).count(max_value)>1:   
#     print("?")
# else:
#     print(max(counter,key=counter.get))










