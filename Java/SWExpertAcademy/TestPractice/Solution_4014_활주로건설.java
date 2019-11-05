package com.ssafy.solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int ans, N, X;
	static int[][] map;
	static boolean[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			check = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				// 가로 탐색
				Arrays.fill(check, false);
				curC: for (int j = 1; j < N; j++) {
					int diff = map[i][j - 1] - map[i][j];
					if (diff > 1 || diff < -1) {
						break curC;
					} else if (diff == 1) { // 내리막 길을 만났을 때
						int tj = j;
						int tempH = map[i][tj];
						for (int k = 0; k < X; k++) {
							if (isSide(i, tj)) // X길이의 경사로를 놓았을 때 경계를 넘어가면 패스
								break curC;
							if (map[i][tj] != tempH) // X길이 경사로를 놓을 높이가 바뀌면 패스
								break curC;
							if (check[tj])	break curC;
							else	check[tj] = true;
							tj++;
						}
					} else if (diff == -1) { // 오르막 길을 만났을 때
						int tj = j - 1;
						int tempH = map[i][tj];
						for (int k = 0; k < X; k++) {
							if (isSide(i, tj)) // X길이의 경사로를 놓았을 때 경계를 넘어가면 패스
								break curC;
							if (map[i][tj] != tempH) // X길이 경사로를 놓을 높이가 바뀌면 패스
								break curC;
							if (check[tj])	break curC;
							else	check[tj] = true;
							tj--;
						}
					}
					if (j == N - 1) {
//						System.out.println("가로 : " + i);
						ans++;
					}
				}

				// 세로 탐색
				Arrays.fill(check, false);
				curR: for (int j = 1; j < N; j++) {
					int diff = map[j - 1][i] - map[j][i];
					if (diff > 1 || diff < -1) {
						break curR;
					} else if (diff == 1) { // 내리막 길을 만났을 때
						int tj = j;
						int tempH = map[tj][i];
						for (int k = 0; k < X; k++) {
							if (isSide(tj, i)) // X길이의 경사로를 놓았을 때 경계를 넘어가면 패스
								break curR;
							if (map[tj][i] != tempH) // X길이 경사로를 놓을 높이가 바뀌면 패스
								break curR;
							if (check[tj])	break curR;
							else	check[tj] = true;
							tj++;
						}
					} else if (diff == -1) { // 오르막 길을 만났을 때
						int tj = j - 1;
						int tempH = map[tj][i];
						for (int k = 0; k < X; k++) {
							if (isSide(tj, i)) // X길이의 경사로를 놓았을 때 경계를 넘어가면 패스
								break curR;
							if (map[tj][i] != tempH) // X길이 경사로를 놓을 높이가 바뀌면 패스
								break curR;
							if (check[tj])	break curR;
							else	check[tj] = true;
							tj--;
						}
					}
					if (j == N - 1) {
//						System.out.println("세로 : " + i);
						ans++;
					}
				}
			}

			bw.write("#" + test_case + " " + ans + "\n");
			bw.flush();
		}
	}

	private static boolean isSide(int r, int c) {
		if (r >= N || c >= N || r < 0 || c < 0)
			return true;
		return false;
	}
}
