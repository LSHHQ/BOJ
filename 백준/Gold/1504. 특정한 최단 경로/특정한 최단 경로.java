import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 수
		int M = Integer.parseInt(st.nextToken()); // 간선의 수

		list = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<int[]>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			list.get(from).add(new int[] { to, cost });
			list.get(to).add(new int[] { from, cost });
		}

		st = new StringTokenizer(br.readLine());
		int point1 = Integer.parseInt(st.nextToken());
		int point2 = Integer.parseInt(st.nextToken());

		// 1. 1에서 시작했을 때 point1, 2 중 어디가 더 가까운지 계산
		// 2. 가까운 포인트~ 먼포인트 거리 얼만지 계산

		int[] tmp = deijkstra(1);
		if (tmp[point2] == Integer.MAX_VALUE || tmp[point1] == Integer.MAX_VALUE || tmp[N] == Integer.MAX_VALUE) {
			bw.write("-1");
			bw.flush();
			bw.close();
			return;
		}

		int routeA = 0;
		int routeB = 0;

		routeA += tmp[point1];
		routeA += deijkstra(point1)[point2];
		routeA += deijkstra(point2)[N];

		routeB += tmp[point2];
		routeB += deijkstra(point2)[point1];
		routeB += deijkstra(point1)[N];

		bw.write(String.valueOf(Math.min(routeA, routeB)));

		bw.flush();
		bw.close();
	}

	static int N;
	static List<ArrayList<int[]>> list;

	static int[] deijkstra(int start) {

		int[] cost = new int[N + 1];
		Arrays.fill(cost, (int) Integer.MAX_VALUE);

		PriorityQueue<int[]> PQ = new PriorityQueue<>((a, b) -> {
			return a[1] - b[1];
		});

		PQ.add(new int[] { start, 0 });
		cost[start] = 0;
		while (!PQ.isEmpty()) {
			int from = PQ.poll()[0];

			for (int[] node : list.get(from)) {
				int to = node[0];
				int price = node[1];

				if (cost[from] + price < cost[to]) {
					cost[to] = cost[from] + price;
					PQ.add(new int[] { to, cost[to] });
				}
			}
		}
		return cost;
	}

}// class
