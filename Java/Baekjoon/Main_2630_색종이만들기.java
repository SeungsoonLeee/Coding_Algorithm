import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int whiteCnt;
	static int blueCnt;
	static int[][] matrix;
	static boolean[][] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		check = new boolean[N][N];
		whiteCnt = 0;
		blueCnt = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(matrix[i][j] + " ");
			}
//			System.out.println();
		}

		bfs(0, 0);

		System.out.println(whiteCnt);
		System.out.println(blueCnt);
	}

	private static void bfs(int y, int x) {
		Queue<Integer> queY = new LinkedList<Integer>();
		Queue<Integer> queX = new LinkedList<Integer>();
		Queue<Integer> queRange = new LinkedList<Integer>();
		queY.offer(y);
		queX.offer(x);
		queRange.offer(N);

		outer: while (!queY.isEmpty()) {
			int curY = queY.poll();
			int curX = queX.poll();
			int curRange = queRange.poll();

			for (int i = curY; i < curY + curRange; i++) {
				for (int j = curX; j < curX + curRange; j++) {
					if (i == curY && j == curX) {
						if (curRange == 1) { // 1칸일 경우 체크
							if (!check[curY][curX]) {
								if (matrix[curY][curX] == 0) {
									whiteCnt++;
								} else {
									blueCnt++;
								}
								check[curY][curX] = true;
							}
						}
					} else {
						if (matrix[curY][curX] != matrix[i][j]) {
							int tempRange = curRange / 2;
							// 첫번째
							queY.offer(curY);
							queX.offer(curX);
							queRange.offer(tempRange);
							// 두번째
							queY.offer(curY);
							queX.offer(curX + tempRange);
							queRange.offer(tempRange);
							// 세번재
							queY.offer(curY + tempRange);
							queX.offer(curX);
							queRange.offer(tempRange);
							// 네번째
							queY.offer(curY + tempRange);
							queX.offer(curX + tempRange);
							queRange.offer(tempRange);
							continue outer;
						}
					}

					// 여기까지 왔다면 색종이 색깔이 모두 같은 경우임
					if (!check[curY][curX]) {
						if (matrix[curY][curX] == 0) {
							whiteCnt++;
						} else {
							blueCnt++;
						}
						check[curY][curX] = true;
					}
				}
			}
		}
	}
}
