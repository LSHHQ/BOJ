import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //~N까지의 번호가 주어짐
		int M = Integer.parseInt(st.nextToken()); // M개의 이중 m개의 카드를 준다
		int K = Integer.parseInt(st.nextToken()); // 철수가 내는 카드
		
		// 나 민수는 철수가 내는 카드 를 보고 내가 가진 m개의 수 중에 이보다 큰 카드를 낸다.
		// 이보다 큰 카드 중 가장 최소의 카드를 낼것이다. 내는 카드를 출력
		
		int[] input = new int[M];
		boolean[] used = new boolean[M];
		
		int Max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			Max = Math.max(input[i], Max);
		}
		
		//카운팅 정렬
		int[] myCard = new int[M];
		int[] count = new int[Max+1];
		for (int i = 0; i < M; i++) {
			count[input[i]]++;
		}
		
		for (int i = 1; i < Max+1; i++) {
			count[i] += count[i-1];
		}
		
		for (int i = M-1; i >= 0; i--) {
			myCard[--count[input[i]]] = input[i];
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {

			int number = Integer.parseInt(st.nextToken());
			
			int min = 0; 	//최소인덱스
			int max = M-1;	//최대 인덱스
			int idx = 0;
			
			while(min<=max) {
				int mid = (max+min)/2;
				int now = myCard[mid];
				
				// top to low
				if(number < now) {
					idx = mid;
					max = mid-1;
				}else {
					min = mid+1;
				}
			}
			
			if(!used[idx]) {
				used[idx] = true;
			}else {
				boolean flag = false;
				for (int j = idx+1; j < M; j++) {
					if(!used[j]) {
						idx = j;
						used[idx] = true;
						flag = true;
						break;
					}
				}
				
				if(!flag) { //더 큰애중 남은게 없으면 제일작은거 줘버리기
					for (int j = 0; j < idx; j++) {
						if(!used[j]) {
							idx = j;
							used[idx] = true;
							break;
						}
					}
				}
			}
			
			bw.write(String.valueOf(myCard[idx])+"\n");
		}
		
		bw.flush();
		bw.close();
	}

}// class