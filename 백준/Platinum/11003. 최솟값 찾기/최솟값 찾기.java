import java.io.*;
import java.util.*;

public class Main {

	static int stoi(String s) {return Integer.parseInt(s);}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
			
		st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		
		LinkedList<int[]> deq = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			int[] tmp = new int[] {stoi(st.nextToken()),i}; //밸류, 인덱스 
			// 범위 밖인 밸류 버리기
			while(!deq.isEmpty() && deq.peekFirst()[1] <= i-M) deq.pollFirst();
			// 지금 들어오는 값보다 큰 값은 어차피 들어온 값이 나갈때까지 최솟값으로 출력되지 않으니까 필요없으니까 버린다.
			while(!deq.isEmpty() && deq.peekLast()[0] > tmp[0]) deq.pollLast();
			deq.addLast(tmp);
			bw.write(String.valueOf(deq.peekFirst()[0])+" ");
		}
		
		
		bw.flush();
		bw.close();
	}// main()
	
	
	
}// Main
