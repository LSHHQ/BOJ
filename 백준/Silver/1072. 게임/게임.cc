#include<iostream>
#include<cstring>
#include<vector>
#include<algorithm>
using namespace std;


int main() 
{
		ios::sync_with_stdio(0);
		cin.tie(0);
		cout.tie(0);

		long long X, Y;

		cin >>  X  >>  Y;
		int Z = Y * 100 /  X;

		if(Z >= 99)
		{
			cout <<-1 ;
			return 0;
		}

		//최소 이겨야하는 횟수 & 최대 이겨야하는 횟수
		long left = 0;
		//long right = X;
		long right = 1000000000;
		//변하지 않는 최댓값 + 1
		//upper-bound
		while (left <= right)
		{
			int mid = (right + left) / 2 ;
			int val = (Y + mid) * 100 / (X + mid);

			if (val > Z) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}

		cout <<  left;
}
