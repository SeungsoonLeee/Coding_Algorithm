package com.ssafy.homework;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] comb, parents, peopleCnt, leftSide, rightSide;
	static int leftTotal, rightTotal, ans;
	static boolean[] check;
	static int[][] matrix;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		peopleCnt = new int[N];
		parents = new int[N];
		comb = new int[N];
		check = new boolean[N];
		matrix = new int[N][N];
		ans = INF;

		for (int i = 0; i < N; i++) {
			peopleCnt[i] = sc.nextInt();
		}

		for (int i = 0; i < N; i++) {
			int cnt = sc.nextInt();
			for (int j = 0; j < cnt; j++) {
				int to = sc.nextInt() - 1;
				matrix[i][to] = 1;
				matrix[to][i] = 1;
			}
		}

		for (int i = 1; i <= N / 2; i++) {
			combination(0, 0, i);
		}

		if (ans == INF)
			ans = -1;

		System.out.println(ans);
	}

	public static void combination(int before, int idx, int count) {
		if (idx == count) {
			Arrays.fill(parents, -1);
			Arrays.fill(check, false);
			leftTotal = 0;
			rightTotal = 0;

			int index = 0;
			leftSide = new int[count];
			for (int i = 0; i < count; i++) { // 좌측 지역구
				leftSide[index++] = comb[i];
				check[comb[i]] = true;
				leftTotal += peopleCnt[comb[i]];
			}
			index = 0;
			rightSide = new int[N - count];
			for (int i = 0; i < N; i++) { // 우측 지역구
				if (check[i])
					continue;
				rightSide[index++] = i;
				rightTotal += peopleCnt[i];
			}

//			System.out.println(Arrays.toString(leftSide));
//			System.out.println(Arrays.toString(rightSide));
//			System.out.println(leftTotal + " " + rightTotal);
			for (int i = 0; i < count - 1; i++) { // 좌측 union
				for (int j = i + 1; j < count; j++) {
					if (matrix[leftSide[i]][leftSide[j]] == 1)
						union(leftSide[i], leftSide[j]);
				}
			}

			for (int i = 0; i < N - count - 1; i++) { // 우측 union
				for (int j = i + 1; j < N - count; j++) {
					if (matrix[rightSide[i]][rightSide[j]] == 1)
						union(rightSide[i], rightSide[j]);
				}
			}

//			System.out.println(Arrays.toString(parents));
//			System.out.println();
			int tempCnt = 0;
			for (int i = 0; i < N; i++) {
				if (parents[i] < 0)
					tempCnt++;
			}
			if (tempCnt > 2)
				return;
			System.out.println(Arrays.toString(leftSide));
			System.out.println(Arrays.toString(rightSide));
			System.out.println(leftTotal + " " + rightTotal);
			int temp = Math.abs(leftTotal - rightTotal);
			if (ans > temp)
				ans = temp;

			return;
		}
		for (int i = before + 1; i <= N; i++) {
			comb[idx] = i - 1;
			combination(i, idx + 1, count);
		}
	}

	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			parents[rootB] = rootA;
			return true;
		}
		return false;
	}

	public static int find(int f) {
		if (parents[f] < 0)
			return f;
		return parents[f] = find(parents[f]);
	}

}
