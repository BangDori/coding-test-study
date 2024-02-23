import sys
input = sys.stdin.readline

JUNGLE = 'J'; OCEAN ='O'; ICE = 'I'

def calculateAccOfPos(row, col):
    j1, o1, i1 = acc[row-1][col]
    j2, o2, i2 = acc[row][col-1]
    j3, o3, i3 = acc[row-1][col-1]
    info = board[row-1][col-1]

    j_cnt = j1+j2-j3
    o_cnt = o1+o2-o3
    c_cnt = i1+i2-i3
    if info == JUNGLE: j_cnt += 1
    elif info == OCEAN: o_cnt += 1
    else: c_cnt += 1

    return (j_cnt, o_cnt, c_cnt)

def calculateSectionInfoOfPos(r1, c1, r2, c2):
    j1, o1, i1 = acc[r2][c2]
    j2, o2, i2 = acc[r1-1][c2]
    j3, o3, i3 = acc[r2][c1-1]
    j4, o4, i4 = acc[r1-1][c1-1]

    return j1-j2-j3+j4, o1-o2-o3+o4, i1-i2-i3+i4

row, col = map(int, input().split())
K = int(input())
board = [list(input().strip()) for _ in range(row)]

# (J, O, I)
acc = [[(0, 0, 0)] * (col+1) for _ in range(row+1)]

for r in range(1, row+1):
    for c in range(1, col+1):
        acc[r][c] = calculateAccOfPos(r, c)

for _ in range(K):
    a, b, c, d = map(int, input().split())
    print(*calculateSectionInfoOfPos(a, b, c, d))