import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = stoi(br.readLine());
		
		//배열에서 3개의 값을 뽑아내고 이 합이 0에 가장 가까울 때 사용한 배열 값을 오름차순으로 출력
		
		//앞에서 두개 먼저 뽑고
		//세번째 수를 이분탐색으로 조정한다.
		//가장 가까운수를 구함
		//>>N^2/4 logN
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = stoi(st.nextToken());
		Arrays.sort(arr);
		
		int[] idx = new int[3];
		long min = Long.MAX_VALUE;
		int F = -1; //first index
		int L = 0, R = N-1;
		
		outter:while(F++ < N-3) {
			L = F+1;
			R = N-1;
			
			//투 포인터
			while(L!=R) {
				
				long sum = (long)arr[F]+arr[L]+arr[R];
				
				if(Math.abs(sum)<min) {
					min = Math.abs(sum);
					idx = new int[] {F, L, R};
				}
				
				if(sum<0) L++;
				else if(sum>0) R--;
				else {
					idx = new int[] {F, L, R};
					break outter;
				}
				
			}
		}
		
		System.out.println(arr[idx[0]]+" "+arr[idx[1]]+" "+arr[idx[2]]);
		
		
		
	}
}
