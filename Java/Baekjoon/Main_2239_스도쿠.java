import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int[][] map;
	static boolean isComplete;
	static ArrayList<Point> zeroPlace;
	static int N = 9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[N][N];
		zeroPlace = new ArrayList<Point>();
		isComplete = false;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt("" + line.charAt(j));
				if (map[i][j] == 0)
					zeroPlace.add(new Point(i, j));
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println(); 
		}

		dfs(0);
	}

	private static void dfs(int idx) {
		if(isComplete)
			return;
		
		if (idx >= zeroPlace.size()) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			isComplete = true;
			return;
		}

		Point cur = zeroPlace.get(idx);
		for (int i = 1; i < 10; i++) {
			if (!checkHorizonal(cur, i) || !checkVertical(cur, i) || !checkBlock(cur, i))
				continue;
			map[cur.r][cur.c] = i;
			dfs(idx + 1);
			map[cur.r][cur.c] = 0;
		}
	}

	private static boolean checkHorizonal(Point p, int num) {
		for (int i = 0; i < N; i++) {
			if (map[p.r][i] == num)
				return false;
		}
		return true;
	}

	private static boolean checkVertical(Point p, int num) {
		for (int i = 0; i < N; i++) {
			if (map[i][p.c] == num)
				return false;
		}
		return true;
	}

	private static boolean checkBlock(Point p, int num) {
		int r = 3 * (p.r / 3);
		int c = 3 * (p.c / 3);
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				if (map[i][j] == num)
					return false;
			}
		}
		return true;
	}
}

class Point {
	int r;
	int c;

	public Point() {
	}

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + "]";
	}
}