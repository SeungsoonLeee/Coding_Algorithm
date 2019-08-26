
package com.lss.baekjoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_2667_단지번호붙이기_BFS {
	static int N;
	static int[][] map;
	static int[][] checked;
	static int[] danjiCnt;
	static int danji;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
				bfs(i, j);
			}
		}

		danjiCnt = Arrays.copyOf(danjiCnt, danji - 1);
		Arrays.sort(danjiCnt);
		System.out.println(danji - 1);
		for (int c : danjiCnt) {
			System.out.println(c);
		}
	}

	public static void bfs(int Y, int X) {
		if (map[Y][X] == 0 || checked[Y][X] != 0)
			return;
		Queue<Integer> queueY = new LinkedList<Integer>();
		Queue<Integer> queueX = new LinkedList<Integer>();
		checked[Y][X] = danji;
		danjiCnt[danji - 1]++;
		queueY.offer(Y);
		queueX.offer(X);

		while (!queueY.isEmpty()) {
			int curY = queueY.poll();
			int curX = queueX.poll();
			for (int i = 0; i < dir.length; i++) {
				int nextY = curY + dir[i][0];
				int nextX = curX + dir[i][1];
				if (nextY >= N || nextX >= N || nextY < 0 || nextX < 0)
					continue;
				if (map[nextY][nextX] == 0 || checked[nextY][nextX] != 0)
					continue;
				checked[nextY][nextX] = danji;
				danjiCnt[danji - 1]++;
				queueY.offer(nextY);
				queueX.offer(nextX);
			}
		}
		danji++;
	}

}