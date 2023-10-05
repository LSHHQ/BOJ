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
				list.get(i).addLast(NS[j] - '0'); // 0: 12시 ... 2:3시(오른쪽 인접)....6: 9시(왼쪽 인접)
			}
		}

		int N = Integer.parseInt(br.readLine()); // 바퀴 회전 수
		for (int i = 0; i < N; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int cogNum = Integer.parseInt(st.nextToken()) - 1; // 인덱스에 맞추기 위해 -1
			int dir = Integer.parseInt(st.nextToken()); // 1: 시계., -1:반시계
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

	static int revolve(int cogNum, int dir) { // 이걸로 재귀식으로 옆에있는애 돌려버리고, 그애도 다른애 돌릴 수 있는데 옆에 애 돌렸을때 다시 돌아와서 우리 애 돌릴까봐 ..

		if (visited[cogNum])
			return 0;

		visited[cogNum] = true; // 다시 돌아와서 돌리는거 방지

		int myRight = list.get(cogNum).get(2);
		int myLeft = list.get(cogNum).get(6);
		int yourRight = myLeft;
		int yourLeft = myRight;
		if (cogNum - 1 >= 0)
			yourRight = list.get(cogNum - 1).get(2);
		if (cogNum + 1 < 4)
			yourLeft = list.get(cogNum + 1).get(6);

//		System.out.print(cogNum+1+"   ");
//		for (int i = 0; i < 8; i++) {
//			System.out.print(list.get(cogNum).get(i)+" ");
//		}
//		System.out.println();

		if (dir == 1)
			list.get(cogNum).addFirst(list.get(cogNum).pollLast());
		else if (dir == -1)
			list.get(cogNum).addLast(list.get(cogNum).pollFirst());

//		System.out.print(cogNum+1+"   ");
//		for (int i = 0; i < 8; i++) {
//			System.out.print(list.get(cogNum).get(i)+" ");
//		}
//		System.out.println();

		if (myRight != yourLeft)
			revolve(cogNum + 1, -dir); //여기서 한쪽만 돌려버리고 끝나버림, 양쪽 다 돌릴수도 있는데..

		if (myLeft != yourRight)
			revolve(cogNum - 1, -dir);

		return 0;
	}

}
