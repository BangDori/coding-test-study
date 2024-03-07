# 16946
# 벽이 아니라, 영역에 집중

from collections import deque
import sys
input = sys.stdin.readline

# 영역 구하기
def get_sections():
    visited = [[False] * col for _ in range(row)]
    sections = []
    sizes = []
    
    for curr_r in range(row):
        for curr_c in range(col):
            if map[curr_r][curr_c] == WALL or visited[curr_r][curr_c]: continue

            queue = deque([(curr_r, curr_c)])
            visited[curr_r][curr_c] = True
            
            section = set()
            size = 1

            while queue:
                r, c = queue.popleft()

                for i in range(4):
                    nr = r + dr[i]
                    nc = c + dc[i]

                    if 0 <= nr < row and 0 <= nc < col and not visited[nr][nc]:
                        if map[nr][nc] == WALL:
                            section.add((nr, nc))
                        else:
                            visited[nr][nc] = True
                            size += 1
                            queue.append((nr, nc))
            
            sections.append(section)
            sizes.append(size)

    return sections, sizes

# 맵 변환하기
def transform_map(sections, sizes):
    for i, section in enumerate(sections):
        for r, c in section:
            map[r][c] += sizes[i]

WALL = 1
dr = [1, -1, 0, 0]
dc = [0, 0, -1, 1]

row, col = map(int, input().split())
map = [list(map(int, list(input().strip()))) for _ in range(row)]

sections, sizes = get_sections()
transform_map(sections, sizes)

for r in range(row):
    for c in range(col):
        print(map[r][c] % 10, end='')
    print()