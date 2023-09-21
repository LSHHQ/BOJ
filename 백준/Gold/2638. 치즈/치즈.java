import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		isOutsideZero = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					cheese++;
			}
		}

		checkOutside(0, 0); // 맨 처음 외부 판단하기
		
		int hours =0 ;
		while(cheese!=0) {
			mark();
//			print();
			melt();
			hours++;
		}
		
		System.out.println(hours);

	}

	static int H, W, cheese = 0;
	static int[][] map;
	static boolean[][] isOutsideZero;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static void checkOutside(int ROW, int COL) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { ROW, COL });
		isOutsideZero[ROW][COL] = true;

		while (!que.isEmpty()) {
			int row = que.peek()[0];
			int col = que.poll()[1];

			for (int i = 0; i < 4; i++) {
				int nrow = row + dr[i];
				int ncol = col + dc[i];

				if (nrow < 0 || ncol < 0 || nrow >= H || ncol >= W || isOutsideZero[nrow][ncol])
					continue;

				if (map[nrow][ncol] == 0) {
					isOutsideZero[nrow][ncol] = true;
					que.add(new int[] { nrow, ncol });
				}
			}
		}
	}

	static void mark() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {

				if (map[i][j] == 1) {
					int count = 0;// 0의 갯수를 카운트
					for (int d = 0; d < 4; d++) {
						int nrow = i + dr[d];
						int ncol = j + dc[d];

						if (map[nrow][ncol] == 0 && isOutsideZero[nrow][ncol] == true)
							count++;
					}
					if (count >= 2)
						map[i][j] = count;
				}

			}
		}
	}

	static void melt() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] >= 2) {
					map[i][j] = 0;
					cheese--;
					checkOutside(i, j);
				}
			}
		}
	}

	static void print() {
		System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.printf("%2d", map[i][j]);
			}
			System.out.println();
		}
	}

}