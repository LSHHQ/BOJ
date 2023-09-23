import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            time = new int[N];
            indeg = new int[N];
            edge = new ArrayList[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
                edge[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                edge[from].add(to); // 반대로 해준다.
                indeg[to]++;
            }

            W = Integer.parseInt(br.readLine()) - 1;
            // input
            
            
            bw.write(String.valueOf(func()) + "\n");
        }

        bw.flush();
        bw.close();
    }
    
   static int W, N;
   static int[] time, indeg;
   static List[] edge;
   
   static int func() {
	   Queue<Integer> que = new LinkedList<Integer>();
		int[] result = new int[N];

		for (int i = 0; i < N; i++) {
			if (indeg[i] == 0) {
				result[i] = time[i];
				que.add(i);
			}
		}

		while (!que.isEmpty()) {
			int now = que.poll();
			
			for (int i : (ArrayList<Integer>) edge[now]) {
				
				result[i] = Math.max(result[i], result[now] + time[i]);
				indeg[i]--;

				if (indeg[i] == 0)
					que.offer(i);
			}
		}
		
		return result[W];
   }
    

}// class
