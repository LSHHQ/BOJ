import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    // N은 N보다 작거나 같은 제곱수들의 합으로 나타낼 수 있다.
    // 이러한 표현을 사용할 때, N에대한 최소 항의 개수를 출력

    // f(1) = 1
    // f(2) = 2
    // f(3) = 3
    // f(4) = 1
    // f(5) = 2
    // f(6) = 3
    // f(7) = 4
    // f(8) = 2
    // f(9) = 1
    // f(10) = 2;

    // 일단 최악의 경우 N -> N개 임
    // 어떤 수의 제곱은 1이고  어떤 수의 제곱의 2배일때는 2이고
    // 어떤 수의 제곱의 3배일때는 3이겟고, 아무것도 아닐때는 그보다
    // 아래에 있는 숫자의 +1,  되는 값중 최소값?

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = stoi(br.readLine());

        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i-1]+1; //최악의 경우

            for (int j = 1; j*j <= i; j++) {
                if(dp[i] > dp[i-j*j]+1) dp[i] = dp[i-j*j]+1;
            }
        }

        System.out.println(dp[N]);
    }// main

}// Main