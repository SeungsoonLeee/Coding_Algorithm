package com.lss.swExpertAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_5648_원자소멸시뮬레이션 {
	static int N, ans, cnt = 0;
	static int[][] map;
	static int[][] tempMap;
	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
	static Queue<Atom> atoms;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input/Solution_5648_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		map = new int[4002][4002];
		tempMap = new int[4002][4002];
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = 0;
			for (int i = 0; i < args.length; i++) {
				Arrays.fill(map[i], 0);
				Arrays.fill(tempMap[i], 0);
			}

			atoms = new LinkedList<Atom>();
			N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				int x = (sc.nextInt() + 1000) * 2;
				int y = (sc.nextInt() + 1000) * 2;
				int d = sc.nextInt();
				int e = sc.nextInt();
				map[y][x] = 1;
				tempMap[y][x] = e;
				atoms.offer(new Atom(x, y, d, e));
			}

			moveAtoms();

			System.out.printf("#%d %d\n", test_case, ans);
//			System.out.println(cnt);
		}
	}

	public static void moveAtoms() {
		Queue<Atom> tempAtoms = new LinkedList<Atom>();
		while (!atoms.isEmpty()) {
			while (!atoms.isEmpty()) {
//				cnt++;
				Atom cur = atoms.poll();
				map[cur.y][cur.x]--;
				tempMap[cur.y][cur.x] = 0;
				int nextY = cur.y + dir[cur.d][0];
				int nextX = cur.x + dir[cur.d][1];
				if (nextY < 0 || nextX < 0 || nextY > 4000 || nextX > 4000)
					continue;
				map[nextY][nextX]++;
				tempMap[nextY][nextX] += cur.e;
				tempAtoms.offer(new Atom(nextX, nextY, cur.d, cur.e));
			}

			while (!tempAtoms.isEmpty()) {
//				cnt++;
				Atom check = tempAtoms.poll();
				if (map[check.y][check.x] == 1) {
					atoms.offer(check);
				} else if (map[check.y][check.x] > 1) {
					map[check.y][check.x] = 0;
					ans += tempMap[check.y][check.x];
					tempMap[check.y][check.x] = 0;
				}
			}
		}
	}
}

class Atom {
	int x;
	int y;
	int d;
	int e;

	public Atom() {
	}

	public Atom(int x, int y, int d, int e) {
		this.x = x;
		this.y = y;
		this.d = d;
		this.e = e;
	}
}