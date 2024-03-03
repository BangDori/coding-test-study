import sys
input = sys.stdin.readline

N, M = map(int, input().split())
P = list(map(int, input().split()))
info = []
count = [0] * (N+1)
for _ in range(N-1):
    A,B,C = map(int, input().split())
    info.append([A,B,C])

def setCount():
    for i in range(M-1):
        if P[i] < P[i+1]:
            count[P[i]] += 1
            count[P[i+1]] -= 1
        else:
            count[P[i+1]] += 1
            count[P[i]] -= 1
SUM = 0
answer = 0
setCount()

for i in range(N-1):
    SUM += count[i+1]
    answer += min(info[i][0] * SUM, info[i][1] * SUM + info[i][2])
print(answer)