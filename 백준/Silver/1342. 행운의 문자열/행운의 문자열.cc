#include<iostream>
#include<cstring>
#include<vector>
#include<algorithm>
using namespace std;

int strLen, ans;
int arr['z' - 'a' + 1];
int len = 'z' - 'a' + 1;

void func(int depth, int pre) {
	if (depth == strLen)
	{
		ans++;
		return;
	}

	for (int i = 0; i < len; i++)
	{
		if (arr[i] == 0 || i == pre) continue;
		arr[i]--;
		func(depth + 1, i);
		arr[i]++;
	}
}

int main() {

	string str;
	cin >> str;
	 strLen = str.length();

	for (int i = 0; i < strLen ; i++)
	{
		arr[str[i] - 'a']++;
	}

	func(0, -1);

	cout << ans;
}

