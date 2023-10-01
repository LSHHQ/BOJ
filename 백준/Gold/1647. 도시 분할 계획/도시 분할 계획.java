import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 최소신장트리 알고리즘을 이용하면 N-1개의 간선만을 이용해 모두 인접한 집합을 이룬다
		// 이 때 가장 큰 비용이 드는 간선을 제거하면 두개의 집단으로 나누어지며 두개의 집합이 이뤄지게 된다.
		
		PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)->{return a[2]-b[2];});
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			PQ.add(new int[] {from, to, cost});
		}
		
		p = new int[N];
		Arrays.fill(p, -1);
		
		int expense = 0;
		int cnt = 0;
		while(cnt!=N-2) {
			int from = PQ.peek()[0];
			int to = PQ.peek()[1];
			int cost = PQ.peek()[2];
			PQ.poll();
			
			if(canUnion(from,to)) {
				expense+=cost;
				cnt++;
			}
		}
		
		System.out.println(expense);
		
	} // main
	
	static int N, M;
	static int[] p;
	
	static int find(int x) {
		if(p[x]==-1) return x;
		return p[x] = find(p[x]);
	}
	
	static boolean canUnion(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return false;
		p[y] = x;
		return true;
	}
	
} // class