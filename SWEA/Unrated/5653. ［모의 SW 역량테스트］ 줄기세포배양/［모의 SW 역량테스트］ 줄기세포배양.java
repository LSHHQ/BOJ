import java.io.*;
import java.util.*;

public class Solution {
/* 
#1 22
#2 36
#3 90
#4 164
#5 712
 */

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
            //줄기세포가 분포된 세로크기 N, 가로크기 M
            int H = stoi(st.nextToken()); // 세로 크기
            int W = stoi(st.nextToken()); // 가로 크기
            int K = stoi(st.nextToken()); // K시간에 살아있는 세포(비활성 + 활성) 수 출력(배양시간)
            
            // 활성화되는 시각을 기준으로 PQ를 활용하자.
            // 활성시각 - 에너지 = 삽입시각, 활성시각 + 에너지 = 죽는 시각

            int size = 700;
            int s = 325;
            Cell[][] map = new Cell[size][size];
            Queue<Point> que = new LinkedList<>();
            
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    int energy = stoi(st.nextToken());
                    if(energy!=0){
                        map[s + i][s + j] = new Cell(0, energy);
                        que.add(new Point(s+i,s+j));
                    }
                }
            }
            
            while(!que.isEmpty()) {
            	
            	int row = que.peek().row;
            	int col = que.peek().col;
            	int time = map[row][col].time;
            	int energy = map[row][col].energy;
            	
            	que.poll();
            	
            	for (int i = 0; i < 4; i++) {
            		int nrow = row+dr[i];
            		int ncol = col+dc[i];
            		
            		int ntime = time + energy + 1;
            		
            		if(ntime>K) continue;
            		
            		if(map[nrow][ncol]==null) {
						map[nrow][ncol] = new Cell(ntime, energy);
						que.add(new Point(nrow, ncol));
            		}else {
            			
            			//if(map[nrow][ncol].time < ntime) continue;
            			if(map[nrow][ncol].time > ntime) {
            				
            				map[nrow][ncol].time = ntime;
            				map[nrow][ncol].energy = energy;
            				que.add(new Point(nrow,ncol));
            				
            			} else if(map[nrow][ncol].time == ntime) {
            				
            				if(map[nrow][ncol].energy < energy) {
            					map[nrow][ncol].energy = energy;
            					que.add(new Point(nrow,ncol));
            				}
            				
            			}
            		}
				}//direction
            }// while(que)
            
            int ans = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(map[i][j]==null) continue;
                    if(map[i][j].time + map[i][j].energy*2 > K) ans++;
                }
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
    }

    static class Cell {
        int time; //삽입 시간
        int energy; //에너지
        Cell(int t, int e){ time = t; energy = e; }
    }

    static void print(Cell[][] map){
        int min = 315;
        int max = 340;
        for (int i = min; i < max; i++) {
            for (int j = min; j < max; j++) {
                if(map[i][j]==null) System.out.print("0 ");
                else System.out.print(map[i][j].energy+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}