import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static char[][] map;
	static int[][] wbr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			wbr = new int[N][3];

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'W') {
						wbr[i][1]++;
						wbr[i][2]++;
					} else if (map[i][j] == 'B') {
						wbr[i][0]++;
						wbr[i][2]++;
					} else {
						wbr[i][0]++;
						wbr[i][1]++;
					}
//					System.out.print(map[i][j] + " ");
				}
//				System.out.println();
			}

			int ans = Integer.MAX_VALUE, temp = 0;
			for (int i = 1; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					temp = 0;

					// 흰색
					for (int k = 0; k < i; k++) {
						temp += wbr[k][0];
					}
					// 파란색
					for (int k = i; k < j; k++) {
						temp += wbr[k][1];
					}
					// 빨간색
					for (int k = j; k < N; k++) {
						temp += wbr[k][2];
					}

					if (temp < ans)
						ans = temp;
				}
			}

			System.out.println("#" + test_case + " " + ans);
		}
	}
}
