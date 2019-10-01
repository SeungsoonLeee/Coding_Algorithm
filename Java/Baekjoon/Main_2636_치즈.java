package solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_치즈 {
	static int N, M, totalCheese, hour, cnt;
	static int[][] map;
	static boolean[][] check;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new boolean[N][M];
		totalCheese = 0;
		hour = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					totalCheese++;
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println();
		}

		int tempR;
		int tempC;
		boolean isNotside;
		while (totalCheese > 0) {
			bfs(0, 0);
		}

		System.out.println(hour + "\n" + cnt);
	}

	private static void bfs(int r, int c) {
		check = new boolean[N][M];
		Queue<Integer> queY = new LinkedList<Integer>();
		Queue<Integer> queX = new LinkedList<Integer>();
		queY.offer(r);
		queX.offer(c);
		check[r][c] = true;
		cnt = totalCheese;
		while (!queY.isEmpty()) {
			int curY = queY.poll();
			int curX = queX.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = curY + dir[i][0];
				int nextX = curX + dir[i][1];
				if (nextY >= N || nextX >= M || nextY < 0 || nextX < 0)
					continue;
				if (check[nextY][nextX])
					continue;
				if (map[nextY][nextX] == 0) {
					check[nextY][nextX] = true;
					queY.offer(nextY);
					queX.offer(nextX);
				} else {
					map[nextY][nextX] = 0;
					check[nextY][nextX] = true;
					totalCheese--;
				}
			}
		}
		hour++;
	}
}
