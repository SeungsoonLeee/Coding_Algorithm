package com.ssafy.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1082_화염에서탈출_이승순 {
	static char[][] map;
	static int[][] time;
	static boolean[][] check;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static Queue<Point> f;
	static Point s, d;
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		time = new int[R][C];
		check = new boolean[R][C];
		f = new LinkedList<Point>();
		// 빈칸 : .
		// 불 : *
		// 바위 : X
		// 집 : D
		// 시작 : S

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
//				System.out.print(map[i][j] + " ");
				if (map[i][j] == 'S') {
					s = new Point(i, j);
				} else if (map[i][j] == '*') {
					f.offer(new Point(i, j));
				} else if (map[i][j] == '*') {
					d = new Point(i, j);
				}
			}
//			System.out.println();
		}

		for (int count = 0; count < R * C; count++) {
			if (count >= 5)
				break;
			moveFire();
			movePerson();

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

		System.out.println(time[d.y][d.x]);
	}

	private static void movePerson() {
	}

	private static void moveFire() {
		int size = f.size();
		for (int i = 0; i < size; i++) {
			Point cur = f.poll();
			for (int j = 0; j < 4; j++) {
				int nextY = cur.y + dir[j][0];
				int nextX = cur.x + dir[j][1];
				if (isSide(nextY, nextX) || map[nextY][nextX] == 'X' || map[nextY][nextX] == '*'
						|| map[nextY][nextX] == 'D')
					continue;
				f.offer(new Point(nextY, nextX));
				map[nextY][nextX] = '*';
			}
		}
	}

	private static boolean isSide(int r, int c) {
		if (r >= R || c >= C || r < 0 || c < 0)
			return true;
		return false;
	}
}

class Point {
	int y;
	int x;

	public Point() {
	}

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public String toString() {
		return "Point [y=" + y + ", x=" + x + "]";
	}
}