import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = init(st.nextToken()); // 맵 크기, N*N;
		M = init(st.nextToken()); // 남겨 둘 치킨집 수

		House = new ArrayList<>();
		Chicken = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = init(st.nextToken());
				if(num==1) {
					House.add(new int[] {i,j});
				}else if(num==2) {
					Chicken.add(new int[] {i,j});
				}
			}
		}
		hsize = House.size();
		csize = Chicken.size();
		visited = new boolean[csize];
		selected = new int[M][2];
		DFS(0,0);
		
		System.out.println(minCity);

		bw.flush();
		bw.close();
	}// main

	static int N, M;

	static List<int[]> House;
	static List<int[]> Chicken;
	static int hsize ;
	static int csize ;
	
	static boolean[] visited;
	static int[][] selected;
	static int minCity = Integer.MAX_VALUE;
	// 치킨집 m개 combination.
	
	
	
	static void DFS(int IDX, int depth) {
		if(depth==M) {
			int sum = 0;
			
			for (int i = 0; i < hsize; i++) {
				
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					min =  Math.min(min,getDistance(House.get(i), selected[j]));
				}
				sum += min;
			}
			minCity = Math.min(sum, minCity);
			return;
		}
		
		
		for (int i = IDX; i < csize; i++) {
			if(!visited[i]) {
				visited[i] =true;
				selected[depth] = Chicken.get(i); 
				DFS(i+1, depth+1);
				visited[i] = false;
			}
		}
		
	}
	
	

	static int getDistance(int[] pos1, int[] pos2) {
		return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
	}

	static int init(String s) {
		return Integer.parseInt(s);
	}

}// class
