import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken()); // 강의동 수
        int M = stoi(st.nextToken()); // 공사구간 수
        long K = Long.parseLong(st.nextToken()); // 가진 돌의 수

        PriorityQueue<int[]> PQ = new PriorityQueue<>((a,b)->a[1]-b[1]);

        // 0-base
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) PQ.add(new int[]{i, stoi(st.nextToken())});

        p = new int[N];

        for (int i = 1; i < N; i++)
            p[i] = i-1; //0 번째 선수를 제외하고 자기보다 -1을 대표로 가리키도록 한다.

        boolean flag = false; // 0번째랑 N-1번째랑 끊어져 있을 떄 true
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken())-1;
            int b = stoi(st.nextToken())-1;
            if ((a == 0 || b == 0) && (a == N-1 || b == N-1)) {
                flag = true;
                continue;
            }
            p[Math.max(a,b)] = Math.max(a,b); //끊어진애 중 큰애는 자기 자신을 대표로 하도록 한다.
        }

        if(flag==false){
            p[find(N-1)] = 0;
        }

        for (int i = 0; i < N; i++) {
            p[i]=find(i);
//            System.out.print(p[i]+" ");
        }

        if(M<=1){
            System.out.println("YES");
            return;
        }


        long expense = 0;
        HashSet<Integer> set = new HashSet<>();
        while(!PQ.isEmpty()){
            int[] now = PQ.poll();
            int target = now[0];
            int cost = now[1];

            if(!set.contains(p[target])){
                set.add(p[target]);
                expense+=cost;
                if(expense>K){
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }// main

    static int[] p;
    static int find(int x){
        if(p[x]==x) return x;
        return find(p[x]);
    }

}// Main