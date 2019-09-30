import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int ans;
	static int N, M;
	static ArrayList<Point> p = new ArrayList<Point>();
	static int[][] map;
	static int[][] dir = { { -2, 0 }, { 2, 0 }, { 0, -2 }, { 0, 2 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = 0;
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					boolean check = false;

					for (int k = 0; k < 4; k++) {
						int nextR = i - dir[k][0];
						int nextC = j - dir[k][1];
						if (nextR >= N || nextC >= M || nextR < 0 || nextC < 0)
							continue;
						if (map[nextR][nextC] == 1)
							check = true;
					}

					if (check)
						continue;

					map[i][j] = 1;
					ans++;
				}
			}

			System.out.println("#" + test_case + " " + ans);
		}
	}
}
