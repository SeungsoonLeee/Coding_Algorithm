package com.lss.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17406_배열돌리기 {
	static int N, M, K;
	static int[][] arr;
	static int[][] initArr;
	static int[] nums;
	static RotateInfo[] rotateInfo;
	static int ans;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		initArr = new int[N][M];
		rotateInfo = new RotateInfo[K];
		nums = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				initArr[i][j] = arr[i][j];
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			rotateInfo[i] = new RotateInfo(r, c, s);
			nums[i] = i;
		}

		do {
			// 배열을 본래 상태로 초기화 시켜줌.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j] = initArr[i][j];
				}
			}
			
			// 순열마다 돌리는 작업
			for (int i = 0; i < K; i++) {
				roatate(rotateInfo[nums[i]]);
			}

			// 최소 합 계산
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < M; j++) {
					sum += arr[i][j];
				}
				if (ans > sum)
					ans = sum;
			}
		} while (nextPermutation()); // 다음 순열 진행

		System.out.println(ans);
	}

	public static boolean nextPermutation() {
		int i = K - 1;
		while (i > 0 && nums[i - 1] >= nums[i])
			i--;
		if (i == 0)
			return false;

		int j = K - 1;
		while (nums[i - 1] >= nums[j])
			j--;
		swap(i - 1, j);

		j = K - 1;
		while (i < j) {
			swap(i++, j--);
		}
		return true;
	}

	public static void roatate(RotateInfo ri) {
		for (int i = 1; i <= ri.s; i++) {
			int curR = ri.r - i;
			int curC = ri.c + i;
			int temp = arr[curR][curC];
			// 윗줄
			for (int j = curC; j >= ri.c - i; --j) {
				if (j == ri.c - i) {
					curC = ri.c - i;
					break;
				}
				arr[curR][j] = arr[curR][j - 1];
			}
			// 왼쪽줄
			for (int j = curR; j <= ri.r + i; ++j) {
				if (j == ri.r + i) {
					curR = ri.r + i;
					break;
				}
				arr[j][curC] = arr[j + 1][curC];
			}
			// 아랫줄
			for (int j = curC; j <= ri.c + i; ++j) {
				if (j == ri.c + i) {
					curC = ri.c + i;
					break;
				}
				arr[curR][j] = arr[curR][j + 1];
			}
			// 오른쪽줄
			for (int j = curR; j >= ri.r - i; --j) {
				if (j == ri.r - i) {
					arr[j+1][curC] = temp;
					curR = ri.r - i;
					break;
				}
				arr[j][curC] = arr[j - 1][curC];
			}
		}
	}

	public static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}

class RotateInfo {
	int r, c, s;

	public RotateInfo() {
	}

	public RotateInfo(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}