import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static long[][] boss;
	static int max;
	static int K;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 보유 캐릭터 갯수
		int M = sc.nextInt(); // 하루에 사용할 캐릭터 갯수
		K = sc.nextInt();

		long[] damageAll = new long[N]; // 캐릭터들의 초당 데미지 배열
		long[] damageHigh = new long[M]; // 가장 센 M개 캐릭터를 강한 순으로 배열

		for (int i = 0; i < N; i++) {
			damageAll[i] = sc.nextLong();
		}

		Arrays.sort(damageAll);

		for (int i = 0; i < M; i++) {
			damageHigh[i] = damageAll[N - 1 - i];
		}

		boss = new long[K][2]; // 0열은 체력, 1열을 메소
		for (int i = 0; i < K; i++) {
			boss[i][0] = sc.nextLong();
			boss[i][1] = sc.nextLong();
		}

		// 캐릭터마다 검사
		int ans = 0;
		for (int i = 0; i < M; i++) {
			max = 0; // 최대보상
			dfs(max, damageHigh[i], 900, 0);
			ans += max;
		}

		System.out.println(ans);
	}

	// sum은 지금까지의 메소합, damage는 현재 캐릭터의 초당 데미지, leftTime은 남은 시간, idx는 몇 번째 보스를 잡고 있는지
	public static void dfs(int sum, long damage, int leftTime, int idx) {
		
		if (idx == K || leftTime <= 0) {
			if (sum > max) {
				max = sum;
			}
			return;
		}

		
		
		// 이 보스를 잡을 지 말지.. 못잡는다면, 잡는다는 선택을 하지 말 것!
		// 잡는다..!
		if(damage * (long)leftTime >= boss[idx][0]) {
			int tmp = leftTime;
			tmp -= boss[idx][0]/(long)damage;
			if(boss[idx][0] % (long)damage > 0) {
				tmp--;
			}
			
			dfs(sum+(int)boss[idx][1], damage, tmp , idx+1);
		}
		
		// 안잡는다..! 모든 값을 그대로 두고, 다음 보스로 넘어가기
		dfs(sum, damage, leftTime, idx + 1);
		
		
	}
}
