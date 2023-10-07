import java.io.*;
import java.util.*;

public class Main {

	static int stoi(String s) {return Integer.parseInt(s);}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = stoi(br.readLine());
		int M = stoi(br.readLine());
		
		ArrayList<Edge>[] edge = new ArrayList[N];
		ArrayList<Edge>[] edge_r = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			edge[i] = new ArrayList<Edge>();
			edge_r[i] = new ArrayList<Edge>();
		}
			
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken())-1;
			int to = stoi(st.nextToken())-1;
			int cost = stoi(st.nextToken());
			edge[from].add(new Edge(to,cost));
			edge_r[to].add(new Edge(from,cost));
		}
		st = new StringTokenizer(br.readLine());
		int start = stoi(st.nextToken())-1;
		int end = stoi(st.nextToken())-1;
		// 입력 끝
		
		// 1. 최장거리 구하기
		int[] dp = new int[N];
		PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)-> b[1]-a[1]);
		PQ.add(new int[] {start,0});
		
		while(!PQ.isEmpty()) {
			int from = PQ.peek()[0];
			int expense = PQ.peek()[1];
			PQ.poll();
			
			if(expense > dp[from]) continue;
			
			for (Edge ch: edge[from] ) {
				int to = ch.to;
				int next = expense+ch.cost;
				if(dp[to] < next) {
					dp[to] = next;
					PQ.add(new int[] {to,next});
				}
			}
		}
		
		// 2. 사용한 도로 구하기
		
		int ans = 0;
		boolean[] visited = new boolean[N];
		visited[end] = true;
		Queue<Integer> que = new LinkedList<>();
		que.add(end);
		
		while(!que.isEmpty()) {
			int from = que.poll();
			
			for (Edge ch: edge_r[from] ) {
				if(dp[from]-ch.cost == dp[ch.to]) {
					ans++;
					if(visited[ch.to]) continue;
					visited[ch.to] = true;
					que.add(ch.to);
				}
			}
		}
		
		System.out.print(dp[end]+"\n"+ans);
	}// main
	

	static class Edge{
		int to;
		int cost;
		public Edge(int t, int c) {
			to = t;
			cost = c;
		}
	}
	
}
