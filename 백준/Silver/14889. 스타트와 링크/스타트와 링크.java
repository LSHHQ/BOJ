import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		
		N = Integer.parseInt(br.readLine());
		adj = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) adj[i][j] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[N];
		ans = Integer.MAX_VALUE;
		func(0,0);
		
		System.out.println(ans);
		
	} // main
	
	static int N, ans;
	static int[][] adj;
	static boolean[] visited;
	
	
	static void func(int idx, int count) {
		if(count==N/2) {
			
			List<Integer> selec = new ArrayList<>();
			List<Integer> notsel = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				if(visited[i]) selec.add(i);
				else notsel.add(i);
			}
			
			ans = Math.min(ans,getDiff(selec, notsel));
			
			if(ans==0) {
				System.out.println(0);
				System.exit(0);
			}
			
			return;
		}
		
		if(idx==N) return;
		
		
		visited[idx] = true;
		func(idx+1, count+1);
		visited[idx] = false;
		func(idx+1,count);
	}
	
	static int getDiff(List<Integer> selec, List<Integer> notsel) {
		int sum1 = 0;
		int sum2 = 0;
		
		for (int i = 0; i < N/2; i++) {
			for (int j = i+1; j < N/2; j++) {
				int A = selec.get(i);
				int B = selec.get(j);
				int a = notsel.get(i);
				int b = notsel.get(j);
				sum1+=adj[A][B]+adj[B][A];
				sum2+=adj[a][b]+adj[b][a];
			}
		}
		
		return Math.abs(sum1-sum2);
	}
	
	
} // class