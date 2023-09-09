import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	private static void solution() throws Exception {
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int low = 0, high = n - 1;
		int min = Integer.MAX_VALUE;
		
		int left = 0, right = 0;
		
		while (low < high) {
			
			int sum = Math.abs(arr[high] + arr[low]);
			
			if (sum < min) {
				min = sum;
				left = low;
				right = high;
			}
		
			if (sum == 0)
				break;

			if (arr[high] + arr[low] > 0)
				high--;
			
			else
				low++;
		}

		System.out.println(arr[left] + " " + arr[right]);
	}

	public static void main(String[] args) throws Exception {
		solution();
	}
}