import java.io.*;
import java.util.*;

/*
	1을 세우는건 모든 경우의 수를 따진다.
	BFS로 2의 영역을 늘린다
	0갯수를 계산하고 최댓값을 갱신한다
 */


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int[][] map = new int[H][W];
		List<int[]> list = new ArrayList<>();//0이 존재하는 위치 저장 >> 1로 만들 후보군
				
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) {
					list.add(new int[] {i,j});
				}
			}
		} 
		
		search(map,list);
		
		System.out.println(safeMax);

	}// main
	
	static int H,W;
	static boolean[][] visited;  // 초기화 주의
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int safeMax = 0;
	
	static void search(int[][] src, List<int[]> list) {

		int size = list.size();
		int[] first = new int[2]; // 첫번째 1위치
		int[] second = new int[2]; // 두번째 1위치
		int[] third = new int[2];
		
		for (int i = 0; i < size; i++) {
			
			first = list.get(i);
			
			for (int j = i+1; j < size; j++) {
				
				second = list.get(j);
				
				for (int k = j+1; k < size; k++) {
					
					third = list.get(k);
					
					int[][] map = copy(src);
					
					map[first[0]][first[1]] = 1;
					map[second[0]][second[1]] = 1;
					map[third[0]][third[1]] = 1;
					
					BFS(map);
					
					safeMax = Math.max(safeMax,getZero(map));
					
				}
			}
		}
		
		
	}
	
	
	static void BFS(int[][] map){
		visited = new boolean[H][W];
		
		Queue<int[]> que= new LinkedList<>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]==2) {
					que.add(new int[] {i,j});
					visited[i][j] = true;
				}
			}
		}
		
		while(!que.isEmpty()) {
			int[] old = que.poll();
			int or = old[0];
			int oc = old[1];
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = or+dr[dir];
				int nc = oc+dc[dir];
				
				if(nr>=0 && nr<H && nc<W && nc>=0) {
				}else {
					continue;
				}
				
				if(map[nr][nc]==0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					map[nr][nc] = 2;
					que.add(new int[] {nr,nc});
				}
			}
			
		}
		
		
	}
	
	static int getZero(int[][] map) {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]==0) {
					count++;
				}
			}
		}
		return count;
	}
	
	static int[][] copy(int[][] src) {
		int[][] copy = new int[H][W];
		for (int i = 0; i < H; i++) {
			copy[i] = Arrays.copyOf(src[i], W);
		}
		return copy;
	}

	static void print(int[][] board) {
		System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.printf("%2d ", board[i][j]);
			}
			System.out.println();
		}
	}// print

}