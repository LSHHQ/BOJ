import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>max) { max = map[i][j];}
			}
		}
		
		int maxCnt = 1;
		
		for (int h = 1; h < max; h++) {
			
			int cnt = 0;
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					if(map[i][j]>h && !visited[i][j]) {
						visited[i][j] = true;
						DFS(i,j,h);
						cnt++;
					}
				}
			}
			
			if(cnt>maxCnt) {
				maxCnt = cnt;
			}
		}
		
		System.out.println(maxCnt);
	}

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static void DFS(int row, int col, int h) {
		
		for (int dir = 0; dir < 4; dir++) {
			int nrow = row+dr[dir];
			int ncol = col+dc[dir];
			
			if(nrow>=N || ncol>=N || nrow<0 || ncol<0 
					|| visited[nrow][ncol] || map[nrow][ncol]<=h)
				continue;
			
			visited[nrow][ncol] = true;
			DFS(nrow,ncol,h);
		}
	}

}
