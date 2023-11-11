import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int seq = 1;
        while(true){
            int N = stoi(br.readLine());
            if(N==0) break;

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }

            PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)->{return a[2]-b[2];});
            PQ.add(new int[] {0,0,map[0][0]});
            int[][] dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            int[] dr = {1,-1,0,0};
            int[] dc = {0,0,1,-1};
            while(!PQ.isEmpty()){
                int[] node = PQ.poll();
                int row = node[0];
                int col = node[1];
                int cost = node[2];

                for (int i = 0; i < 4; i++) {
                    int nrow = row+dr[i];
                    int ncol = col+dc[i];
                    if(nrow<0 || ncol <0 || nrow==N || ncol==N) continue;

                    if(cost+map[nrow][ncol] < dp[nrow][ncol]){
                        dp[nrow][ncol] = cost+map[nrow][ncol];
                        PQ.add(new int[] {nrow, ncol, dp[nrow][ncol]});
                    }
                }
            }

            sb.append("Problem ").append(seq++).append(": ").append(dp[N-1][N-1]).append("\n");
        }

        System.out.println(sb);

    }// main

}// Main