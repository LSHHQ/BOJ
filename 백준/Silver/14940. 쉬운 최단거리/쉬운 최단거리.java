import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W];

		int[] sttPos = new int[2];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					sttPos[0] = j;
					sttPos[1] = i;
					map[i][j] = 0;
					visited[i][j] = true;
				}
			}
		}

		BFS(sttPos);

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]==1&&visited[i][j]==false) {
					map[i][j]=-1;
				}
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}

	static int H, W;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static Queue<int[]> que = new LinkedList<>();

	static void BFS(int[] pos) {
		que.add(pos);

		while (!que.isEmpty()) {
			int[] temp = que.poll();
			int x = temp[0];
			int y = temp[1];
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (nx < W && nx >= 0 && ny < H && ny >= 0 && map[ny][nx] == 1 && visited[ny][nx] == false) {
					map[ny][nx] = map[y][x] + 1;
					visited[ny][nx] = true;
					que.add(new int[] { nx, ny });
				}
			}
		}
	}

}
