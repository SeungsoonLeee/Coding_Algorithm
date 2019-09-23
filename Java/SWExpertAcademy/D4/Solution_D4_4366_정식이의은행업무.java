package com.lss.swExpertAcademy;

import java.util.Scanner;

public class Solution_D4_4366_정식이의은행업무 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int ans = 0;
			String bi = sc.next();
			String tri = sc.next();

			outer: for (int i = 0; i < bi.length(); i++) {
				StringBuilder sb = new StringBuilder(bi);
				sb.setCharAt(i, bi.charAt(i) == '1' ? '0' : '1');
				int biToDeci = Integer.parseUnsignedInt(sb.toString(), 2);
				String tempTri = getTri(biToDeci);
//				System.out.println("2진수를 10진수로 : " + biToDeci);
//				System.out.println("그 수를 다시 3진수로 : " + tempTri);
				
				int cnt = 0;
				if (tempTri.length() > tri.length() || tri.length() - tempTri.length() >= 2) {
					continue;
				}
				if (tri.length() == tempTri.length()) {
					for (int j = 0; j < tri.length(); j++) {
						if (tempTri.charAt(j) != tri.charAt(j))
							cnt++;
						if (cnt > 1)
							continue outer;
					}
					if (cnt == 1) {
						ans = biToDeci;
						break outer;
					}
				} else {
					String tri2 = tri.substring(1);
					cnt++;
					for (int j = 0; j < tri2.length(); j++) {
						if (tempTri.charAt(j) != tri2.charAt(j))
							cnt++;
						if (cnt > 1)
							continue outer;
					}
					if (cnt == 1) {
						ans = biToDeci;
						break outer;
					}
				}
			}

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	private static String getTri(int num) {
		String temp = "";
		while (num / 3 > 0) {
			temp += num % 3;
			num /= 3;
		}
		temp += num;
		StringBuilder sb = new StringBuilder(temp);

		return sb.reverse().toString();
	}
}
