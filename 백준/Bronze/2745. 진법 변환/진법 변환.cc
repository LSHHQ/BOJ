#include <iostream>
#include <string>
#include <cmath> 

using namespace std;

int main() {
    string N; 
    int B;   
    cin >> N >> B;

    long long result = 0; 
    int length = N.length(); 

    for (int i = 0; i < length; i++) {
        int digitValue;
        if (N[i] >= '0' && N[i] <= '9') {
            digitValue = N[i] - '0';
        } else {
            digitValue = N[i] - 'A' + 10; 
        }

        result += digitValue * pow(B, length - i - 1);
    }

    cout << result ;

    return 0;
}
