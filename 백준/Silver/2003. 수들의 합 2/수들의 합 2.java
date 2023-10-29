import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = stoi(st.nextToken()) + arr[i-1];

        int L=0, R=1, ans=0;

        //수를 더 늘려야 하는데 늘릴 수 없어 R이 범위를 초과하는 경우
        //수를 더 줄여야 하는데 줄일 수 없어 L이 R과 같아지는 경우
        //더이상 경우의 수가 안나온다.
        while(true) {
            int permut = arr[R] - arr[L];

            if(permut < M){ //수를 늘려야함
                R++;
            }else if(permut > M){ //수를 줄여야함
                L++;
            }else{
                ans++;
                L++;
            }

            if(R==N+1){
                R--;
                if(++L==N+1) break;
            }

        }

        System.out.println(ans);

    }// main

}// Main