package com.ssafy.solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N, X, Y, Z, A, B, C;
	static long[] colorX, colorY, colorZ;
	static long[] ans;
	static long[][] colorXYZ;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			Z = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			colorX = new long[N];
			colorY = new long[N];
			colorZ = new long[N];
			colorXYZ = new long[N][N];
			ans = new long[N];

			// x 좌표 한 줄에 대한 color 색상값의 수 계산
			for (int i = 0; i < X; i++) {
//				colorXYZ[0][Math.abs(i - A) % N]++;
				colorX[Math.abs(i - A) % N]++;
			}
			// y 좌표 한 줄에 대한 color 색상값의 수 계산
			for (int i = 0; i < Y; i++) {
//				colorXYZ[1][Math.abs(i - B) % N]++;
				colorY[Math.abs(i - B) % N]++;
			}
			// z 좌표 한 줄에 대한 color 색상값의 수 계산
			for (int i = 0; i < Z; i++) {
//				colorXYZ[2][Math.abs(i - C) % N]++;
				colorZ[Math.abs(i - C) % N]++;
			}

			long[] temp = new long[N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp[(i + j) % N] += colorX[i] * colorY[j];
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans[(i + j) % N] += temp[i] * colorZ[j];
				}
			}

			bw.write("#" + test_case);
			for (Long num : ans)
				bw.write(" " + num);
			bw.write("\n");
			bw.flush();
		}
	}
}