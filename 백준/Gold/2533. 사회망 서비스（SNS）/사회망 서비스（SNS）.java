import java.io.*;
import java.util.*;


// ctrl + w : 단어선택
// shit + ctrl + enter ; 현재 구문 완성
// Alt + 1 : 도구 창 닫고 열기
public class Main {
    static int stoi(String s){return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N = stoi(br.readLine());
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) adj[i] = new ArrayList();

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken())-1;
            int to = stoi(st.nextToken())-1;
            adj[from].add(to);
            adj[to].add(from);
        }

        // 자신이 얼리어답터이면 자신과 연결된 노드는 얼리어답터 이거나, 얼리어답터가 아니어도 된다.
        // 내가 얼리어답터가 아니면 연결된 노드는 얼리어답터 여야 한다.
        // 자신이 얼리어답터일 경우와 아닐 경우를 생각해서 해야됌. [~][2] 짜리 dp 배열을 만들어야할듯

        dp = new int[N][2];
        visited = new boolean[N];
        DFS(0);

        System.out.println(Math.min(dp[0][0],dp[0][1]));

    }// main

    static int N;
    static int[][] dp;
    static List<Integer>[] adj;
    static boolean[] visited;

    static void DFS(int fr){
        visited[fr] = true;
        dp[fr][0] = 0; //얼리어답터 아닐 때 얼리어답터 수
        dp[fr][1] = 1; //얼리어답터 일 때.
        for (int to: adj[fr]) {
            if(visited[to]) continue;
            DFS(to);
            dp[fr][0] += dp[to][1]; //내가 얼리 아니면 자식은 얼리여야하니까
            dp[fr][1] += Math.min(dp[to][0],dp[to][1]);
            //내가 얼리이면 자식은 얼리 or 안얼리
            //이중 최소가 되는 값을 더해준다.
        }
    }

}// Main