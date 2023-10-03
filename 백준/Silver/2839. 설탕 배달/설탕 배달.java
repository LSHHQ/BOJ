import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int Q5 = N/5;
		int R5 = N%5;
		int Q3 = 0;
		boolean flag = true;
		
		
		while(true) {
			if(R5%3==0) {
				Q3=R5/3;
				break;
			}else {
				if(Q5<=0) {
					flag = false;
					break ;
				}
				Q5--;
				R5 += 5;
			}
		}
		
		
		if(flag) {
			System.out.println(Q5+Q3);
		}else {
			System.out.println(-1);
		}
		
		
		
		br.close();
	}
	
	

}
