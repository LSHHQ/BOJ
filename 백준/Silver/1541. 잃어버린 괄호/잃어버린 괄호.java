import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(),"+,-",true);
		
		int ans = 0;
		boolean flag = false; //앞에 -부호가 붙는지
		
		while(st.hasMoreTokens()) {
			String now = st.nextToken();
			
			if(now.equals("+")) {
				continue;
			}else if(now.equals("-")) {
				flag = true;
				continue;
			}
				
			int num = stoi(now);
			if(num>0) {
				if(flag) ans -= num;
				else ans += num;
			}else {
				flag = false;
				ans += num;
			}
		}
		
		System.out.println(ans);
		
		
	}
}
