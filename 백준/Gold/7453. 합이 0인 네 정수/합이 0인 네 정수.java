import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = stoi(br.readLine());
        int[][] arr = new int[4][N];
        
        
        for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				arr[j][i] = stoi(st.nextToken());
			}
		}
        
        // 4개의 배열을 2개 그룹으로 나눈다.
        // 각 그룹에 존재하는 두개의 배열을 합해서 나오는 수를 모두 리스트에 저장한다.
        // 정렬한다.
        // 정렬된 두 배열로 투포인트 써서 풀자.
        
        
        int[] first = new int[N*N];
        int[] second = new int[N*N];
        int index = 0;
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				first[index] = arr[0][i] + arr[1][j];
				second[index++] = arr[2][i] + arr[3][j];
			}
		}
        
        Arrays.sort(first);
        Arrays.sort(second);
        
//        int L = 0;
//        int R = N*N - 1;
        long ans = 0;
        
//        while(L<N*N && R>=0) {
//        	
//        	int sum = first[L]+second[R];
//        	
//        	if(sum>0) {
//        		R--;
//        	}else if(sum<0) {
//        		L++;
//        	}else {
//        		//val==0일때 중복되는 수가 몇개있는지 찾고 중복되는수 * 중복되는수 만큼 답에 더해줌.
//        		//그리고 나서 포인터 위치 조정
//        		int fCnt = 1;
//        		int sCnt = 1;
//        		
//        		//upper-bound
//        		//현재 밸류와 크기가 같은 인덱스 중 제일 큰 값을 찾기
//        		int left = L;
//        		int right = N*N-1;
//        		int idx = L;
//        		while(left<=right) {
//        			int mid = (left+right)>>1;
//        			
//        			if(first[mid]>first[L]) {
//        				right = mid-1;
//        			}else {
//        				idx = mid;
//        				left = mid+1;
//        			}
//        		}
//        		
//        		fCnt = idx+1 - L;
//        		L = idx+1;
//        		
//        		//lower-bound
//        		left = 0;
//        		right = R;
//        		idx = R;
//        		while(left<=right) {
//        			int mid = (left+right)>>1;
//        			
//        			if(second[mid]<second[R]) {
//        				left = mid+1;
//        			}else {
//        				idx = mid;
//        				right = mid-1;
//        			}
//        		}
//        		sCnt = R- (idx-1);
//        		R = idx-1;
//        		
//        		ans+= fCnt * sCnt;
//        	}
//        }
        
        for (int f : first) {
            // second 배열에서 -f의 개수를 찾아서 ans에 추가
            int left = 0, right = N * N;
            while (left < right) {
                int mid = (left + right) / 2;
                if (second[mid] < -f) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            int lower = right;
            left = 0;
            right = N * N;
            while (left < right) {
                int mid = (left + right) / 2;
                if (second[mid] <= -f) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            int upper = right;
            ans += (long)(upper - lower);
        }
        
        System.out.println(ans);
        
    }
    
}