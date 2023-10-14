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

        int[] arr = new int[N+1];

        SegmentTree tree = new SegmentTree(arr);

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int comm = stoi(st.nextToken());
            if(comm==0){ //sum
                int left = stoi(st.nextToken());
                int right = stoi(st.nextToken());
                bw.write(tree.getSum(1,1,N,Math.min(left,right),Math.max(left,right))+"\n");
            }else { //update
                int idx = stoi(st.nextToken());
                int val = stoi(st.nextToken());
                tree.update(1,1,N,idx,val-arr[idx]);
                arr[idx] = val;
            }

        }

        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static class SegmentTree {
        long[] tree;

        SegmentTree(int[] arr) {
            int N = arr.length-1;
            int height = (int)Math.ceil(Math.log(N)/Math.log(2));
            int size = (int)Math.pow(2,height+1);
            tree = new long[size];
        }

        void update(int node, int start, int end, int idx, int diff) {
            if(idx<start || idx>end) return;
            tree[node] +=diff;
            if(start==end) return;
            int mid = (start+end)/2;
            update(node*2, start, mid, idx, diff);
            update(node*2+1, mid+1, end, idx, diff);
        }

        long getSum(int node, int start, int end, int left, int right) {
            if(left>end || right<start) return 0;
            if(left<=start && right>=end) return tree[node];
            int mid = (start+end)/2;
            return getSum(node*2, start, mid, left, right)
                    +getSum(node*2+1,mid+1,end,left,right);
        }

    }
}