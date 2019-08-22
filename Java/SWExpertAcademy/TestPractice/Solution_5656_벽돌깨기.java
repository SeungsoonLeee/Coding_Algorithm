
package com.lss.swExpertAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_5656_벽돌깨기 {
	static int[][][] initMap;
	static int[][] map;
	static int[][] tempMap;
	static int N, W, H, ans;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input/Solution_5656_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			ans = 0;
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			initMap = new int[N][H][W];
			map = new int[H][W];
			tempMap = new int[H][W];

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					initMap[0][i][j] = sc.nextInt();
					map[i][j] = initMap[0][i][j];
					tempMap[i][j] = initMap[0][i][j];
					if (map[i][j] != 0)
						ans++;
				}
			}

			dfs(0);

			System.out.printf("#%d %d\n", test_case, ans);
		}

	}

	public static void dfs(int cnt) {
		if (cnt >= N) {
			int remain = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] != 0)
						remain++;
				}
			}
			if (remain < ans)
				ans = remain;
			return;
		}
		for (int i = 0; i < W; i++) {
			// 터치기 전 맵 저장해 두고
			for (int k = 0; k < H; k++) {
				for (int j = 0; j < W; j++) {
					initMap[cnt][k][j] = map[k][j];
				}
			}
//			System.out.println("cnt : " + cnt + ",w : " + i);
			fire(0, i);
			blockDown();
			dfs(cnt + 1);
			// 이전 맵으로 되돌림
//			System.out.println("맵 되돌리기!");
			for (int k = 0; k < H; k++) {
				for (int j = 0; j < W; j++) {
					map[k][j] = initMap[cnt][k][j];
				}
			}
		}
	}

	public static void fire(int h, int w) {
		Queue<Integer> queY = new LinkedList<Integer>();
		Queue<Integer> queX = new LinkedList<Integer>();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tempMap[i][j] = map[i][j];
			}
		}

		while (map[h][w] == 0 && h < H) { // 폭발 지점의 y좌표를 찾아서
			h++;
			if (h >= H)
				return;
		}
		queY.offer(h);
		queX.offer(w);

		while (!queY.isEmpty()) {
			int curY = queY.poll();
			int curX = queX.poll();
			tempMap[curY][curX] = 0;

			for (int i = 0; i < 4; ++i) { // 4방향으로 폭탄을 터뜨리는데,
				for (int j = 0; j < map[curY][curX]; ++j) { // 폭탄의 범위만큼 터뜨림
					int nextY = curY + dir[i][0] * j;
					int nextX = curX + dir[i][1] * j;
					if (nextY >= 0 && nextX >= 0 && nextY < H && nextX < W && tempMap[nextY][nextX] != 0) {
						// 더 터져야 할 폭탄들을 queue에 offer
						queY.offer(nextY);
						queX.offer(nextX);
					}
				}
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = tempMap[i][j];
			}
		}
	}

	public static void blockDown() {
//		System.out.println("블록 다운 전");
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(tempMap[i][j] + " ");
//			}
//			System.out.println();
//		}

		for (int i = 0; i < W; i++) {
			int idx = 0;
			int[] temp = new int[H];
			for (int j = H - 1; j >= 0; --j) {
				if (map[j][i] != 0)
					temp[idx++] = map[j][i];
			}
			idx = 0;
			for (int j = H - 1; j >= 0; --j) {
				map[j][i] = temp[idx++];
			}
		}

//		System.out.println("블록 다운 후");
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(tempMap[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
}
