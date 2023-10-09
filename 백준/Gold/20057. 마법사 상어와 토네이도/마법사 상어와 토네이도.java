import java.io.*;
import java.util.*;

public class Main {

	static int stoi(String s) {return Integer.parseInt(s);}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = stoi(br.readLine());
		map = new int[N][N];
		out = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		int[] pnt = {N/2, N/2};
		
		int[] dr = {0,1,0,-1};
		int[] dc = {-1,0,1,0};
		int dir = 0;
		boolean[][] visited = new boolean[N][N];
		visited[pnt[0]][pnt[1]] = true;
		
		while(!visited[0][0]) {
			
			pnt[0] += dr[dir];
			pnt[1] += dc[dir];
			spread(dir, pnt);
			visited[pnt[0]][pnt[1]]=true;
			
			int ndir = (dir+1)%4;
			int nrow = pnt[0] + dr[ndir];
			int ncol = pnt[1] + dc[ndir];
			if(!visited[nrow][ncol]) dir = ndir;
			
		}
		
		System.out.println(out);
		
		
		
	}// main
	
	static int N, out;
	static int[][] map;
	
	// 왼쪽으로 향했을때의 기준으로 각 방향벡터를 짜자
	// 기준: 새로 이동할 좌표로 부터 떨어진 거리
	// 알파, 5, 10, 7, 1, 2 순서

	// 좌측으로 향할때의 기준
	//                  { a,  5,   10,  7,  1,  2}
	static int[] sdr  = { 0,  0,   -1, -1, -1, -2,    1,  1,  1, 2 };
	static int[] sdc  = {-1, -2,   -1,  0,  1,  0,   -1,  0,  1, 0 };
	
	// 우측으로 향할때: dc의 부호만 바꿔준다.
	// 위쪽으로 향할떄: dr과 dc를 바꾼다.
	// 아래쪽ㅇ 향할때: dc 부호를 바꾼다. dr과 dc를 역할을 바꾼다.
	
	static void spread(int dir, int[] point) {
		int row = point[0];
		int col = point[1];
		
		int[][] delta =  getDelta(dir);
		int[] dr = delta[0];
		int[] dc = delta[1];
		int[] portion = getPortion(map[row][col]);
		map[row][col] = 0;
		
		for (int i = 0; i < 10; i++) {
			int nrow = row+dr[i];
			int ncol = col+dc[i];
			
			//바깥에 나갔을 때
			if(nrow<0 || ncol<0 || ncol>=N || nrow >=N) {
				out += portion[i];
				continue;
			}
			
			map[nrow][ncol] += portion[i];
		}
		
	}
	
	static int[][] getDelta(int dir) {
		int[][] delta = new int[2][10]; // 0 : dr, 1 : dc
		
		if(dir==0) { //좌
			delta[0] = sdr;
			delta[1] = sdc;
		}else if(dir==1) { //하
			for (int i = 0; i < 10; i++) delta[0][i] = -sdc[i];
			delta[1] = sdr;
		}else if(dir==2) { //우
			delta[0] = sdr;
			for (int i = 0; i < 10; i++) delta[1][i] = -sdc[i];
		}else if(dir==3) { //상
			delta[0] = sdc;
			delta[1] = sdr;
		}
		
		return delta;
	}
	
	static int[] getPortion (int amount) {
		int[] portion = new int[10];
		// a, 5, 10, 7, 1, 2
		portion[0] = amount;
		portion[1] = amount/20;
		
		portion[2] = amount/10;
		portion[3] = (int)(amount*0.07);
		portion[4] = amount/100;
		portion[5] = amount/50;
		
		portion[6] = amount/10;
		portion[7] = (int)(amount*0.07);
		portion[8] = amount/100;
		portion[9] = amount/50;
		
		for (int i = 1; i <= 9; i++) portion[0]-=portion[i];
		return portion;
	}
	
	
	static void print(int[][] board) {
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.printf("%3d",board[i][j]);
			}
			System.out.println();
		}
	}
	
}
