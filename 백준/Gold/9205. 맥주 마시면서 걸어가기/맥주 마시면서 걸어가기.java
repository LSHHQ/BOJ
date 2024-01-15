import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = stoi(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            int N = stoi(br.readLine()); //맥주를 파는 편의점의 수

            List<int[]> point = new ArrayList<>();
            for (int i = 0; i < N+2; i++) {
                st = new StringTokenizer(br.readLine());
                point.add(new int[] {stoi(st.nextToken()), stoi(st.nextToken())});
            }

            boolean[][] adj = new boolean[N+2][N+2];

            for (int i = 0; i < N+2; i++) {
                int[] now = point.get(i);
                for (int j = i+1; j < N+2; j++) {
                    int[] next = point.get(j);
                    if( Math.abs(now[0]-next[0])+Math.abs(now[1]-next[1])<=1000) {
                        adj[i][j] = true;
                        adj[j][i] = true;
                    }
                }
            }

            boolean flag = false;

            boolean[] visited = new boolean[N+2];
            LinkedList<Integer> que = new LinkedList<>();
            que.add(0);
            visited[0] = true;
            outter: while (!que.isEmpty()) {
                int now = que.poll();
                for (int i = 0; i < N+2; i++) {
                    if(visited[i] || !adj[now][i]) continue;
                    visited[i] = true;
                    que.add(i);
                    if(i==N+1) {
                        flag = true;
                        break outter;
                    }
                }
            }

            bw.write(flag? "happy\n":"sad\n");

        }

        bw.flush();
        bw.close();
    }// main

}// Main