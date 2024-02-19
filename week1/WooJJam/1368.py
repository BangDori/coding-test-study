import sys
input = sys.stdin.readline
N = int(input())
parent = [0] * (N+1)
edge = []

for i in range(1, N+1):
    edge.append([int(input()), 0, i])

for i in range(1, N+1):
    info = list(map(int, input().split()))
    for j in range(N):
        if i == j+1: continue
        edge.append([info[j], i, j+1])

edge.sort()

for i in range(N+1):
    parent[i] = i

def find(parent, x):
    if parent[x] != x:
        parent[x] = find(parent, parent[x])
    return parent[x]

def union(parent, a, b):
    if a > b:
        parent[a] = b
    else:
        parent[b] = a

answer = 0
for cost, a ,b in edge:
    findA = find(parent, a)
    findB = find(parent, b)

    if findA != findB:
        union(parent, findA, findB)
        answer += cost
        
print(answer)