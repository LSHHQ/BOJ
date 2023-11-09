#include<iostream>
#include<cstring>
#include<vector>
#include<algorithm>
using namespace std;

int cnt_recur = 0;
int cnt_dp = 0;

int fibo(int n) {
	if (n == 1 || n == 2) {
		cnt_recur++;
		return 1;
	}
	return fibo(n - 1) + fibo(n - 2);
}


int main() 
{
		ios::sync_with_stdio(0);
		cin.tie(0);
		cout.tie(0);

		int N;
		cin >> N;

		fibo(N);

		int dp[41];
		dp[1], dp[2] = 1;
		for (int i = 3; i <= N; i++)
		{
			cnt_dp++;
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		cout << cnt_recur << " " << cnt_dp;
		return 0;
}
