package com.ssafy.step1.homework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1233_사칙연산유효성검사 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int ans = 1;
			boolean isImpossible = false;
			int n = Integer.parseInt(br.readLine());
			String[] str = new String[n];
			for (int i = 0; i < n; i++) {
				str[i] = br.readLine();
			}

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(str[i]);
				int idx = 0;
				int[] data = new int[st.countTokens()];
				while (st.hasMoreTokens()) {
					String temp = st.nextToken();
					if (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/"))
						data[idx++] = temp.charAt(0);
					else
						data[idx++] = Integer.parseInt(temp);
				}
				if (data.length == 2) { // 자식 노드가 하나도 없을때(리프 노드일 때) 반드시 숫자여야 함
					if (data[1] == '+' || data[1] == '-' || data[1] == '*' || data[1] == '/') {
						isImpossible = true;
						break;
					}
				} else if (data.length == 3) { // 왼쪽 자식 노드만 있을 때가 있으면 안됨
					isImpossible = true;
					break;
				} else { // 양쪽 자식 노드가 모두 있을 때 연산자 자리는 숫자가 오면 안됨
					if (data[1] >= '0' && data[1] <= '9') {
						isImpossible = true;
						break;
					}
				}
			}

			if (isImpossible)
				ans = 0;
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}