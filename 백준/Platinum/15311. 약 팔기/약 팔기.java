import java.io.*;
import java.util.*;

public class Main {

	static int stoi(String s) {return Integer.parseInt(s);}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = stoi(br.readLine());
		
		int Q = N/1000;
		
		if(Q>0) {
			bw.write(String.valueOf(Q+999)+"\n");
			for (int i = 0; i < Q; i++) bw.write("1000 "); 
			for (int i = 0; i < 999; i++) bw.write("1 ");
		}else {
			bw.write(String.valueOf(N)+"\n");
			for (int i = 0; i < N; i++) bw.write("1 ");
		}
		
		bw.flush();
		bw.close();
		
	}// main
	
}
