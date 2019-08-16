import java.util.HashMap;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		HashMap<String, Integer> cc = new HashMap<String, Integer>();
		cc.put("0001101", 0); // 0
		cc.put("0011001", 1); // 1
		cc.put("0010011", 2); // 2
		cc.put("0111101", 3); // 3
		cc.put("0100011", 4); // 4
		cc.put("0110001", 5); // 5
		cc.put("0101111", 6); // 6
		cc.put("0111011", 7); // 7
		cc.put("0110111", 8); // 8
		cc.put("0001011", 9); // 9
		for (int test_case = 1; test_case <= T; test_case++) {
			int ans = 0;
			String code = "";
			int[] codeNum = new int[8];
			int N = sc.nextInt(); // 세로
			int M = sc.nextInt(); // 가로

			int lastIdx = -1;
			sc.nextLine();
			for (int i = 0; i < N; i++) {
				String msg = sc.nextLine();
				lastIdx = msg.lastIndexOf('1');
				if (lastIdx > 0) {
					code = msg.substring(lastIdx - 55, lastIdx + 1);
				}
			}

			String temp;
			int passCode = 0;
			for (int i = 0; i < 8; i++) {
				temp = code.substring(i * 7, i * 7 + 7);
				codeNum[i] = cc.get(temp);
				if (i % 2 == 0) {
					passCode += codeNum[i] * 3;
				} else {
					passCode += codeNum[i];
				}
				ans += codeNum[i];
			}
			if (passCode % 10 != 0)
				ans = 0;

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}