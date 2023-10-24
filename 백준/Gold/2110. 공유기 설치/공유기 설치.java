import java.io.*;
import java.util.*;

public class Main {
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		// 가장 인접한 두 공유기 사이의 거리를 가능한 크게 배정
		
		// 최대거리를 맥시멈-미니멈으로 설정해놓고 설치가능한지 확인해본다
		// 안되면 최대거리를 그 반으로 설정한다
		// >> 위와같은 방식으로 이분탐색을 수행한다.
		
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		C = stoi(st.nextToken());
		
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = stoi(br.readLine());
		}
		
		Arrays.sort(arr);
		
		// Upper-bound
		int left = 1;
		int right = arr[N-1]-arr[0];
		int ans = 0;

		while(left<=right) {
			int mid = (left+right)>>1;
			if(CanInstall(mid)) {
				ans = mid;
				left = mid+1;
			}
			else right = mid-1;
		}
		
		System.out.println(ans);
	}
	
	static int N,C;
	static int[] arr;
	
	static boolean CanInstall(int dis) {
		int count = 1; //설치한 횟수
		int pre = arr[0];
		
		for (int i = 1; i < N; i++) {
			int now = arr[i];
			if(now-pre>=dis) {
				pre = now;
				if(++count==C) return true;
			}
		}
		
		return false;
	}
	
}
