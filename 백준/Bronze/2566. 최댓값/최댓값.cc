#include <iostream>
using namespace std;

int main() {
    int grid[9][9];
    int max_value = -1;  // 최댓값을 -1로 초기화하여 모든 값이 0인 경우도 처리
    int row = 0, col = 0;

    // 9x9 격자판 읽기
    for (int i = 0; i < 9; ++i) {
        for (int j = 0; j < 9; ++j) {
            cin >> grid[i][j];
            // 현재 값이 최댓값보다 큰지 확인
            if (grid[i][j] > max_value) {
                max_value = grid[i][j];
                row = i + 1; // 1-based 인덱싱으로 조정
                col = j + 1; // 1-based 인덱싱으로 조정
            }
        }
    }

    // 최댓값과 그 위치 출력
    cout << max_value << endl;
    cout << row << " " << col << endl;

    return 0;
}
