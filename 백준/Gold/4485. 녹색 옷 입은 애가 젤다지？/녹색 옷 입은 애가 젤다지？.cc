#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main()
{
		ios::sync_with_stdio(0);
		cin.tie(0);
		cout.tie(0);

        int seq = 1;
        while (true) {
            int N;
            cin >> N;
            if (N == 0) return 0;

            vector<vector<int>> map(N, vector<int>(N));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cin >> map[i][j];
                }
            }

            //grater : 오름차순 정렬.
            priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> PQ;
            PQ.push({ 0, 0, map[0][0] });
            vector<vector<int>> dp(N, vector<int>(N, INT32_MAX));

            vector<int> dr = { 1, -1, 0, 0 };
            vector<int> dc = { 0, 0, 1, -1 };
            while (!PQ.empty()) {
                vector<int> node = PQ.top();
                PQ.pop();
                int row = node[0];
                int col = node[1];
                int cost = node[2];

                for (int i = 0; i < 4; i++) {
                    int nrow = row + dr[i];
                    int ncol = col + dc[i];
                    if (nrow < 0 || ncol < 0 || nrow == N || ncol == N) continue;

                    if (cost + map[nrow][ncol] < dp[nrow][ncol]) {
                        dp[nrow][ncol] = cost + map[nrow][ncol];
                        PQ.push({ nrow, ncol, dp[nrow][ncol] });
                    }
                }
            }

            cout << "Problem " << seq++ << ": " << dp[N - 1][N - 1] << "\n";
        }

}