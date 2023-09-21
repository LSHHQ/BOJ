import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
 
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int[][] costMap = new int[N][N];
 
            for (int i = 0; i < N; i++) {
                char[] row = br.readLine().toCharArray();
                Arrays.fill(costMap[i], Integer.MAX_VALUE);
                for (int j = 0; j < N; j++) {
                    map[i][j] = row[j] - '0';
                }
            }
 
            int[] dr = { 0, 1, 0, -1 };
            int[] dc = { 1, 0, -1, 0 };
 
            PriorityQueue<int[]> que = new PriorityQueue<>((a,b)-> {return a[2]-b[2];});
 
            que.add(new int[] { 0, 0, 0 }); // 행, 열, 코스트
            costMap[0][0] = 0;
            while (!que.isEmpty()) {
                int[] tmp = que.poll();
                int Row = tmp[0];
                int Col = tmp[1];
                int Cost = tmp[2];
                 
                if(Row==N-1&&Col==N-1)
                    break;
                 
                for (int dir = 0; dir < 4; dir++) {
                    int nRow = Row + dr[dir];
                    int nCol = Col + dc[dir];
                    int nCost = Cost;
                    if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < N
                            && map[nRow][nCol] + nCost < costMap[nRow][nCol]) {
                         
                        nCost += map[nRow][nCol];
                        costMap[nRow][nCol] = nCost;
                        que.add(new int[] { nRow, nCol, nCost });
                    }
                }
            }
 
 
            bw.write("#" + tc + " ");
            bw.write(String.valueOf((costMap[N - 1][N - 1])));
            bw.write("\n");
        }
 
        bw.flush();
        bw.close();
    } // main
 
    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
 
}