import sys
input = sys.stdin.readline

n, m = map(int, input().split())
travel = list(map(int, input().split()))
edges = [list(map(int, input().split())) for _ in range(n-1)]
visit_count = [0 for _ in range(n+1)]

# 1 -> 999일 떄, 1->2->3->...->999 다 찍으면 N번을 다 돌아버림. 그렇기에 총 O(N*M) 됨.
# 그래서 그냥 i와 i+1 일 떄만 찍고, 누적합으로 계산
for i in range(m-1):
    if travel[i] < travel[i+1]:
        visit_count[travel[i]] += 1
        visit_count[travel[i+1]] -= 1
    else:
        visit_count[travel[i]] -= 1
        visit_count[travel[i+1]] += 1

for i in range(1, n):
    visit_count[i] += visit_count[i-1]

min_cost = 0
# 최소 비용 구하기
for i in range(1, n):
    if visit_count[i] > 0:
        ticket, passed, card = edges[i-1]
        min_cost += min(card + visit_count[i] * passed, ticket * visit_count[i])

print(min_cost)