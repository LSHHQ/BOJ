import java.io.*;
import java.util.*;

class Info {
	char[][] map;
	int[] posRed;
	int[] posBlue;
	boolean flagRed = true;
	boolean flagBlue = true;

	public Info(char[][] m, int[] pr, int[] pb) {
		map = m;
		posRed = pr;
		posBlue = pb;
	}

	public Info(Info other) { // 복사 생성자
		map = new char[other.map.length][other.map[0].length];
		for (int i = 0; i < other.map.length; i++) {
			map[i] = Arrays.copyOf(other.map[i], other.map[i].length);
		}
		posRed = Arrays.copyOf(other.posRed, 2);
		posBlue = Arrays.copyOf(other.posBlue, 2);
		flagRed = other.flagRed;
		flagBlue = other.flagBlue;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		char[][] Map = new char[H][W];

		int[] Red = new int[2];
		int[] Blue = new int[2];

		for (int i = 0; i < H; i++) {
			String row = br.readLine();
			for (int j = 0; j < W; j++) {
				Map[i][j] = row.charAt(j);
				if (Map[i][j] == 'R') {
					Red[0] = i;
					Red[1] = j;
				} else if (Map[i][j] == 'B') {
					Blue[0] = i;
					Blue[1] = j;
				}
			}
		}

		Info first = new Info(Map, Red, Blue);

		BFS(first);
		
		if(ans==0)
			ans = -1;
		System.out.println(flag ? ans : "-1");

	}// main

	static int H, W;
	static int[][][][] visited = new int[10][10][10][10]; // 레드 row,col / 블루 row,col
	static int ans;
	static boolean flag = true;

	// 0:우, 1:좌, 2:하, 3:상
	static int[] deltaRow = { 0, 0, 1, -1 };
	static int[] deltaCol = { 1, -1, 0, 0 };

	static void BFS(Info firstInfo) {

		Queue<Info> que = new LinkedList<>();

		que.add(firstInfo);
		int[] pr = firstInfo.posRed;
		int[] pb = firstInfo.posBlue;
		visited[pr[0]][pr[1]][pb[0]][pb[1]] = 1;

		while (!que.isEmpty()) {

			Info old = new Info(que.poll());
			int[] or = old.posRed;
			int[] ob = old.posBlue;
//			print(old.map);
//			System.out.printf("%d %d %d %d   %d\n", or[0], or[1], ob[0], ob[1], visited[or[0]][or[1]][ob[0]][ob[1]]);

			if (old.flagRed == false && old.flagBlue == true) {
				ans = visited[or[0]][or[1]][ob[0]][ob[1]] - 1;
				return;
			}

			if (visited[or[0]][or[1]][ob[0]][ob[1]] == 11) {
				flag = false;
				return;
			}

			for (int i = 0; i < 4; i++) {
				char dir = '0';
				if (i == 0) {
					dir = 'R';
				} else if (i == 1) {
					dir = 'L';
				} else if (i == 2) {
					dir = 'U';
				} else if (i == 3) {
					dir = 'D';
				}
				Info next = toDirection(old, dir);
				int[] nr = next.posRed;
				int[] nb = next.posBlue;

				if (next.flagBlue == true && visited[nr[0]][nr[1]][nb[0]][nb[1]] == 0) {
					visited[nr[0]][nr[1]][nb[0]][nb[1]] = visited[or[0]][or[1]][ob[0]][ob[1]] + 1;


					if (next.flagRed == false) {
//						print(next.map);
//						System.out.printf("%d %d %d %d   %d\n",or[0],or[1],ob[0],ob[1], visited[or[0]][or[1]][ob[0]][ob[1]]);
						ans = visited[nr[0]][nr[1]][nb[0]][nb[1]] - 1;
						return;
					}

					que.add(next);
				}
			}

		}

	}

	static Info toDirection(Info old, char direction) {

		Info infoNew = new Info(old);

		int[] posR = infoNew.posRed;
		int[] posB = infoNew.posBlue;

		int rowR = posR[0];
		int colR = posR[1];
		int rowB = posB[0];
		int colB = posB[1];

		if (direction == 'R') {
			if (colR > colB) {
				moveColor(infoNew, direction, 'R');
				moveColor(infoNew, direction, 'B');
			} else {
				moveColor(infoNew, direction, 'B');
				moveColor(infoNew, direction, 'R');
			}
		} else if (direction == 'L') {
			if (colR < colB) {
				moveColor(infoNew, direction, 'R');
				moveColor(infoNew, direction, 'B');
			} else {
				moveColor(infoNew, direction, 'B');
				moveColor(infoNew, direction, 'R');
			}
		} else if (direction == 'U') {
			if (rowR > rowB) {
				moveColor(infoNew, direction, 'B');
				moveColor(infoNew, direction, 'R');
			} else {
				moveColor(infoNew, direction, 'R');
				moveColor(infoNew, direction, 'B');
			}
		} else if (direction == 'D') {
			if (rowR < rowB) {
				moveColor(infoNew, direction, 'B');
				moveColor(infoNew, direction, 'R');
			} else {
				moveColor(infoNew, direction, 'R');
				moveColor(infoNew, direction, 'B');
			}
		}

		return infoNew;
	}

	static void moveColor(Info infoNew, char direction, char color) {
		int dir = -1;

		if (direction == 'R') {
			dir = 0;
		} else if (direction == 'L') {
			dir = 1;
		} else if (direction == 'U') {
			dir = 3;
		} else if (direction == 'D') {
			dir = 2;
		}

		int dr = deltaRow[dir];
		int dc = deltaCol[dir];

		char[][] map = infoNew.map;

		int row = 0;
		int col = 0;
		if (color == 'R') {
			row = infoNew.posRed[0];
			col = infoNew.posRed[1];
		} else {
			row = infoNew.posBlue[0];
			col = infoNew.posBlue[1];
		}

		map[row][col] = '.';

		while (true) {
			row += dr;
			col += dc;

			if (map[row][col] == 'O') {
				if (color == 'R') {
					infoNew.posRed[0] = row;
					infoNew.posRed[1] = col;
					infoNew.flagRed = false;
				} else if (color == 'B') {
					infoNew.posBlue[0] = row;
					infoNew.posBlue[1] = col;
					infoNew.flagBlue = false;
				}
				return;
			}

			if (map[row][col] != '.') {
				map[row - dr][col - dc] = color;

				if (color == 'R') {
					infoNew.posRed[0] = row - dr;
					infoNew.posRed[1] = col - dc;
				} else if (color == 'B') {
					infoNew.posBlue[0] = row - dr;
					infoNew.posBlue[1] = col - dc;
				}
				return;
			}
		}

	} // move

	static void print(char[][] board) {
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.printf("%2c ", board[i][j]);
			}
			System.out.println();
		}
	}// print

}
