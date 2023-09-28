import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //동전의 종류
		int k = Integer.parseInt(st.nextToken()); //돈
		
		int[] token = new int[N];
		for (int i = 0; i < N; i++) {
			token[i] = Integer.parseInt(br.readLine().trim());
		}
		
		int[] dp = new int[k+1];
		Arrays.fill(dp, 100001);
		dp[0] = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = token[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j-token[i]]+1);
			}
		}
		
		System.out.println(dp[k]==100001 ? -1 : dp[k]);
		
	} // main
	
	

} // class











