import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int stoi(String s) {return Integer.parseInt(s);}
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
 
        int T = stoi(br.readLine());
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
 
        for (int tc = 1; tc <= T; tc++) {
 
            st = new StringTokenizer(br.readLine());
            int H = stoi(st.nextToken()); // 세로 크기
            int W = stoi(st.nextToken()); // 가로 크기
            int K = stoi(st.nextToken()); // K시간에 살아있는 세포(비활성 + 활성) 수 출력(배양시간)
             
            HashMap<Point, Cell> map = new HashMap<>();
            Queue<Point> que = new LinkedList<>();
             
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    int energy = stoi(st.nextToken());
                    if(energy==0) continue;
                    Point pnt = new Point(i,j);
                    map.put(pnt, new Cell(0,energy));
                    que.add(pnt);
                }
            }
             
            while(!que.isEmpty()) {
                 
                int row = que.peek().row;
                int col = que.peek().col;
                Cell cell = map.get(que.poll());
                int time = cell.time;
                int energy = cell.energy;
                 
                int ntime = time + energy + 1;
                if(ntime>K) continue;
                 
                for (int i = 0; i < 4; i++) {
                    int nrow = row+dr[i];
                    int ncol = col+dc[i];
                    Point nPnt = new Point(nrow,ncol);
                     
                    if(map.getOrDefault(nPnt,null)==null) {
                        map.put(nPnt,new Cell(ntime, energy));
                        que.add(nPnt);
                    }else {
                         
                        Cell nCell = map.get(nPnt);
                         
                        if(nCell.time > ntime) {
                             
                            nCell.time = ntime;
                            nCell.energy = energy;
                            que.add(new Point(nrow,ncol));
                             
                        } else if(nCell.time == ntime) {
                             
                            if(nCell.energy < energy) {
                                nCell.energy = energy;
                                que.add(new Point(nrow,ncol));
                            }
                        }
                    }
                }//direction
            }// while(que)
             
            int ans = 0;
            for (Point key : map.keySet() ) {
                Cell cell = map.get(key);
                if(cell.time+cell.energy*2 > K) ans++;
            }
             
            bw.write("#" + tc + " " + ans+ "\n");
        } //tc
 
        bw.flush();
        bw.close();
    }
     
    static class Point {
        int row;
        int col;
        Point(int r, int c) { row = r; col = c; }
         
        @Override
        public int hashCode() {
            int result = row*500 + col*17;
            return result;
        }
         
        @Override
        public boolean equals(Object obj) {
            Point object = (Point)obj;
            return this.row == object.row && this.col ==object.col ;
        }
    }
 
    static class Cell {
        int time; //삽입 시간
        int energy; //에너지
        Cell(int t, int e){ time = t; energy = e; }
    }
 
 
}