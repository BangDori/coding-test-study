import sys
input = sys.stdin.readline

n = int(input())
stones = list(map(int, input().split()))

# dp[i] = 0 ~ i번 째 중 힘이 제일 적게 드는 경우의 값
dp = [1e9 for _ in range(n)]
dp[0] = 0

for curr in range(n):
    for next in range(curr):
        power = (curr - next) * (1 + abs(stones[curr] - stones[next]))
        next_power = max(dp[next], power)

        dp[curr] = min(dp[curr], next_power)

print(dp[-1])