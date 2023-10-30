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
				
//				System.out.println(i+" "+j+" "+mid);
//				System.out.println(arr[i]+" "+arr[j]+" "+arr[mid]);
//				System.out.println("----------------");
				
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
			} 
		}
		
		System.out.println(arr[idx[0]]+" "+arr[idx[1]]+" "+arr[idx[2]]);
		
	}
}
