#include <iostream>
#include <cmath> // 절대값을 구하기 위한 헤더 파일

using namespace std;

int main() {
    long long N, M; // N과 M을 long long 타입으로 선언

    // 두 도메인의 유명도 입력 받기
    cin >> N >> M;

    // 절대 차이를 계산하고 출력
    cout << abs(N - M) << endl;

    return 0;
}
