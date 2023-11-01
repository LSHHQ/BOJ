#include <stdio.h>
#include <iostream>
#include <vector>
#include <stack>


using namespace std;


bool visited[101];
vector<vector<int>> adj(101);

int main()
{

	int N, M;

	cin >> N >> M;

	for (int i = 0; i < M; i++)
	{
		int from, to;
		cin >> from >> to;
		adj[from].push_back(to);
		adj[to].push_back(from);
	}

	stack<int> stk;
	stk.push(1);

	int count = 0;
	while (!stk.empty()) {
		int now = stk.top();
		stk.pop();
		visited[now] = true;

		for ( int next : adj[now])
		{
			if (!visited[next]) 
			{
				visited[next] = true;
				stk.push(next);
				count++;
			}
		}
	}

	cout << count;

}