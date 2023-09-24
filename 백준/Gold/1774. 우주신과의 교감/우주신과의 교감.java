import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 우주신 갯수 (황선자 제외)
		M = Integer.parseInt(st.nextToken()); // 이미 연결된 우주신과의 통로 갯수
		

		pts = new Point[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pts[i] = new Point(x,y);
		}
		
		PriorityQueue<edge> PQ = new PriorityQueue<Main.edge>((a,b)->{
			double c = a.cost - b.cost;
			return (c > 0.0 ? 1 : -1);
		});
		
		//이미 연결된 좌표 cost = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			PQ.add(new edge(from,to, 0));
		}
		
		for (int from = 0; from < N; from++) {
			for (int to = from+1; to < N; to++) {
				PQ.add(new edge(from,to));
			}
		}
		
		p = new int[N];
		Arrays.fill(p, -1);
		
		double total = 0.0;
		while(!PQ.isEmpty()) {
			int from = PQ.peek().from;
			int to = PQ.peek().to;
			double cost = PQ.poll().cost;
			
			//union 성공하면 true, 이미 연합이면 false
			if(union(from, to)) {
				total += cost;
			}
		}
		
		System.out.printf("%.2f",total);
		
	}
	
	static int N, M ;
	static int[] p;
	static Point[] pts;
	
	static int find(int x) {
		if(p[x] == -1) return x;
		return p[x] = find(p[x]);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y) return false;
		
		p[y] = x;
		return true;
	}
	
	static class Point{
		int x;
		int y;
		public Point(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
	
	static class edge{
		int from;
		int to;
		double cost = 0.0;
		
		public edge(int f, int t, int c) {
			from = f;
			to = t;
			cost = 0.0;
		}
		
		public edge(int f, int t) {
			from = f;
			to = t;
			cost = Math.sqrt(Math.pow(pts[f].x-pts[t].x,2) + Math.pow(pts[f].y-pts[t].y,2) );
		}
	}
	
	
	
}// class
