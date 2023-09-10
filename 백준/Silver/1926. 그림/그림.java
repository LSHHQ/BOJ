import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visited = new boolean[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0; // 그림 갯수
		int maxSize = 0;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]==1 && visited[i][j]==false) {
					visited[i][j]=true;
					size = 0;
					count++;
					DFS(i,j);
					maxSize = Math.max(maxSize, size);
				}
			}
		}
		
		System.out.println(count);
		System.out.println(maxSize);
		
	}
	
	static int H, W, size; //높이, 너비, 사이즈
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	
	static void DFS(int row, int col) {
		size++;
		
		for (int i = 0; i < 4; i++) {
			int nrow = row+dr[i];
			int ncol = col+dc[i];
			
			if(nrow<0 || nrow>=H || ncol<0 || ncol>=W)
				continue;
			
			if(map[nrow][ncol]==1 && visited[nrow][ncol]==false) {
				visited[nrow][ncol] = true;
				DFS(nrow,ncol);
			}
		}
	}
	
}
