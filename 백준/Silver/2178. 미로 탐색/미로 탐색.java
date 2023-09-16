
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W];
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < H; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				map[i][j] = temp[j] - '0';
			}
		}

		visited[0][0] = true;
		System.out.println(BFS(0, 0));

		br.close();
	}

	public static int H, W;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static int BFS(int x, int y) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { x, y });

		while (!que.isEmpty()) {
			int[] temp = que.poll();
			int ox = temp[0];
			int oy = temp[1];
			int nx, ny;
			for (int idx = 0; idx < 4; idx++) {
				nx = ox + dx[idx];
				ny = oy + dy[idx];
				if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] != 0 && visited[nx][ny] == false) {
					visited[nx][ny] = true;
					map[nx][ny] = map[ox][oy] + 1;
					que.add(new int[] { nx, ny });
					if (nx == H - 1 && ny == W - 1)
						return map[nx][ny];
				}
			}
		}
		return 0;
	}

}