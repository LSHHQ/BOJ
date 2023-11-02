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
			while(!deq.isEmpty() && deq.peekFirst()[1] <= i-M) deq.pollFirst();
			while(!deq.isEmpty() && deq.peekLast()[0] > tmp[0]) deq.pollLast();
			deq.addLast(tmp);
			bw.write(deq.peekFirst()[0]+" ");
		}
		
		
		bw.flush();
		bw.close();
	}// main()
	
	
	
}// Main
