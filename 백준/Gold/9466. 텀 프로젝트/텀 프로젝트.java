import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		int T = Integer.parseInt(br.readLine());
//		int T = 1;

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine()); // 학생수
			p = new int[N];

			st = new StringTokenizer(br.readLine());
			
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
			
			for (int i = 0; i < N; i++) {
				p[i] = Integer.parseInt(st.nextToken()) - 1;
//				System.out.print(p[i]+" ");
			}
			
			
			
			int count = 0;
			isCycle = new boolean[N];
			visited = new boolean[N];
			gone = new boolean[N];
			
			for (int x = 0; x < N; x++) {
				if (!isCycle[x] && !gone[x]) {
					find(x, x);
				}
				if(!isCycle[x])
					count++;
			}
			System.out.println(count);
		}

	}

	static int[] p;
	static boolean[] isCycle, visited, gone;

	static void find(int first, int x) {
		if(gone[x]) {
			del(first);
			return;
		}
		
		if (visited[x]) {
			isCycle[x] = true;
			cycle(p[x]);
			del(first);
			return;
		}

		visited[x] = true;
		find(first, p[x]);
	}
	
	static void cycle(int x) {
		if(isCycle[x]) {
			return;
		}
		
		isCycle[x] = true;
		cycle(p[x]);
	}
	
	static void del(int x) {
		if(isCycle[x] || gone[x]) {
			return;
		}
		visited[x] = false;
		gone[x] = true;
		del(p[x]);
	}


}// class
