import java.util.Scanner;

public class Main{
	static int R, C;
	static int[][] map;
	static boolean check[];
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int ans, cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		ans = 0;
		cnt = 0;
		map = new int[R][C];
		check = new boolean[26];

		for (int i = 0; i < R; i++) {
			String line = sc.next();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j) - 'A';
				// System.out.print(map[i][j] + " ");
			}
			// System.out.println();
		}

		dfs(0, 0);
		System.out.println(ans);
	}

	private static void dfs(int r, int c) {
		cnt++;
		if (ans < cnt)
			ans = cnt;
		check[map[r][c]] = true;
		for (int i = 0; i < 4; i++) {
			int nextR = r + dir[i][0];
			int nextC = c + dir[i][1];
			if (nextR >= R || nextC >= C || nextR < 0 || nextC < 0)
				continue;
			if (check[map[nextR][nextC]])
				continue;
			dfs(nextR, nextC);
			cnt--;
			check[map[nextR][nextC]] = false;
		}
	}
}
