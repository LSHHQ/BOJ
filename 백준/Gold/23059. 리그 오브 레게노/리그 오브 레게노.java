import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //아이템 사이의 관계 수
		HashMap<String, item> hash = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();
			
			item a = hash.getOrDefault(A, null);
			item b = hash.getOrDefault(B, null);
			
			if(a==null) hash.put(A, new item(A,B));
			else a.child.add(B);
			
			if(b==null) hash.put(B, new item(B));
			
			hash.get(B).indeg++;
		}
		
		// 현재 구매 가능한 아이템 중 구매하지 않은 아이템을 찾는다.
		// 찾은 아이템을 사전순으로 구매한다.
		
		PriorityQueue<String> PQ = new PriorityQueue<>((a,b) -> {return a.compareTo(b);});
		
		for (String key : hash.keySet()) {
			item now = hash.get(key);
			if(now.indeg==0) PQ.add(key);
		}
		
		Queue<String> que = new LinkedList<>();
		
		int count = hash.size();
		while(!PQ.isEmpty()) {
			item par = hash.get(PQ.peek());
			sb.append(PQ.poll()+"\n");
			count--;
			
			for (String ch : par.child ) {
				item now = hash.get(ch);
				if(--now.indeg==0) que.add(ch);
			}
			
			if(PQ.isEmpty()) {
				PQ.addAll(que);
				que = new LinkedList<>();
			}
		}
		
		bw.write(count==0 ? sb.toString() : String.valueOf(-1));
		bw.flush();
		bw.close();
	}// main
	
	static class item{
		String name;
		List<String> child;
		int indeg=0;
		
		public item(String n) {
			name = new String(n);
			child = new ArrayList<>();
		}

		public item(String n, String c) {
			name = new String(n);
			child = new ArrayList<>();
			child.add(new String(c));
		}
		
	}
	
}// class
