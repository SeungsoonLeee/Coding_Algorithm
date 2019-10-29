import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, sr, sc, ans;
	static int[][] map;
	static boolean[] check;
	static int[][] dir = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = -1;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			check = new boolean[101];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
//					System.out.print(map[i][j] + " ");
				}
//				System.out.println();
			}

			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
//					Arrays.fill(check, false);
					func(i, j);
				}
			}

			System.out.printf("#%d %s\n", test_case, ans);
		}
	}

	private static void func(int r, int c) {
//		check[map[r][c]] = true;
		int nextR, nextC, nextR2, nextC2, nextR3, nextC3, nextR4, nextC4;

		for (int i = 1; i <= N; i++) {
			nextR = r + dir[0][0] * i;
			nextC = c + dir[0][1] * i;
			if (isSide(nextR, nextC))
				break;
//			check[map[nextR][nextC]] = true;

			for (int j = 1; j <= N; j++) {
				nextR2 = nextR + dir[1][0] * j;
				nextC2 = nextC + dir[1][1] * j;
				if (isSide(nextR2, nextC2)) {
					break;
				}
//				check[map[nextR2][nextC2]] = true;

				for (int a = 1; a <= N; a++) {
					nextR3 = nextR2 + dir[2][0] * a;
					nextC3 = nextC2 + dir[2][1] * a;
					if (isSide(nextR3, nextC3)) {
						break;
					}
//					check[map[nextR3][nextC3]] = true;

					for (int b = 1; b <= N; b++) {
						nextR4 = nextR3 + dir[3][0] * b;
						nextC4 = nextC3 + dir[3][1] * b;
						if (nextR4 == r && nextC4 == c) {
							Arrays.fill(check, false);
							if (checkRC(nextR, nextC, nextR2, nextC2, nextR3, nextC3, nextR4, nextC4))
								break;
							int temp = i + j + a + b;
							if (ans < temp)
								ans = temp;
							break;
						}
						if (isSide(nextR4, nextC4)) {
							break;
						}
//						check[map[nextR4][nextC4]] = true;
					}

				}

			}

		}
	}

	private static boolean checkRC(int nextR, int nextC, int nextR2, int nextC2, int nextR3, int nextC3, int nextR4,
			int nextC4) {
		// { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 }
		int tempR = nextR4, tempC = nextC4;
		while (nextR4 < nextR && nextC4 < nextC) {
			if (check[map[nextR4][nextC4]])
				return true;
			check[map[nextR4++][nextC4++]] = true;
		}
		while (nextR < nextR2 && nextC > nextC2) {
			if (check[map[nextR][nextC]])
				return true;
			check[map[nextR++][nextC--]] = true;
		}
		while (nextR2 > nextR3 && nextC2 > nextC3) {
			if (check[map[nextR2][nextC2]])
				return true;
			check[map[nextR2--][nextC2--]] = true;
		}
		while (nextR3 > tempR && nextC3 < tempC) {
			if (check[map[nextR3][nextC3]])
				return true;
			check[map[nextR3--][nextC3++]] = true;
		}

		return false;
	}

	private static boolean isSide(int r, int c) {
		if (r >= N || c >= N || r < 0 || c < 0)
			return true;
//		if (check[map[r][c]]) {
//			return true;
//		}
		return false;
	}
}
