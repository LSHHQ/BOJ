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
		int[] arr = new int[N+1];
		int[] dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for (int i = 1; i <= N; i++) {
			arr[i] = stoi(st.nextToken());
			
			for (int j = 0; j < i; j++) {
				if(arr[j]<arr[i] && dp[j]>=dp[i]) {
					dp[i] = dp[j]+1;
					max = Math.max(dp[i], max);
				}
			}
		}
		
		System.out.println(max);
		
	}// main()
}// Main
