// n*n 배열에서 최대 크기의 마름모 내의 숫자를 모두 더하라

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int ans = 0;
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			int mid = n / 2;
            
			for (int i = 0; i < n; i++) {
				String data = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = data.charAt(j) - '0';
					if (Math.abs(mid - i) + Math.abs(mid - j) <= mid)
						ans += map[i][j];
				}
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}