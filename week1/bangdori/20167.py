import sys
sys.setrecursionlimit(10 * 2)
input = sys.stdin.readline

food_cnt, need_satisfaction = map(int, input().split())
food = [0] + list(map(int, input().split())) # Index 맞추기
max_energy = 0

# 먹는 경우와 먹지 않는 경우에 대한 백트래킹 함수
def back_tracking(current_index, current_staisfaction, current_energy):
    global max_energy
    if current_index >= len(food):
        max_energy = max(max_energy, current_energy)
        return

    next_satisfaction = current_staisfaction + food[current_index]
    if next_satisfaction >= need_satisfaction:
        next_energy = current_energy + (next_satisfaction - need_satisfaction)
        back_tracking(current_index + 1, 0, next_energy) # 다음 만족도는 0에서 시작
    else: back_tracking(current_index + 1, next_satisfaction, current_energy)
    
    # 아예 해당 구간 선택하지 않는 분기
    # 문제 조건에서 애벌레는 '연속적으로' 먹을 때만 만족도가 누적된다.
    back_tracking(current_index + 1, 0, current_energy)

back_tracking(1, 0, 0)
print(max_energy)