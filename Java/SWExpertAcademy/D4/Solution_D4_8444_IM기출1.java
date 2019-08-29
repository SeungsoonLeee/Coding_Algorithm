package com.lss.swExpertAcademy;

import java.util.Scanner;

public class Solution_D4_8444_IM기출1 {
	static int N, ans;
	static int[] room;
	static int[] tempRoom;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			ans = 0;
			N = sc.nextInt();
			room = new int[N];
			tempRoom = new int[N];

			for (int i = 0; i < N; i++) {
				room[i] = sc.nextInt();
			}

			for (int i = 0; i < N; i++) {
				if (room[i] != tempRoom[i]) {
					switchOn(i);
					ans++;
				}
			}

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	private static void switchOn(int i) {
		for (int j = i; j < N; j = j + (i + 1)) {
			tempRoom[j] = tempRoom[j] == 0 ? 1 : 0;
		}
	}
}
