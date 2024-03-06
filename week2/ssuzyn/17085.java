import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 17085 {

	static char[][] map;
	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	public static int getCrossSize(int x, int y) {
		int size = 0; 
		while(true) {
			boolean flag = true;
			for(int i = 0; i < 4; i++) {
				int newX = x + dx[i] * size;
				int newY = y + dy[i] * size;
				
				if(!isValid(newX, newY) || map[newX][newY] != '#') {
					flag = false;
					break;
				}
			}
			
			if(flag) size++;
			else break;
		}
		
		return size - 1;
	}
	
	public static void makeCross(int x, int y, int size, char val) {
		for(int i = 0; i <= size; i++) {
			for(int j = 0; j < 4; j++) {
				int newX = x + dx[j] * i;
				int newY = y + dy[j] * i;
				map[newX][newY] = val;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == '#') {
					int size1 = getCrossSize(i, j);
					System.out.println("size = " + size1);
					
					for(int k = 0; k <= size1; k++) {
						makeCross(i, j, k, '.');
						printCross();
						
						for(int r = 0; r < N; r++) {
							for(int c = 0; c < M; c++) {
								if(map[r][c] == '#') {
									int size2 = getCrossSize(r, c);
									int width1 = 4 * k + 1;
									int width2 = 4 * size2 + 1;
									
									System.out.println("width1 = " + width1 + " width2 = " + width2);
									answer = Math.max(answer, width1 * width2);
								}
							}
						}
						makeCross(i, j, k, '#');
						printCross();
						System.out.println();
					}
				}
				
				
			}
		}
		
		System.out.println(answer);

	}
	
	public static void printCross() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
