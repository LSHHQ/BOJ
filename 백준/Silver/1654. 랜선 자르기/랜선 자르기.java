import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선
		int N = Integer.parseInt(st.nextToken()); // 필요한 랜선

		int[] arr = new int[K];

		long high =0;
		long low =0;
		long mid;

		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			high = Math.max(arr[i], high);
		}

		high++;

		while (low < high) {
			long ea = 0;
			mid = (high + low) / 2;

			for (int i = 0; i < K; i++) {
				ea += arr[i] / mid;
			}

			if (ea < N) {
				high = mid;

			} else if (ea >= N) {
				low = mid + 1;
			}

		}

		System.out.print(low - 1);
	}
}
