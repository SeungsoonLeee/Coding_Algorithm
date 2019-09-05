//캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다. 게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다.
//격자판은 1×1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대 하나이다. 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.
//성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다.
//궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다.
//각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다.
//궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다.
//같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다.
//궁수의 공격이 끝나면, 적이 이동한다. 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다.
//모든 적이 격자판에서 제외되면 게임이 끝난다. 
//게임 설명에서 보다시피 궁수를 배치한 이후의 게임 진행은 정해져있다. 따라서, 이 게임은 궁수의 위치가 중요하다.
//격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.
//격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.
//
//[입력]
//첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다. 둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸이다.
//
//[출력]
//첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.
//
//[제한]
//3 ≤ N, M ≤ 15
//1 ≤ D ≤ 10

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, D, ans;
	static int[][] map;
	static int[][] initMap;
	static int[] comb;
	static int cnt;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		ans = 0;
		comb = new int[3];
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		map = new int[N + 1][M];
		initMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				initMap[i][j] = map[i][j];
			}
		}

		combination(0, 0);

		System.out.println(ans);
	}

	public static void combination(int before, int idx) {
		if (idx >= 3) {
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					map[i][j] = initMap[i][j];
			cnt = 0;
			play();
			return;
		}
		for (int i = before + 1; i <= M; i++) {
			comb[idx] = i - 1;
			combination(i, idx + 1);
		}
	}

	public static void play() {
		Queue<Integer> killY = new LinkedList<Integer>();
		Queue<Integer> killX = new LinkedList<Integer>();

		while (!isMapEmpty()) {
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			// 각 궁수 별 위치를 파악해서 위치시키고
			// 궁수별 사거리에 위치한 가장 가깝운 왼쪽 적을 que에 삽입
			for (int i = 0; i < 3; i++) {
				int archerY = N - 1;
				int archerX = comb[i];
				breakPoint: for (int j = 0; j < D; ++j) {
					int rangeY = archerY;
					int rangeX = archerX - j;
					while (rangeX <= archerX + j) {
						if (rangeY >= 0 && rangeX >= 0 && rangeX < M) {
							if (map[rangeY][rangeX] == 1) {
								killY.offer(rangeY);
								killX.offer(rangeX);
								break breakPoint;
							}
						}
						if (rangeX < archerX)
							rangeY--;
						else
							rangeY++;
						rangeX++;
					}
				}
			}

			// que를 하나씩 꺼내서 처리
			while (!killY.isEmpty()) {
				int y = killY.poll();
				int x = killX.poll();
				if (map[y][x] == 1)
					cnt++;
				map[y][x] = 0;
			}

			// 한턴씩 내려옴
			for (int i = 0; i < M; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (j == 0)
						map[j][i] = 0;
					else
						map[j][i] = map[j - 1][i];
				}
			}
		}
		
		if (ans < cnt)
			ans = cnt;
	}

	public static boolean isMapEmpty() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] != 0)
					return false;
		return true;
	}
}