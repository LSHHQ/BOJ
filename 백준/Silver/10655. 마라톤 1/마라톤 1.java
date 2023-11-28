import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = stoi(br.readLine());

        int[][] points = new int[N][2];
        int[] sum = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = stoi(st.nextToken());
            points[i][1] = stoi(st.nextToken());
            if(i!=0) sum[i] = sum[i-1] + getDistance(points[i-1], points[i]);
        }

        // 세개의 점을 택해서 1 -> 3번점 거리 x 랑, 1 -> 2 -> 3 거리 y를 구한다.
        // x와 y의 차이가 가장 큰 점을 찾았을 때, 그 때의 2번 점을 제외한다.
        // 누적합?

        int maxDiff = 0;
        for (int i = 0; i <= N-3; i++) {
            int two = getDistance(points[i],points[i+2]); //두개짜리 선분
            int three = sum[i+2]-sum[i]; //점 세개짜리 선분
            maxDiff = Math.max(maxDiff, three - two);
        }

        System.out.println(sum[N - 1] - maxDiff);

    }// main

    static int getDistance(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

}// Main