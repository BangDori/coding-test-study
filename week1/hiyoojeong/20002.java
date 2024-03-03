import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사과나무
public class No20002 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n + 1][n + 1];
		int[][] sum = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + map[i][j];
			}
		}
		
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print(sum[i][j] + " ");
			}
			System.out.println();
		}

		int max = Integer.MIN_VALUE;
		for (int k = 0; k < n; k++) {
			for (int i = 1; i <= n - k; i++) {
				for (int j = 1; j <= n - k; j++) {
					int value = sum[i + k][j + k] - sum[i + k][j - 1] - sum[i - 1][j + k] + sum[i - 1][j - 1];
					max = Math.max(max, value);
				}
			}
		}

		System.out.println(max);
	}

}
