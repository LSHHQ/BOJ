import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] stair = new int[N];
        int[][] dp = new int[N][2];
        //dp 배열에 해당 계단을 밟기까지 점수 몇을 얻을 수 있는지와
        //2번째 밟아서 계산된 값인지, 1번밟아서 계산된 값인지 알 수 있어야 한다.

        for (int i = 0; i < N; i++)
            stair[i] = stoi(br.readLine());

        if(N==1) {
            System.out.println(stair[0]);
            return;
        }

        dp[0][0] = stair[0];
        dp[0][1] = stair[0];
        dp[1][0] = stair[1];
        dp[1][1] = dp[0][0]+stair[1];

        for (int i = 2; i < N; i++) {
            dp[i][0] = Math.max(dp[i-2][0],dp[i-2][1])+stair[i];
            dp[i][1] = dp[i-1][0]+stair[i];
        }


        System.out.println(Math.max(dp[N-1][0],dp[N-1][1]));

    }// main

    static int stoi(String s){return Integer.parseInt(s);}

}// Main