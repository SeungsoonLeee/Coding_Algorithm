package com.ssafy.jungol;

import java.util.Scanner;

public class Main_2074_홀수마방진_이승순 {
	static int N, ans;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		// 1. 첫 번째 자리 수는 첫번째 가운데 행
		// 2. N의 배수라면 바로 아래 행
		// 3. 그렇지 않으면 왼쪽 위
		// 4. 행이 첫번쨰를 벗어나면 마지막 행으로, 열이 첫번째를 벗어나면 마지막 열로

		map = new int[N][N];

		int r = 0;
		int c = N / 2;
		int num = 1;
		int end = N * N;
		while (num <= end) {
			map[r][c] = num;
			if (num % N == 0) {
				r++;
			} else {
				r--;
				c--;
				if (r < 0)
					r = N - 1;
				if (c < 0)
					c = N - 1;
			}
			num++;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
