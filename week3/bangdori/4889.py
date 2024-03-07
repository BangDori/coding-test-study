# 4889

import sys
input = sys.stdin.readline

OPEN = '{'; CLOSE = '}'

current_case = 1
while True:
    datas = list(input().strip())
    if datas[0] == "-": break

    stack = []
    change_count = 0

    for data in datas:
        # 스택이 비어있을 경우
        if not stack:
            if data == CLOSE:
                change_count += 1
                stack.append(OPEN)
            else:
                stack.append(data)

        # 스택에 괄호가 존재하는 경우
        else:
            # 스택의 마지막이 열기이고, 데이터가 닫기라면
            if stack[-1] == OPEN and data == CLOSE:
                stack.pop()
            else:
                stack.append(data)
    
    # 스택의 마지막 데이터를 변환하면서 괄호 제거
    while stack:
        data1 = stack.pop()
        data2 = stack.pop()

        # 데이터가 동일하다면 변환 필요
        if data1 == data2:
            change_count += 1

    print(f'{current_case}. {change_count}')
    current_case += 1