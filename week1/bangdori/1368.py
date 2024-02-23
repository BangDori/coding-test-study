import sys
input = sys.stdin.readline

N = int(input())
costs = [int(input()) for _ in range(N)]
board = [list(map(int, input().split())) for _ in range(N)]

parent = [i for i in range(N+1)]

def find(x):
    if parent[x] == x: return x
    parent[x] = find(parent[x])
    return find(parent[x])

def union(a, b):
    pa, pb = find(a), find(b)
    parent[min(pa, pb)] = max(pa, pb) 

# 비용, a, b
queue = []

for i, cost in enumerate(costs):
    queue.append((cost, 0, i+1))

for r in range(N):
    for c in range(N):
        if r == c: continue
        queue.append((board[r][c], r+1, c+1))

queue.sort()

answer = 0
for cost, a, b in queue:
    if find(a) != find(b):
        union(a, b)
        answer += cost

print(answer)