package com.ssafy.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1681_해밀턴순환회로_이승순 {
	static int N, ans;
	static int[][] map;
	static int[] nums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		nums = new int[N - 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println();
		}

		for (int i = 0; i < nums.length; i++) {
			nums[i] = i + 1;
		}

		int temp;
		ans = Integer.MAX_VALUE;
		nextP: do {
			temp = 0;
			// 회사에서 첫 방문지까지
			if (map[0][nums[0]] == 0)
				continue;
			temp += map[0][nums[0]];
			if (temp > ans)
				continue;
			// 첫 방문지부터 마지막 방문지까지
			for (int i = 0; i < nums.length - 1; i++) {
				if (map[nums[i]][nums[i + 1]] == 0)
					continue nextP;
				temp += map[nums[i]][nums[i + 1]];
				if (temp > ans)
					continue nextP;
			}

			// 마지막 방문지에서 회사 까지
			if (map[nums[nums.length - 1]][0] == 0)
				continue;
			temp += map[nums[nums.length - 1]][0];

			if (ans > temp)
				ans = temp;
		} while (nextP());

		System.out.println(ans);
	}

	private static boolean nextP() {
		int i = nums.length - 1;
		while (i > 0 && nums[i - 1] >= nums[i])
			i--;
		if (i == 0)
			return false;

		int j = nums.length - 1;
		while (nums[i - 1] >= nums[j])
			j--;
		swap(i - 1, j);

		j = nums.length - 1;
		while (i < j)
			swap(i++, j--);

		return true;
	}

	private static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}