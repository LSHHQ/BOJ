import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 총캐릭
		M = Integer.parseInt(st.nextToken()); // 선택캐릭
		K = Integer.parseInt(st.nextToken()); // 보스수

		dps = new long[N];
		HP = new long[K];
		meso = new long[K];

		for (int i = 0; i < N; i++)
			dps[i] = Long.parseLong(br.readLine());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			HP[i] = Long.parseLong(st.nextToken());
			meso[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(dps);

		long ans = 0;

		for (int i = 0; i < M; i++) {
			maxMeso = 0;
			solution(dps[N - 1 - i], 15 * 60, 0, 0);
			ans += maxMeso;
		}

		System.out.println(ans);

	}

	static int N, M, K; // 캐릭터 수, 하루 사용캐릭터 수, 보스의 수
	static long[] dps; // 캐릭터 하나가 15분동안 가하는 데미지
	static long[] HP; // 보스의 HP
	static long[] meso; // 보스의 value
	static long maxMeso;

	// 보스를 잡아 얻을 수 있는 최대 메소를 출력
	// 조합문제인데 보스를 몇마리 픽할지 정해지지 않았기 때문에 모든 경우를 탐색해야됌

	static void solution(long dps, long leftSec, long gotMeso, int idx) {
		if (gotMeso > maxMeso)
			maxMeso = gotMeso;
		
		if(idx==K || leftSec==0)
			return;

		long a = HP[idx] % dps == 0 ? 0 : 1;
		long usedSec = (HP[idx] / dps) + a;

		solution(dps, leftSec, gotMeso, idx + 1);

		if (leftSec < usedSec)
			return;
		
		solution(dps, leftSec - usedSec, gotMeso + meso[idx], idx + 1);

	}// solution()

}
