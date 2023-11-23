import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = stoi(br.readLine());

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            System.out.print(tmp[0]);
            System.out.println(tmp[tmp.length-1]);
        }


        bw.flush();
        bw.close();
    }// main

}// Main