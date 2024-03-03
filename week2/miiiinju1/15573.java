//5. 채굴
package 백준_2024년.스터디_11주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class problem5 {
    static class Point {
        int y,x;
        public Point(int y, int x) {
            this.y= y;
            this.x =x;
        }
    }
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N,M,K;
    static int check(int mid) {

        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));

        int count = 0;
        boolean[][] visited = new boolean[yLimit][xLimit];
        while(!q.isEmpty()) {
            Point now = q.poll();
            for(int i= 0;i<4;i++) {
                int Y = dy[i] + now.y;
                int X = dx[i] + now.x;

                if(Y>=0&&X>=0&&Y<yLimit&&X<xLimit) {
                    if(!visited[Y][X]) {
                        if(map[Y][X]>0&&map[Y][X]<=mid) {
                            count++;
                            map[Y][X] = 0;
                        }
                        if(map[Y][X]==0) {
                            visited[Y][X] = true;
                            q.add(new Point(Y, X));
                        }

                    }
                }
            }
        }
        return count;
    }
    static int[][] map;
    static int[][] original;

    static int yLimit = -1, xLimit = -1;

    static void cleanUp() {
        for(int i= 0;i<yLimit;i++) {
            for(int j = 0;j<xLimit;j++) {
                map[i][j] = original[i][j];
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        yLimit = N+1;
        xLimit = M+2;
        map = new int[yLimit][xLimit];
        original = new int[yLimit][xLimit];
        for(int i= 1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 1;j<=M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                original[i][j] = map[i][j];

            }
        }
        int lo = 0, hi = 1000001;
        while(lo+1<hi) {
            int mid = (hi-lo)/2 + lo;
            int temp = check(mid);
            if(temp>=K) {
                hi = mid;
            }
            else {
                lo = mid;
            }
            cleanUp();

        }
        System.out.println(hi);

    }
}
