package com.ssafy.solve;

import java.util.Arrays;
import java.util.Scanner;

public class Main_1863_종교_이승순 {
	static int N, M, ans;
	static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ans = 0;
		N = sc.nextInt();
		M = sc.nextInt();
		parent = new int[N + 1];
		Arrays.fill(parent, -1);

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			union(a, b);
		}

		for (int i = 1; i <= N; i++) {
			if (parent[i] == -1)
				ans++;
		}
//		System.out.println(Arrays.toString(parent));
		System.out.println(ans);
	}

	private static boolean union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if (A != B) {
			parent[B] = A;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if (parent[a] < 0)
			return a;
		return parent[a] = find(parent[a]);
	}
}
