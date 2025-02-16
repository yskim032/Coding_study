# 두 정수 A와 B를 입력받은 다음, A×B를 출력하는 프로그램을 작성하시오.
# 첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)

A,B=map(int, input().split())
# A와 B가 1~9 사이의 숫자가 아닐 경우 다시 입력받기
while not (0<A<10 and 0<B<10):  # A와 B가 1~9 사이의 숫자인지 확인
    A,B=map(int,input().split())  # 조건에 맞지 않으면 다시 입력받음

# A와 B의 곱을 출력
print(A*B)