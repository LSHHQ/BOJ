import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
			
		int T  = Integer.parseInt(br.readLine()); 
		int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
		int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
		
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine()); //체스판 크기 
			
			int[] now = new int[2];
			int[] des = new int[2];
			
			st = new StringTokenizer(br.readLine());
			now[0] = Integer.parseInt(st.nextToken());
			now[1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			des[0] = Integer.parseInt(st.nextToken());
			des[1] = Integer.parseInt(st.nextToken());
			
			Queue<int[]> que = new LinkedList<int[]>();
			que.add(now);
			int[][] map = new int[N][N];
			map[now[0]][now[1]] = 1;
			while(!que.isEmpty()) {
				int row = que.peek()[0];
				int col = que.poll()[1];
				
				for (int i = 0; i < 8; i++) {
					int nrow = row+dr[i];
					int ncol = col+dc[i];
					
					if(nrow<0 || ncol<0 || nrow>=N || ncol >=N
							|| map[nrow][ncol]!=0)
						continue;
					
					map[nrow][ncol] = map[row][col]+1;
					que.add(new int[] {nrow, ncol});
					if(nrow==des[0] && ncol==des[1])
						break;
				}
			}
			
			sb.append(map[des[0]][des[1]]-1+"\n");
		}
		
		System.out.println(sb);
	}
	
	
}// class
