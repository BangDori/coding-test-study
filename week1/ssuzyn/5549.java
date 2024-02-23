import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 5549 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		
		int[][] jungle = new int[N+1][M+1];
		int[][] ocean = new int[N+1][M+1];
		int[][] ice = new int[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
			String input = br.readLine();
			for(int j = 1; j <= M; j++) {
				char tmp = input.charAt(j-1);
				
				jungle[i][j] = jungle[i][j - 1] + jungle[i - 1][j] - jungle[i - 1][j - 1];
				ocean[i][j] = ocean[i][j - 1] + ocean[i - 1][j] - ocean[i - 1][j - 1];
				ice[i][j] = ice[i][j - 1] + ice[i - 1][j] - ice[i - 1][j - 1];
				
				if(tmp == 'J') jungle[i][j]++;
				else if(tmp == 'O') ocean[i][j]++;
				else ice[i][j]++;
			}
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			int e1 = Integer.parseInt(st.nextToken());
			int e2 = Integer.parseInt(st.nextToken());
			
			int jungleCnt = jungle[e1][e2] + jungle[s1-1][s2-1] - jungle[s1-1][e2] - jungle[e1][s2-1];
			int oceanCnt = ocean[e1][e2] + ocean[s1-1][s2-1] - ocean[s1-1][e2] - ocean[e1][s2-1];
			int iceCnt = ice[e1][e2] + ice[s1-1][s2-1] - ice[s1-1][e2] - ice[e1][s2-1];
			
			sb.append(jungleCnt + " " + oceanCnt + " " + iceCnt).append("\n");

		}
		
		System.out.println(sb.toString());

	}

}
