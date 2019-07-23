// 100x100 사다리 맵이 주어진다.
// 도착지점이 '2'인 사다리 시작 인덱스를 구해라
// 입력 ex)
// 1 0 0 0 0 1 0 1
// 1 0 0 0 0 1 1 1
// 1 0 0 0 0 1 0 1
// 1 1 1 1 1 1 0 1
// 1 0 0 0 0 1 0 1
// 1 1 1 1 1 1 0 1
// 1 0 0 0 0 1 1 1
// 2 0 0 0 0 1 0 1
//
// 출력 ex)
// #1 0

import java.util.Scanner;

public class Solution_D4_1210_Ladder{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int[][] d = { { 0, -1 }, { 0, 1 } };
		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = sc.nextInt();
			int[][] map = new int[100][100];
			int r = 0, c = 0;

			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 2) {
						r = i;
						c = j;
					}
				}
			}

			while (r > 0) {
				int nc;
				for (int i = 0; i < 2; ++i) {
					nc = c + d[i][1];
					if(nc < 0 || nc >= 100) continue;
					if(map[r][nc] == 1) {
						while (map[r][nc] == 1) {
							nc += d[i][1];
							if(nc < 0 || nc >= 100) break;
						}
						nc -= d[i][1];
						c = nc;
						break;
					}
				}
				r--;
			}
			
			System.out.printf("#%d %d\n", n, c);
		}

	}
}
