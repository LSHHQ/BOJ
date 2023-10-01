import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		//기본적으로 i번 비행기는 i번 게이트에 배정시킨다.
		parent = new int[G+1];
		for (int i = 0; i <= G; i++) parent[i] = i;
		
		
		int ans = 0;
		for (int i = 0; i < P; i++) {
			int aircraft = Integer.parseInt(br.readLine()); //비행기 번호를 받아오면서
			int gate = find(aircraft); //해당 비행기 번호가 도킹돼있는 곳을 찾음. 
			
			if(gate==0) break;
			// find했을때 0이랑 연결됐으면 해당 비힝기는 이제 연결이 불가한거다. >> 비행기가 도착 못하면 뒤에꺼 볼 필요도 없이 공항이 폐쇄

			ans++; //비행기 도킹 수 증가
			union(gate, gate-1); // i번 비행기가 등장할 때마다 다음에 같은 비행기가 -1번째 게이트에 향하도록 유니온 해준다.
		}
		
		System.out.println(ans);
		
	} // main
	
	static int G, P;
	static int[] parent;
	
	static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x!=y) parent[x] = y; //y,x 위치 바뀌면 틀리게 된다.(더 작은 수가 부모가 되게 해야함.
	}
	
} // class