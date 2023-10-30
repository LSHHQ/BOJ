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
		
		int[] idx = new int[N];
		long min = Long.MAX_VALUE;
		outter: for (int i = 0; i <= N-3; i++) {
			for (int j = i+1; j <= N-2; j++) {
				
				//이분탐색 돌리기
				int left = j+1;
				int right = N-1;
				int mid = 0;
				long sum = 0;
				
				while(left<=right) {
					
					mid = (left+right)>>1;
				
					//반복문의 종료가 인풋에따라 다르게 이루어져서
					//종료시점이 최소일때 임을 보장하지 않는다.
					//내 생각엔 종료시점 mid +-1 이 최소일것같다. >>아닌걸로 확인했음.
					// 최소가 되는 시점이 마지막 부근이 아님.
					// 중간 시점에 있는 경우가 많이 있음.
					// 아래 구문이 반복문 안에 있는것은 필수적이다.
					sum = (long)arr[i]+arr[j]+arr[mid];
					long tmp = Math.abs(sum);
					if(tmp<min) {
						min = tmp;
						idx = new int[] {i,j,mid};
					}
				
					
					if(sum<0) left = mid+1;
					else if(sum>0) right = mid-1;
					else if(sum==0) {
						idx = new int[] {i,j,mid};
						break outter;
					}
				}
				
//				mid = (left+right)>>1;
//				sum = (long)arr[i]+arr[j]+arr[mid];
//				long tmp = Math.abs(sum);
//				if(tmp<min) {
//					min = tmp;
//					idx = new int[] {i,j,mid};
//				}
			} 
		}
		
		System.out.println(arr[idx[0]]+" "+arr[idx[1]]+" "+arr[idx[2]]);
		
	}
}
