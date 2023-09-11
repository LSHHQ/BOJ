import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[][] firstBoard = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				firstBoard[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(firstBoard, 5);
		System.out.println(max);

	}// main

	static int N;
	static int max= 0;

	static void DFS(int[][] board, int moves) {
		if (moves == 0) {
			max = Math.max(getMax(board), max);
			 return;
		}


		for (int i = 0; i < 4; i++) {
			int[][] newBoard = copyBoard(board); //반복문 바깥에 두면 안됨
			int[][] result = direction(newBoard, i);
			DFS(result, moves - 1);
		}

	}

	static int getMax(int[][] board) { // 보드에서 가장 큰 블록을 반환
		int maxNum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				maxNum = Math.max(maxNum, board[i][j]);
			}
		}
		return maxNum;
	}

	static int[][] direction(int[][] board, int i) {
		if (i == 0)
			return toRight(board);
		else if (i == 1)
			return toLeft(board);
		else if (i == 2)
			return toTop(board);
		else
			return toDown(board);
	}

	static int[][] toRight(int[][] board) {

		for (int j = N - 1; j >= 0; j--) {
			for (int i = 0; i < N; i++) {

				if (board[i][j] == 0) {
					for (int k = j - 1; k >= 0; k--) {
						if (board[i][k] != 0) {
							board[i][j] = board[i][k];
							board[i][k] = 0;
							break;
						}
					}
				}

				if (board[i][j] != 0) {
					for (int k = j - 1; k >= 0; k--) {
						if (board[i][k] != 0 && board[i][j] != board[i][k]) {
							break;
						}

						if (board[i][j] == board[i][k]) {
							board[i][j] *= 2;
							board[i][k] = 0;
							max = Math.max(board[i][j], max);
							break;
						}
					}
				}

			}
		}
		return board;
	} // toRight

	static int[][] toLeft(int[][] board) {
		
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {

				if (board[i][j] == 0) {
					for (int k = j + 1; k < N; k++) {
						if (board[i][k] != 0) {
							board[i][j] = board[i][k];
							board[i][k] = 0;
							break;
						}
					}
				}

				if (board[i][j] != 0) {
					for (int k = j + 1; k < N; k++) {
						if (board[i][k] != 0 && board[i][j] != board[i][k]) {
							break;
						}

						if (board[i][j] == board[i][k]) {
							board[i][j] *= 2;
							board[i][k] = 0;
							max = Math.max(board[i][j], max);
							break;
						}
					}
				}

			}
		}
		return board;
	} // toLeft

	static int[][] toTop(int[][] board) {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (board[i][j] == 0) {
					for (int k = i + 1; k < N; k++) {
						if (board[k][j] != 0) {
							board[i][j] = board[k][j];
							board[k][j] = 0;
							break;
						}
					}
				}

				if (board[i][j] != 0) {
					for (int k = i + 1; k < N; k++) {
						if (board[k][j] != 0 && board[i][j] != board[k][j]) {
							break;
						}

						if (board[i][j] == board[k][j]) {
							board[i][j] *= 2;
							board[k][j] = 0;
							max = Math.max(board[i][j], max);
							break;
						}
					}
				}

			}
		}
		return board;
	} // toTop

	static int[][] toDown(int[][] board) {
		
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {

				if (board[i][j] == 0) {
					for (int k = i - 1; k >= 0; k--) {
						if (board[k][j] != 0) {
							board[i][j] = board[k][j];
							board[k][j] = 0;
							break;
						}
					}
				}

				if (board[i][j] != 0) {
					for (int k = i - 1; k >= 0; k--) {
						if (board[k][j] != 0 && board[i][j] != board[k][j]) {
							break;
						}

						if (board[i][j] == board[k][j]) {
							board[i][j] *= 2;
							board[k][j] = 0;
							max = Math.max(board[i][j], max);
							break;
						}
					}
				}

			}
		}
		return board;
	} // toDown

	static int[][] copyBoard(int[][] source) {
		int[][] copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(source[i], N);
		}
		return copy;
	}

}// class
