import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		int N = Integer.parseInt(br.readLine()) + 1;

		int[][] cost = new int[N][3];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}

		final int inf = 100000000;
		int ans = Integer.MAX_VALUE;
		for (int c = 0; c < 3; c++) {
			int[][] dp = new int[N][3]; // 1번을 R,G,B로 칠해놓고 i을 R,G,B로 칠할 때 의 최소비용

			for (int i = 0; i < 3; i++) dp[1][i] = inf;
			dp[1][c] = cost[1][c];

			for (int i = 2; i < N; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
			}

			for (int i = 0; i < 3; i++) {
				if (i == c) continue;
				ans = Math.min(ans, dp[N - 1][i]);
			}
		}

		System.out.println(ans);
	}

} // class
