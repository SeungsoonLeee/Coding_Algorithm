import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution{
	static int N, ans;
	static int[][] map;
	static boolean[][] check;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			check = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt("" + line.charAt(j));
				}
			}
			bfs(0, 0);

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	private static void bfs(int r, int c) {
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
		check[r][c] = true;
		pq.offer(new Pos(r, c, map[r][c]));
		while (!pq.isEmpty()) {
			Pos curP = pq.poll();
			if (curP.r == N - 1 && curP.c == N - 1) {
				ans = curP.v;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nextR = curP.r + dir[i][0];
				int nextC = curP.c + dir[i][1];
				if (nextR >= N || nextC >= N || nextR < 0 || nextC < 0)
					continue;
				if (check[nextR][nextC])
					continue;
				check[nextR][nextC] = true;
				pq.offer(new Pos(nextR, nextC, curP.v + map[nextR][nextC]));
			}
		}
	}
}

class Pos implements Comparable<Pos> {
	int r;
	int c;
	int v;

	public Pos() {
	}

	public Pos(int r, int c, int v) {
		this.r = r;
		this.c = c;
		this.v = v;
	}

	@Override
	public int compareTo(Pos o) {
		return Integer.compare(this.v, o.v);
	}

}