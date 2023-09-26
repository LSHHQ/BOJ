import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] edge = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edge[a][b] = true;
			edge[b][a] = true;
		}
		
		int min = Integer.MAX_VALUE;
		int ans = -1;
		
		for (int i = 0; i < N; i++) {
			
			boolean[] visited = new boolean[N];
			Queue<int[]> que = new LinkedList<>();
			que.add(new int[] {i,0});
			visited[i] = true;
			
			int sum = 0;
			while(!que.isEmpty()) {
				int now = que.peek()[0];
				int cnt = que.poll()[1];
				sum += cnt;
				
				if(sum>=min) break;
				for (int next = 0; next < N; next++) {
					if(edge[now][next] && !visited[next]) {
						visited[next] = true;
						que.add(new int[] {next, cnt+1});
					}
				}
			}
			
			if(sum<min) {
				min = sum;
				ans = i+1;
			}
		}
		
		System.out.println(ans);
	}
	

} // class
