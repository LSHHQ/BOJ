import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		// 사다리를 어떻게 표현할거임?
		
		// 각 세로선마다 배열을 가지고 있으면서
		// 방향이 바뀔떄와 그 방향을 알 수 있으면 된다.
		
		st = new StringTokenizer(br.readLine());
		W = stoi(st.nextToken()); // 세로 선 갯수
		M = stoi(st.nextToken()); // 이미 존재하는 가로선 갯수
		H = stoi(st.nextToken()); // 가로선을 놓을 수 있는 위치의 갯수 >> 높이
		
		map = new int[H][W];
		for (int i = 0; i < H; i++) Arrays.fill(map[i], -1);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken())-1;
			int b = stoi(st.nextToken())-1;
			map[a][b] = b+1;
			map[a][b+1] =b;
		}
		
		ans = 10;
		func(0,0,0);
		System.out.println(ans == 10? -1 : ans);
		
	}
	static int W,M,H,ans;
	static int[][] map;
	
	static void func(int row, int col, int count) {
		
		if(count>=ans) return;
		
		if(accept()) {
			ans = Math.min(count, ans);
			return;
		}
		
		if(count==3) return;
		
		if(row==H) {
			row = 0;
			if(++col==W-1) return;
		}
		
		int now = map[row][col];
		int next = map[row][col+1];
		int a = col-1 >= 0? map[row][col-1] : -1;
		int b = col+2 <  W? map[row][col+2] : -1;
		
		if(now==-1 && next==-1 && a!=col && b!=col+1) {
			map[row][col] = col+1;
			map[row][col+1] = col;
//			print();
			func(row+1,col, count+1);
			map[row][col] = -1;
			map[row][col+1] = -1;
			func(row+1,col, count);
		}else {
			func(row+1,col,count);
		}
		
		
	}
	
	static boolean accept() {

		for (int stt = 0; stt < W; stt++) {
			int row = 0;
			int col = stt;
			int now = map[row][col];
			
			while(row!=H) {
				now = map[row][col];
				if(now!=-1) col = now;
				row++;
			}
			
			if(col!=stt) return false;
		}
		
		return true;
	}
	
	static void print() {
		System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.printf("%3d",map[i][j]);
			}
			System.out.println();
		}
		
	}
	
	static int stoi(String s) {return Integer.parseInt(s);}
}