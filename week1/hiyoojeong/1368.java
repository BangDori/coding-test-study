import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 물대기
public class No1368 {
	static int n;
	static int[] parents;

	static class Node {
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		// 논을 연결하는 비용
		List<Node> costs = new ArrayList<>();

		for (int i = 0; i < n; i++) { // 직접 논에 우물을 파는 방법
			int cost = Integer.parseInt(br.readLine());
			costs.add(new Node(i, i, cost));
		}

		for (int i = 0; i < n; i++) { // 이미 물을 대고 있는 다른 논으로부터 물을 끌어오는 방법
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if (i < j) {
					costs.add(new Node(i, j, cost));
				}
			}
		}

		// 비용 기준 오름차순으로 정렬
		Collections.sort(costs, (o1, o2) -> o1.cost - o2.cost);

		parents = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			parents[i] = i;
		}

		int sum = 0, cnt = 0;
		for (Node node : costs) {
			if (union(node.from, node.to)) {
				sum += node.cost;
				cnt++;

				if (cnt == n) {
					break;
				}
			}
		}

		System.out.println(sum);

	}

	public static boolean union(int from, int to) {
		int fromRoot = findSet(from);
		int toRoot = findSet(to);

		if (fromRoot == toRoot && from != to) {
			return false;
		}

		if (fromRoot == toRoot && from == to) {
			parents[toRoot] = n;
			return true;
		}

		parents[toRoot] = fromRoot;
		return true;
	}

	public static int findSet(int v) {
		if (parents[v] == v) {
			return v;
		} else {
			return parents[v] = findSet(parents[v]);
		}
	}
}
