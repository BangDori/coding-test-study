import sys
input = sys.stdin.readline

N, K = map(int, input().split())
food = list(map(int, input().split()))
answer = 0

def dfs(index, satisfaction, energy):
    global answer
    if index == N:
        answer = max(answer, energy)
        return
    
    if satisfaction == 0:
        dfs(index + 1, satisfaction, energy)
        
    satisfaction += food[index]

    if satisfaction >= K:
        energy += satisfaction - K
        satisfaction = 0

    dfs(index + 1, satisfaction, energy)

dfs(0,0,0) # 인덱스, 만족도, 에너지

print(answer)