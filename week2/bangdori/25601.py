import sys
input = sys.stdin.readline

def is_find_class(a, b):
    is_find = False

    next_a = tree[a]
    while next_a != "root":
        if next_a == b:
            is_find = True
            break

        next_a = tree[next_a]
    
    return is_find

N = int(input())
tree = {}

for _ in range(N-1):
    child, parent = input().strip().split()

    tree[child] = parent
    if not tree.get(parent):
        tree[parent] = "root"

classA, classB = input().strip().split()
answer = is_find_class(classA, classB) | is_find_class(classB, classA)

print(1 if answer else 0)