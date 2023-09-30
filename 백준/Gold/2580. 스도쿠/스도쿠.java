import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < GRID_SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < GRID_SIZE; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solveBoard();
		printBoard();
	}// main

	static final int GRID_SIZE = 9;
	static int[][] board = new int[GRID_SIZE][GRID_SIZE];

	
	static boolean solveBoard() {
		
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				
				if(board[i][j]==0) {
					
					for (int numberToTry = 1; numberToTry <= 9; numberToTry++) {
						if(isValid(numberToTry,i,j)) {
							board[i][j]=numberToTry;
							
							if(solveBoard()) {
								return true;
							}else {
								board[i][j]=0;
							}
						}
					}
					
					return false; //완성불가
				}
			}
		}
		return true;
		
	}
	
	static boolean isValidInRowAndColumn(int num, int row, int col) {
		
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == num || board[i][col] == num) {
				return false;
			}
		}
		return true;
	}
	
	static boolean isValidInBox(int num, int row, int col) {
		row = row-row%3;
		col = col-col%3;
		for (int i = row; i < row+3; i++) {
			for (int j = col; j < col+3; j++) {
				if(board[i][j]==num) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean isValid(int num, int row, int col){
		return isValidInRowAndColumn(num, row, col) && isValidInBox(num, row, col);
	}
	
	static void printBoard() {
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}

}
