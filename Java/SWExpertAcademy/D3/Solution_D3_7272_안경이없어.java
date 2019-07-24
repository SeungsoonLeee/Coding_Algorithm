//어느 날 경근이는 알파벳 대문자로 이루어진 두 문자열을 비교해야 했는데, 이 날은 공교롭게도 안경이 없었다.
//경근이는 매우 눈이 나빠서 안경을 벗으면 문자열을 문자 단위로 구별할 수는 있지만, 두 문자가 정확히 같은 지는 알지 못한다.
//특히 알파벳 대문자 같은 경우 문자에 나 있는 구멍의 개수가 같으면 같은 문자이고, 다르면 다른 문자라고 생각한다.
//예를 들어 구멍이 하나도 없는 CEFGHIJKLMNSTUVWXYZ들을 같은 문자로 생각하고,
//구멍이 한 개 나 있는 ADOPQR들을 같은 문자로 생각하며,
//구멍이 두 개 나 있는 유일한 문자 B는 유일하게 정확히 알 수 있다.
//알파벳 대문자로 이루어진 두 문자열이 주어졌을 때, 경근이는 두 문자열이 같다고 판별하는지 다르다고 판별할 것인가?

//입력
//5
//ABCD EFGH
//ABCD PBZO
//PRQO OQAD
//ZXCV HJKL
//BBBB BBB

//출력
//#1 DIFF
//#2 SAME
//#3 SAME
//#4 SAME
//#5 DIFF

import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		String ans = "";
		T = sc.nextInt();
		char[] noCircle = { 'C', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z' };
		char[] oneCircle = { 'A', 'D', 'O', 'P', 'Q', 'R' };
		char[] twoCircle = { 'B' };
		for (int test_case = 1; test_case <= T; test_case++) {
			String str1 = sc.next();
			String str2 = sc.next();
			for (int i = 0; i < noCircle.length; i++) {
				str1 = str1.replace(noCircle[i], 'a');
			}
			for (int i = 0; i < oneCircle.length; i++) {
				str1 = str1.replace(oneCircle[i], 'b');
			}
			str1 = str1.replace('B', 'c');
			
			for (int i = 0; i < noCircle.length; i++) {
				str2 = str2.replace(noCircle[i], 'a');
			}
			for (int i = 0; i < oneCircle.length; i++) {
				str2 = str2.replace(oneCircle[i], 'b');
			}
			str2 = str2.replace('B', 'c');
			
			if (str1.equals(str2))	ans = "SAME";
			else	ans = "DIFF";

			System.out.printf("#%d %s\n", test_case, ans);
		}
	}
}