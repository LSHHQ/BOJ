import java.io.*;
import java.util.*;

public class Main {
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		S = stoi(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i]  = stoi(st.nextToken());
		
		// 배열 둘로 나누고, 조합을 통해 나올 수 있는 모든 수열의 합 경우의 수를 저장한다
		// 정렬한다음 투포인터로 답을 구한다
		left = new ArrayList<Integer>();
		right = new ArrayList<Integer>();
		
		makeSumCase(0, 0, N/2, left);
		makeSumCase(0, N/2, N, right);
		Collections.sort(left);
		Collections.sort(right);
		
		int lenL = left.size();
		int lenR = right.size();
		int L = 0;
		int R = lenR-1;
		long cnt = 0;
		while(true) {
			if(L==lenL || R ==-1) break;
			int valL = left.get(L);
			int valR = right.get(R);
			
			if(valL+valR==S) {

				long cntL = 1;
				while(L<lenL-1 && left.get(L+1)==valL) {
					cntL++;
					L++;
				}
				
				long cntR = 1;
				while(R>0 && right.get(R-1)==valR) {
					cntR++;
					R--;
				}
				cnt += cntL*cntR;
				L++;
				R--;
			}
			else if(valL+valR<S) L++;
			else if(valL+valR>S) R--;
		}
		// 두 리스트 모두 아무것도 선택 안했을때로, 0인 경우를 가지고 있기 때문에 하나의 리스트에서 S가 나오는 경우도 반영이 된다.
		// S==0인 경우를 찾을 경우에는, 두 리스트 모두 아무것도 선택 안했을 때 의 경우가 하나 추가돼서 카운트 되니까 -1 해줘야된다.
		System.out.println(S==0? --cnt : cnt);
	}
	
	static int N, S;
	static int[] arr;
	static List<Integer> left, right;
	
	static void makeSumCase(int sum, int idx, int end, List<Integer> list) {
		if(idx==end) {
			list.add(sum);
			return;
		}
		makeSumCase(sum, idx+1, end, list); // 현재 인덱스 선택하지 않는 경우의 수
		makeSumCase(sum+arr[idx], idx+1, end, list); //현재 인덱스 선택해 집합에 포함하는 경우
	}
	
	
}// Main
