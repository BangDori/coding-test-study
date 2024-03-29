//1. 자바의 형변환

package 백준_2024년.스터디_11주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class problem1 {
    static HashSet<String> visited = new HashSet<>();
    static void findA(String now) {
        if(map.get(now).isEmpty()) {
            visited.add(now);
            return ;
        }
        for (String s : map.get(now)) {
            findA(s);
        }
    }

    static boolean findB(String now) {
        if(map.get(now).isEmpty()) {
            if(visited.contains(now)) {
                return true;
            }
        }
        for (String s : map.get(now)) {
            if(findB(s)) {
                return true;
            }
        }
        return false ;
    }

    static HashMap<String, ArrayList<String>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i= 1;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();

            map.computeIfAbsent(B, k -> new ArrayList<>());
            map.computeIfAbsent(A, k -> new ArrayList<>());
            map.get(B).add(A);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();
        findA(A);
        if(findB(B)) {
            System.out.println(1);
            return ;
        }
        System.out.println(0);

    }
}

/*
자식 -> 부모 방향으로 탐색할 수 있게 하면 될듯?
*
*   integer - number - object
*                     /
*               string
*
*
*
*    number - object
*             /
*       string
*
*
*
* */