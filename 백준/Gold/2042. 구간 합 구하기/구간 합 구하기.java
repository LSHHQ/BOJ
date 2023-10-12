import java.io.*;
import java.util.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
	
		st = new StringTokenizer(br.readLine());
		
		int N = stoi(st.nextToken()); //수의 갯수
		int M = stoi(st.nextToken()); //수의 변경이 일어나는 횟수
		int K = stoi(st.nextToken()); //구간의 합을 구하는 횟수
		
		long[] arr = new long[N+1];
		for (int i = 1; i <= N; i++) arr[i] = stol(br.readLine());
		
		SegmentTree tree = new SegmentTree(arr);
		
//		System.out.println();
//		for (int i = 0; i < tree.size; i++) System.out.printf("%3d",i);
//		System.out.println();
//		for (int i = 0; i < tree.size; i++) System.out.printf("%3d",tree.tree[i]);
//		System.out.println();
		
		
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int command = stoi(st.nextToken());
			
			if(command == 1) { //데이터 변경
				int idx = stoi(st.nextToken());   // idx 번째 수를 
				long num = stol(st.nextToken()); // num으로 변경
				tree.update(1, 1, N, idx,num-arr[idx]);
				arr[idx]=num;	//유의 **
			}else if(command == 2) {
				int left = stoi(st.nextToken());
				int right = stoi(st.nextToken());
				bw.write(tree.sum(1,1,N,left,right)+"\n");
			}
		}
		
		bw.flush();
		bw.close();
	}// main
	
	
	
	static int stoi(String s) {return Integer.parseInt(s);}
	static long stol(String s) {return Long.parseLong(s);}
}

class SegmentTree{
	long[] tree;
	int size;
	
	public SegmentTree(long[] arr) {
		int arrSize = arr.length-1;
		int height = (int)Math.ceil(Math.log(arrSize)/Math.log(2)); //높이 계산
		size = (int)Math.pow(2,height+1); //배열 크기 계산
		tree = new long[size];
		build(arr, 1, 1, arrSize);
	}
	
	public long build(long[] arr, int node, int start, int end) {
		if(start==end) return tree[node] = arr[start];
		
		int mid = start+(end-start)/2;

		return tree[node] = build(arr, node*2, start, mid) 
				+ build(arr,node*2+1, mid+1, end);
	}
	
					// 현재 노드               시작                 끝      변경데이터 idx   변경 데이터와 원래 데이터 차이
	public void update(int node, int start, int end, int idx, long diff) {
		if( idx<start || idx>end ) return;
		
		tree[node] += diff;
		
		if(start!=end) {
			int mid = start+(end-start)/2;
			update(node*2, start, mid, idx, diff);
			update(node*2+1, mid+1, end, idx, diff);
		}
	}
	
	public long sum(int node, int start, int end, int left, int right) {
		if( left>end || right<start ) return 0;
		if( left<=start && end<=right) return tree[node];
		int mid = start+(end-start)/2;
		return sum(node*2, start, mid, left, right)
				+sum(node*2+1, mid+1, end, left, right);
	}
	
}
