import java.io.*;
import java.util.*;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Main {
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st ;

		
		N = stoi(br.readLine());
		energy = new int[N];
		adj = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			energy[i] = stoi(br.readLine());
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			// 0-base
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken())-1;
			int to = stoi(st.nextToken())-1;
			int cost = stoi(st.nextToken());
			adj[from].add(new Edge(to, cost));
			adj[to].add(new Edge(from, cost));
		}
		
		ans = new int[N];
		int[][] arr = new int[N][2];
		visited = new boolean[N];
		DFS(0, arr);
		
		bw.write("1\n");
		for (int i = 1; i < N; i++) {
//			System.out.println(i+1+" | "+arr[i][0] +" "+ (arr[i][1]+1));
			
			if(arr[i][0]<=energy[i]) {
				bw.write("1\n");
				continue;
			}
			
			int en = energy[i];
			int now = i;
			while(true) {
				int next = arr[now][1];			
				en -= arr[now][0] - arr[next][0];
				if(en<0) {							
					bw.write(now+1+"\n");		
					break;
				}
				now = next;					
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static int N;
	static int[] energy, ans;
	static boolean[] visited;
	static ArrayList<Edge>[] adj;
	
	static void DFS(int now, int[][] arr) {
		visited[now] = true;
		for (Edge edge : adj[now]) {
			if(visited[edge.to]) continue;
			arr[edge.to][0] = arr[now][0]+edge.cost;
			arr[edge.to][1] = now;
			DFS(edge.to,arr);
		}
	}
	
	static class Edge {
		int to;
		int cost;
		Edge(int t, int c) {to = t; cost = c;}
	}
	
}// Main
