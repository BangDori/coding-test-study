import sys
input = sys.stdin.readline

def get_info_board(row, col, board):
    jongsu = (0, 0)
    arduinos = []

    for r in range(row):
        for c in range(col):
            if board[r][c] == EMPTY: continue

            if board[r][c] == ARDUINO: arduinos.append((r, c))
            elif board[r][c] == JONGSU: jongsu = (r, c) 
    
    return jongsu, arduinos

def move_jongsu(jongsu, dir, move_count):
    row, col = jongsu
    is_explode = False

    board[row][col] = EMPTY
    nr, nc = row + dr[dir], col + dc[dir]

    if board[nr][nc] == ARDUINO: is_explode = True
    board[nr][nc] = JONGSU

    jongsu = (nr, nc)

    return jongsu, move_count + 1, is_explode

def calculate_distance_to_jongsu(jongsu, nr, nc):
    jr, jc = jongsu

    return abs(jr-nr) + abs(jc-nc)

def move_arduinos(jongsu, arduinos):
    new_arduinos = []

    for arduino in arduinos:
        ar, ac = arduino
        # 이동 방향, 거리
        move = (-1, -1, 1e9)
        
        board[ar][ac] = EMPTY
        for dir in range(1, 10):
            nr = ar + dr[dir]
            nc = ac + dc[dir]

            if 0 <= nr < row and 0 <= nc < col:
                distance = calculate_distance_to_jongsu(jongsu, nr, nc)
                if distance < move[2]: move = (nr, nc, distance)

        new_arduinos.append((move[0], move[1]))

    booms = []
    for new_arduino in new_arduinos:
        nar, nac = new_arduino

        if board[nar][nac] == JONGSU: return True
        if board[nar][nac] == ARDUINO: booms.append((nar, nac))

        board[nar][nac] = ARDUINO
    
    for r, c in booms:
        board[r][c] = EMPTY
    
    return False

ARDUINO = 'R'; JONGSU = 'I'
EMPTY = '.'

dr = [0, 1, 1, 1, 0, 0, 0, -1, -1, -1]
dc = [0, -1, 0, 1, -1, 0, 1, -1, 0, 1]

row, col = map(int, input().split())
board = [list(input().strip()) for _ in range(row)]
dirs = list(map(int, list(input().strip())))

# 종수와 아두이노의 위치를 계산한다.
jongsu, arduinos = get_info_board(row, col, board)

# 이동 횟수 및 폭발 여부 선언
move_count, is_explode = 0, False 

# 과정을 반복한다.
for dir in dirs:
    # 1. 종수가 이동한다.
    # 2. 종수의 위치가 미친 아두이노가 있는 위치인지 확인한다. (YES = 종료)
    jongsu, move_count, is_explode = move_jongsu(jongsu, dir, move_count)
    if is_explode: break

    # 3. 미친 아두이노는 종수와 거리가 가장 가까운 방향으로 이동한다.
    # 4. 미친 아두이노의 위치가 종수의 위치와 동일하다면 게임이 종료된다.
    # 5. 미친 아두이노의 위치가 동일한 경우 동일한 칸에 있는 아두이노는 모두 폭발한다.
    is_explode = move_arduinos(jongsu, arduinos)
    if is_explode: break

    jongsu, arduinos = get_info_board(row, col, board)

if is_explode: print("kraj", move_count)
else:
    for r in range(row):
        for c in range(col):
            print(board[r][c],end='')
        print()