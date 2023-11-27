import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int H = stoi(st.nextToken());
        int W = stoi(st.nextToken());
        int[][] map = new int[H][W];
        Queue<int[]> que = new LinkedList<>();
        
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                if(stoi(st.nextToken())==1) {
                    map[i][j] = 0; //상어가 있을 떄,
                    que.add(new int[] {i,j});
                }else map[i][j] = -1; //방문 안했을 경우
            }
        }

        int[] dr = {1, -1, 0, 0, 1, -1, 1, -1};
        int[] dc = {0, 0, 1, -1, 1, 1, -1, -1};
        
        int ans = 0;
        while(!que.isEmpty()){
            int row = que.peek()[0];
            int col = que.peek()[1];
            que.poll();

            int next = map[row][col] + 1;
            for (int i = 0; i < 8; i++) {
                int nrow = row+dr[i];
                int ncol = col+dc[i];
                if(nrow==-1 || ncol==-1 || nrow==H || ncol==W) continue;
                
                if(map[nrow][ncol]==-1 || next<map[nrow][ncol]){
                    map[nrow][ncol] = next;
                    ans = Math.max(next,ans);
                    que.add(new int[] {nrow, ncol});
                }
            }
        }

        System.out.println(ans);
    }// main

}// Main