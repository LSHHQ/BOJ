import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		list = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			list.add(new LinkedList<Integer>());
		}

		for (int i = 0; i < 4; i++) {
			char[] NS = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				list.get(i).addLast(NS[j] - '0');
			}
		}

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int cogNum = Integer.parseInt(st.nextToken()) - 1; 
			int dir = Integer.parseInt(st.nextToken());
			visited = new boolean[4];
			revolve(cogNum, dir);
		}

		int sum = 0;
		for (int i = 0; i < 4; i++)
			sum += list.get(i).get(0) * Math.pow(2, i);

		System.out.println(sum);

	}

	static List<LinkedList<Integer>> list = new ArrayList<>();
	static boolean[] visited = new boolean[4];

	static void revolve(int cogNum, int dir) {

		if (visited[cogNum])
			return;

		visited[cogNum] = true;

		int myRight = list.get(cogNum).get(2);
		int myLeft = list.get(cogNum).get(6);
		int yourRight = myLeft;
		int yourLeft = myRight;
		if (cogNum - 1 >= 0)
			yourRight = list.get(cogNum - 1).get(2);
		if (cogNum + 1 < 4)
			yourLeft = list.get(cogNum + 1).get(6);

		if (dir == 1)
			list.get(cogNum).addFirst(list.get(cogNum).pollLast());
		else if (dir == -1)
			list.get(cogNum).addLast(list.get(cogNum).pollFirst());

		if (myRight != yourLeft)
			revolve(cogNum + 1, -dir);

		if (myLeft != yourRight)
			revolve(cogNum - 1, -dir);

		return;
	}

}
