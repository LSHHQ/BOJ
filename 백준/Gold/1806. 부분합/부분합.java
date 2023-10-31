import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int S = stoi(st.nextToken());
        int[] arr = new int[N+1];

        //연속된 수들의 부분합 중 합이 S이상이 되면서, 가장 짧은것의 길이를 구해 출력
        // 1. 세그먼트 트리도 가능할듯, 트리의 원소 값이 S 이상일때 가장 작은 단위에서 검사하는 방식으로.
        // 2. NlogN짜리 풀이, 앞에부터 하나씩 시작될 원소를 정하고 범위를 이분탐색으로 구해서 최소범위 구하는방식
        //    >> 각 원소의 값이 모두 양수이기 때문에 가능한 방식

        //누적합으로 만들어야함
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = stoi(st.nextToken()) + arr[i - 1];
        }

        if(arr[N]<S){
            System.out.println(0);
            return;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {

            if(arr[N]-arr[i]<S) break;

            int left = i+1;
            int right = N;
            int idx = 0;
            //lower-bound
            while(left<=right){
                int mid = (left+right)>>1;
                int value = arr[mid] - arr[i];

                if(value < S){
                    left = mid+1;
                }else{
                    //if(value>=S)
                    idx = mid;
                    right = mid-1;
                }
            }

            ans = Math.min(idx-i,ans);
        }
        System.out.println(ans);

    }// main

}// Main