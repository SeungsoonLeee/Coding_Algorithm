import java.util.Scanner;

public class Solution {
	static int N;
	static int ans;
	static boolean[] checkedCol;
	static int[][] diagonalMap;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; ++test_case) {
			ans = 0;
			N = sc.nextInt();
			checkedCol = new boolean[N];
			diagonalMap = new int[N][N];

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
			if (!checkedCol[col] && diagonalMap[row][col] == 0) {
				checkedCol[col] = true;
				int tempR = row;
				int tempC = col;
				while (tempR < N && tempC >= 0) { // 왼쪽 아래 대각선
					diagonalMap[tempR++][tempC--] += 1;
				}
				tempR = row;
				tempC = col;
				while (tempR < N && tempC < N) { // 오른쪽 아래 대각선
					diagonalMap[tempR++][tempC++] += 1;
				}
				dfs(row + 1);
				checkedCol[col] = false;
				tempR = row;
				tempC = col;
				while (tempR < N && tempC >= 0) { // 왼쪽 아래 대각선
					diagonalMap[tempR++][tempC--] -= 1;
				}
				tempR = row;
				tempC = col;
				while (tempR < N && tempC < N) { // 오른쪽 아래 대각선
					diagonalMap[tempR++][tempC++] -= 1;
				}
			}
		}
	}
}
