package com.lss.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<Integer> sizeList;
	static int fishCnt;
	static Shark babyShark;
	static int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		sizeList = new PriorityQueue<Integer>();
		ans = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 6) {
					fishCnt++;
					sizeList.offer(map[i][j]);
				}
				if (map[i][j] == 9) {
					babyShark = new Shark(i, j, 0);
				}
			}
		}

		while (fishCnt > 0) {
			int tempR = babyShark.r;
			int tempC = babyShark.c;
			if (!isPossible())
				break;
			for (int i = 0; i < visited.length; i++) {
				Arrays.fill(visited[i], false);
			}
			move(babyShark.r, babyShark.c);
			if(tempR == babyShark.r && tempC == babyShark.c) break;
		}

		System.out.println(ans);
	}

	private static boolean isPossible() {
		
		if (babyShark.size > sizeList.peek())
			return true;
		return false;
	}

	private static void move(int r, int c) {
		Queue<Integer> queR = new LinkedList<Integer>();
		Queue<Integer> queC = new LinkedList<Integer>();
		ArrayList<Fish> eatFish = new ArrayList<Fish>();
		visited[r][c] = true;
		queR.offer(r);
		queC.offer(c);

		int distance = 0;
		while (!queR.isEmpty()) {
			int size = queR.size();
			boolean findMinDistance = false;
			distance++;
			while (size > 0) {
				int curR = queR.poll();
				int curC = queC.poll();
				for (int i = 0; i < 4; i++) {
					int nextR = curR + dir[i][0];
					int nextC = curC + dir[i][1];
					if (nextR >= N || nextC >= N || nextR < 0 || nextC < 0 || map[nextR][nextC] > babyShark.size
							|| visited[nextR][nextC])
						continue;
					if (map[nextR][nextC] != 0 && map[nextR][nextC] < babyShark.size) {
						
						eatFish.add(new Fish(nextR, nextC, map[nextR][nextC]));
						findMinDistance = true;
					}
					visited[nextR][nextC] = true;
					queR.offer(nextR);
					queC.offer(nextC);
				}
				size--;
			}
			if (findMinDistance)
				break;
		}

		Collections.sort(eatFish);
		if (!eatFish.isEmpty()) {
			sizeList.poll();
			map[babyShark.r][babyShark.c] = 0;
			map[eatFish.get(0).r][eatFish.get(0).c] = 9;
			babyShark.cnt++;
			babyShark.r = eatFish.get(0).r;
			babyShark.c = eatFish.get(0).c;
			fishCnt--;
			ans += distance;
			if (babyShark.size == babyShark.cnt) {
				babyShark.size++;
				babyShark.cnt = 0;
			}
			eatFish.clear();
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
		if (this.r == o.r)
			return Integer.compare(this.c, o.c);
		return Integer.compare(this.r, o.r);
	}
}

