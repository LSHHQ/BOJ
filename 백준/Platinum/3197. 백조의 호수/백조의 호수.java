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

		lake = new char[H][W];
		isWater = new boolean[H][W];
		visited = new boolean[H][W];

		int idx = 0;
		for (int i = 0; i < H; ++i) {
			lake[i] = br.readLine().toCharArray();
			for (int j = 0; j < W; ++j) {
				if (lake[i][j] == 'L') {
					if (++idx == 1)
						swan1 = new Point(i, j);
					else
						swan2 = new Point(i, j);
					lake[i][j] = '.';
				}
				if (lake[i][j] == '.') {
					isWater[i][j] = true;
					melt_next.add(new Point(i, j));
				}
			}
		}

		swan_next.add(swan1);
		visited[swan1.row][swan1.col] = true;

		int days = 0;
		while(!visited[swan2.row][swan2.col]) {
			
			swan_now.addAll(swan_next);
			swan_next.clear();
			melt_now.addAll(melt_next);
			melt_next.clear();

			while (!melt_now.isEmpty()) {

				Point now = melt_now.poll();
				lake[now.row][now.col] = '.';

				for (int i = 0; i < 4; ++i) {
					Point next = now.add(delta[i]);
					if (next.isOut() || isWater[next.row][next.col])
						continue;

					isWater[next.row][next.col] = true;
					melt_next.add(next); // 현재 큐가 아닌 다음 녹을 큐에 넣어줌
				}
			}

			while (!swan_now.isEmpty()) {

				Point now = swan_now.poll();

				for (int i = 0; i < 4; ++i) {
					Point next = now.add(delta[i]);

					if (next.isOut() || visited[next.row][next.col])
						continue;

					visited[next.row][next.col] = true;
					if (lake[next.row][next.col] == '.')
						swan_now.add(next);
					if (lake[next.row][next.col] == 'X')
						swan_next.add(next);
				}
			}
			
			days++;
		}

		System.out.println(days - 1);
	}

	static int H, W;

	static Point swan1, swan2;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static char[][] lake;
	static boolean[][] visited;
	static boolean[][] isWater;

	static Queue<Point> swan_now = new LinkedList<>();
	static Queue<Point> swan_next = new LinkedList<>();
	static Queue<Point> melt_now = new LinkedList<>();
	static Queue<Point> melt_next = new LinkedList<>();

	static class Point {
		int row, col;

		Point() {
		}

		Point(int r, int c) {
			row = r;
			col = c;
		}

		boolean isOut() {
			return row < 0 || col < 0 || row >= H || col >= W;
		}

		Point add(int[] other) {
			return new Point(row + other[0], col + other[1]);
		}
	}
}
