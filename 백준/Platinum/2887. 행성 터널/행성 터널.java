import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());

//		planets = new ArrayList<int[]>();
		
		int[][] X = new int[N][2];
		int[][] Y = new int[N][2];
		int[][] Z = new int[N][2];
		

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			X[i][0] = Integer.parseInt(st.nextToken());
			Y[i][0] = Integer.parseInt(st.nextToken());
			Z[i][0] = Integer.parseInt(st.nextToken());
			X[i][1] = i;
			Y[i][1] = i;
			Z[i][1] = i;
		}

		
		Arrays.sort(X,(a,b)->{ return a[0] - b[0]; });
		Arrays.sort(Y,(a,b)->{ return a[0] - b[0]; });
		Arrays.sort(Z,(a,b)->{ return a[0] - b[0]; });
		
		PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)->{return a[2]-b[2];});
		
		for (int i = 0; i < N-1; i++) {
			int cost_X = X[i+1][0]-X[i][0];
			int cost_Y = Y[i+1][0]-Y[i][0];
			int cost_Z = Z[i+1][0]-Z[i][0];
			PQ.add(new int[] {X[i][1],X[i+1][1],cost_X});
			PQ.add(new int[] {Y[i][1],Y[i+1][1],cost_Y});
			PQ.add(new int[] {Z[i][1],Z[i+1][1],cost_Z});
		}
		
		p = new int[N];
		Arrays.fill(p, -1);
		
		long total = 0;
		while(!PQ.isEmpty()) {
			int from = PQ.peek()[0];
			int to = PQ.peek()[1];
			int cost = PQ.poll()[2];
			
			if(!isUnion(from,to)) {
				total += cost;
			}
		}
		
		System.out.println(total);
	}

	static int N;
	static int[] p;
	static List<int[]> planets;

	static int getCost(int[] A, int[] B) {
		return Math.min(Math.min(Math.abs(A[0] - B[0]), Math.abs(A[1] - B[1])), Math.abs(A[2] - B[2]));
	}
	
	static int find(int x) {
		if(p[x]==-1)
			return x;
		return p[x] = find(p[x]);
	}
	
	static boolean isUnion(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y)
			return true;
		p[y] = x;
		return false;
	}

}// class