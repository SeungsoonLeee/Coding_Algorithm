package com.lss.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	static int fishCnt;
	static Shark babyShark;
	static int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -0 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		ans = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 6) {
					fishCnt++;
				}
				if (map[i][j] == 9) {
					babyShark = new Shark(i, j, 0);
				}
			}
		}

		while (fishCnt > 0) {
			for (int i = 0; i < visited.length; i++) {
				Arrays.fill(visited[i], false);
			}
			move(babyShark.r, babyShark.c);
		}

		System.out.println(ans);
	}

	private static void move(int r, int c) {
		Queue<Integer> queR = new LinkedList<Integer>();
		Queue<Integer> queC = new LinkedList<Integer>();
		visited[r][c] = true;
		queR.offer(r);
		queC.offer(c);

		while (!queR.isEmpty()) {
			int curR = queR.poll();
			int curC = queC.poll();
			for (int i = 0; i < 4; i++) {
				int nextR = curR + dir[i][0];
				int nextC = curR + dir[i][0];
				if (nextR >= N || nextC >= N || nextC < 0 || nextC < 0 || map[nextR][nextC] > babyShark.size)
					continue;
				if (map[nextR][nextC] < babyShark.size) {
					babyShark.cnt++;
					babyShark.r = nextR;
					babyShark.c = nextC;
					fishCnt--;
					return;
				}

			}
		}
	}
}

class Shark {
	int r;
	int c;
	int cnt;
	int size;

	public Shark() {
	}

	public Shark(int r, int c, int cnt) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
		this.size = 2;
	}
}

class Fish implements Comparable<Fish> {
	int r;
	int c;
	int size;

	public Fish() {
	}

	public Fish(int r, int c, int size) {
		this.r = r;
		this.c = c;
		this.size = size;
	}

	@Override
	public int compareTo(Fish o) {
		if (this.c == o.c)
			return Integer.compare(this.r, o.r);
		return Integer.compare(this.c, o.c);
	}
}