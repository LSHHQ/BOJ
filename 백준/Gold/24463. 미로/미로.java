import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new char[N][M];
        List<int[]> point = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                if(row.charAt(j)=='.') map[i][j]='@';
                else map[i][j] = '+';
                if(isEntOrExit(i,j)) point.add(new int[]{i, j});
            }
        }

        start = point.get(0);
        end = point.get(1);

        // idea.
        // BFS + 다익스트라 개념으로 완탐한다음에 도착하면 도착한거리에서 -1 새겨진데를 찾으면서 "."으로 바꾼다.

        int[][] dp = new int[N][M];
        dp[start[0]][start[1]] = 2;
        Queue<int[]> que = new LinkedList<>();
        que.add(start);
        while (!que.isEmpty()) {
            int row = que.peek()[0];
            int col = que.peek()[1];
            que.poll();

            for (int i = 0; i < 4; i++) {
                int nrow = row + dr[i];
                int ncol = col + dc[i];
                if(nrow == -1 || ncol == -1 || nrow==N || ncol==M
                || map[nrow][ncol]=='+' || dp[nrow][ncol] !=0 ) continue;
                dp[nrow][ncol]=dp[row][col]+1;
                que.add(new int[]{nrow, ncol});
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println();
//            for (int j = 0; j < M; j++) {
//                System.out.printf("%3d",dp[i][j]);
//            }
//        }
//        System.out.println();

        que.add(end);
        map[end[0]][end[1]]='.';

        while (!que.isEmpty()) {
            int row = que.peek()[0];
            int col = que.peek()[1];
            que.poll();
            for (int i = 0; i < 4; i++) {
                int nrow = row + dr[i];
                int ncol = col + dc[i];
                if(nrow == -1 || ncol == -1 || nrow==N || ncol==M) continue;

                if(dp[nrow][ncol]==dp[row][col]-1){
                    map[nrow][ncol]='.';
                    que.add(new int[] {nrow, ncol});
                    break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(map[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }// main

    static int N,M;
    static int[] start, end;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    static char[][] map;

    static boolean isEntOrExit(int i, int j){
        if( (i==0 || i==N-1) && map[i][j]=='@') return true;
        else if( (j==0 || j==M-1) && map[i][j]=='@') return true;
        else return false;
    }

}// Main