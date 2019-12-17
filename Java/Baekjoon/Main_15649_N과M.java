package com.lss.baeckjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15649_N과M {
	static int N, M;
	static int[] nums;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		nums = new int[N];

		int idx = arr.length - 1;
		for (int i = 0; i < M; i++) // np 처리를 위한 arr
			arr[idx - i] = 1;
		for (int i = 0; i < N; i++) // 1부터 숫자들
			nums[i] = i + 1;

		do {
			for (int i = N - 1; i >= 0; --i) {
				if (arr[i] == 1)
					System.out.print(nums[i] + " ");
			}
			System.out.println();
		} while (np());
	}

	public static boolean np() {
		int i = N - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			--i;
		if (i <= 0)
			return false;

		int j = N - 1;
		while (arr[i - 1] >= arr[j])
			--j;
		swap(i - 1, j);

		j = N - 1;
		while (i < j)
			swap(i++, j--);

		return true;
	}

	public static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}