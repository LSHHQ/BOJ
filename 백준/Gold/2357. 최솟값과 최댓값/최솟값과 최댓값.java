import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		arr = new int[N+1];
		for (int i = 1; i <= N; i++) arr[i] = stoi(br.readLine());

		SegmentTree tree = new SegmentTree();
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int left = stoi(st.nextToken());
			int right = stoi(st.nextToken());
			Node ans = tree.get(1, 1, N, left, right);
			bw.write(ans.min+" "+ans.max+"\n");
		}
		
//		System.out.println("####");
//		for (int i = 1; i < tree.tree.length; i++) {
//			if(tree.tree[i] == null ) {
//				System.out.println(i+ " : -1 -1");
//				continue;
//			}
//			Node test = tree.tree[i];
//			System.out.println(i+" : "+test.min+" "+test.max);
//		}
		
		bw.flush();
		bw.close();
	}
	
	static int[] arr;
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static class SegmentTree {
		Node[] tree;
		
		SegmentTree() {
			int N = arr.length-1;
			int height = (int)Math.ceil(Math.log(N)/Math.log(2));
			tree = new Node[(int)Math.pow(2,height+1)];
			build(1,1,N);
		}
		
		Node build(int node, int start, int end) {
			if(start==end) return tree[node] = new Node(arr[start], arr[start]);
			int mid = (start+end)/2;
			Node left  = build(node*2, start, mid);
			Node right = build(node*2+1, mid+1,end);
			
//			System.out.println();
//			System.out.println(node);
//			System.out.println(left.min+" "+left.max+" || "+right.min+" "+right.max);
			
			int min = Math.min(left.min, right.min);
			int max = Math.max(left.max, right.max);
			return tree[node] = new Node(min, max);
		}
		
		Node get(int node, int start, int end, int left, int right) {
			if( left > end || right < start) return new Node(MAX,MIN);
			if( (left<=start && right>=end) || start==end ) return tree[node];
			
			int mid = (start+end)/2;
			Node L = get(node*2  , start,mid, left, right);
			Node R = get(node*2+1, mid+1,end, left, right);
			int min = Math.min(L.min, R.min);
			int max = Math.max(L.max, R.max);
			return new Node(min, max);
		}
		
		
	}
	
	static class Node {
		int min;
		int max;
		public Node(int m, int M) { min = m; max = M; }
	}
}
