import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 땅 크기
		L = Integer.parseInt(st.nextToken()); // 최소 차
		R = Integer.parseInt(st.nextToken()); // 최대 차

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		boolean flag = true;
		int day = 0;
		
		while(flag) {
			visited = new boolean[N][N];
			flag = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					if (visited[i][j])
						continue;
					
					adj = new LinkedList<>();
					adj.add(new int[] { i, j });
					
					BFS(i, j);
					
					if(cnt==1)
						continue;
					
					flag = true;
					while (!adj.isEmpty()) {
						int row = adj.peek()[0];
						int col = adj.poll()[1];
						map[row][col] = sum / cnt;
					}
				}
			}
			
			day++;
		}
		
		System.out.println(day-1);

	}

	static Queue<int[]> adj;
	static int N, L, R, sum, cnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };


	static void BFS(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {r,c});
		cnt = 1;
		sum = map[r][c];
		visited[r][c] = true;
		
		while(!que.isEmpty()) {
			int row = que.peek()[0];
			int col = que.poll()[1];
			
			for (int i = 0; i < 4; i++) {
				int nrow = row + dr[i];
				int ncol = col + dc[i];
				
				if (nrow < 0 || ncol < 0 || nrow >= N || ncol >= N || visited[nrow][ncol])
					continue;
				
				int diff = Math.abs(map[nrow][ncol] - map[row][col]);
				
				if (diff >= L && diff <= R) {
					visited[nrow][ncol] = true;
					sum += map[nrow][ncol];
					cnt++;
					adj.add(new int[] { nrow, ncol });
					que.add(new int[] { nrow, ncol });
				}
			}
			
		}
		
		
	}

}// class
