package com.ssafy.solution;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int N, K;
	static double ans;
	static double[] points;
	static double[] select;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); // 강좌 수
			K = sc.nextInt(); // 선택할 강좌 수
			points = new double[N];
			select = new double[K];
			ans = 0.0;

			for (int i = 0; i < N; i++) {
				points[i] = (double) sc.nextInt();
			}

			Arrays.sort(points);
			int idx = points.length - K;
			for (int i = 0; i < K; i++) {
				select[i] = points[idx++];
				ans = calSkill(ans, select[i]);
			}

			System.out.printf("#%d %f\n", test_case, ans);
		}
	}

	private static double calSkill(double A, double M) {
		return (A + M) / 2.0;
	}

}
