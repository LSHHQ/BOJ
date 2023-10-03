import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static ArrayList<Student> stu;
    
    public static void main(String[] args) throws IOException {
        stu = new ArrayList<>();
        Map<Integer, Student> hm = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N*N;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            
            int[] like = new int[4];
            for(int j=0;j<4;j++) like[j] = Integer.parseInt(st.nextToken());
            
            Student s = new Student(num, like);
            stu.add(s);
        }
        int[][] empty = new int[N][N]; // 인접한 자리에 빈자리가 몇개인지 표시
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                empty[i][j] = 4;
                if(i == 0 || i == N-1) empty[i][j]--;
                if(j == 0 || j == N-1) empty[i][j]--;
            }
        }
        
        int[][] map = new int[N][N];
        map[1][1] = stu.get(0).num; //첫 학생 배치
        empty[0][1]--;
        empty[2][1]--;
        empty[1][0]--;
        empty[1][2]--;


        for(int k=1;k<N*N;k++){
            Student  s = stu.get(k);
            int[][] like = new int[N][N]; //인접에 친구 몇명인지 표시할 애

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j] != 0) continue;

                    for(int d=0;d<4;d++){
                        int nrow = i + dr[d];
                        int ncol = j + dc[d];
                        
                        if(0 <= nrow && nrow < N && 0 <= ncol && ncol < N) {
                            for (int num : s.friend) {
                                if(num == map[nrow][ncol]) like[i][j]++;
                            }
                        }
                    }
                }
            }

            int max_empty = -1;
            int max_like = -1;
            int row = -1;
            int col = -1;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j] != 0)
                        continue;
                    if(like[i][j] > max_like){
                        max_like = like[i][j];
                        max_empty = empty[i][j];
                        row = i;
                        col = j;
                    } else if(like[i][j] == max_like && empty[i][j] > max_empty){
                        max_empty = empty[i][j];
                        row = i;
                        col = j;
                    }
                }
            }

            map[row][col] = s.num;
            hm.put(s.num, s);
            for(int d=0;d<4;d++){
                int nrow = row + dr[d];
                int ncol = col + dc[d];
                if(0 <= nrow && nrow < N && 0 <= ncol && ncol < N){
                    empty[nrow][ncol]--;
                }
            }

        }


        int score = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int cnt = 0;
                for(int k=0;k<N*N;k++){
                    if(stu.get(k).num == map[i][j]){
                        for(int d=0;d<4;d++){
                            int nrow = i + dr[d];
                            int ncol = j + dc[d];
                            if(0 <= nrow && nrow < N && 0 <= ncol && ncol < N){
                                for(int s_n:stu.get(k).friend){
                                    if(map[nrow][ncol] == s_n){
                                        cnt++;
                                    }
                                }
                            }
                        }

                    }
                }

                if(cnt == 1){
                    score++;
                } else if(cnt == 2){
                    score += 10;
                } else if(cnt == 3){
                    score += 100;
                } else if(cnt == 4){
                    score += 1000;
                }

            }
        }

        System.out.println(score);
    }
    
    static class Student {
    	int num;
    	int[] friend;
    	
    	public Student(int n, int[] l){
    		num = n;
    		friend = l;
    	}
    }
}