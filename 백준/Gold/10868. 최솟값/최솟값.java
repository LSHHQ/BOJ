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
//        System.out.println(Arrays.toString(tree.tree));
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int left = stoi(st.nextToken());
            int right = stoi(st.nextToken());
            bw.write(tree.min(1,1,N,left,right)+"\n");
        }
        bw.flush();
        bw.close();
    }// main

    static int[] arr;
    static int stoi(String s) {return Integer.parseInt(s);}

    static class SegmentTree {
        int[] tree;

        SegmentTree() {
            int N = arr.length-1;
            int height = (int) Math.ceil(Math.log(N)/Math.log(2));
            tree = new int[(int)Math.pow(2,height+1)];
            build(1,1,N);
        }

        int build(int node, int start, int end) {
            if(start==end) return tree[node] = arr[start];
            int mid = (start+end)/2;
            return tree[node] = Math.min(
                    build(node*2, start, mid),
                    build(node*2+1, mid+1, end)
            );
        }

        int min(int node, int start, int end, int left, int right) {
            if(start > right || end < left) return Integer.MAX_VALUE;
            if((left<=start && right>=end) || start==end) return tree[node];
            int mid = (start+end)/2;
            return Math.min(
                    min(node*2,start,mid,left,right),
                    min(node*2+1,mid+1,end,left,right)
            );
        }
    } // SegmentTree
}// Main