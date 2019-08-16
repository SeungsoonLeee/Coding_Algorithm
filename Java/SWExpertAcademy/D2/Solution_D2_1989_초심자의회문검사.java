// 팰린드롬 검사
//[제약 사항]
//각 단어의 길이는 3 이상 10 이하이다.
//
//[입력]
//가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
//각 테스트 케이스의 첫 번째 줄에 하나의 단어가 주어진다.
//
//[출력]
//출력의 각 줄은 '#t'로 시작하고, 공백을 한 칸 둔 다음 정답을 출력한다.
// (t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.) 

import java.util.Scanner;

public class Solution{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for (int test_case = 1; test_case <= T; ++test_case) {
			int ans = 1;
			String str = sc.nextLine();

			int sIdx = 0;
			int eIdx = str.length() - 1;

			while (sIdx <= eIdx) {
				if (str.charAt(sIdx++) != str.charAt(eIdx--)) {
					ans = 0;
					break;
				}
			}

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}