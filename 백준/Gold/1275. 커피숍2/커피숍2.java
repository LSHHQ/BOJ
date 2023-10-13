import javax.imageio.plugins.bmp.BMPImageWriteParam;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken()); //수의 갯수
        int Q = stoi(st.nextToken()); //턴의 갯수

        arr = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = stol(st.nextToken());


        SegmentTree tree = new SegmentTree();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int left = stoi(st.nextToken());
            int right = stoi(st.nextToken());
            bw.write(tree.sum(1, 1, N, Math.min(left,right), Math.max(left,right) )+"\n");

            int idx = stoi(st.nextToken());
            long value = stol(st.nextToken());
            tree.update(1,1, N, idx,value-arr[idx]);
            arr[idx] = value;
        }

        bw.flush();
        bw.close();
    }

    static long[] arr;
    static int stoi(String s) {return Integer.parseInt(s);}
    static long stol(String s) {return Long.parseLong(s);}

    static class SegmentTree{
        long[] tree;
        int size;

        public SegmentTree(){
            int len = arr.length-1;
            int height = (int)Math.ceil(Math.log(len)/Math.log(2));
            size = (int)Math.pow(2,height+1);
            tree = new long[size];
            build(1, 1, len);
        }

        long build(int node, int start, int end){
            if(start==end) return tree[node]=arr[start];
            int mid = start+(end-start)/2;
            return tree[node] = build(node*2, start, mid) + build(node*2+1, mid+1, end);
        }

        void update(int node, int start, int end, int idx, long diff){
            if( idx<start || idx>end ) return;
            tree[node] += diff;
            if( start==end ) return;
            int mid = start+(end-start)/2;
            update(node*2, start, mid, idx, diff);
            update(node*2+1, mid+1, end, idx, diff);
        }

        long sum(int node, int start, int end, int left, int right){
            if( left>end || right<start) return 0;
            if(start>=left && end<=right) return tree[node];
            int mid = start+(end-start)/2;
            return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right) ;
        }
    }
}