//N x N 배열 안에서 M x M 합 중 최대값을 구해라
//
//[제약 사항]
//1. N 은 5 이상 15 이하이다.
//2. M은 2 이상 N 이하이다.
//3. 각 영역의 파리 갯수는 30 이하 이다.
//
//[입력]
//가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
//각 테스트 케이스의 첫 번째 줄에 N 과 M 이 주어지고,
//다음 N 줄에 걸쳐 N x N 배열이 주어진다.
//
//[출력]
//출력의 각 줄은 '#t'로 시작하고, 공백을 한 칸 둔 다음 정답을 출력한다.
// (t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.) 

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			int ans = -1;
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] nums = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					nums[i][j] = sc.nextInt();
				}
			}

			int temp;
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					temp = 0;
					for (int k = 0; k < M; k++) {
						for (int k2 = 0; k2 < M; k2++) {
							temp += nums[i + k][j + k2];
						}
 					}
					if (temp > ans)
						ans = temp;
				}
			}

			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}