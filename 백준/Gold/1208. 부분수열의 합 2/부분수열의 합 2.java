import java.io.*;
import java.util.*;
import java.util.function.Function;

// 좋은 풀이 저장
// 이분탐색 안하고 한쪽 배열에서 나올 수 있는 합을 구해놓고
// 다른쪽 배열에서 나올 수 있는 합과 조합했을 때 S 가 나오면 카운트하는 방식

public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, m;
	static long res;
	static int[] nums;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = stoi.apply(st.nextToken());
		m = stoi.apply(st.nextToken());

		nums = new int[n];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = stoi.apply(st.nextToken());
		}

		map = new HashMap<>();
		res = 0;
		right(n / 2, 0);
		left(0, 0);

		System.out.println(m == 0 ? res - 1 : res);
	}

	private static void left(int idx, int sum) {
		if (idx == n / 2) {
			if(map.get(m - sum) != null) {
			res += map.get(m - sum);
			}
			return;
		}
		left(idx + 1, sum);
		left(idx + 1, sum + nums[idx]);
	}

	private static void right(int idx, int sum) {
		if (idx == n) {
			map.put(sum, map.getOrDefault(sum, 0) + 1);
			return;

		}
		right(idx + 1, sum);
		right(idx + 1, sum + nums[idx]);
	}
}