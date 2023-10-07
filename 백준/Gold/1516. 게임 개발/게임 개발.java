import java.io.*;
import java.util.*;

public class Main {

	static int stoi(String s) {return Integer.parseInt(s);}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		// 각 건물이 완성되기 까지 걸리는 최소시간....
		// 한 건물이 지어지기 전에 지어져아할 건물을 부모이다.
		// 각 노드(건물)에 지어지기 까지의 최소시간을 저장한다.
		// 노드의 부모가 여럿이라면 부모 중 가장 시간이 오래걸리는 건물이 지어지기 전에 걸리는 시간이다.
		
		int[] indeg = new int[N];
		node = new Node[N];
		for (int i = 0; i < N; i++) node[i] = new Node(-1);			
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			node[i].time = stoi(st.nextToken());
			while(true) {
				int num = stoi(st.nextToken())-1;
				if(num==-2) break;
				node[num].child.add(i);
				indeg[i]++;
			}
		}
		
		Queue<Node> que = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if(indeg[i]==0) que.add(node[i]);
		}
		
		while(!que.isEmpty()) {
			Node par = que.poll();
			par.parentTime += par.time;
			
			for (int c : par.child ) {
				Node chi = node[c];
				chi.parentTime=Math.max(chi.parentTime, par.parentTime);
				if(--indeg[c]==0) que.add(node[c]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			bw.write(node[i].parentTime+"\n");
		}
		bw.flush();
		bw.close();
		
	}// main
	
	static Node[] node;
	
	static class Node{
		int time;
		int parentTime = 0;
		List<Integer> child;
		
		public Node(int t) {
			time = t;
			child = new ArrayList<>();
		}
	}
	
}
