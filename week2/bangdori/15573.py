# 공기와 맞닿아 있는 광물 하나를 골라 채굴할 수 있다.
# 바닥과 광물과만 맞닿아 있으면 채굴할 수 없다.

from heapq import heappush, heappop
import sys
input = sys.stdin.readline

dr = [1, -1, 0, 0]
dc = [0, 0, -1, 1]

row, col, k = map(int, input().split())
board = [[0] * (col+2) for _ in range(row+1)]

row += 1; col += 2

for i in range(1, row):
    line = [0] + list(map(int, input().split())) + [0]

    board[i] = line

heap = []
visited = [[False] * (col) for _ in range(row)]

# 광물의 광도를 기준으로 우선순위 큐 구현
for r in range(row):
    for c in range(col):
        if board[r][c] == 0: continue

        count = 0
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if 0 <= nr < row and 0 <= nc < col and board[nr][nc] == 0:
                count += 1

        if count >= 1:
            visited[r][c] = True
            heappush(heap, (board[r][c], r, c))

# 광물을 하나씩 캐가기
current_k = 0
answer = 0
while heap:
    current_k += 1
    power, r, c = heappop(heap)
    answer = max(answer, power) 

    if current_k >= k:
        break

    board[r][c] = 0
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]

        if 0 <= nr < row and 0 <= nc < col and board[nr][nc] != 0 and not visited[nr][nc]:
            visited[nr][nc] = True
            heappush(heap, (board[nr][nc], nr, nc))

print(answer)