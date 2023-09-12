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

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 1. 구역을 탐색하면 새로운 값으로 바꾸기.
		// 적록구역은 합쳐버리고, 블루구역은 따로 구분할 수 있게

		// 2. visited만들어서 탐색한 곳을 값을 바꾸지 않기
		// 각 색상마다 카운트 변수를 둬서 카운트하기
		// BFS한번 돌려서 전체 구역 카운트 한 다음
		// R, G 인 곳을 N으로 만들고 해당부분만 visited초기화시켜서
		// 다시 BFS돌리기

		// 2로 간다

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j])
					BFS(i, j, map[i][j]);
				if (map[i][j] == 'R' || map[i][j] == 'G') {
					visited[i][j] = false;
					map[i][j] = 'N';
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j])
					BFS(i, j, map[i][j]);
			}
		}

		System.out.println(areaR+areaG+areaB+" "+(areaB+areaRG));

	}

	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dc = { 1, -1, 0, 0 };
	static int[] dr = { 0, 0, 1, -1 };
	static int areaR = 0, areaG = 0, areaB = 0, areaRG = 0;

	static void BFS(int r, int c, char RGB) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { r, c });
		visited[r][c] = true;

		if (RGB == 'R')
			areaR++;
		else if (RGB == 'G')
			areaG++;
		else if (RGB == 'B')
			areaB++;
		else if (RGB == 'N')
			areaRG++;

		while (!que.isEmpty()) {
			int[] pos = que.poll();
			int oldRow = pos[0];
			int oldCol = pos[1];
			for (int dir = 0; dir < 4; dir++) {
				if (oldRow + dr[dir] >= 0 && oldRow + dr[dir] < N && oldCol + dc[dir] >= 0 && oldCol + dc[dir] < N
						&& visited[oldRow + dr[dir]][oldCol + dc[dir]] == false
						&& map[oldRow + dr[dir]][oldCol + dc[dir]] == RGB) {
					int newRow = oldRow + dr[dir];
					int newCol = oldCol + dc[dir];
					visited[newRow][newCol] = true;
					que.add(new int[] { newRow, newCol });
				}
			}
		}

	}

}
