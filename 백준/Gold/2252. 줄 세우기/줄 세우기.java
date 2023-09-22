import java.io.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] indegree = new int[N];

		List[] edge = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			edge[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			edge[from].add(to);
			indegree[to]++;
		}

		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] == 0)
				que.add(i);
		}

		while (!que.isEmpty()) {
			int poll = que.poll();
			bw.write(String.valueOf(poll + 1) + " ");
			for (int i : (ArrayList<Integer>) edge[poll]) {
				indegree[i]--;
				if (indegree[i] == 0)
					que.add(i);
			}
		}

		bw.flush();
		bw.close();
	}

}// class
