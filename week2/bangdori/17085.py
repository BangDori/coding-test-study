import sys
input = sys.stdin.readline

dr = [1, -1, 0, 0]
dc = [0, 0, -1, 1]

def get_crosses(row, col, board):
    crosses = []

    for r in range(row):
        for c in range(col):
            if board[r][c] != CROSS: continue

            size = 0

            while True:
                count = 0

                for i in range(4):
                    nr = r + dr[i] * size
                    nc = c + dc[i] * size

                    if 0 <= nr < row and 0 <= nc < col and board[nr][nc] == CROSS:
                        count += 1
                
                if count == 4:
                    crosses.append([size, r, c])
                    size += 1
                else:
                    break
    
    return crosses

def get_cross_info(row, col, cross):
    size, r, c = cross

    visited = [[False] * col for _ in range(row)]
    visited[r][c] = True
    for s in range(1, size+1):
        for i in range(4):
            nr = r + dr[i] * s
            nc = c + dc[i] * s

            visited[nr][nc] = True
    
    return visited

def is_overlap_cross(cross, visited):
    size, r, c = cross

    for s in range(1, size+1):
        for i in range(4):
            nr = r + dr[i] * s
            nc = c + dc[i] * s

            if visited[nr][nc]:
                return True
    
    return False

def calculate_max_multiply_on_crosses(row, col, crosses):
    max_multiply_value = 1

    for i in range(len(crosses)):
        if crosses[i][0] == 0: 
            break

        visited = get_cross_info(row, col, crosses[i])

        for j in range(i+1, len(crosses)):
            if is_overlap_cross(crosses[j], visited):
                continue
            
            val_a = crosses[i][0] * 4 + 1
            val_b = crosses[j][0] * 4 + 1
            max_multiply_value = max(max_multiply_value, val_a * val_b)

    return max_multiply_value

CROSS = '#'

row, col = map(int, input().split())
board = [list(input().strip()) for _ in range(row)]

crosses = get_crosses(row, col, board)
crosses.sort(reverse=True)

answer = calculate_max_multiply_on_crosses(row, col, crosses)
print(answer)