package com.ssafy.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1733_오목_이승순 {
	static int[][] map;
	static int check;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 } };
	static int ans, ansR, ansC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 19;
		map = new int[N][N];
		check = 0;
		ans = 0;
		ansR = 0;
		ansC = 0;

		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt;
				if (map[i][j] != 0) {
					for (int k = 0; k < 4; k++) {
						cnt = 1;
						int tempI = i;
						int tempJ = j;
						int tr, tc;
						while (map[tempI][tempJ] != 0) {
							int pre = map[tempI][tempJ];
							tempI -= dir[k][0];
							tempJ -= dir[k][1];
							if (tempI >= N || tempJ >= N || tempI < 0 || tempJ < 0)
								break;
							if (map[tempI][tempJ] != pre)
								break;
							if (cnt >= 6)
								break;
							cnt++;
						}
						tr = tempI + dir[k][0];
						tc = tempJ + dir[k][1];
						tempI = i;
						tempJ = j;
						while (map[tempI][tempJ] != 0) {
							int pre = map[tempI][tempJ];
							tempI += dir[k][0];
							tempJ += dir[k][1];
							if (tempI >= N || tempJ >= N || tempI < 0 || tempJ < 0)
								break;
							if (map[tempI][tempJ] != pre)
								break;
							if (cnt >= 6)
								break;
							cnt++;
						}
						if (cnt == 5) {
							ans = map[i][j];
							ansR = tr + 1;
							ansC = tc + 1;
							break outer;
						}
					}
				}
			}
		}

		if (ans == 0)
			System.out.println(0);
		else
			System.out.println(ans + "\n" + ansR + " " + ansC);
	}
}
