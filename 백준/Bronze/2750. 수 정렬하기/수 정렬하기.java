import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = stoi(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(br.readLine());
        }

        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            bw.write(arr[i]+"\n");
        }

        bw.flush();
        bw.close();
    }// main

}// Main