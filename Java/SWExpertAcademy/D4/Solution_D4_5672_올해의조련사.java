import java.util.Scanner;

public class Solution {
	static int N;
	static char[] chars;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			String ans = "";
			N = sc.nextInt();
			chars = new char[N];

			for (int i = 0; i < N; i++) {
				chars[i] = sc.next().charAt(0);
			}

			int left = 0;
			int right = N - 1;
			while (left < right) {
				if (chars[left] < chars[right]) {
					ans += chars[left++];
				} else if (chars[left] > chars[right]) {
					ans += chars[right--];
				} else {
					int tempL = left;
					int tempR = right;
					while (tempL < tempR) {
						if (chars[tempL] < chars[tempR]) {
							ans += chars[left++];
							break;
						} else if (chars[tempL] > chars[tempR]) {
							ans += chars[right--];
							break;
						}
						tempL++;
						tempR--;
						if (tempL == tempR || tempL > tempR)
							ans += chars[left++];
					}
				}
				if (left == right)
					ans += chars[left];
			}

			System.out.printf("#%d %s\n", test_case, ans);
		}
	}
}
