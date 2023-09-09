import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        firstMap = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                firstMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[W];
        combination(0, 0);

        System.out.println(maxCount);
    }// main

    static int H, W, D;
    static int[][] firstMap;
    static int[][] map;
    static boolean[] visited;
    static int count = 0;// 제거한 병사의 수
    static int maxCount = Integer.MIN_VALUE;
    static int[] dr = { 0, -1, 0 };
    static int[] dc = { -1, 0, 1 };

    // 3명의 궁수를 배치하는 방법을 조합을 통해 구하고.
    // 이 경우마다 몇명의 적이 제거되는지 구한다.
    // 궁수는 같은 적을 공격할 수 있고 동시에 공격함을 유의한다.

    static void combination(int idx, int picked) {
        if (picked == 3) {

            int[] selected = new int[3];
            int cnt = 0;

            for (int j = 0; j < W; j++) {
                if (visited[j]) { // 3개 여기서 뽑힌다.
                    selected[cnt++] = j;
                }
            }

            map = copyMap(firstMap);

            count = 0;
            int moveS = 0;
            
            while (true) {

                boolean[][] targeted = new boolean[H][W];

                for (int i = 0; i < 3; i++) {

                    int[] target = targeting(selected[i]);
                    int row = target[0];
                    int col = target[1];

                    if (row == -1)
                        continue;

                    if (!targeted[row][col]) {
                        targeted[row][col] = true;
                        count++;
                    }
                }

                maxCount = Math.max(count, maxCount);
                
                if(count!=0) {
                    for (int i = 0; i < H; i++) {
                        for (int j = 0; j < W; j++) {
                            if (targeted[i][j]) {
                                map[i][j] = 0;
                            }
                        }
                    }
                }
                
                moveSoldier();
                if(moveS==H+1) {
                    break;
                }
                moveS++;

            }

            return;
        }

        for (int j = idx; j < W; j++) {
            visited[j] = true;
            combination(j + 1, picked + 1);
            visited[j] = false;
        }

    }

    static int[] targeting(int COL) {
        int[] target = new int[2];
        int[][] distance = new int[H][W];
        distance[H - 1][COL] = 1;
        int minDis = Integer.MAX_VALUE;

        PriorityQueue<int[]> PQ = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        }); // 열이 작은 순으로 두기..
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { H - 1, COL });
        
        if(map[H-1][COL]==1) {
            return new int[] {H-1,COL};
        }

        while (!que.isEmpty()) {
            int row = que.peek()[0];
            int col = que.poll()[1];

            if (distance[row][col] >= minDis || distance[row][col]+1 > D) {
            	
            	continue;
            }

            for (int i = 0; i < 3; i++) {
                int nrow = row + dr[i];
                int ncol = col + dc[i];

                if (nrow < 0 || ncol < 0 || nrow >= H || ncol >= W)
                    continue;

                if (distance[nrow][ncol] == 0) {
                    distance[nrow][ncol] = distance[row][col] + 1;
                    que.add(new int[] { nrow, ncol });
                }

                if (map[nrow][ncol] == 1) {
                    minDis = distance[nrow][ncol];
                    PQ.add(new int[] { nrow, ncol });
                }

            }
        }

        if (PQ.isEmpty()) {
            return new int[] { -1, -1 };
        }

        target = PQ.poll();
        return target;
    }

    static int[][] copyMap(int[][] map) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++)
            copy[i] = Arrays.copyOf(map[i], W);
        return copy;
    }

    static void moveSoldier() {
        for (int i = H - 2; i >= 0; i--) {
            for (int j = 0; j < W; j++) {
                map[i + 1][j] = map[i][j];
                map[i][j] = 0;
            }
        }
    }

    static void print() {
        System.out.println();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}// class