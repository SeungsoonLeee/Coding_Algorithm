// NxN 배열 안의 가로 세로 팰린드롬 검사
//
//[제약 사항]
//각 칸의 들어가는 글자는 c언어 char type으로 주어지며 'A', 'B', 'C' 중 하나이다.
//글자 판은 무조건 정사각형으로 주어진다.
//ABA도 회문이며, ABBA도 회문이다. A또한 길이 1짜리 회문이다.
//가로, 세로 각각에 대해서 직선으로만 판단한다.
//
//[입력]
//각 테스트 케이스의 첫 번째 줄에는 찾아야 하는 회문의 길이가 주어지며, 다음 줄에 테스트 케이스가 주어진다.
//총 10개의 테스트 케이스가 주어진다.
//
//[출력]
//#부호와 함께 테스트 케이스의 번호를 출력하고, 공백 문자 후 찾은 회문의 개수를 출력한다. 

import java.util.Scanner;

public class Solution {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for (int test_case = 1; test_case <= T; ++test_case) {
			int ans = 0;
			int n = Integer.parseInt(sc.nextLine());
			int size = 8;
			String data;
			char[][] chars = new char[size][size];

			for (int i = 0; i < size; i++) {
				data = sc.nextLine();
				for (int j = 0; j < size; j++) {
					chars[i][j] = data.charAt(j);
				}
			}

			String str;
			for (int i = 0; i <= size - n; i++) {
				for (int j = 0; j < size; j++) {
					str = "";
					for (int k = 0; k < n; k++) {
						str += chars[i + k][j];
					}
					if (isPalindrom(str))
						ans++;
				}
			}

			for (int i = 0; i < size; i++) {
				for (int j = 0; j <= size - n; j++) {
					str = "";
					for (int k = 0; k < n; k++) {
						str += chars[i][j + k];
					}
					if (isPalindrom(str))
						ans++;
				}
			}

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	public static boolean isPalindrom(String str) {
		boolean temp = true;
		int sIdx = 0;
		int eIdx = str.length() - 1;

		while (sIdx <= eIdx) {
			if (str.charAt(sIdx++) != str.charAt(eIdx--)) {
				temp = false;
				break;
			}
		}
		return temp;
	}
}
