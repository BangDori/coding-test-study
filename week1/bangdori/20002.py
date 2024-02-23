import sys
input = sys.stdin.readline

SIZE = int(input())
board = [list(map(int,input().split())) for _ in range(SIZE)]
acc = [[0] * (SIZE+1) for _ in range(SIZE+1)]

for i in range(1, SIZE+1):
    for j in range(1, SIZE+1):
        acc[i][j] = acc[i-1][j] + acc[i][j-1] - acc[i-1][j-1] + board[i-1][j-1]

max_profit = acc[1][1]
for size in range(SIZE):
    for i in range(1, SIZE-size+1):
        for j in range(1, SIZE-size+1):
            current_profit = acc[i+size][j+size] - acc[i-1][j+size] - acc[i+size][j-1] + acc[i-1][j-1]
            max_profit = max(max_profit, current_profit)

print(max_profit)