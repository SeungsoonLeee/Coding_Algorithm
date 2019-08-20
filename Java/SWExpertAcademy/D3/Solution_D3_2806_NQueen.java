import java.util.Scanner;

public class Solution {
	static int N;
	static int ans;
	static boolean[] checkedCol;
	static boolean[] diagonalRD;
	static boolean[] diagonalLD;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; ++test_case) {
			ans = 0;
			N = sc.nextInt();
			checkedCol = new boolean[N];
			diagonalRD = new boolean[2 * N];
			diagonalLD = new boolean[2 * N];

			dfs(0);

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	public static void dfs(int row) {
		if (row >= N) {
			ans++;
			return;
		}
		for (int col = 0; col < N; col++) {
			if (!checkedCol[col] && !diagonalRD[row - col + N] && !diagonalLD[row + col]) {
				checkedCol[col] = diagonalRD[row - col + N] = true;
				dfs(row + 1);
				checkedCol[col] = diagonalRD[row - col + N] = false;
			}
		}
	}
}
