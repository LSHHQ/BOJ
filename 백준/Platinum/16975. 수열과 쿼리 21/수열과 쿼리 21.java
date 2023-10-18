import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[N+1]; //수열 크기 최대 10만
        for (int i = 1; i <=N; i++) arr[i] = stoi(st.nextToken());

        SegmentTree tree = new SegmentTree();

        int M = stoi(br.readLine()); //쿼리 갯수, 최대 10만
        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            if(stoi(st.nextToken())==1){
                // left~right에 k만큼 더함
                // k : -백만 ~ +백만, 원소 하나에 최댓값 천억
                int left = stoi(st.nextToken());
                int right = stoi(st.nextToken());
                int k = stoi(st.nextToken());
                tree.update(1,1,N,left,right,k);
//                tree.print();
            }else{
                // Ax를 출력
                int x = stoi(st.nextToken());
                bw.write(tree.get(1,1,N,x)+"\n");
            }
        }

        bw.flush();
        bw.close();
    }// main

    static int N;
    static int[] arr;
    static int stoi(String s) {return Integer.parseInt(s);}

    static class SegmentTree {
        long[] tree;

        SegmentTree() {
            int height = (int)Math.ceil(Math.log(N)/Math.log(2));
            tree = new long[(int)Math.pow(2,height+1)];
            init(1,1,N);
        }

        void init(int node, int start, int end) {
            if(start==end) {
                tree[node] = arr[start];
            }else{
                int mid = (start+end)>>1;
                init(node*2, start, mid);
                init(node*2+1, mid+1, end);
            }
        }

        void update(int node, int start, int end, int left, int right, int k){
            if(end<left || start>right) return;
            if(start>=left && end<=right) {
                tree[node] += k;
                return;
            }
            int mid = (start+end)>>1;
            update(node*2   ,       start, mid, left, right, k);
            update(node*2+1 ,  mid+1, end, left, right, k);
        }

        long get(int node, int start, int end, int idx){
            if(start>idx || end<idx) return 0;
            if(start==end) return tree[node];
            int mid = (start+end)>>1;
            return tree[node] + get(node*2, start, mid, idx) +
                    get(node*2+1, mid+1, end, idx);
        }

        void print(){
            System.out.println();
            for (int i = 0; i < tree.length; i++) {
                System.out.printf("%4d",i);
            }
            System.out.println();
            for (int i = 0; i < tree.length; i++) {
                System.out.printf("%4d", tree[i]);
            }
        }
    } // SegmentTree
}// Main