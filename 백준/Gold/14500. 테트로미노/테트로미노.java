import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int H, W, max = 0;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };
	
	static PriorityQueue<Integer> PQ ;

	public static void main(String[] args) throws Exception {

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

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				visited[i][j] = true;
				initPQ();
				DFS(i, j, 1, map[i][j]);
				visited[i][j] = false;
				if (PQ.size() >= 3) {
					int around = map[i][j]+PQ.poll()+PQ.poll()+PQ.poll();
					max = Math.max(max, around);
				}
			}
		}

		System.out.println(max);

	} // main


	static void DFS(int row, int col, int depth, int sum) {
		if (depth == 4) {
			max = Math.max(max, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nrow = row + dr[i];
			int ncol = col + dc[i];

			if (nrow < 0 || ncol < 0 || nrow >= H || ncol >= W)
				continue;

			if (!visited[nrow][ncol]) {
				visited[nrow][ncol] = true;
				DFS(nrow, ncol, depth + 1, sum + map[nrow][ncol]);
				visited[nrow][ncol] = false;
			}

			if (depth == 1) {
				PQ.add(map[nrow][ncol]);
			}

		}
	}// DFS
	
	static void initPQ() {
		PQ = new PriorityQueue<>((a, b) -> {return b - a;});
	}

}// class
