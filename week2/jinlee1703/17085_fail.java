import java.io.*;
import java.util.*;

public class Main {
	static final int[] dx = { -1, 0, 1, 0 };
	static final int[] dy = { 0, 1, 0, -1 };
	static final int[] CROSS_SIZE = {3, 2, 1, 0};
	static final int[] CROSS_SCORE = {13, 9, 5, 1};
	static int N, M;
	static char[][] P;
	static boolean[][] visited;

	public static void main(String[] args) {
		int[] cnt = {0, 0, 0, 0};

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		P = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			P[i] = sc.next().toCharArray();
		}

		for (int i = 0; i < CROSS_SIZE.length; i++) {
			int size = CROSS_SIZE[i];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (P[j][k] == '#' && !visited[j][k]) {
						if (isCross(j, k, size)) {
							saveVisited(j, k, size);
							cnt[i]++;
						}
					}
				}
			}
		}

		List<Integer> scores = new ArrayList<>();
		for (int i = 0; i < CROSS_SIZE.length - 1; i++) {
			int count = cnt[i];
			int score = CROSS_SCORE[i];

			for (int j = 0; j < count; j++) {
				scores.add(score);
			}
		}

		int answer = 1;
		if (scores.size() == 0) {
			if (cnt[4] == 0) {
				System.out.println(0);
			} else {
				System.out.println(1);
			}
		} else {
			for (int score : scores) {
				answer *= score;
			}
		}
		System.out.println(answer);
	}

	private static boolean isCross(int x, int y, int size) {
		for (int i = 0; i < 4; i++) {
			// 방향별 '#' 체크
			for (int j = 1; j <= size; j++) {
				int nx = x + (dx[i] * j);
				int ny = y + (dy[i] * j);

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) return false;
				if (P[nx][ny] != '#') return false;
				if (visited[nx][ny]) return false;
			}
		}
		return true;
	}

	private static void saveVisited(int x, int y, int size) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= size; j++) {
				int nx = x + (dx[i] * j);
				int ny = y + (dy[i] * j);
				visited[nx][ny] = true;
			}
		}
	}
}