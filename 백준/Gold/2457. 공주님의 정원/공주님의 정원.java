import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //3월 1일부터 11월 30일까지 매일 꽃이 하나 이상 피어있어야한다.
        //11월 30일에 지는 꽂은 안되는거다.
        //가능한 적은 꽃을 선택해서 조건을 만족하라.
        //조건을 만족하도록 못뽑으면 0을 출력

        //일단, 3월1일에 피어있는 꽃들 중에서 가장 늦게 지는애를 선택
        //늦게지는애가 7월5일에 진다면
        //7월5일에 피어있는 꽃 들 중에서 가장 늦게 지는애를 선택... >>반복

        //월 * 100 + 일 한걸로 저장해서 풀면 간단할듯
        // 앞에 수가 301보다 작고, 뒤에 수중에 가장 큰 꽃을 고르면 된다.

        int N = stoi(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = stoi(st.nextToken())*100 + stoi(st.nextToken());
            arr[i][1] = stoi(st.nextToken())*100 + stoi(st.nextToken());
        }

        int end = 301;
        int cnt = 0;
        boolean flag = false;
        while(true){

            int max = 0;
            int nextEnd = 0;
            flag = false;

            for (int i = 0; i < N; i++) {
                //현재 꽃이 지는 시간 보다 다음 꽃이 그 이후에 필 경우.
                if(end<arr[i][0]) continue;
                if(max < arr[i][1]-end){
                    max = arr[i][1] - end;
                    nextEnd = arr[i][1];
                    flag = true;
                }
            }

            cnt++;
            if(nextEnd>1130 || !flag) break;

            end = nextEnd;
        }

        System.out.println(flag? cnt : 0);

    }// main

}// Main