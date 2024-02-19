import sys
input = sys.stdin.readline
M, N = map(int, input().split()) # 세로, 가로
K = int(input())
maps = []
jungle = [[0 for _ in range(N+1)] for _ in range(M+1)]
sea = [[0 for _ in range(N+1)] for _ in range(M+1)]
ice = [[0 for _ in range(N+1)] for _ in range(M+1)]

for _ in range(M):
    maps.append(list(map(str, input().rstrip())))

prefix = []

def setPrefix():
    for i in range(1, M+1):
        for j in range(1, N+1):
            jungle[i][j] = jungle[i-1][j] + jungle[i][j-1] - jungle[i-1][j-1]
            sea[i][j] = sea[i-1][j] + sea[i][j-1] - sea[i-1][j-1] 
            ice[i][j] = ice[i-1][j] + ice[i][j-1] - ice[i-1][j-1]
            if maps[i-1][j-1] == "J": # 정글
                jungle[i][j] += 1
            elif maps[i-1][j-1] == "O": # 바다
                sea[i][j] += 1
            elif maps[i-1][j-1] == "I": # 얼음
                ice[i][j] += 1

setPrefix()
def getPrefix(a,b,c,d):
    j = jungle[c][d] - jungle[c][b-1] - jungle[a-1][d] + jungle[a-1][b-1]
    o = sea[c][d] - sea[c][b-1] - sea[a-1][d] + sea[a-1][b-1]
    i = ice[c][d] - ice[c][b-1] - ice[a-1][d] + ice[a-1][b-1]
    print(j, o, i)
    
for _ in range(K):
    a, b, c, d = map(int, input().split()) # 왼쪽 위 (a,b) 오른쪽 아래 (c,d)
    getPrefix(a,b,c,d)