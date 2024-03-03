import java.io.*;
import java.util.*;

public class Main {
	private static int n;
	private static int k;
	private static int[] bob;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		bob = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			bob[i] = Integer.parseInt(st.nextToken());
		}


		dfs(0, 0, 0, false);
		dfs(0, 0, 0, true);
		System.out.println(answer);
	}

	private static void dfs(int num, int sum, int score, boolean status) {
		if (num == n) {
			answer = Math.max(answer, score);
			return;
		}
		if (status) {
			sum += bob[num];
			if (sum >= k) {
				score += sum - k;
				sum = 0;
				status = false;
			}
		}
		if (status) {
			dfs(num + 1, sum, score, true);
		} else {
			dfs(num + 1, sum, score, false);
			dfs(num + 1, sum, score, true);
		}
	}
}