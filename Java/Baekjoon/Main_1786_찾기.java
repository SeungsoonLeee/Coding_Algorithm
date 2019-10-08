package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		int tLen = T.length();
		int pLen = P.length();
		int[] next = new int[pLen];
		char[] ts = T.toCharArray();
		char[] ps = P.toCharArray();
		ArrayList<Integer> ans = new ArrayList<Integer>();

		// 1. 탐색 실패한 경우에 돌아갈 인덱스 베열을 먼저 만들어줌.
		for (int i = 1, j = 0; i < pLen; i++) {
			while (j > 0 && ps[i] != ps[j])
				j = next[j - 1];

			if (ps[i] == ps[j])
				next[i] = ++j;
		}

		int cnt = 0;
		for (int i = 0, j = 0; i < tLen; i++) {
			while (j > 0 && ts[i] != ps[j]) {
				j = next[j - 1];
			}
			if (ts[i] == ps[j]) {
				if (j == pLen - 1) { // 패턴의 끝까지 다 맞는 경우
					cnt++;
					ans.add(i + 1 - pLen + 1);
					j = next[j];
				} else {
					++j;
				}
			}
		}

		System.out.println(cnt);
		for (int i = 0; i < ans.size(); i++) {
			System.out.print(ans.get(i) + " ");
		}
	}
}
