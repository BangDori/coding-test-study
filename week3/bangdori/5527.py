# 5527
# 교대 패턴 (0101010 or 101010...)

# 전구의 영역을 분리?

# ○ ○ ● ● ○ ● ○ ○ ○ ●
# 1 1 0 0 1 0 1 1 1 0

# (○) (○ ●) (● ○ ● ○) (○) (○ ●)
# (1) (2) (4) (1) (2)
# 1 + 2 + 4 = 7
# 2 + 4 + 1 = 7
# 4 + 1 + 2 = 7
# ? 버근가

import sys
input = sys.stdin.readline

N = int(input())
lights = list(map(int, input().split()))

section = []

length = 1
for i in range(1, len(lights)):
    if lights[i] != lights[i-1]: length += 1
    else:
        section.append(length)
        length = 1
section.append(length)

answer = 0
if len(section) == 1: answer = section[0]
elif len(section) == 2:
    answer = section[0] + section[1]
else:
    for i in range(2, len(section)):
        answer = max(answer, section[i]+section[i-1]+section[i-2])

print(answer)