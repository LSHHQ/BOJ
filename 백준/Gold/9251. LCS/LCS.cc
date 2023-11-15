#include<iostream>
#include<string>
#include<algorithm>
using namespace std;

int dp[1001][1001];
int main()
{
		ios::sync_with_stdio(0);
		cin.tie(0);
		cout.tie(0);
	
		string N, M;
		cin >> N >> M;

		int n = N.length();
		int m = M.length();
		
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= m ; j++)
			{
				if (N[i-1] == M[j-1]) dp[i][j] = dp[i - 1][j - 1] + 1;
				else dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
			}
		}

		cout << dp[N.length()][M.length()];

		return 0;
}