package com.ssafy.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static String op;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			op = st.nextToken();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
//					System.out.print(map[i][j] + " ");
				}
//				System.out.println();
			}

			moveNums(op);

			System.out.println("#" + test_case);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}

	}

	private static void moveNums(String op) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		switch (op) {
		case "up":
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[j][i] != 0)
						temp.add(map[j][i]);
				}

				calNums(temp);

				for (int j = 0; j < N; j++) {
					if (j < temp.size())
						map[j][i] = temp.get(j);
					else
						map[j][i] = 0;
				}
				temp.clear();
			}
			break;
		case "down":
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[j][i] != 0)
						temp.add(map[j][i]);
				}

				calNums(temp);

				int idx = 0;
				for (int j = N - 1; j >= 0; j--) {
					if (idx < temp.size())
						map[j][i] = temp.get(idx++);
					else
						map[j][i] = 0;
				}
				temp.clear();
			}
			break;
		case "left":
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0)
						temp.add(map[i][j]);
				}

				calNums(temp);

				for (int j = 0; j < N; j++) {
					if (j < temp.size())
						map[i][j] = temp.get(j);
					else
						map[i][j] = 0;
				}
				temp.clear();
			}
			break;
		case "right":
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0)
						temp.add(map[i][j]);
				}

				calNums(temp);

				int idx = 0;
				for (int j = N - 1; j >= 0; j--) {
					if (idx < temp.size())
						map[i][j] = temp.get(idx++);
					else
						map[i][j] = 0;
				}
				temp.clear();
			}
			break;
		}
	}

	private static void calNums(ArrayList<Integer> temp) {
		for (int j = 0, size = temp.size(); j < size - 1; j++) {
			if ((int) temp.get(j) == (int) temp.get(j + 1)) {
				temp.set(j, (int) temp.get(j) + (int) temp.get(j + 1));
				temp.remove(j + 1);
				size--;
			}
		}
	}
}
