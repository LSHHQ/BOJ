import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		visited = new boolean[N+1];
		sequence = new int[N+1];
		
		for (int i = 1; i < N+1; i++)
			list[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		
		for (int i = 1; i < N+1; i++)
			Collections.sort(list[i]);
		
		visited[R] = true;
		DFS(R);
		
		for (int i = 1; i < N+1; i++) 
			bw.write(String.valueOf(sequence[i])+"\n");
		
		bw.flush();
		bw.close();
	}// main
	
	static int N,M,R, count = 0; //정점, 간선, 시작정점
	static List[] list ;
	static int[] sequence;
	static boolean[] visited;
	
	static void DFS(int now) {
		sequence[now] = ++count;
		int size = list[now].size();
		
		for (int i = 0; i < size; i++) {
			int next = (int)list[now].get(i);
			
			if(visited[next])
				continue;
			visited[next] = true;
			DFS(next);
		}
	}
	
}// class



