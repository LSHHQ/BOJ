import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = stoi(br.readLine());

        // f(n) = f(n-1) + f(n-2)
        long[] fibo = new long[N+1];
        fibo[0] = 0;
        fibo[1] = 1;

        // bottom-to-top
        for (int i = 2; i <= N; i++) {
            fibo[i] = fibo[i-1] + fibo[i-2];
        }

        bw.write(fibo[N]+" ");

        bw.flush();
        bw.close();
    }// main

}// Main