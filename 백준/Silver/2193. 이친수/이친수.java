import java.io.*;
import java.util.*;

public class Main {

	static int stoi(String s) {return Integer.parseInt(s);}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		int N = stoi(br.readLine());
		
		//앞에 수가 0일때 0과 1 모두 올 수 있고
		//앞에 수가 1일때 0만 올 수 있다.

		long[] dp = new long[N+1];
		
		dp[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		System.out.println(dp[N]);
			
	}// main()
	
}// Main
