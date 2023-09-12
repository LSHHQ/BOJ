import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()) + 1;// 정점
        int M = Integer.parseInt(st.nextToken()); // 간선
        int R = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<Integer>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            list.get(vertex).add(child);
            list.get(child).add(vertex);
        }
        
        for (int i = 0; i < N; i++) {
			Collections.sort(list.get(i));
		}
        
        boolean[] visited = new boolean[N];
        Queue<Integer> que = new LinkedList<>();
        que.add(R);
        int[] count = new int[N];
        int seq = 0;
        
        while(!que.isEmpty()) {
            int fr = que.poll();
            visited[fr] =true;
            count[fr] = ++seq;
            for (int to : list.get(fr)) {
                if(!visited[to]) {
                    visited[to] = true;
                    que.add(to);
                }
            }
        }
        
        for (int i = 1; i < N; i++) {
            System.out.println(count[i]);
        }
        
    } // main
}