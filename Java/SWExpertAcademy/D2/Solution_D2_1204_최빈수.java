// 1000개의 숫자를 입력 받았을 때 최빈수를 구하라.

import java.util.Scanner;

public class Solution_D2_1204_최빈수 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			int result = 0, maxCount = 0;
			int[] count = new int[150];

			for (int i = 0; i < 1000; ++i) {
				int num = sc.nextInt();
				count[num]++;
			}

			for (int i = 0; i <= 100; ++i) {
				if (maxCount <= count[i]) {
					maxCount = count[i];
					result = i;
				}
			}
			
			System.out.printf("#%d %d\n", test_case, result);
		}
	}
}