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
		
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
		selected = new int[M];
		DFS(0, 0);
		System.out.print(sb);
	}
	
	static int N,M;
	static int[] input, selected, pre;
	
	static void DFS(int idx, int count) {
		if(count==M) {
			for (int i = 0; i < M; i++) {
				sb.append(selected[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		int before = -1;
		
		for (int i = idx; i < N; i++) {
			if(input[i]==before) continue;
			selected[count]= input[i];
			before = input[i];
			DFS(i+1, count+1);
		}
		
		
	}
	
}// class
