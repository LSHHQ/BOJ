import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		while (true) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if (N + M == 0)
				break;

			st = new StringTokenizer(br.readLine());
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			edge = new int[N][N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edge[from][to] = cost;
			}

			route = new ArrayList[N];
			Arrays.fill(route, new ArrayList<>());
			
			dijkstra(stt);
			removeEdge(stt, end);
			dijkstra(stt);
			System.out.println(expense[end]==inf? -1 : expense[end]);
		}
	}

	static final int inf = Integer.MAX_VALUE;
	static int N;
	static int[] expense;
	static int[][] edge;
	static List<Integer>[] route;
	
	static void removeEdge(int stt, int end) {
		if(stt==end) return;
		int size = route[end].size();
		for (int i = 0 ; i<size; i++) {
			int fr = route[end].get(i);
			if(edge[fr][end]==0) continue;
			edge[fr][end]=0;
			removeEdge(stt,fr);
		}
	}

	static void dijkstra(int start) {
		expense = new int[N];
		Arrays.fill(expense, inf);
		expense[start] = 0;

//		PriorityQueue<int[]> PQ = new PriorityQueue<>((a, b) -> {return a[1] - b[1];});
		Queue<int[]> PQ = new LinkedList<>();
		PQ.add(new int[] { start, 0 });

		while (!PQ.isEmpty()) {
			int from = PQ.peek()[0];
			int cost = PQ.peek()[1];
			PQ.poll();

			for (int to = 0; to < N; to++) {
				if (edge[from][to] == 0) continue;
				int sum = edge[from][to] + cost;
				if (sum < expense[to]) {
					expense[to] = sum;
					route[to] = new ArrayList<>();
					route[to].add(from);
					PQ.add(new int[] { to, sum });
				} else if (sum == expense[to]) {
					route[to].add(from);
				}
			}
		}
	}

}// class
