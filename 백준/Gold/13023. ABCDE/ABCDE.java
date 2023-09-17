import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new List[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++)
			list[i] = new ArrayList<Integer>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[p].add(c);
			list[c].add(p);
		}
		
		for (int i = 0; i < N; i++) {
			if(!flag)DFS(i,0);
		}
		
		System.out.println(flag? 1 : 0);
	}

	
	static int N, M;
	static boolean[] visited;
	static List[] list;
	static boolean flag = false;
	
	static void DFS(int nodeNum, int count) {
		if(count==5 || flag) {
			flag = true;
			return;
		}

		int size = list[nodeNum].size();
		for (int i = 0; i < size; i++) {
			int childNum = (int)list[nodeNum].get(i);
			
			if(visited[childNum])
				continue;
			
			visited[childNum] = true;
			DFS(childNum, count+1);
			visited[childNum] = false;
		}
	}
}