package com.ssafy.solve;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_D4_1258_행렬찾기_이승순 {
	static int N;
	static int[][] matrix;
	static boolean[][] check;
	static int[][] dir = { { 1, 0 }, { 0, 1 } };
	static ArrayList<RC> list;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			int cnt = 0;
			N = sc.nextInt();
			matrix = new int[N][N];
			check = new boolean[N][N];
			list = new ArrayList<RC>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (matrix[i][j] != 0 && !check[i][j]) {
						list.add(bfs(i, j));
						cnt++;
					}
				}
			}

			Collections.sort(list);
			System.out.printf("#%d %d ", test_case, cnt);
			for (RC rc : list) {
				System.out.printf("%d %d ", rc.r, rc.c);
			}
			System.out.println();
		}
	}

	public static RC bfs(int y, int x) {
		Queue<Integer> queueY = new LinkedList<Integer>();
		Queue<Integer> queueX = new LinkedList<Integer>();
		int maxY = y;
		int maxX = x;
		int minY = y;
		int minX = x;
		check[y][x] = true;
		queueY.offer(y);
		queueX.offer(x);

		while (!queueY.isEmpty()) {
			int curY = queueY.poll();
			int curX = queueX.poll();
			for (int i = 0; i < 2; i++) {
				int nextY = curY + dir[i][0];
				int nextX = curX + dir[i][1];
				if (nextY >= N || nextX >= N || nextY < 0 || nextX < 0)
					continue;
				if (matrix[nextY][nextX] == 0 || check[nextY][nextX])
					continue;
				check[nextY][nextX] = true;
				queueY.offer(nextY);
				queueX.offer(nextX);
				if (nextY > maxY)
					maxY = nextY;
				if (nextX > maxX)
					maxX = nextX;
				if (nextY < minY)
					minY = nextY;
				if (nextX < minX)
					minX = nextX;
			}
		}
		RC rc = new RC(maxY - minY + 1, maxX - minX + 1);
		return rc;
//		System.out.println(minY + " " + minX);
//		System.out.println(maxY + " " + maxX);
	}

	public static class RC implements Comparable<RC> {
		int r;
		int c;

		public RC() {
		}

		public RC(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(RC o) {
			if (this.r * this.c == o.r * o.c)
				return Integer.compare(this.r, o.r);
			return Integer.compare(this.r * this.c, o.r * o.c);
		}
	}
}
