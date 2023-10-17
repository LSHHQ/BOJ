import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		SegmentTree tree = new SegmentTree();
		
		int M = stoi(br.readLine());
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			
			if(stoi(st.nextToken())==1) {
				// 사탕 꺼내기
				int seq = stoi(st.nextToken()); //몇번째 꺼내는지
				int ans = tree.get(1,1,N,seq);
				tree.candy[ans]--;
				bw.write(ans+"\n");
			}else {
				// 사탕 넣기
				int taste = stoi(st.nextToken()); //맛
				int num  = stoi(st.nextToken()); //갯수 (음수의 경우 빼는 것)
				tree.put(1,1,N,taste,num);
				tree.candy[taste] += num;
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static final int N = 1000000; //맛의 범위
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static class SegmentTree {
		int[] candy;
		int[] tree;
		
		SegmentTree() {
			int height = (int)Math.ceil(Math.log(N)/Math.log(2));
			tree = new int[(int)Math.pow(2, height+1)];
			candy = new int[N+1];
		}
		
		void put(int node, int start, int end, int taste, int num) {
			if( taste<start || taste>end ) return ;
			tree[node] += num;
			if(start==end) return;
			int mid = (start+end) >> 1;
			put(node*2	, start, mid, taste, num);
			put(node*2+1, mid+1, end, taste, num);
		}
		
		int get(int node, int start, int end, int num) {
			tree[node]--;
			if(start==end) return start;
			
			int mid = (start+end) >> 1;
			if(num <= tree[node*2]) return get(node*2, start, mid, num);
			else return get(node*2+1, mid+1, end, num-tree[node*2]);
		}
		
	}
}