import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // M개의 단방향 간선
		X = Integer.parseInt(st.nextToken())-1; // 모일 마을의 번호, 0-base
		
		/*
		 * 1.정방향 간선을 활용해서
		 *   X에서 각 마을로의 최단경로를 구한다.
		 * 2.역방향 간선을 활용해서
		 *   X에서 각 마을로의 최단경로를 구한다.
		 * 3. 이떄 두 디피 배열의 합 중 최댓값을 출력한다.
		 */
		
		ArrayList[] ortho = new ArrayList[N]; 
		ArrayList[] reverse = new ArrayList[N]; 
		for (int i = 0; i < N; i++) {
			ortho[i] = new ArrayList<edge>();
			reverse[i] = new ArrayList<edge>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1; // 0-base
			int to   = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			ortho[from].add(new edge(to,cost));
			reverse[to].add(new edge(from,cost));
		}
		int[] O = dijkstra(ortho);
		int[] R = dijkstra(reverse);
		
		
		int max = 0;
		for (int i = 0; i < N; i++) max = Math.max(O[i] + R[i], max);
		
		System.out.println(max);
		
	}// main
	
	static int N,M,X;
	static final int INF = Integer.MAX_VALUE;
	
	static int[] dijkstra(ArrayList<edge>[] adj) {
		int[] dp = new int[N];
		Arrays.fill(dp, INF);
		dp[X] = 0;
		   
		PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)->{return a[1]-b[1];});
		PQ.add(new int[] {X,0});
		
		while(!PQ.isEmpty()) {
			int from = PQ.peek()[0];
			PQ.poll();
			
			for (edge e : adj[from]) {
				int to = e.to;
				int cost = e.cost + dp[from];
				if(cost < dp[to]) {
					dp[to] = cost;
					PQ.add(new int[] {to, cost});
				}
			}
		}
		
		return dp;
	}
	
	
	static class edge {
		int to;
		int cost;
		public edge(int t, int c) {
			to = t;
			cost = c;
		}
	}
	
}// class
