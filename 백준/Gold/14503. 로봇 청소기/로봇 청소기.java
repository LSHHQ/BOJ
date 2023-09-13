import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];

		st = new StringTokenizer(br.readLine());
		vac = new int[3];
		vac[0] = Integer.parseInt(st.nextToken());
		vac[1] = Integer.parseInt(st.nextToken());
		vac[2] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int row = vac[0];
		int col = vac[1];
		int dir = vac[2];
		map[row][col] = -1; // 청소끝 : -1
		int count = 1;

		while (true) {

			if (search(row, col)) { // 청소 할 칸이 있는 경우

				while (true) {
					dir = (dir + 3) % 4;

					int nr = row + dr[dir];
					int nc = col + dc[dir];

					if (nr < H && nr >= 0 && nc < W && nc >= 0 && map[nr][nc] == 0) {
						row = nr;
						col = nc;
						map[row][col] = -1;
						count++;
						break;
					}

				}

			} else { // 없는 경우

				int nr = row - dr[dir];
				int nc = col - dc[dir];

				if (nr < H && nr >= 0 && nc < W && nc >= 0 && map[nr][nc] != 1) {
					row = nr;
					col = nc;
				} else {
					break;
				}
			}
			
		}

		System.out.println(count);

	}// main

	static int H, W;
	static int[][] map;
	static int[] vac;
	// 0북 1동 2남 3서
	// 반시계 방향으로 90도 회전 (좌측으로)
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static boolean search(int row, int col) {

		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			if (nr < H && nr >= 0 && nc < W && nc >= 0 && map[nr][nc] == 0) {
				return true;
			} 
		}

		return false;
	}

	static void print(int[][] board) {
		System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.printf("%2d ", board[i][j]);
			}
			System.out.println();
		}
	}// print

}