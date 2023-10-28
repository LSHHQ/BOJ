import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken()); //수열 길이
        int K = stoi(st.nextToken()); // 삭제 가능 횟수
        boolean[] isEven = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if(stoi(st.nextToken())%2==0) isEven[i] = true;
        }
        //K=0 일경우 반례가 있다. 아직 안잡음
        //그림 그려가면서 코드를 더 깊이 이해하기.
        int left = 0, right = 0, del = 0, ans = 0;
        while (right!=N) {
            if(isEven[right]) {
                right++;
            }else{
                if(del<K) {
                    del++;
                    right++;
                }else{
                    //if(del==K)
                    while(left<=right && isEven[left++]);
                    right++;
                }
            }
            ans = Math.max(right - left - del,ans);
        }
        System.out.println(ans);
    }// main

}// Main