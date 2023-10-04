import java.io.*;
import java.util.*;

public class Main {
	static HashMap<String, Person> hash;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine()); //친구관계의 수
			hash = new HashMap<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String A = st.nextToken();
				String B = st.nextToken();
				
				Person A_parent = hash.getOrDefault(A, null);
				Person B_parent = hash.getOrDefault(B, null);
				
				if(A_parent==null) {
					hash.put(A, new Person(A,A));
				}
				
				if(B_parent==null) {
					hash.put(B, new Person(B,B));
				}
				
				union(A,B);
				
				bw.write(String.valueOf(hash.get(find(B)).num)+"\n");
			}
			
		}
		
		bw.flush();
		bw.close();
	}// main
	
	static String find(String str) {
		Person per = hash.get(str);
		String par = per.parent;
		if(par.equals(str)) return str;
		return per.parent = new String(find(par));
	}
	
	static void union(String A, String B) {
		String a = find(A);
		String b = find(B);
		if(a.equals(b)) return;
		
		Person perA = hash.get(a);
		perA.parent = new String(b);
		
		Person perB = hash.get(b);
		perB.num += perA.num;
	}
	
	static class Person{
		String name;
		String parent;
		int num = 1; //네트워크 된 수
		public Person(String n, String p) {
			name = new String(n);
			parent = new String(p);
		}
	}
	
}// class
