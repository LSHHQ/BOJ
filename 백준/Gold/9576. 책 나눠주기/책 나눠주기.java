import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((o1,o2)->{return o1[1]-o2[1];});
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				PQ.add(new int[] {a,b});
			}
			
			int cnt = 0;
			boolean[] used = new boolean[N+1];
			//b가 작은순으로 둔 다음, a~b중 남아있는 가장 작은 수 부터 배정해준다. or a가 큰 순으로 둔 다음, a~b중 남아있는 가장 큰 수 배정
			
			while(!PQ.isEmpty()) {
				int a = PQ.peek()[0];
				int b = PQ.peek()[1];
				PQ.poll();
				
				for (int i = a; i <= b; i++) {
					if(!used[i]) {
						used[i] = true;
						cnt++;
						break;
					}
				}
			}
			
			System.out.println(cnt);
		}
		
		
	} // main
	
	
} // class