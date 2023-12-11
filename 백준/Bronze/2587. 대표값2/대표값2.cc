#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int numbers[5];
    int sum = 0;
    
    for (int i = 0; i < 5; i++) {
        cin >> numbers[i];
        sum += numbers[i];
    }

    int average = sum / 5;

    sort(numbers, numbers + 5);

    int median = numbers[2];

    cout << average << endl;
    cout << median << endl;

    return 0;
}
