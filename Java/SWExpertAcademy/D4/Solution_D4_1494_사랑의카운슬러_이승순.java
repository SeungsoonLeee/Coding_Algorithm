package com.ssafy.solve;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_D4_1494_사랑의카운슬러_이승순 {
	static int N;
	static Worm[] worms;
	static int[] nums;
	static long totalSumX;
	static long totalSumY;
	static long ans;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input2.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			N = sc.nextInt();
			worms = new Worm[N];
			nums = new int[N / 2];
			ans = Long.MAX_VALUE;
			totalSumX = 0;
			totalSumY = 0;
			for (int i = 0; i < N; i++) {
				worms[i] = new Worm(sc.nextLong(), sc.nextLong());
				totalSumX += worms[i].x;
				totalSumY += worms[i].y;
			}

			combination(0, 0);

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	private static void combination(int before, int idx) {
		if (idx == N / 2) {
			long tempSumX = totalSumX;
			long tempSumY = totalSumY;
			for (int i : nums) {
				tempSumX -= 2 * worms[i].x;
				tempSumY -= 2 * worms[i].y;
			}
			long temp = calVector(tempSumX, tempSumY);
			if (ans > temp) {
				ans = temp;
			}
			return;
		}
		for (int i = before + 1; i <= N; i++) {
			nums[idx] = i - 1;
			combination(i, idx + 1);
		}
	}

	public static long calVector(long x, long y) {
		return x * x + y * y;
	}

	static class Worm {
		long x;
		long y;

		public Worm() {
		}

		public Worm(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
}
