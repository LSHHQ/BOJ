import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 연산 수
		Command = new int[K][3];

		Map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int rad = Integer.parseInt(st.nextToken());
			Command[i][0] = row;
			Command[i][1] = col;
			Command[i][2] = rad;
		}

		int[] selected = new int[K];
		boolean[] visited = new boolean[K];
		Permutation(visited, selected, 0);
		System.out.print(min);

	}// main

	static int[][] Map;
	static int[][] Command;
	static int H, W, K;

	// 우 상 좌 하
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int min = Integer.MAX_VALUE;

	static void Permutation(boolean[] visited, int[] selected, int count) {

		if (count == K) {
			int[][] map = copyMap(Map);
			
			
			for (int i = 0; i < K; i++) {
				int num = selected[i];
				Rotate(Command[num], map);
			}
			min = Math.min(getMinRow(map), min);

			return;
		}

		for (int i = 0; i < K; i++) {

			if (!visited[i]) {
				visited[i] = true;
				selected[count] = i;
				Permutation(visited, selected, count + 1);
				visited[i] = false;
			}

		}

	}

	static void Rotate(int[] command, int[][] map) {
		int centRow = command[0];
		int centCol = command[1];
		int R = command[2];

		for (int r = R; r >= 1; r--) {

			// R+1 * 4
			LinkedList<Integer> list = new LinkedList<>();

			int row = centRow + r;
			int col = centCol - r;

			int dir = 0;
			boolean flag = false;
			while (true) {
				int nrow = row + dr[dir];
				int ncol = col + dc[dir];

				list.add(map[nrow][ncol]);

				if (ncol == centCol + r)
					dir = 1;

				if (nrow == centRow - r) {
					dir = 2;
					flag = true;
				}

				if (ncol == centCol - r)
					dir = 3;

				if (nrow == centRow + r && flag)
					break;

				row = nrow;
				col = ncol;
			}

			list.addLast(list.pollFirst());

			row = centRow + r;
			col = centCol - r;
			dir = 0;
			flag = false;
			while (!list.isEmpty()) {

				int nrow = row + dr[dir];
				int ncol = col + dc[dir];

				map[nrow][ncol] = list.pollFirst();

				if (ncol == centCol + r)
					dir = 1;

				if (nrow == centRow - r) {
					dir = 2;
					flag = true;
				}

				if (ncol == centCol - r)
					dir = 3;

				if (nrow == centRow + r && flag)
					break;

				row = nrow;
				col = ncol;
			}
		}

	}

	static int getMinRow(int[][] map) {
		int ans = Integer.MAX_VALUE;
		int H = map.length;
		int W = map[0].length;
		for (int i = 0; i < H; i++) {
			int sum = 0;
			for (int j = 0; j < W; j++) {
				sum += map[i][j];
			}
			ans = Math.min(sum, ans);
		}
		return ans;
	}

	static int[][] copyMap(int[][] map) {
		int[][] copy = new int[H][W];
		for (int i = 0; i < H; i++) {
			copy[i] = Arrays.copyOf(map[i], W);
		}
		return copy;
	}

	static boolean[] copyArr(boolean[] visited) {
		boolean[] copy = new boolean[K];
		copy =Arrays.copyOf(visited, K);
		return copy;
	}

	static int[] copyArr(int[] visited) {
		int[] copy = new int[K];
		copy = Arrays.copyOf(visited, K);
		return copy;
	}

}// class