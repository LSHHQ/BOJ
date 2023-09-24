import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //주어진 수
		M = Integer.parseInt(st.nextToken()); //수열 길이
		
		visited = new boolean[N];	
		permutation(0, 0);
		
	}
	
	static int N,M;
	static boolean[] visited;
	
	static void permutation(int idx,int depth) {
		if(depth==M) {
			for (int i = 0; i < N; i++) {
				if(visited[i]) {
					System.out.print(i+1+" ");
				}
			}
			System.out.println();
			return;
		}
		
		
		for (int i = idx; i < N; i++) {
			
			if(!visited[i]) {
				visited[i] = true;
				permutation(i+1, depth+1);
				visited[i] = false;
			}
		}
		
		
		
	}
	
}
