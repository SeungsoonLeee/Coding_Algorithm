//N2개의 방이 N×N형태로 늘어서 있다.
//위에서 i번째 줄의 왼쪽에서 j번째 방에는 1이상 N2 이하의 수 Ai,j가 적혀 있으며, 이 숫자는 모든 방에 대해 서로 다르다.
//당신이 어떤 방에 있다면, 상하좌우에 있는 다른 방으로 이동할 수 있다.
//물론 이동하려는 방이 존재해야 하고, 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야 한다.
//처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동할 수 있는지 구하는 프로그램을 작성하라.
//
//[입력]
//첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
//각 테스트 케이스의 첫 번째 줄에는 하나의 정수 N (1 ≤ N ≤ 103)이 주어진다.
//다음 N개의 줄에는 i번째 줄에는 N개의 정수 Ai, 1, … , Ai, N (1 ≤ Ai, j ≤ N2) 이 공백 하나로 구분되어 주어진다.
//Ai, j는 모두 서로 다른 수이다.
//
//[출력]
//각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,
//한 칸을 띄운 후, 처음에 출발해야 하는 방 번호와 최대 몇 개의 방을 이동할 수 있는지를 공백으로 구분하여 출력한다.
//이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 출력한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, cnt, ans;
	static int[][] room;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; ++test_case) {
			ans = Integer.MAX_VALUE;
			cnt = -1;
			N = Integer.parseInt(br.readLine());
			room = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; ++j) {
					bfs(i, j);
				}
			}

			System.out.printf("#%d %d %d\n", test_case, ans, cnt);
		}
	}

	public static void bfs(int y, int x) {
		int tempCnt = 1;
		Queue<Integer> queueY = new LinkedList<Integer>();
		Queue<Integer> queueX = new LinkedList<Integer>();
		queueY.offer(y);
		queueX.offer(x);

		while (!queueY.isEmpty()) {
			int curY = queueY.poll();
			int curX = queueX.poll();
			for (int i = 0; i < 4; i++) {
				int nextY = curY + dir[i][0];
				int nextX = curX + dir[i][1];
				if (nextY >= N || nextX >= N || nextY < 0 || nextX < 0)
					continue;
				if (room[nextY][nextX] == room[curY][curX] + 1) {
					tempCnt++;
					queueY.offer(nextY);
					queueX.offer(nextX);
				}
			}
		}

		if (cnt < tempCnt) {
			ans = room[y][x];
			cnt = tempCnt;
		} else if (cnt == tempCnt && ans > room[y][x]) {
			ans = room[y][x];
		}
	}
}
