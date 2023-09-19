import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		
		int N = Integer.parseInt(br.readLine()); //정점의 수
		int M = Integer.parseInt(br.readLine()); // 간선의 수
		
		List<ArrayList<int[]>> list = new ArrayList<>();
		int[] DP = new int[N+1];
		int[] route = new int[N+1];
		
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
					route[to] = from;
					PQ.add(new int[] {to, DP[to]});
				}
			}
		}
		
		
		Stack<Integer> stack = new Stack<>();
		stack.push(end);
		
		int idx = end;
		while(route[idx] != 0) {
			stack.push(route[idx]);
			idx = route[idx];
		}
		
		bw.write(String.valueOf(DP[end])+"\n");
		bw.write(String.valueOf(stack.size())+"\n");
		while(!stack.isEmpty()) {
			bw.write(String.valueOf(stack.pop())+" ");
		}
		
		bw.flush();
		bw.close();
	}

}// class