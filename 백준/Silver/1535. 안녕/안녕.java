import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 사람의 번호가 1~ N
        // 세준이는 각 사람에게 1번만 말할 수 있음
        // i번사람에게 말하면 L[i] 체력 잃ㅇ므, J[i]의 기쁨을 얻음
        // 처음에 체력 100, 기쁨0
        // 체력이 1이상이 되게 하면서 기쁨이 최대가 되는 경우 구하기.
        // 냅색 문제, 가방에 가능한 많은 가치의 물건 넣기. 최대무게 = 99

        int N = stoi(br.readLine());
        int L = 99; //최대무게

        int[] W = new int[N+1]; //무게
        int[] V = new int[N+1]; //가치
        int[][] dp = new int[N+1][L+1]; //N,L에 가능한 최대 이익

        // 1-base
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) W[i] = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) V[i] = stoi(st.nextToken());

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                if(W[i] <= j) dp[i][j] = Math.max(dp[i-1][j-W[i]]+ V[i], dp[i-1][j]);
                else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][L]);

    }


}// Main