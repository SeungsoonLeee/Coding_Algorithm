package com.ssafy.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	static int N, K;
	static char[] input;
	static ArrayList<String> nums;
	static ArrayList<Integer> ans;

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			input = new char[N];
			nums = new ArrayList<String>();
			ans = new ArrayList<Integer>();

			String line = sc.next();
			for (int i = 0; i < N; i++) {
				input[i] = line.charAt(i);
			}

			makeNums();
			numsToDecimal();

			System.out.println("#" + test_case + " " + ans.get(ans.size() - K));
		}
	}

	private static void makeNums() {
		int cnt = N / 4;

		for (int i = 0; i < cnt; i++) {
			char temp = input[N - 1];
			for (int j = N - 1; j >= 1; j--) {
				input[j] = input[j - 1];
			}
			input[0] = temp;

			for (int j = 0; j < 4; j++) {
				String temp2 = "";
				for (int k = j * cnt; k < (j + 1) * cnt; k++) {
					temp2 += input[k];
				}

				if (!nums.contains(temp2))
					nums.add(temp2);
			}
		}
	}

	private static void numsToDecimal() {
		for (int i = 0, size = nums.size(); i < size; i++) {
			ans.add(Integer.parseInt(nums.get(i), 16));
		}
		Collections.sort(ans);
	}

}
