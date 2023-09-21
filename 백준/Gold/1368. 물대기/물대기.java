import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		V = Integer.parseInt(br.readLine())+1; // 정점의 수

		price = new int[V];

		for (int i = 1; i < V; i++)
			price[i] = Integer.parseInt(br.readLine()); // i번째 우물을 파는데 드는 비용

		PriorityQueue<edge> PQ = new PriorityQueue<>((a, b) -> {
			return a.cost - b.cost;
		});

		for (int from = 1; from < V; from++) {
			st = new StringTokenizer(br.readLine());
			PQ.add(new edge(0,from, price[from]));
			
			for (int i = 1; i <= from; i++) {
				st.nextToken();
			}
				
			for (int to = from+1; to < V; to++) {
				int cost = Integer.parseInt(st.nextToken());
				PQ.add(new edge(from,to,cost));
			}
		}

		p = new int[V];
		Arrays.fill(p, -1);
		
		int total = 0;
		while (!PQ.isEmpty()) {
			int from = PQ.peek().from;
			int to = PQ.peek().to;
			int cost = PQ.peek().cost;
			PQ.poll();

			if (!isUnion(from, to)) {
				total += cost;
			}
		}

		System.out.println(total);

	}

	static int V;
	static int[] p, price;

	static int find(int x) {
		if (p[x] == -1)
			return x;
		return p[x] = find(p[x]);
	}

	// 이미 연합이면 true를 출력, 아니면 연합해주고 false 를 출력
	static boolean isUnion(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return true;
		
		if(x > y) {
			p[x] = y;
		}else {
			p[y] = x;
		}

		return false;
	}

	static class edge {
		int from;
		int to;
		int cost;

		public edge(int f, int t, int c) {
			from = f;
			to = t;
			cost = c;
		}
	}
}// class