package com.lss.swExpertAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_D4_3378_스타일리쉬들여쓰기 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input/Solution_3378_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int R = 0, C = 0, S = 0;
			int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0; // a, c, e 각각이 0이면 b, d, f도 각각 무조건 0
			int p = sc.nextInt();
			int q = sc.nextInt();
			String[] pLine = new String[p];
			String[] qLine = new String[q];
			int[] ans = new int[q];
			boolean[] check = new boolean[q];
			String line;
			
			for (int i = 0; i < p; i++) {
				pLine[i] = sc.next();
			}
			for (int i = 0; i < q; i++) {
				qLine[i] = sc.next();
			}

			for (R = 1; R <= 20; R++) {
				for (C = 1; C <= 20; C++) {
					next: for (S = 1; S <= 20; S++) {// R, C, S 값 설정해주고
						a = 0;b = 0;c = 0;d = 0;e = 0;f = 0;
						for (int i = 0; i < p; i++) { // 각 라인별로
							line = pLine[i];
							int dotCnt = 0;
							int checkDotCnt = R * (a - b) + C * (c - d) + S * (e - f);
							
							for (int j = 0; j < line.length(); j++) { // 특문확인
								if (line.charAt(j) == '(')
									++a;
								else if (line.charAt(j) == ')')
									++b;
								else if (line.charAt(j) == '{')
									++c;
								else if (line.charAt(j) == '}')
									++d;
								else if (line.charAt(j) == '[')
									++e;
								else if (line.charAt(j) == ']')
									++f;
							}

							if (line.startsWith(".")) { // 온점 갯수 확인
								while (line.charAt(dotCnt) == '.')
									dotCnt++;
							}

							if (dotCnt != checkDotCnt) // 온점 갯수가 맞지 않으면 다음 R, C, S로
								continue next;
						}
						
						
						// 넘어 왔다면 온점 들여쓰기에 문제가 없는 R, C, S 경우임 -> 내꺼 체크
						a = 0;b = 0;c = 0;d = 0;e = 0;f = 0;
						for (int i = 0; i < q; i++) {
							line = qLine[i];
							int ansDotCnt = R * (a - b) + C * (c - d) + S * (e - f);
							if(check[i] == true && ans[i] != ansDotCnt)
								ans[i] = -1;
							else {
								check[i] = true;
								ans[i] = ansDotCnt;
							}
								
							for (int j = 0; j < line.length(); j++) { // 특문확인
								if (line.charAt(j) == '(')
									++a;
								else if (line.charAt(j) == ')')
									++b;
								else if (line.charAt(j) == '{')
									++c;
								else if (line.charAt(j) == '}')
									++d;
								else if (line.charAt(j) == '[')
									++e;
								else if (line.charAt(j) == ']')
									++f;
							}
						}
					}
				}
			}

			System.out.print("#" + test_case);
			for (int i : ans) {
				System.out.printf(" " + i);
			}
			System.out.println();
		}
	}

}
