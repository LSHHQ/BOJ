import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());

		N  = Integer.parseInt(st.nextToken());
		M  = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		selected = new int[M];
		
		DFS(0);
		
		bw.flush();
		bw.close();
	}
	
	static int N,M;
	static int[] selected;
	static boolean[] visited;
	
	static void DFS(int count) throws IOException {
		if(count == M) {
			for (int i = 0; i < M; i++) 
				bw.write(String.valueOf(selected[i])+" ");
			bw.write("\n");
			
			return;
		}
		
		
		
		for (int i = 1; i <= N; i++) {
			if(visited[i])
				continue;
			
			visited[i] = true;
			selected[count] = i;
			DFS(count+1);
			visited[i] = false;
		}
	}
	

}
