import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N == K) {
			System.out.println("0\n" + N);
			return;
		}

		len = Math.max(N, K) + 2;
		times = new int[len];
		parentOf = new int[len];
		BFS();

		bw.write(String.valueOf(minSec) + "\n");

		Stack<Integer> route = new Stack<Integer>();
		int idx = K;
		while (true) {
			route.push(idx);
			idx = parentOf[idx];
			if (idx == N)
				break;
		}
		route.push(N);

		while (!route.isEmpty())
			bw.write(String.valueOf(route.pop()) + " ");

		bw.flush();
		bw.close();
	}// main

	static int N, K, len;
	static int[] times;
	static int[] parentOf;
	static int[] move = { -1, 1, 2 };
	static int minSec = Integer.MAX_VALUE; // 초 .

	static void BFS() {
		Queue<Integer> que = new LinkedList<>();
		que.add(N);
		times[N] = 1; // 처음에 1초로 시작
		parentOf[N] = -1; // 뿌리는 -1로 표시

		while (!que.isEmpty()) {
			int now = que.poll();

			for (int i = 0; i <= 2; i++) {
				int next = now;
				if (i <= 1)
					next += move[i];
				else
					next *= move[i];

				if (next < 0 || next >= len || times[next] != 0)
					continue;

				times[next] = times[now] + 1;
				parentOf[next] = now;
				que.add(next);

				if (next == K) {
					minSec = times[now];
					return;
				}

			}

		} // while

	}// BFS()

}// class
