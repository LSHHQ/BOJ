import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		possible = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				board[i][j] = board[i][j] == 0 ? -99 : 0; // 놓을 수 있는 곳은 0 안되면 -99
				if (board[i][j] == 0)
					possible[i]++;
			}
		}
		
		int ans = 0;
		BT(0, 0, 0);
		ans += max;
		max = 0;
		BT(0, 1, 0);
		ans += max;

		System.out.println(ans);

	}// main

	static int N;
	static int[] possible;
	static int[][] board;
	static int max = Integer.MIN_VALUE;

	// 비숍을 놓을 수 있는 곳은 0이고 이외의 다른 수가 들어있으면 무조건 놓지 못한다.
	static void BT(int row, int col, int count) {

		if (col >= N) {
			col = col % 2 == 0 ? 1 : 0;
			row = row + 1;
			if (row == N) {
				max = Math.max(max, count);
				return;
			}
		}

		if (board[row][col] == 0) {
			mark(row, col);
			BT(row, col + 2, count + 1); // 선택0
			removeMark(row, col);
		}

		BT(row, col + 2, count); // 선택x
	}

	// 아래쪽만 마킹하기. 해당 위치에 비숍을 넣음으로서 아래쪽 대각이 0에서 벗어나는 양수를 가지게 된다.
	static void mark(int row, int col) {

		board[row][col] += 50;
		for (int i = 1; i < N; i++) {
			if (row + i >= N)
				continue;

			if (col + i < N) {
				board[row + i][col + i]++;
			}

			if (col - i >= 0) {
				board[row + i][col - i]++;
			}
		}
	}

	static void removeMark(int row, int col) {

		board[row][col] -= 50;
		for (int i = 1; i < N; i++) {
			if (row + i >= N)
				continue;
			if (col + i < N)
				board[row + i][col + i]--;
			if (col - i >= 0)
				board[row + i][col - i]--;
		}
	}

	static void print(int[][] board) {
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.printf("%3d", board[i][j]);
			}
			System.out.println();
		}
	}

}// class