import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] tree;
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

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n - Math.max(i, j); k++) {
					recursive(i, j, k);
				}
			}
		}
		System.out.println(answer);
	}

	private static void recursive(int x, int y, int depth) {
		int sum = 0;
		for (int i = x; i < x + depth + 1; i++) {
			for (int j = y; j < y + depth + 1; j++) {
				sum += tree[i][j];
			}
		}
		answer = Math.max(answer, sum);
	}
}
