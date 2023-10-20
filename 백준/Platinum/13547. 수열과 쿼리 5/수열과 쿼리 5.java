import java.io.*;
import java.util.*;

public class Main {

	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[N+1];
		for (int i = 1; i <= N; i++) arr[i] = stoi(st.nextToken());
		
		M = stoi(br.readLine());
		query = new int[M][3];
		int seq = 0;
		while(seq<M) {
			st = new StringTokenizer(br.readLine());
			int L = stoi(st.nextToken());
			int R = stoi(st.nextToken());
			query[seq] = new int[] {seq++, L, R}; 
		}
		
		div = (int)Math.sqrt(M);
		Arrays.sort(query,(a,b)->{return sort(a,b);});
		
		cnt = new int[1000001];
		ans = new int[M];
		left = query[0][1];
		right = query[0][1] - 1;
		while (right < query[0][2]) {
			if (cnt[arr[++right]]++ == 0) {
				ans[query[0][0]]++;
			}
		}
		
		for (int i = 1; i < M; i++) {
			ans[query[i][0]] = ans[query[i - 1][0]];
			while (left < query[i][1]) {
				if (--cnt[arr[left++]] == 0) {
					ans[query[i][0]]--;
				}
			}
			while (left > query[i][1]) {
				if (cnt[arr[--left]]++ == 0) {
					ans[query[i][0]]++;
				}
			}
			while (right < query[i][2]) {
				if (cnt[arr[++right]]++ == 0) {
					ans[query[i][0]]++;
				}
			}
			while (right > query[i][2]) {
				if (--cnt[arr[right--]] == 0) {
					ans[query[i][0]]--;
				}
			}
		}
		
		for (int i = 0; i < M; i++) bw.write(ans[i]+"\n");
		
		bw.flush();
		bw.close();
	}// main()
	
	static int N, M, left, right, div;
	static int[] arr, cnt, ans;
	static int[][] query;
	
	static int sort(int[] a, int[] b) {
		int o1 = a[1]/div, o2 = b[1]/div;
		if(o1==o2) return a[2]-b[2];
		return o1-o2;
	}
	
	
}// Main
