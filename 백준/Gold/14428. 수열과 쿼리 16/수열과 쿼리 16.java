import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		
		int N = stoi(br.readLine());
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) arr[i] = stoi(st.nextToken()); 
		
		SegmentTree tree = new SegmentTree();
		
		int M = stoi(br.readLine());
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			if(stoi(st.nextToken())==1) {
				// update
				int idx = stoi(st.nextToken());
				int val = stoi(st.nextToken());
				tree.update(1,1,N,idx,val);
				arr[idx] = val;
				
			}else {
				// print
				int left = stoi(st.nextToken());
				int right = stoi(st.nextToken());
				bw.write(tree.getMin(1,1,N,left,right).idx+"\n");
			}
			
		}
		
		
		bw.flush();
		bw.close();
	}
	
	static int[] arr;
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static class SegmentTree {
		Node[] tree;
		
		SegmentTree() {
			int N = arr.length-1;
			int height = (int)Math.ceil(Math.log(N)/Math.log(2));
			tree = new Node[(int)Math.pow(2, height+1)];
			build(1,1,N);
		}
		
		Node build(int node, int start, int end) {
			if(start==end) return tree[node] = new Node(arr[start], start);
			int mid = (start+end)/2;
			Node L = build(node*2, start, mid);
			Node R = build(node*2+1, mid+1, end);
			if(L.val<=R.val) return tree[node] = new Node(L.val, L.idx);
			else return tree[node] = new Node(R.val,R.idx);
		}
		
		Node update(int node, int start, int end, int idx, int val) {
			if(idx<start || idx>end ) return tree[node];
			if(start==end) return tree[node] = new Node(val,idx);
			
			int mid = (start+end)/2;
			Node L = update(node*2	, start, mid, idx, val);
			Node R = update(node*2+1, mid+1, end, idx, val);
			if(L.val<=R.val) 
				return tree[node] = new Node(L.val, L.idx);
			else 
				return tree[node] = new Node(R.val,R.idx);
		}
		
		
		Node getMin(int node, int start, int end, int left, int right) {
			if( left>end || right<start ) return new Node(Integer.MAX_VALUE, -1);
			if( (left<=start && right>=end) || start==end) return tree[node];
			
			int mid = (start+end)/2;
			Node L = getMin(node*2	, start, mid, left, right);
			Node R = getMin(node*2+1, mid+1, end, left, right);
			if(L.val<=R.val) return new Node(L.val, L.idx);
			else return new Node(R.val,R.idx);
		}
		
		
	}
	
	static class Node {
		int val;
		int idx;
		public Node(int v, int i) {
			val = v; idx = i;
		}
	}
	
}
