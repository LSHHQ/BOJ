import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //보석의 수
		int K = Integer.parseInt(st.nextToken()); //가방의 수
		
		int[][] jewel = new int[N+1][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // 보석 무게
			int v = Integer.parseInt(st.nextToken()); // 보석 가치
			jewel[i][0] = w;
			jewel[i][1] = v;
		}
		jewel[N][0] = Integer.MAX_VALUE; //예외처리*
		
		int[] bag = new int[K];
		boolean[] used = new boolean[K];
		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(jewel,(a,b)->{return a[0]-b[0];});
		Arrays.sort(bag);
		//입력 끝
		
		/*
		<그리디>
		가방과 보석을 무게순으로 오름차순한다.
		작은 가방부터 넣을 수 있는 보석을 다 넣어본다. 
		다 넣고 나서 가장 가치가 높은 보석을 넣기로 한다.
		 */
		
		PriorityQueue<Integer> PQ = new PriorityQueue<>((a,b)->{return b-a;});
		
		long ans = 0;
		int idx = 0;
		for (int i = 0; i < K; i++) {
			
			while(true) {
				if(jewel[idx][0] <= bag[i])	PQ.add(jewel[idx++][1]);
				else break;
			}
			
			if(!PQ.isEmpty()) {
				ans+=PQ.poll();
			}
		}
		
		System.out.println(ans);
		
	} // main

} // class