import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 20002 {
	
	static int N;
	static int[][] apple;
	static int[][] prefixSum;
	
	public static void setting() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + apple[i][j];
			}
		}
	}
	
	public static int getAnswer() {
		int answer = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int X = Math.min(N - i, N - j);
				for (int k = 0; k <= X; k++) {
					int tmp = prefixSum[i + k][j + k] - prefixSum[i - 1][j + k] - prefixSum[i + k][j - 1] + prefixSum[i - 1][j - 1];
					answer = Math.max(answer, tmp);
				}
			}
		}
		
		return answer;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		apple = new int[N+1][N+1];
		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				apple[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		prefixSum = new int[N+1][N+1];
		setting();
		System.out.println(getAnswer());

	}

}
