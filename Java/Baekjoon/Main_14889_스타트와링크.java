package com.ssafy.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14889_스타트와링크 {
	static int N, ans;
	static int[][] matrix;
	static int[] nums, leftTeam, rightTeam;
	static boolean[] check;
	static int sum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		check = new boolean[N];
		nums = new int[N / 2];
		leftTeam = new int[N / 2];
		rightTeam = new int[N / 2];
		ans = Integer.MAX_VALUE;
		sum = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(matrix[i][j] + " ");
			}
//			System.out.println();
		}

		comb(0, 0, N / 2);
		System.out.println("sum = " + sum);
		System.out.println(ans);
	}

	private static void comb(int before, int idx, int cnt) {
		if (idx == cnt) {
			if(nums[0] != 0)
				return;
			sum++;
			Arrays.fill(check, false);
			for (int i = 0; i < N / 2; i++) {
				check[nums[i]] = true;
				leftTeam[i] = nums[i];
			}
			int tempIdx = 0;
			for (int i = 0; i < N; i++) {
				if (!check[i])
					rightTeam[tempIdx++] = i;
			}
//			System.out.println(Arrays.toString(nums));
			System.out.println(Arrays.toString(leftTeam) + " " + Arrays.toString(rightTeam));

			int tempAns = calTalent();
			if (ans > tempAns)
				ans = tempAns;

			return;
		}
		for (int i = before + 1; i <= N; i++) {
			nums[idx] = i - 1;
			comb(i, idx + 1, cnt);
		}
	}

	private static int calTalent() {
		int leftPoints = 0;
		int rightPoints = 0;

		for (int i = 0; i < N / 2 - 1; i++) {
			for (int j = i + 1; j < N / 2; j++) {
				leftPoints += matrix[leftTeam[i]][leftTeam[j]];
				leftPoints += matrix[leftTeam[j]][leftTeam[i]];
				rightPoints += matrix[rightTeam[i]][rightTeam[j]];
				rightPoints += matrix[rightTeam[j]][rightTeam[i]];
			}
		}
		return Math.abs(leftPoints - rightPoints);
	}
}
