import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int M = stoi(st.nextToken()); // 집의 수
            int N = stoi(st.nextToken()); // 길의 수
            if(M*N==0) break;

            PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)->a[2]-b[2]);

            int sum = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int from = stoi(st.nextToken());
                int to = stoi(st.nextToken());
                int cost = stoi(st.nextToken());
                sum+=cost;
                PQ.add(new int[] {from, to, cost});
            }

            p = new int[M];
            Arrays.fill(p, -1);

            int expense = 0;
            while (!PQ.isEmpty()) {
                int[] now = PQ.poll();
                int x = now[0];
                int y = now[1];
                if(unionize(x,y)) expense+=now[2];
            }

            System.out.println(sum-expense);
        }

    }// main

    static int[] p;

    static int find(int x){
        if(p[x]==-1) return x;
        return p[x] = find(p[x]);
    }

    //완료하면 true, 이미 돼있어서 실행안하면 false
    static boolean unionize(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y) return false;
        p[y] = x;
        return true;
    }


}// Main