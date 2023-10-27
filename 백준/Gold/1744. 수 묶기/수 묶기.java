import java.io.*;
import java.util.*;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Main {
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		
		int N = stoi(br.readLine());
		
		List<Integer> pos = new ArrayList<>();
		List<Integer> neg = new ArrayList<>();
		
		int sum = 0;

		boolean zero = false; //0이 있는지 묻는다.
		for (int i = 0; i < N; i++) {
			int tmp = stoi(br.readLine());
			if(tmp>1) pos.add(tmp);
			else if(tmp<0) neg.add(tmp);
			else if(tmp==0) zero = true;
			else if(tmp==1) sum++;
		}
		
		Collections.sort(pos, (a,b)->{return b-a;});
		Collections.sort(neg);
		
		int len = pos.size();
		
		for (int i = 0; i+1 < len; i+=2) {
			int pre = pos.get(i);
			int post = pos.get(i+1);
			sum+=pre*post;
		}
		
		if(len%2==1) sum+=pos.get(len-1);
		
		len = neg.size();
		for (int i = 0; i+1 < len; i+=2) {
			int pre = neg.get(i);
			int post = neg.get(i+1);
			sum+=pre*post;
		}
		
		if(len%2==1 && zero==false ) sum+=neg.get(len-1);
		
		System.out.println(sum);
		
	}
	
	
}// Main
