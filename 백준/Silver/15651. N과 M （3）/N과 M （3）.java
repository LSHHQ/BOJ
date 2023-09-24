import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		selected = new int[M];
		DFS(0);
		System.out.print(sb);
	}
	
	static int N,M;
	static int[] selected;
	
	static void DFS(int count) {
		if(count==M) {
			for (int i = 0; i < M; i++) {
				sb.append(selected[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			selected[count]= i;
			DFS(count+1);
		}
		
		
	}
	
}// class
