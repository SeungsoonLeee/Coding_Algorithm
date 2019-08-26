
package com.lss.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2667_단지번호붙이기_DFS {
	static int N;
	static int[][] map;
	static int[][] checked;
	static int[] danjiCnt;
	static int danji;
	static boolean ischecked;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ischecked = false;
		N = sc.nextInt();
		map = new int[N][N];
		checked = new int[N][N];
		danjiCnt = new int[N * N];
		danji = 1;

		for (int i = 0; i < N; i++) {
			String temp = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = (int) temp.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(i, j);
				if (ischecked) {
					danji++;
					ischecked = false;
				}
			}
		}

		danjiCnt = Arrays.copyOf(danjiCnt, danji - 1);
		Arrays.sort(danjiCnt);
		System.out.println(danji - 1);
		for (int c : danjiCnt) {
			System.out.println(c);
		}
	}

	public static void dfs(int Y, int X) {
		if (map[Y][X] == 0 || checked[Y][X] != 0)
			return;
		checked[Y][X] = danji;
		danjiCnt[danji - 1]++;

		for (int i = 0; i < dir.length; i++) {
			int nextY = Y + dir[i][0];
			int nextX = X + dir[i][1];
			if (nextY >= N || nextX >= N || nextY < 0 || nextX < 0)
				continue;
			if (map[nextY][nextX] == 0 || checked[nextY][nextX] != 0)
				continue;
			dfs(nextY, nextX);
		}
	}

}