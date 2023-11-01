import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
			
		int[][] fibo = new int[41][2];

		fibo[0][0] = 1;
		fibo[0][1] = 0;
		
		fibo[1][0] = 0;
		fibo[1][1] = 1;
		
		for (int i = 2; i <= 40; i++) {
			fibo[i][0] = fibo[i-2][0]+fibo[i-1][0];
			fibo[i][1] = fibo[i-2][1]+fibo[i-1][1];
		}
		
		int T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int num = stoi(br.readLine());
			System.out.print(fibo[num][0]);
			System.out.print(" ");
			System.out.println(fibo[num][1]);
		}
		
	}// main()
}// Main
