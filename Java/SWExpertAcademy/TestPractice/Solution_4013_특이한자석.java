import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int N, K, C;
	static int[][] magnet;
	static int[] rotateM;
	static int[] rotateD;
	static boolean[] check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			K = sc.nextInt();
			C = 4;
			magnet = new int[C][8];
			check = new boolean[C];
			// N : 0, S : 1
			for (int i = 0; i < C; i++) {
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = sc.nextInt();
				}
			}

			rotateM = new int[K];
			rotateD = new int[K];
			for (int i = 0; i < K; i++) {
				rotateM[i] = sc.nextInt() - 1;
				rotateD[i] = sc.nextInt();
			}

			for (int i = 0; i < K; i++) {
				rotateMagnet(rotateM[i], rotateD[i]);
				Arrays.fill(check, false);
//				for (int ii = 0; ii < C; ii++) {
//					for (int j = 0; j < 8; j++) {
//						System.out.print(magnet[ii][j]);
//					}
//					System.out.println();
//				}
//				System.out.println();
			}

			System.out.printf("#%d %d\n", test_case, CalScore());
		}
	}

	private static void rotateMagnet(int num, int d) {
//		System.out.println(num + " " + d);
		check[num] = true;
		// 2 & 6
		if (d > 0) { // 시계방향
			int temp = magnet[num][7];
			for (int i = 7; i > 0; i--) {
				magnet[num][i] = magnet[num][i - 1];
			}
			magnet[num][0] = temp;
			if (num == 0) {
				if (!check[num + 1] && magnet[num][3] != magnet[num + 1][6])
					rotateMagnet(num + 1, d > 0 ? -1 : 1);
			} else if (num == C - 1) {
				if (!check[num - 1] && magnet[num][7] != magnet[num - 1][2])
					rotateMagnet(num - 1, d > 0 ? -1 : 1);
			} else {
				if (!check[num + 1] && magnet[num][3] != magnet[num + 1][6])
					rotateMagnet(num + 1, d > 0 ? -1 : 1);
				if (!check[num - 1] && magnet[num][7] != magnet[num - 1][2])
					rotateMagnet(num - 1, d > 0 ? -1 : 1);
			}
		} else { // 반시계방향
			int temp = magnet[num][0];
			for (int i = 0; i < 7; i++) {
				magnet[num][i] = magnet[num][i + 1];
			}
			magnet[num][7] = temp;
			if (num == 0) {
				if (!check[num + 1] && magnet[num][1] != magnet[num + 1][6])
					rotateMagnet(num + 1, d > 0 ? -1 : 1);
			} else if (num == C - 1) {
				if (!check[num - 1] && magnet[num][5] != magnet[num - 1][2])
					rotateMagnet(num - 1, d > 0 ? -1 : 1);
			} else {
				if (!check[num + 1] && magnet[num][1] != magnet[num + 1][6])
					rotateMagnet(num + 1, d > 0 ? -1 : 1);
				if (!check[num - 1] && magnet[num][5] != magnet[num - 1][2])
					rotateMagnet(num - 1, d > 0 ? -1 : 1);
			}
		}
	}

	private static int CalScore() {
		int score = 0;
		for (int i = 0; i < C; i++) {
			if (magnet[i][0] == 1)
				score += 1 * Math.pow(2, i);
		}
		return score;
	}
}
