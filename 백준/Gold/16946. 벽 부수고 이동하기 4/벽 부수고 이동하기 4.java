import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W][2];
		visited = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				map[i][j][0] = row[j] - '0';
				if (map[i][j][0] == 1)
					map[i][j][0] = -1;
			}
		}

		number = 1;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j][0] == 0) {
					mark = 0;
					count = 0;
					DFS_1(i, j);
					
					DFS_2(i, j);
					number++;
				}
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j][0] == -1) {
					map[i][j][1] = sum(i, j)%10;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j][0] == -1) {
					sb.append(map[i][j][1]);
				} else {
					sb.append("0");
				}
			}
			sb.append("\n");
		}
		

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int H, W, count, mark, number;
	static boolean[][] visited;
	static int[][][] map;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static void DFS_1(int row, int col) {
		visited[row][col] = true;
		count++;
		mark = Math.max(count, mark);

		for (int i = 0; i < 4; i++) {
			int nrow = row + dr[i];
			int ncol = col + dc[i];

			if (nrow < 0 || ncol < 0 || nrow == H || ncol == W || visited[nrow][ncol])
				continue;

			if (map[nrow][ncol][0] == 0) {
				DFS_1(nrow, ncol);
			}
		}
	}

	static void DFS_2(int row, int col) {
		visited[row][col] = false;
		map[row][col][0] = mark;
		map[row][col][1] = number;

		for (int i = 0; i < 4; i++) {
			int nrow = row + dr[i];
			int ncol = col + dc[i];

			if (nrow < 0 || ncol < 0 || nrow == H || ncol == W || !visited[nrow][ncol])
				continue;

			if (map[nrow][ncol][0] == 0) {
				DFS_2(nrow, ncol);
			}
		}
	}

	static int sum(int row, int col) {
		int sum = 1;

		int[] visit = new int[4];
		int size = 0;

		di: for (int i = 0; i < 4; i++) {
			int nrow = row + dr[i];
			int ncol = col + dc[i];

			if (nrow < 0 || ncol < 0 || nrow == H || ncol == W || map[nrow][ncol][0] == -1)
				continue;

			for (int j = 0; j < 4; j++) {
				if (visit[j] == map[nrow][ncol][1]) {
					continue di;
				}
			}

			visit[size++] = map[nrow][ncol][1];
			sum += map[nrow][ncol][0];
		}

		return sum;
	}

	static void print() {
		System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.printf("%2d ", map[i][j][0]);
			}
			System.out.println();
		}
		System.out.println();

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.printf("%2d ", map[i][j][1]);
			}
			System.out.println();
		}

	}

}// class