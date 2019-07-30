package com.ssafy.step1.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Solution_D4_1218_괄호짝짓기 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			int ans = 0;
			int n = Integer.parseInt(sc.next());
			String str = sc.next();
			Stack<Character> stack = new Stack<Character>();

			for (int i = 0; i < n; i++) {
				if (str.charAt(i) == ')' && !stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
				} else if (str.charAt(i) == ']' && !stack.isEmpty() && stack.peek() == '[') {
					stack.pop();
				} else if (str.charAt(i) == '}' && !stack.isEmpty() && stack.peek() == '{') {
					stack.pop();
				} else if (str.charAt(i) == '>' && !stack.isEmpty() && stack.peek() == '<') {
					stack.pop();
				} else {
					stack.push(str.charAt(i));
				}
			}

			if (stack.empty())
				ans = 1;
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
