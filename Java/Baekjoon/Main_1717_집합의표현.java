import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		parents = new int[N + 1];

		Arrays.fill(parents, -1);

		for (int i = 0; i < M; i++) {
			int op = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();

			if (op == 0) {
				union(a, b);
			} else {
				if (find(a) == find(b))
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	private static int find(int f) {
		if (parents[f] < 0) // 음수인 경우 root라는 의미이므로 자기 자신을 리턴
			return f;
		return parents[f] = find(parents[f]); // path compression
	}
}
