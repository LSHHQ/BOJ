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
		boolean flag = true;
		while(!visited[swan2.row][swan2.col]) {
			
			Queue<Point> meltA = flag ? melt_now : melt_next;
			Queue<Point> meltB = !flag ? melt_now : melt_next;
			Queue<Point> swanA = flag ? swan_now : swan_next;
			Queue<Point> swanB = !flag ? swan_now : swan_next;
			
			while (!meltB.isEmpty()) {

				Point now = meltB.poll();
				lake[now.row][now.col] = '.';

				for (int i = 0; i < 4; ++i) {
					Point next = now.sum(delta[i]);
					
					if (next.isOut() || isWater[next.row][next.col])
						continue;
					isWater[next.row][next.col] = true;
					meltA.add(next); // 현재 큐가 아닌 다음 녹을 큐에 넣어줌
				}
			}

			while (!swanB.isEmpty()) {

				Point now = swanB.poll();

				for (int i = 0; i < 4; ++i) {
					Point next = now.sum(delta[i]);

					if (next.isOut() || visited[next.row][next.col])
						continue;
					visited[next.row][next.col] = true;
					if (lake[next.row][next.col] == '.')
						swanB.add(next);
					if (lake[next.row][next.col] == 'X')
						swanA.add(next);
				}
			}
			
			flag = !flag;
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

		Point(int r, int c) {
			row = r;
			col = c;
		}

		boolean isOut() {
			return row < 0 || col < 0 || row >= H || col >= W;
		}

		Point sum(int[] delta) {
			return new Point(row + delta[0], col + delta[1]);
		}
	}
}
