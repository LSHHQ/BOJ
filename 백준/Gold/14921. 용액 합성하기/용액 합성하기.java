import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		
		int N = Integer.parseInt(br.readLine());
		int[] acid = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			acid[i] = Integer.parseInt(st.nextToken());
		
		int low = 0;
		int high = N-1;

		int min = Integer.MAX_VALUE;
		
		while(low<high) {
			
			int A = acid[low];
			int B = acid[high];
			
			int fusion = A+B;
			
			if(Math.abs(fusion) < Math.abs(min)) {
				min = fusion;
			}
			
			if(fusion==0)
				break;
			
			if(fusion>0)
				high--;
			else
				low++;
		}
		
		System.out.print(min);
		
	}
}