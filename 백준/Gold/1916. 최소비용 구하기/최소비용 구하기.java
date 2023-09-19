import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		
		int N = Integer.parseInt(br.readLine()); //정점의 수
		int M = Integer.parseInt(br.readLine()); // 간선의 수
		
		List<ArrayList<int[]>> list = new ArrayList<>();
		int[] DP = new int[N+1];
		
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<int[]>());
			Arrays.fill(DP, (int)Integer.MAX_VALUE);
		}
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.get(from).add(new int[] {to, cost});
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		
		PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)-> {return a[1] - b[1] ;});
		
		PQ.add(new int[] {start, 0});
		DP[start] = 0;
		while(!PQ.isEmpty()) {
			int from = PQ.poll()[0];
			
			if(from == end)
				break;
			
			for(int[] node : list.get(from)) {
				int to = node[0];
				int cost = node[1];
				
				if(DP[from] + cost < DP[to]) {
					DP[to] = DP[from] + cost;
					PQ.add(new int[] {to, DP[to]});
				}
			}
		}
		
		System.out.println(DP[end]);
	}

}// class