import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int[][] check;
	static int N, M, ans, holeR, holeC, time;
	static int[][][] dir = { { {} } // 인덱스를 맞추기 위한 빈칸
			, { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } } // 상하좌우
			, { { -1, 0 }, { 1, 0 } } // 상하
			, { { 0, -1 }, { 0, 1 } } // 좌우
			, { { -1, 0 }, { 0, 1 } } // 상우
			, { { 1, 0 }, { 0, 1 } } // 하우
			, { { 1, 0 }, { 0, -1 } } // 하좌
			, { { -1, 0 }, { 0, -1 } } }; // 상좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; ++test_case) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ans = 0;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			holeR = Integer.parseInt(st.nextToken());
			holeC = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			check = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
//					System.out.print(map[i][j] + " ");
				}
//				System.out.println();
			}

			bfs(holeR, holeC, time);

			System.out.printf("#%d %d\n", test_case, ans);
		}

	}

	private static void bfs(int r, int c, int t) {
		Queue<Integer> queR = new LinkedList<Integer>();
		Queue<Integer> queC = new LinkedList<Integer>();
		queR.offer(r);
		queC.offer(c);
		check[r][c] = 1;
		ans++;
		for (int i = 1; i < time; i++) {
			int size = queR.size();
			for (int j = 0; j < size; j++) {
				int curR = queR.poll();
				int curC = queC.poll();
				int d = map[curR][curC];
				for (int k = 0; k < dir[d].length; k++) {
					int nextR = curR + dir[d][k][0];
					int nextC = curC + dir[d][k][1];
					if (isSide(nextR, nextC) || check[nextR][nextC] != 0 || map[nextR][nextC] == 0)
						continue;
					int pipeNum = map[nextR][nextC];
					if (dir[d][k][0] == -1 && dir[d][k][1] == 0) { // 상
						if (pipeNum == 3 || pipeNum == 4 || pipeNum == 7)
							continue;
					} else if (dir[d][k][0] == 1 && dir[d][k][1] == 0) { // 하
						if (pipeNum == 3 || pipeNum == 5 || pipeNum == 6)
							continue;
					} else if (dir[d][k][0] == 0 && dir[d][k][1] == -1) { // 좌
						if (pipeNum == 2 || pipeNum == 6 || pipeNum == 7)
							continue;
					} else if (dir[d][k][0] == 0 && dir[d][k][1] == 1) { // 우
						if (pipeNum == 2 || pipeNum == 4 || pipeNum == 5)
							continue;
					}
					queR.offer(nextR);
					queC.offer(nextC);
					check[nextR][nextC] = check[curR][curC] + 1;
					ans++;
				}
			}
		}
	}

	private static boolean isSide(int r, int c) {
		if (r >= N || c >= M || r < 0 || c < 0)
			return true;
		return false;
	}

}
