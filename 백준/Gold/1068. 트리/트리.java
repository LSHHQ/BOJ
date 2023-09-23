import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());
		edge = new boolean[N][N];

		st = new StringTokenizer(br.readLine());
		int root = 0;
		for (int to = 0; to < N; to++) {
			int from = Integer.parseInt(st.nextToken());
			if (from == -1)
				root = to;
			else
				edge[from][to] = true;
		}

		int del = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			edge[i][del] = false;
		}
		
		if(del==root) {
			System.out.println(0);
			return;
		}
		count = 0;
		DFS(root);
		System.out.println(count);
	}

	static int N, M, count;
	static boolean[][] edge;
	
	static void DFS(int stt){
		
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			if(edge[stt][i]) {
				DFS(i);
				flag = true;
			}
		}
		
		if(!flag)
			count++;
	}

}// class
