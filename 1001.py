# 두 정수 A와 B를 입력받은 다음, A-B를 출력하는 프로그램을 작성하시오.

A,B=map(int,input().split())
while not (0<A<10 and 0<B<10):
    A,B=map(int,input().split())
print(A-B)
