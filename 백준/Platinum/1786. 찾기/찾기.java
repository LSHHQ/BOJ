import java.io.*;
import java.util.*;

// https://loosie.tistory.com/192#h3
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		String whole = br.readLine();
		String P = br.readLine();
		count = 0;
		sb = new StringBuilder();
		KMP(whole,P);
		
		bw.write(count+"\n");
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
	}// main
	
	static int count;
	static StringBuilder sb;
	
	static void KMP(String whole, String pattern) {
		int[] table = makeTable(pattern);
			
		int n1 = whole.length();
		int n2 = pattern.length();
			
		int idx = 0; 
		for(int i=0; i< n1; i++) {
			while(idx>0 && whole.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if(whole.charAt(i) == pattern.charAt(idx)) {
				if(idx == n2-1) {
					count++;
					sb.append(i-idx+1+" ");
					idx =table[idx];
				}else {
					idx ++;
				}
			}
		}
	}
	
	static int[] makeTable(String whole) {
		int len = whole.length();
		int[] table = new int[len];
		
		int idx = 0;
		for (int i = 1; i < len; i++) {
			
			while(idx!=0 && whole.charAt(idx)!=whole.charAt(i)) {
				idx = table[idx-1];
			}
			
			if(whole.charAt(idx) == whole.charAt(i)) {
				table[i] = ++idx;
			}
		}
		return table;	
	}
	
}// class