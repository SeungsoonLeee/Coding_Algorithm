package com.ssafy.step1.homework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1244_최대상금_이승순 {
	static int ans;
	static String str;
	static int[] nums;
	static int n;
	static boolean[][] checked;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = 0;
			checked = new boolean[20][1000000];
			StringTokenizer st = new StringTokenizer(br.readLine());
			str = st.nextToken();
			nums = new int[str.length()];
			for (int i = 0; i < str.length(); i++) {
				nums[i] = str.charAt(i) - '0';
			}
			n = Integer.parseInt(st.nextToken());

			getHighPoint(0, n);

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	public static void getHighPoint(int idx, int n) {
		if (n <= 0) {
			int temp = arrToNum(nums);
			if (ans < temp)
				ans = temp;
			System.out.println(temp);
			return;
		}

		for (int i = idx; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				swap(i, j);
				if (checked[n][arrToNum(nums)]) {
					swap(i, j);
					continue;
				}
				getHighPoint(i, n - 1);
				checked[n][arrToNum(nums)] = true;
				swap(i, j);
			}
		}
	}

	public static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static int arrToNum(int[] arr) {
		String num = "";
		for (int i = 0; i < nums.length; i++) {
			num += nums[i];
		}
		return Integer.parseInt(num);
	}
}

// #1 321
// #2 7732
// #3 857147
// #4 87664
// #5 88832
// #6 777770
// #7 966354
// #8 954311
// #9 332211
// #10 987645