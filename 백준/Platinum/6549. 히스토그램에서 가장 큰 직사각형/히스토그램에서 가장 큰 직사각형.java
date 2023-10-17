import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			if(N==0) break;
			
			arr = new int[N+1];
			for (int i = 1; i <= N; i++) arr[i] = stoi(st.nextToken());
			
			tree = new SegmentTree();
			
			bw.write(tree.getMaxSquare(1,N)+"\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int N;
	static int[] arr;
	static SegmentTree tree;
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static class SegmentTree {
		int[] tree;
		
		SegmentTree() {
			int height = (int)Math.ceil(Math.log(N)/Math.log(2));
			tree = new int[(int)Math.pow(2, height+1)];
			init(1,1,N);
		}
		
		int init(int node, int start, int end) {
			if(start==end) return tree[node] = start;
			int mid = (start+end)/2;
			int L = init(node*2,start,mid);
			int R = init(node*2+1,mid+1,end);
			if(arr[L]<=arr[R]) return tree[node] = L;
			else return tree[node] = R;
		}
		
		int getLocalMinIdx(int node, int start, int end, int left, int right) {
			if(left>end || right<start) return -1;
			if(( start>=left && end<=right) || start==end ) return tree[node];
			
			int mid = (start+end)/2;
			int L = getLocalMinIdx(node*2  ,start,mid, left, right);
			int R = getLocalMinIdx(node*2+1,mid+1,end, left, right);
			
			if(L==-1) return R;
			else if(R==-1) return L;
			
			if(arr[L]<=arr[R]) return L;
			else return R;
		}
		
		long getMaxSquare(int left, int right) {
			int minIdx = getLocalMinIdx(1,1,N, left, right);
			int width = right-left+1;
			int height = arr[minIdx];

			long area = (long)width*height;
			if(minIdx!=left) area = Math.max(area, getMaxSquare(left, minIdx-1));
			if(minIdx!=right) area = Math.max(area, getMaxSquare(minIdx+1, right));

			return area;
		}
		
		
	}
	
}
