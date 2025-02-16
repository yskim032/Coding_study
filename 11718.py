
while True:
    try:
        print(input())
    except EOFError:  # EOFError가 발생하면 (입력이 끝나면)
        break         # while 루프를 종료합니다 