import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점
		int M = Integer.parseInt(st.nextToken()); // 간선
		
		ArrayList<Integer>[] edge = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			edge[i] = new ArrayList<Integer>();
		}
		
		int[] indeg = new int[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			indeg[to]++;
			edge[from].add(to);
		}
		
		
		PriorityQueue<Integer> PQ = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			if(indeg[i]==0) PQ.add(i);
		}

		while(!PQ.isEmpty()) {
			int vertex = PQ.poll();
			bw.write(String.valueOf(vertex+1)+" ");
			for (int i : edge[vertex]) {
				if(--indeg[i]==0) PQ.add(i);
			}
		}

		bw.flush();
		bw.close();
	}

}// class
