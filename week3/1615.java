package 백준_2024년.스터디_12주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class problem4 {
    static class Point{
        int a,b;

        public Point(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int sum = 0;
        Point[] ary = new Point[M];
        for(int i= 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.computeIfAbsent(b, k -> new ArrayList<>());
            map.get(b).add(a);
            ary[i] = new Point(a, b);
        }

        List<Integer> sorted = map.keySet().stream().sorted().collect(Collectors.toList());

        int before = sorted.get(0);
        for(int i= 1;i<sorted.size();i++) {
            Integer now = sorted.get(i);
            map.get(now).addAll(map.get(before));
            before = now;
        }
        for (Integer i : sorted) {
            Collections.sort(map.get(i));
        }

        //무조건 터질거같은데,,,,,,,


        Arrays.sort(ary, (o1,o2)-> {
            if(o1.a==o2.b) {
                return Integer.compare(o1.b,o2.b);
            }
            return Integer.compare(o1.a,o1.b);
        });
        a: for (Point now : ary) {

            int dest = now.b-1;
            while(!map.containsKey(dest)) {
                dest--;
                if(dest<=0) {
                    continue a;
                }
            }

            ArrayList<Integer> integers = map.get(dest);
            int count = findCount(integers, now.a);
            sum+=count;
        }

//        System.out.println(map);
        System.out.println(sum);


    }
    private static int findCount(ArrayList<Integer> integers, int a) {

        int lo = -1, hi = integers.size();
        while(lo+1<hi) {
            int mid = (hi-lo)/2+lo;

            if(integers.get(mid)<=a) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }

        return integers.size()-hi;


    }
}
/*
 M(M-1)/2는 400만 * 400만 무조건X
 정렬하면?


 //만약 1->5 이면

 //2 3 4 5에서 5보다 작은놈들 다 봐야함


 그럼 뒤에거 기준으로 정렬해둔뒤 앞에거 기준으로 정렬하면

 1 (5) 2(2,3) 3(4,5), 5(1)
 // 5개가 됨

끝나는 지점으로 합쳐놓고 그 내부를 정렬시켜두면 될듯?
근데 이러면 너무 커지는데, 메모리가 128이니깐




//끝나는 지점들 기준으로 정렬해놓고




 */
