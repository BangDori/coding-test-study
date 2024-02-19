import sys
input = sys.stdin.readline
N = int(input())
maps = []
for _ in range(N):
    maps.append(list(map(int, input().split())))

prefix = [[0 for _ in range(N+1)] for _ in range(N+1)]

def setPrefix():
    for i in range(1,N+1):
        for j in range(1,N+1):
            prefix[i][j] = prefix[i][j-1] + prefix[i-1][j] - prefix[i-1][j-1] + maps[i-1][j-1]
setPrefix()
answer = prefix[1][1]

for k in range(N):
    for i in range(1, N-k+1):
        for j in range(1, N-k+1):
            profit = prefix[i+k][j+k] - prefix[i-1][j+k] - prefix[i+k][j-1] + prefix[i-1][j-1]
            answer = max(profit, answer)
print(answer)