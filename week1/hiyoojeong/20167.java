import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 꿈틀꿈틀 호석 애벌레 - 기능성
public class No20167 {

	static int n, k;
	static int[] stick;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		stick = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			stick[i] = Integer.parseInt(st.nextToken());
		}

		answer = 0;
		dfs(0, 0, 0);

		System.out.println(answer);
	}

	public static void dfs(int pos, int eat, int save) {
		if (pos == n) {
			answer = Math.max(save, answer);
			return;
		}

		// 안 먹는 경우
		dfs(pos + 1, 0, save);

		// 먹는 경우
		if (eat + stick[pos] >= k) {
			dfs(pos + 1, 0, save + (eat + stick[pos] - k));
		} else {
			dfs(pos + 1, eat + stick[pos], save);
		}

	}

}
