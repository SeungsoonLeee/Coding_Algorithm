package com.ssafy.solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_D4_4261_빠른휴대전화키패드 {
	static int ans;
	static HashMap<Integer, String> keys;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		keys = new HashMap<Integer, String>();
		keys.put(2, "abc");
		keys.put(3, "def");
		keys.put(4, "ghi");
		keys.put(5, "jkl");
		keys.put(6, "mno");
		keys.put(7, "pqrs");
		keys.put(8, "tuv");
		keys.put(9, "wxyz");

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			String nums = st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			String[] words = new String[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				words[i] = st.nextToken();
			}

			for (int i = 0; i < N; i++) {
				int size = words[i].length();
				if (nums.length() == size) {
					int cnt = 0;
					for (int j = 0; j < size; j++) {
						if (keys.get(Integer.parseInt("" + nums.charAt(j))).contains("" + words[i].charAt(j)))
							cnt++;
					}
					if (cnt == size)
						ans++;
				}
			}

			bw.write("#" + test_case + " " + ans + "\n");
			bw.flush();
		}
	}
}