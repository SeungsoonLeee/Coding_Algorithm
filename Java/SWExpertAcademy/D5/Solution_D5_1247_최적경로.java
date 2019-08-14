//삼성전자의 서비스 기사인 김대리는 회사에서 출발하여 냉장고 배달을 위해 N명의 고객을 방문하고 자신의 집에 돌아가려한다.
//회사와 집의 위치, 그리고 각 고객의 위치는 이차원 정수 좌표 (x, y)로 주어지고 (0 ≤ x ≤ 100, 0 ≤ y ≤ 100)
//두 위치 (x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|으로 계산된다.
//여기서 |x|는 x의 절대값을 의미하며 |3| = |-3| = 3이다. 회사의 좌표, 집의 좌표, 고객들의 좌표는 모두 다르다.
//회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것을 찾으려 한다.
//회사와 집의 좌표가 주어지고, 2명에서 10명 사이의 고객 좌표가 주어질 때,
//회사에서 출발해서 이들을 모두 방문하고 집에 돌아가는 경로 중 총 이동거리가 가장 짧은 경로를 찾는 프로그램을 작성하라.
//여러분의 프로그램은 가장 짧은 경로의 이동거리만 밝히면 된다.
//이 문제는 가장 짧은 경로를 ‘효율적으로’ 찾는 것이 목적이 아니다.
//여러분은 모든 가능한 경로를 살펴서 해를 찾아도 좋다.
//모든 경우를 체계적으로 따질 수 있으면 정답을 맞출 수 있다.
//
//[제약사항]
//고객의 수 N은 2≤N≤10 이다.
//그리고 회사의 좌표, 집의 좌표를 포함한 모든 N+2개의 좌표는 서로 다른 위치에 있으며 좌표의 값은 0이상 100 이하의 정수로 이루어진다.
//
//[입력]
//가장 첫줄은 전체 테스트케이스의 수이다.
//이후, 두 줄에 테스트 케이스 하나씩이 차례로 주어진다.
//각 테스트 케이스의 첫째 줄에는 고객의 수 N이 주어진다. 둘째 줄에는 회사의 좌표,집의 좌표, N명의 고객의 좌표가 차례로 나열된다.
//좌표는 (x,y)쌍으로 구성되는데 입력에서는 x와 y가 공백으로 구분되어 제공된다.
//
//[출력]
//총 10줄에 10개의 테스트 케이스 각각에 대한 답을 출력한다.
//각 줄은 ‘#x’로 시작하고 공백을 하나 둔 다음 최단 경로의 이동거리를 기록한다. 여기서 x는 테스트 케이스의 번호다. 

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int ans;
	static int n;
	static Pos[] location;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; ++test_case) {
			ans = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			location = new Pos[n + 2];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < location.length; i++) {
				Pos temp = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				location[i] = temp;
			}
			// 0:회사, 1:집
			// 순열로 다 거리 구하고 최소인 거리로 출력하자.
			getMinDistance(2);
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

	private static void getMinDistance(int idx) {
		if (idx >= location.length) {
			int temp = calDistance();
			if (ans > temp)
				ans = temp;
			return;
		}
		for (int i = idx; i < location.length; i++) {
			swap(i, idx);
			getMinDistance(idx + 1);
			swap(i, idx);
		}
	}

	private static int calDistance() {
		int distance = 0;
		// 회사에서 첫 방문
		distance += Math.abs(location[0].x - location[2].x) + Math.abs(location[0].y - location[2].y);
		// 첫 방문에서 끝 방문
		for (int i = 2; i < location.length - 1; ++i)
			distance += Math.abs(location[i].x - location[i + 1].x) + Math.abs(location[i].y - location[i + 1].y);
		// 끝 방문에서 집
		distance += Math.abs(location[location.length - 1].x - location[1].x) + Math.abs(location[location.length - 1].y - location[1].y);
		return distance;
	}

	private static void swap(int i, int j) {
		Pos temp = location[i];
		location[i] = location[j];
		location[j] = temp;
	}
}

class Pos {
	public int x;
	public int y;

	public Pos() {
	}

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}