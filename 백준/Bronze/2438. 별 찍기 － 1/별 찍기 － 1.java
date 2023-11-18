import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = stoi(br.readLine());

        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < cnt; j++) {
                bw.write("*");
            }
            bw.write("\n");
            cnt++;
        }

        bw.flush();
        bw.close();
    }// main

}// Main