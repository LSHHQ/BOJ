import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int S = stoi(st.nextToken());
		
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) arr[i] = stoi(st.nextToken()) + arr[i-1];
		
		if(arr[N]<S) {
			System.out.println("0");
			return;
		}
		
		int L = 0;
		int R = 1;
		int ans = Integer.MAX_VALUE;
		while(R!=N+1) {
			
			int val = arr[R]-arr[L];
			
			
			if(val>=S) {
				ans = Math.min(R-L++, ans);
			} else {
				//if(val<S)_
				R++;
			}
		}
		
		System.out.println(ans);
		
	}
}
