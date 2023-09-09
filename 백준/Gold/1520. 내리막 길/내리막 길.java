import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int H, W;
	static int[][] map, dp;
	
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		dp = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		DFS(0,0);
		
		System.out.println(dp[0][0]);
		
	}
	
	static int DFS(int row, int col) {
		
		if(row==H-1 && col==W-1) {
			return 1;
		}
		
		if(dp[row][col]!=-1) {
			return dp[row][col];
		}
		
		dp[row][col] = 0;
		
		for (int i = 0; i < 4; i++) {
			int nrow = row+dr[i];
			int ncol = col+dc[i];
			
			if(nrow<0||nrow>=H||ncol<0||ncol>=W)
				continue;
			
			if(map[nrow][ncol] < map[row][col]) {
				dp[row][col] += DFS(nrow,ncol);
			}
		}
		
		return dp[row][col];
	}
	
}
