import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken()); // N개의 정수
        int K = stoi(st.nextToken()); // 합이 K인 부분합의 갯수를 구해야함.

        int[] sum = new int[N+1]; //누적합 배열
        long cnt = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + stoi(st.nextToken());
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        for (int i = 1; i <= N; i++) {
            cnt += map.getOrDefault(sum[i] - K, 0);
            map.put(sum[i], map.getOrDefault(sum[i],0)+1);
        }

        System.out.println(cnt);
    }// main

}// Main