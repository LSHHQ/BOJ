import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken()); // 키트 수
        K = stoi(st.nextToken()); // 하루마다 내려가는 양

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = stoi(st.nextToken());

        cnt = 0;
        visited = new boolean[N];
        func(0,0);

        System.out.println(cnt);

    }// main

    static int N, K, cnt;
    static int[] arr;
    static boolean[] visited;

    static void func(int depth, int now){
        if(depth==N){
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            if (now + arr[i] - K >= 0) {
                visited[i] = true;
                func(depth + 1, now + arr[i] - K);
                visited[i] = false;
            }
        }
    }

}// Main