import java.io.*;
import java.util.*;

public class Main {
	public static int n;    // 행
	public static int m;    // 열
	public static int k;    // 조사 대상 영역 개수
	public static char[][] map;
	public static int[][] jungle;
	public static int[][] ocean;
	public static int[][] ice;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		setArray();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			System.out.println(solution(a, b, c, d));
		}
	}

	private static String solution(int a, int b, int c, int d) {
		StringBuilder sb = new StringBuilder();
		sb.append(jungle[c][d] - jungle[c][b-1] - jungle[a-1][d] + jungle[a-1][b-1] + " ");
		sb.append(ocean[c][d] - ocean[c][b-1] - ocean[a-1][d] + ocean[a-1][b-1] + " ");
		sb.append(ice[c][d] - ice[c][b-1] - ice[a-1][d] + ice[a-1][b-1] + " ");
		return sb.toString();
	}

	private static void setArray() {
		jungle = new int[n + 1][m + 1];
		ocean = new int[n + 1][m + 1];
		ice = new int[n + 1][m + 1];

		for (int i = 1; i < n + 1; i++) {
			jungle[i][1] = jungle[i-1][1] + (map[i-1][0] == 'J' ? 1 : 0);
			ocean[i][1] = ocean[i-1][1] + (map[i-1][0] == 'O' ? 1 : 0);
			ice[i][1] = ice[i-1][1] + (map[i-1][0] == 'I' ? 1 : 0);
		}
		for (int i = 1; i < m + 1; i++) {
			jungle[1][i] = jungle[1][i-1] + (map[0][i-1] == 'J' ? 1 : 0);
			ocean[1][i] = ocean[1][i-1] + (map[0][i-1] == 'O' ? 1 : 0);
			ice[1][i] = ice[1][i-1] + (map[0][i-1] == 'I' ? 1 : 0);
		}
		for (int i = 2; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				jungle[i][j] = jungle[i-1][j] + jungle[i][j-1] - jungle[i-1][j-1] + (map[i-1][j-1] == 'J' ? 1 : 0);
				ocean[i][j] = ocean[i-1][j] + ocean[i][j-1] - ocean[i-1][j-1] + (map[i-1][j-1] == 'O' ? 1 : 0);
				ice[i][j] = ice[i-1][j] + ice[i][j-1] - ice[i-1][j-1] + (map[i-1][j-1] == 'I' ? 1 : 0);
			}
		}
	}
}