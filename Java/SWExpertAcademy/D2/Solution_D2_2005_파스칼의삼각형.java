import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[][] pascal = new int[n][n];

			for (int i = 0; i < pascal.length; i++) {
				for (int j = 0; j <= i; j++) {
					if (i == j || j == 0) {
						pascal[i][j] = 1;
						continue;
					}
					if (i >= 2)
						pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
				}
			}

			System.out.println("#" + test_case);
			for (int i = 0; i < pascal.length; i++) {
				for (int j = 0; j <= i; j++) {
					System.out.print(pascal[i][j] + " ");
				}
				System.out.println();
			}
		}

	}
}