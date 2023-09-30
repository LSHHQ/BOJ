import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

//		int T = Integer.parseInt(br.readLine());

//		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine()); // N*N보드에 N개의 퀸.
			visited = new int[N][N];
			count = 0;
			
			Solve(0);

//			sb.append("#" + tc + " ");
			sb.append(String.valueOf(count));
//		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int N;
	static int[][] visited;
	static int count;
	
	static void Solve(int row) {
		if(row==N) {
			count++;
			return;
		}
		
		for (int j = 0; j < N; j++) {
			if(visited[row][j]==0) {
				Mark(row,j);
				Solve(row+1);
				removeMark(row,j);
			}
		}
	}

	// 퀸이 놓일 떄, 다른 퀸이 놓아질 수 없는 자리를 true로 바꾼 방문배열을 반환
	static void Mark(int row, int col) {
		
		for (int i = 0; i < N; i++) {
			visited[row][i] ++;
			visited[i][col] ++;

			if (row + i < N && col + i < N)
				visited[row + i][col + i] ++;
			
			if (row + i < N && col - i >= 0)
				visited[row + i][col - i] ++;
			
			if (row - i >= 0 && col + i < N)
				visited[row - i][col + i] ++;
			
			if (row - i >= 0 && col - i >= 0)
				visited[row - i][col - i] ++;
		}
	}
	
	static void removeMark(int row, int col){
		
		for (int i = 0; i < N; i++) {
			visited[row][i] -- ;
			visited[i][col] -- ;

			if (row + i < N && col + i < N)
				visited[row + i][col + i] --;
			
			if (row + i < N && col - i >= 0)
				visited[row + i][col - i] --;
			
			if (row - i >= 0 && col + i < N)
				visited[row - i][col + i] --;
			
			if (row - i >= 0 && col - i >= 0)
				visited[row - i][col - i] --;
		}
	}

} // class
