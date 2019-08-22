
package com.lss.swExpertAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_7699_수지의수지맞는여행 {
	static int[][] map;
	static boolean[] checked;
	static int R, C, ans, cnt;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input/Solution_7699_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			ans = 0;
			cnt = 1;
			checked = new boolean[26];
			R = sc.nextInt();
			C = sc.nextInt();
			map = new int[R][C];

			for (int i = 0; i < R; i++) {
				String temp = sc.next();
				for (int j = 0; j < C; j++) {
					map[i][j] = temp.charAt(j) - 'A';
//					System.out.print(map[i][j] + " ");
				}
//				System.out.println();
			}

			checked[map[0][0]] = true;
			dfs(0, 0);
			System.out.printf("#%d %d\n", test_case, ans);
		}

	}

	public static void dfs(int y, int x) {
		if (ans < cnt)
			ans = cnt;
//		System.out.print((char)(map[y][x]+'A') + " ");

		for (int i = 0; i < 4; ++i) {
			int nextY = y + dir[i][0];
			int nextX = x + dir[i][1];
			if (nextX >= 0 && nextY >= 0 && nextX < C && nextY < R && !checked[map[nextY][nextX]]) {
				checked[map[nextY][nextX]] = true;
				cnt++;
				dfs(nextY, nextX);
				checked[map[nextY][nextX]] = false;
				cnt--;
			}
		}
	}
}
