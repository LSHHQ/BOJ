import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1번 4방향, 2번 2방향, 3번 4방향, 4번 4방향, 5번 1방향

        st = new StringTokenizer(br.readLine());
        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        int[][] map = new int[H][W];
        cctvs = new ArrayList<>();

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = stoi(st.nextToken());
                if(map[i][j]!=0 && map[i][j]!=6) cctvs.add(new Point(i,j));
            }
        }

        size = cctvs.size();

        ans = Integer.MAX_VALUE;
        function(0, map);

        System.out.println(ans);

    }// main

    static int H, W, size, ans ;
    static ArrayList<Point> cctvs;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    static void function(int index, int[][] map){
        if(index== size){
            ans = Math.min(cntZero(map),ans);
            return;
        }

        Point cctv = cctvs.get(index);
        int type = map[cctv.row][cctv.col];

        // 0: 상, 1: 하, 2: 좌, 3: 우
        if(type==1){
            for (int i = 0; i < 4; i++) {
                int[][] cpy = copyMap(map);
                mark(cpy,cctv, i);
                function(index+1, cpy);
            }
        }else if(type==2){
            for (int i = 0; i < 2; i++) {
                int[][] cpy = copyMap(map);
                mark(cpy,cctv,2*i);
                mark(cpy,cctv,2*i+1);
                function(index+1, cpy);
            }
        }else if(type==3){
            int[][] cpy = copyMap(map);
            mark(cpy,cctv,0);
            mark(cpy,cctv,2);
            function(index + 1, cpy);

            cpy = copyMap(map);
            mark(cpy,cctv, 0);
            mark(cpy,cctv, 3);
            function(index + 1, cpy);

            cpy = copyMap(map);
            mark(cpy,cctv, 1);
            mark(cpy,cctv, 2);
            function(index + 1, cpy);

            cpy = copyMap(map);
            mark(cpy,cctv, 1);
            mark(cpy,cctv, 3);
            function(index + 1, cpy);

        } else if (type == 4) {
            for (int i = 0; i < 4; i++) {
                int[][] cpy = copyMap(map);
                for (int j = 0; j < 4; j++) {
                    if (i == j) continue;
                    mark(cpy,cctv, j);
                }
                function(index + 1, cpy);
            }
        }else{
            int[][] cpy = copyMap(map);
            mark(cpy,cctv,0);
            mark(cpy,cctv,1);
            mark(cpy,cctv,2);
            mark(cpy,cctv,3);
            function(index+1,cpy);
        }
    }// function

    static void mark(int[][] map, Point p, int dir){

        int row = p.row;
        int col = p.col;

        while (true) {
            int nrow = row + dr[dir];
            int ncol = col + dc[dir];

            if(nrow==H || ncol==W || nrow==-1 || ncol==-1 || map[nrow][ncol]==6)
                break;

            row = nrow;
            col = ncol;
            if(map[row][col]==0) map[row][col] = -1;
        }

    }

    static int cntZero(int[][] map){
        int ret = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(map[i][j]==0) ret++;
            }
        }
        return ret;
    }

    static int[][] copyMap(int[][] map){
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) copy[i] = Arrays.copyOf(map[i], W);
        return copy;
    }

    static class Point{
        int row;
        int col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}// Main