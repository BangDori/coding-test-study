# 16946
# 벽을 부쉈을 때, 이동 가능한 칸의 개수를 구하는 문제

from collections import deque
import sys
input = sys.stdin.readline

# 주어진 맵에서 벽의 위치들을 반환하는 함수 O(r * c)
def get_walls():
    walls = []

    for r in range(row):
        for c in range(col):
            if map[r][c] == WALL:
                walls.append((r, c))
    
    return walls

# 모든 벽을 순회하며, 변환된 벽의 정보를 반환해주는 함수
def traverse_walls(walls):
    transformed_walls = []

    for wall in walls:
        wr, wc = wall
        count = calculate_move_count(wr, wc)

        transformed_walls.append((wr, wc, count))
    
    return transformed_walls

# 벽이 무너졌을 때 이동가능한 칸의 개수를 구하는 함수
def calculate_move_count(wr, wc):
    queue = deque([(wr, wc)])
    visited = [[False] * col for _ in range(row)]

    visited[wr][wc] = True
    count = 1

    while queue:
        r, c = queue.popleft()

        for dir in range(4):
            nr = r + dr[dir]
            nc = c + dc[dir]

            if 0 <= nr < row and 0 <= nc < col and not visited[nr][nc] and map[nr][nc] != WALL:
                visited[nr][nc] = True
                queue.append((nr, nc))
                count += 1

    return count

# 기존의 맵을 변환
def transform_map(transformed_walls):
    for r, c, count in transformed_walls:
        map[r][c] = count % 10

WALL = 1
dr = [1, -1, 0, 0]
dc = [0, 0, -1, 1]

row, col = map(int, input().split())
map = [list(map(int, list(input().strip()))) for _ in range(row)]

walls = get_walls()
transformed_walls = traverse_walls(walls)
transform_map(transformed_walls)

for r in range(row):
    for c in range(col):
        print(map[r][c], end='')
    print()