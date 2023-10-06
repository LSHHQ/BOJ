import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W];
		PQ = new PriorityQueue<edge>((a,b)->{return a.cost-b.cost;});
		
		init();
		getEdge();
		
		p = new int[num+1];
		Arrays.fill(p, -1);
		int ans = 0;
		
//		print(map);
		while(!PQ.isEmpty()) {
			int from = PQ.peek().from;
			int to = PQ.peek().to;
			int cost = PQ.peek().cost;
			PQ.poll();
			
			
			if(union(from,to)) {
				ans+=cost;
//				System.out.println(from+" "+to+" "+cost);
			}
		}
		
		int cnt = 0 ;
		for (int i = 1; i <= num; i++) {
			find(i);
			if(p[i]==-1) {
				cnt++;
			}
		}
		
		System.out.println(cnt==1? ans : -1);
		
	} // main
	
	static int H,W, num;
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<edge> PQ;
	static int[] p;
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	static int find(int x) {
		if(p[x]==-1) return x;
		return p[x] = find(p[x]);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y) return false;
		
		p[y] = x;
		return true;
	}
	
	
     //각 행과 열에 직선을 긋고 간선의 길이를 측정한다.
	static void getEdge() {
		
		// 행 긋기
		int now = -1;
		int dis = 0;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				
				if(map[i][j]!=0) {
					
					if(now==-1) {
						now = map[i][j];
						dis = 0;
						continue;
					}
					
					if(now==map[i][j]) {
						dis = 0;
						
					}else if(now!=map[i][j]) {
						if(dis>=2) PQ.add(new edge(now,map[i][j],dis));
//						System.out.println(now+" "+map[i][j]+" "+dis);
						now = map[i][j];
						dis = 0;
					}
					
					
				}else if(map[i][j]==0) {
					dis++;
				}
			}
			now = -1;
			dis = 0;
		}
		
		// 열 긋기
		
		now = -1;
		dis = 0;
		
		
		for (int j = 0; j < W; j++) {
			
			for (int i = 0; i < H; i++) {

				if(map[i][j]!=0) {
					
					if(now==-1) {
						now = map[i][j];
						dis = 0;
						continue;
					}
					
					if(now==map[i][j]) {
						dis = 0;
						
					}else if(now!=map[i][j]) {
						if(dis>=2) PQ.add(new edge(now,map[i][j],dis));
						now = map[i][j];
						dis = 0;
					}
						
					
					
				}else if(map[i][j]==0) {
					dis++;
				}
			}
			now = -1;
			dis = 0;
		}
		
		
	}
	
	
	 // 각 섬에 번호를 마킹
	static void mark(int r, int c) {
		map[r][c] = ++num;
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {r,c});
		visited[r][c] = true;
		
		while(!que.isEmpty()) {
			int row = que.peek()[0];
			int col = que.peek()[1];
			que.poll();
			
			for (int i = 0; i < 4; i++) {
				int nrow = row+dr[i];
				int ncol = col+dc[i];
				
				if(nrow<0 || ncol<0 || nrow==H || ncol==W 
						|| visited[nrow][ncol] || map[nrow][ncol]==0 ) continue;
				
				visited[nrow][ncol] = true;
				map[nrow][ncol] = num;
				que.add(new int[] {nrow,ncol});
			}
		}
	}
	
	static void init() throws Exception {
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		num = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]==1 && !visited[i][j]) mark(i,j);
			}
		}
	}
	
	static void print(int[][] board) {
		System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.printf("%2d",board[i][j]);
			}
			System.out.println();
		}
	}
	
} // class

class edge{
	int from;
	int to;
	int cost;
	public edge(int f, int t, int c) {
		from = f;
		to = t;
		cost = c;
	}
}











