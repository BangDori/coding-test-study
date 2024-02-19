import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 기차 여행
public class No10713 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Queue<Integer> plan = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			plan.add(Integer.parseInt(st.nextToken()));
		}

		// costs[i] = i -> i+1로 가는 비용
		int[][] costs = new int[n][3];
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
			costs[i][2] = Integer.parseInt(st.nextToken());
		}

		// uses[i] = i -> i+1로 가는 횟수
		int[] uses = new int[n];
		int pre = plan.poll();
		while (!plan.isEmpty()) {
			int current = plan.poll();

			int start = Math.min(pre, current);
			int end = Math.max(pre, current);
			for (int i = start; i < start + (end - start); i++) {
				uses[i]++;
			}

			pre = current;
		}

		long answer = 0;
		for (int i = 1; i < n; i++) {
			int cost = Math.min(costs[i][0] * uses[i], costs[i][1] * uses[i] + costs[i][2]);
			answer += cost;
		}
		System.out.println(answer);
	}

}
