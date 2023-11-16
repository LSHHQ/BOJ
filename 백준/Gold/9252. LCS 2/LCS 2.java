import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}
    static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();


        int[][] dp = new int[A.length+1][B.length+1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if(A[i-1]==B[j-1]) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        bw.write(dp[A.length][B.length]+"\n");
        ToString(A, A.length, B.length, dp);
        bw.flush();
        bw.close();
    }// main

    static void ToString(char[] str, int i, int j, int[][] dp) throws IOException {
        Stack<Character> stk = new Stack<>();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else {
                stk.push(str[i-1]);
                i--;
                j--;
            }
        }
        while (!stk.isEmpty()) {
            bw.write(stk.pop());
        }
    }

}// Main