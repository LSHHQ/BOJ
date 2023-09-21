import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		V = Integer.parseInt(br.readLine()); //정점의 수
		
		double[][] pos = new double[V][2];
		
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			pos[i][0] = x;
			pos[i][1] = y;
		}
		
		PriorityQueue<edge> PQ = new PriorityQueue<>( (a,b)-> {return Double.compare(a.cost, b.cost);});
		for (int i = 0; i < V; i++) {
			for (int j = i+1; j < V; j++) {
				double cost = getCost(pos[i], pos[j]);
				PQ.add(new edge(i,j, cost));
			}
		}
		
		
		double total = 0;
		p = new int[V];
		Arrays.fill(p, -1);
		
		while(!PQ.isEmpty()) {
			int from = PQ.peek().from;
			int to = PQ.peek().to;
			double cost = PQ.peek().cost;
			PQ.poll();
			
			if(!isUnion(from,to)) {
				total+=cost;
			}
		}
		
		System.out.println(total);
	}
	
	static int V;
	static int[] p;
	
	static int find(int x) {
		if(p[x]==-1)
			return x;
		return p[x] = find(p[x]);
	}
	
	//이미 연합이면 true를 출력, 아니면 연합해주고 false 를 출력
	static boolean isUnion(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y)
			return true;
		p[y] = x;
		return false;
	}
	
	static double getCost(double[] A, double[] B) {
		return Math.sqrt( Math.pow(A[0]-B[0],2) + Math.pow(A[1]-B[1], 2) );
	}
	
	static class edge{
		int from;
		int to;
		double cost;
		public edge(int f,int t, double c) {
			from = f;
			to = t;
			cost = c;
		}
		
	}

}// class
