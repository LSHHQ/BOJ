import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 정수의 갯수
		
		PriorityQueue<Integer> SPQ = new PriorityQueue<>((a,b)->{return b-a;});
		PriorityQueue<Integer> LPQ = new PriorityQueue<>((a,b)->{return a-b;});

		// s피크가 l피크보다 작거나 같아야함
		
		// s사이즈와 l사이즈 차이를 1 이내로 유지한다.
		// 사이즈가 더 큰 큐의 피크가 중간값이다.
		// 사이즈가 같을때에는 sQ의 피크가 중간값이다.
		// 새로운 값이 들어오면 중간값과 비교하고 이보다 작으면 sQ로 크면 lQ로 넣는다
		
		int A = Integer.parseInt(br.readLine());
		
		bw.write(String.valueOf(A)+"\n");
		
		int B = Integer.parseInt(br.readLine());
		SPQ.add(Math.min(A, B));
		LPQ.add(Math.max(A, B));
		
		int mid = 0;
		for (int i = 0; i < N-2; i++) {
			int num = Integer.parseInt(br.readLine());
			
			
			int s = SPQ.size();
			int l = LPQ.size();
//			System.out.println(SPQ.peek()+" "+LPQ.peek());
//			System.out.println(s+" "+l);
//			System.out.println();
			
			// 중간값이 어떤건지 찾기
			if(s>=l) mid = SPQ.peek();
			else mid = LPQ.peek();
			
			bw.write(String.valueOf(mid)+"\n"); //출력
			
			// 중간값이랑 새로운 값이랑 비교해서 큐에 넣어주기
			
			if(num > mid) LPQ.add(num);

			else if(num < mid) SPQ.add(num);
			
			else if(num == mid) {
				if(s>l) LPQ.add(num);
				else if(s<=l) SPQ.add(num);
			}
			
			
			s = SPQ.size();
			l = LPQ.size();
			if(l>s) SPQ.add(LPQ.poll());
			
			s = SPQ.size();
			l = LPQ.size();
			if(s>l+1) LPQ.add(SPQ.poll());
		}
		
		
		int s = SPQ.size();
		int l = LPQ.size();
		
		
		// 중간값이 어떤건지 찾기
		if(s>=l) mid = SPQ.peek();
		else mid = LPQ.peek();
		bw.write(String.valueOf(mid));
//		System.out.println(SPQ.peek()+" "+LPQ.peek());
//		System.out.println(s+" "+l);
//		System.out.println();
		
		
		bw.flush();
		bw.close();
	}// main
	
}// class
