import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;


	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine()); // 계란 수

		eggs = new egg[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i] = new egg();
			eggs[i].durable = Integer.parseInt(st.nextToken()); // 내구도
			eggs[i].Weight = Integer.parseInt(st.nextToken()); // 무게
		}
		max = 0;
		Solve(0, 0);

		bw.write(String.valueOf(max));

		bw.flush();
		bw.close();
	}// main

	/*
	 * 1. 인덱스 앞에 있는 계란부터 들고 임의의 깨지지 않은 계란과 부딪힘. >> 상대방의 무게만큼 내구도가 감소, 0이하 되면 깨짐 2.
	 * 바로 다음 인덱스에 있는 계란을 들고 반복, 가장 오른쪽 계란에 도달하면 종료 3. 최대한 많은 계란을 꺠는 경우에서 몇개의 계란이
	 * 깨지는지 출력
	 */

	static int N;
	static egg[] eggs;
	static int max;

	static void Solve(int index, int broken) {
		if (index == N) {
			return;
		}

		if (eggs[index].durable <= 0) {
			Solve(index + 1, broken);
			return;
		}

		for (int i = 0; i < N; i++) {

			if (i == index || eggs[i].durable <= 0)
				continue;

			boom(eggs[index], eggs[i]);

			int nowBroken = 0;
			
			if (eggs[index].durable <= 0)
				nowBroken++;

			if (eggs[i].durable <= 0)
				nowBroken++;

			max = Math.max(max, broken + nowBroken);
			Solve(index + 1, broken + nowBroken);
			recover(eggs[index], eggs[i]);
		}
	}

	static class egg {
		int durable;
		int Weight;
	}
	
	static void boom(egg A, egg B) {
		A.durable -= B.Weight;
		B.durable -= A.Weight;
	}

	static void recover(egg A, egg B) {
		A.durable += B.Weight;
		B.durable += A.Weight;
	}

}// class
