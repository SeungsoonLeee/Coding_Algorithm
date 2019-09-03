package com.lss.swExpertAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_D4_3234_준환이의양팔저울 {
	static int N, ans;
	static int[] nums;
	static int[] temp;
	static boolean[] isLeft;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input/Solution_D4_3234_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			ans = 0;
			N = sc.nextInt();
			nums = new int[N];
			temp = new int[N];
			isLeft = new boolean[N];

			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}

			permutation(0, 0);

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	public static void permutation(int idx, int count) {
		if (idx == N) {
			func(0, 0, 0);
			return;
		}

		for (int i = idx; i < N; i++) {
			swap(i, idx);
			permutation(idx + 1, count + 1);
			swap(i, idx);
		}
	}

	public static void func(int idx, int left, int right) {
		if (left < right)
			return;
		if (idx == N) {
			ans++;
			return;
		}
		func(idx + 1, left + nums[idx], right);
		func(idx + 1, left, right + nums[idx]);
	}

	public static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
