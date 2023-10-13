import java.io.*;
import java.util.*;

public class Main {
    
    static final long mode = 1000000007;
    
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
        
//        tree.print();
        
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int command = stoi(st.nextToken());
            
            if(command == 1) { //데이터 변경
                
                int idx = stoi(st.nextToken());   // idx 번째 수를 
                long num = stol(st.nextToken()); // num으로 변경
                
                
                tree.update(1, 1, N, idx,num);
                arr[idx]=num;    //유의 **
                
//                tree.print();

            }else if(command == 2) {
                int left = stoi(st.nextToken());
                int right = stoi(st.nextToken());

                bw.write((int)tree.mul(1,1,N,left,right)%mode+"\n");
            }
        }
        
        bw.flush();
        bw.close();
    }// main
    
    
    
    static int stoi(String s) {return Integer.parseInt(s);}
    static long stol(String s) {return Long.parseLong(s);}
    static double stod(String s) {return Double.parseDouble(s);}
    
    static class SegmentTree{
    	long[] tree;
        int size;
        
        public SegmentTree(long[] arr) {
            int arrSize = arr.length-1;
            int height = (int)Math.ceil(Math.log(arrSize)/Math.log(2)); //높이 계산
            size = (int)Math.pow(2,height+1); //배열 크기 계산
            tree = new long[size];
//            Arrays.fill(tree, 1);
            build(arr, 1, 1, arrSize);
        }
        
        public long build(long[] arr, int node, int start, int end) {
            if(start==end) return tree[node] = arr[start];
            
            int mid = start+(end-start)/2;
            
            return tree[node] = build(arr, node*2, start, mid)%mode * build(arr,node*2+1, mid+1, end)%mode;
        }
        
        
        
        // 					현재 노드               시작                 끝      변경데이터 idx 
        public long update(int node, int start, int end, int idx, long val) {
            if( idx<start || idx>end ) return tree[node];
            
            if(start==end) return tree[node] = val;
            
            int mid = start+(end-start)/2;
            return tree[node] = update(node*2, start, mid, idx, val)%mode*
            							update(node*2+1, mid+1, end, idx, val)%mode;
        }
        
        public long mul(int node, int start, int end, int left, int right) {
            if( left>end || right<start ) return 1;
            if( left<=start && end<=right) return tree[node];
            int mid = start+(end-start)/2;
            return ( ( mul(node*2, start, mid, left, right) * mul(node*2+1, mid+1, end, left, right) ) %mode);
        }
        
        public void print() {
            System.out.println();
            for (int i = 0; i < size; i++) System.out.printf("%4d",i);
            System.out.println();
            for (int i = 0; i < size; i++) System.out.printf("%4d", (int)tree[i]);
            System.out.println();
        }
        
    }
}

