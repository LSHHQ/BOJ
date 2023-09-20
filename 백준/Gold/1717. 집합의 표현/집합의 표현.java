import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집합의 수
		int M = Integer.parseInt(st.nextToken()); // 연산의 수

		// 0 : a집합과 b집합을 합침
		// 1 : a와 b가 같은집합에 속하는지 확인
		// a와 b는 n이하의 자연수이며 같을 수 있음

		P = new int[N + 1];
		for (int i = 1; i < N + 1; i++)
			P[i] = i;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			if (command == 0) {
				union(Math.min(A, B), Math.max(A, B));
			} else if (command == 1) {
				bw.write(check(A, B) ? "YES" : "NO");
				bw.write("\n");
			}
		}
		bw.flush();
		bw.close();
	}

	static int[] P;

	// 부모의 부모를 찾는 과정, 찾으면서 path compression 수행
	static int findset(int x) {
		if (x != P[x])
			P[x] = findset(P[x]);
		return P[x];
	}

	static void union(int x, int y) {
		P[findset(y)] = findset(x);
	}

	static boolean check(int x, int y) {
		if (findset(y) == findset(x))
			return true;
		return false;
	}

}// class
