import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = stoi(br.readLine()); // 가지고 있는 숫자의 수, 중복 수 없음
        int[] have = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            have[i] = stoi(st.nextToken());
        }

        Arrays.sort(have);

        // 가지고 있는 카드인지 아닌지 판별할 숫자의 갯수
        int M = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {

            int number = stoi(st.nextToken());

            int left = 0;
            int right = N-1;

            boolean flag = false;
            while(left<=right){
                int mid = left+right >> 1;

                if(have[mid]==number){
                    flag = true;
                    break;
                }else if(number<have[mid]){
                    right = mid-1;
                }else if(number>have[mid]){
                    left  = mid+1;
                }
            }

            bw.write(flag? "1 ": "0 ");


        }

        bw.flush();
        bw.close();



    }// main

}// Main