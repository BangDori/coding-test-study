import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 20167 {
	
	static int N, K, result = 0;
	static int[] feed ;
	
	public static void dfs(int idx, int sum, int energy) {
		
		if(idx == N) {
			result = Math.max(result, energy);
			return;
		}
		
		dfs(idx + 1, 0, energy);
		
		if(sum + feed[idx] >= K) {
			dfs(idx + 1, 0, sum + feed[idx] - K);
		}
		else {
			dfs(idx + 1, sum + feed[idx], energy);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		feed = new int[N];
		for(int i = 0; i < N; i++) {
			feed[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0,0);
		System.out.println(result);

	}

}
