import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] tree;
	static int[][] sumArr;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		tree = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				tree[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sumArr = new int[n + 1][n + 1];
		for (int i = 1; i < n + 1; i++) {
			sumArr[i][0] = sumArr[i - 1][0] + tree[i - 1][0];
		}
		for (int i = 1; i < n + 1; i++) {
			sumArr[0][i] = sumArr[0][i - 1] + tree[0][i - 1];
		}
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				sumArr[i][j] = sumArr[i - 1][j] + sumArr[i][j - 1] - sumArr[i - 1][j - 1] + tree[i - 1][j - 1];
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 1; i < n - k + 1; i++) {
				for (int j = 1; j < n - k + 1; j++) {
					int sum = sumArr[i + k][j + k];
					sum -= sumArr[i - 1][j + k];
					sum -= sumArr[i + k][j - 1];
					sum += sumArr[i - 1][j - 1];

					answer = Math.max(answer, sum);
				}
			}
		}

		System.out.println(answer);
	}
}
