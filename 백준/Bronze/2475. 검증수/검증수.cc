#include <iostream>
using namespace std;

int main() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
        int num;
        cin >> num;
        sum += num * num;
    }

    int checkNumber = sum % 10;
    cout << checkNumber << endl;

    return 0;
}
