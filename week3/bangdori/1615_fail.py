# 1615
# 간선의 개수는 최대 1,999,000

# 모든 경우의 수를 확인하는 방법은 불가능 O(N^2) X
# 이진 탐색으로 확인할 수 있지 않을까?

# 메모리 초과 어떻게 해결함??????????? ㅡㅡ

import sys
input = sys.stdin.readline

def binary_search(target):
    left = 0
    right = len(compare_line)-1
    mid = (left + right) // 2

    while left <= right:
        mid = (left + right) // 2

        if compare_line[mid][1] >= target: right = mid - 1
        else: left = mid + 1
    
    return left

N, M = map(int, input().split())
lines = [list(map(int, input().split())) for _ in range(M)]
lines.sort()

answer = 0

for i in range(len(lines)-1):
    compare_line = lines[i+1:]
    compare_line.sort(key=lambda v: v[1])
    answer += binary_search(lines[i][1])

print(answer)