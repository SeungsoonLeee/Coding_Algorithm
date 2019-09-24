package com.lss.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_전광판광고 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int rotateCnt = Integer.parseInt(st.nextToken());
		String[][] matrix = new String[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = st.nextToken();
//				System.out.print(matrix[i][j]);
			}
//			System.out.println();
		}

		// 양수 : 시계방향
		// 음수 : 반시계방향
		// 인접구간 : 반대방향
		for (int i = 0; i < N / 2; i++) {
			int cnt = rotateCnt % (((N - 2 * i) * 4) - 4);
//			System.out.println(cnt);
			if (cnt >= 0) {
				if (i % 2 == 0)
					rotateClock(matrix, i, cnt);
				else
					rotateUnclock(matrix, i, cnt);
			} else {
				cnt = Math.abs(cnt);
				if (i % 2 == 0)
					rotateUnclock(matrix, i, cnt);
				else
					rotateClock(matrix, i, cnt);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(j == 0)
					System.out.print(matrix[i][j]);
				else
					System.out.print(" " + matrix[i][j]);
			}
			System.out.println();
		}
	}

	public static void rotateClock(String[][] matrix, int i, int rotateCnt) {
//		System.out.println("시계로" + rotateCnt);
		while (rotateCnt-- > 0) {
			int tempR = i;
			int tempC = i;
			String temp = matrix[tempR][tempC];
			while (tempR < N - i - 1) {
				matrix[tempR][tempC] = matrix[++tempR][tempC];
			}
			while (tempC < N - i - 1) {
				matrix[tempR][tempC] = matrix[tempR][++tempC];
			}
			while (tempR > i) {
				matrix[tempR][tempC] = matrix[--tempR][tempC];
			}
			while (tempC > i) {
				matrix[tempR][tempC] = matrix[tempR][--tempC];
			}
			matrix[tempR][tempC + 1] = temp;
		}
		
	}

	public static void rotateUnclock(String[][] matrix, int i, int rotateCnt) {
//		System.out.println("반시계로" + rotateCnt);
		while (rotateCnt-- > 0) {
			int tempR = i;
			int tempC = i;
			String temp = matrix[tempR][tempC];
			while (tempC < N - i - 1) {
				matrix[tempR][tempC] = matrix[tempR][++tempC];
			}
			while (tempR < N - i - 1) {
				matrix[tempR][tempC] = matrix[++tempR][tempC];
			}
			while (tempC > i) {
				matrix[tempR][tempC] = matrix[tempR][--tempC];
			}
			while (tempR > i) {
				matrix[tempR][tempC] = matrix[--tempR][tempC];
			}
			matrix[tempR + 1][tempC] = temp;
		}
	}
}

