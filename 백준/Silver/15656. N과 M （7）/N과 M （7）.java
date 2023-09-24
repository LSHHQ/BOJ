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
		DFS(0);
		System.out.print(sb);
	}
	
	static int N,M;
	static int[] input, selected;
	
	static void DFS(int count) {
		if(count==M) {
			for (int i = 0; i < M; i++) {
				sb.append(selected[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			selected[count]= input[i];
			DFS(count+1);
		}
		
		
	}
	
}// class
