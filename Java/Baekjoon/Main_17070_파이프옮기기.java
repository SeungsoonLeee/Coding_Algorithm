import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[][][] dir = { { { 0, 1 } } // 가로일때 확인할 좌표
			, { { 1, 0 } } // 세로일때 확인할 좌표
			, { { 0, 1 }, { 1, 1 }, { 1, 0 } } }; // 대각선일때 확인할 좌표
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		ans = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println();
		}
		
		map[0][0] = 2;
		map[0][1] = 2;
		dfs(0, 1, 0);

		System.out.println(ans);
	}

	private static void dfs(int r, int c, int pre) {
		if (r == N - 1 && c == N - 1) {
			ans++;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			return;
		}

		int curR = r;
		int curC = c;
		outer: for (int d = 0; d < dir.length; d++) {
			if (pre == 0 && d == 1) // pre가 가로일 때는 세로를 못놓고
				continue;
			if (pre == 1 && d == 0) // pre가 세로일 때는 가로를 못놓음
				continue;
			int cnt = 0;
			for (int i = 0; i < dir[d].length; i++) {
				int nextR = curR + dir[d][i][0];
				int nextC = curC + dir[d][i][1];
				if (isSide(nextR, nextC) || map[nextR][nextC] == 1)
					continue outer;
				cnt++;

				if (d < 2 && cnt == 1) { // 가로 또는 세로
					map[nextR][nextC] = 2;
					dfs(nextR, nextC, d);
					map[nextR][nextC] = 0;
				} else if (d == 2 && cnt == 3) { // 대각선
					map[nextR][nextC + 1] = 2;
					dfs(nextR, nextC + 1, 2);
					map[nextR][nextC + 1] = 0;
				}
			}
		}
	}

	private static boolean isSide(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N)
			return true;
		return false;
	}
}
