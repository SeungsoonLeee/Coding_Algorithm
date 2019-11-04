package com.ssafy.solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int M, N, ans;
	static int[][] dir = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<Integer>[][] map = new ArrayList[10][10];
	static int[] moveA, moveB;
	static BC[] bcs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new ArrayList<Integer>();
			}
		}

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = 0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j].clear();
				}
			}
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			moveA = new int[M];
			moveB = new int[M];
			bcs = new BC[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				bcs[i] = new BC(r, c, d, w);
				for (int a = r - d; a <= r + d; a++) {
					if (a >= 10 || a < 0)
						continue;
					for (int b = c - d; b <= c + d; b++) {
						if (isSide(a, b))
							continue;
						int t = Math.abs(a - r) + Math.abs(b - c);
						if (t <= d)
							map[a][b].add(i);
					}
				}
			}

//			for (int i = 0; i < 10; i++) {
//				for (int j = 0; j < 10; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			int Ar = 0;
			int Ac = 0;
			int Br = 9;
			int Bc = 9;
			calCharge(Ar, Ac, Br, Bc);
			for (int i = 0; i < M; i++) {
				Ar += dir[moveA[i]][0];
				Ac += dir[moveA[i]][1];
				Br += dir[moveB[i]][0];
				Bc += dir[moveB[i]][1];
				calCharge(Ar, Ac, Br, Bc);
//				System.out.println(Ar + " " + Ac + " " + Br + " " + Bc + " ");
			}

			bw.write("#" + test_case + " " + ans + "\n");
			bw.flush();
		}
	}

	private static void calCharge(int ar, int ac, int br, int bc) {
		boolean isSameBC = false;
		if (map[ar][ac].size() > 0 || map[br][bc].size() > 0) {
			outer: for (int i = 0; i < map[ar][ac].size(); i++) {
				for (int j = 0; j < map[br][bc].size(); j++) {
					if (map[ar][ac].get(i).equals(map[br][bc].get(j))) {
						isSameBC = true;
						break outer;
					}
				}
			}

			int tempChargeA = 0;
			int tempChargeB = 0;
			if (isSameBC) {
				int tempSum = 0;
				for (int a = 0; a < map[ar][ac].size(); a++) {
					for (int b = 0; b < map[br][bc].size(); b++) {
						tempChargeA = bcs[map[ar][ac].get(a)].w;
						tempChargeB = bcs[map[br][bc].get(b)].w;
						if (map[ar][ac].get(a) == map[br][bc].get(b))
							tempSum = Math.max(tempSum, (tempChargeA + tempChargeB) / 2);
						else
							tempSum = Math.max(tempSum, (tempChargeA + tempChargeB));
					}
				}
				ans += tempSum;
			} else {
				for (int i = 0; i < map[ar][ac].size(); i++) {
					tempChargeA = Math.max(tempChargeA, bcs[map[ar][ac].get(i)].w);
				}
				for (int i = 0; i < map[br][bc].size(); i++) {
					tempChargeB = Math.max(tempChargeB, bcs[map[br][bc].get(i)].w);
				}
				ans += (tempChargeA + tempChargeB);
			}
		}
	}

	private static boolean isSide(int r, int c) {
		if (r >= 10 || c >= 10 || r < 0 || c < 0)
			return true;
		return false;
	}
}

class BC {
	int r;
	int c;
	int d;
	int w;

	public BC() {
	}

	public BC(int r, int c, int d, int w) {
		this.r = r;
		this.c = c;
		this.d = d;
		this.w = w;
	}

	@Override
	public String toString() {
		return "BC [r=" + r + ", c=" + c + ", d=" + d + ", w=" + w + "]";
	}
}