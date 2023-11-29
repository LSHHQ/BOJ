import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        today = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                today[i][j] = stoi(st.nextToken());
            }
        }

        //시간 1씩  추가하면서 시뮬레이션 돌린다, 다음 시퀀스 전에 빙산 그룹이 몇개인지 판단한다.

        int part = 1;
        int day = 0;
        while(part==1){
            part = 0;
            tommorow = new int[H][W];
            visited = new boolean[H][W];

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if(today[i][j]>0 && !visited[i][j]) {
                        part++;
                        function(i,j);
                    }
                }
            }
//            print(today);
            copy(tommorow,today);

            day++;
        }

        System.out.println(part>1? day-1 : 0);

    }// main

    static int H, W;
    static int[][] today, tommorow;
    static boolean[][] visited;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};

    static void function(int row, int col){
        visited[row][col] = true;
        tommorow[row][col] = Math.max(today[row][col] - getAroundZero(row, col),0);

        for (int i = 0; i < 4; i++) {
            int nrow = row+dr[i];
            int ncol = col+dc[i];
            if(nrow==-1 || ncol==-1 || nrow==H || ncol==W ||  visited[nrow][ncol]) continue;
            if(today[nrow][ncol]>0)function(nrow,ncol);
        }
    }

    static int getAroundZero(int row, int col){
        int zero = 0;
        for (int i = 0; i < 4; i++) {
            int nrow = row+dr[i];
            int ncol = col+dc[i];
            if(nrow==-1 || ncol==-1 || nrow==H || ncol==W) continue;
            if(today[nrow][ncol]<=0) zero++;
        }
        return zero;
    }

    static void copy(int[][] pre, int[][] now){
        for (int i = 0; i < H; i++) now[i] = Arrays.copyOf(pre[i],W);
    }

    static void print(int[][] map){
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.printf("%3d",map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


}// Main