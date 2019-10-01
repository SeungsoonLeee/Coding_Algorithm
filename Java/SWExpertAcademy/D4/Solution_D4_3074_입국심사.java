import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static long ans, maxPeopleCnt;
	static long[] t;

	public static void main(String[] args) throws NumberFormatException, IOException {
		ans = 0;
		maxPeopleCnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			t = new long[N];
			for (int i = 0; i < N; i++) {
				t[i] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(t);
			maxPeopleCnt = t[N - 1];
			ans = maxPeopleCnt * M;
			long maxTime = maxPeopleCnt * M;
			long start = 0;
			long mid = 0;

			while (start <= maxTime) {
				mid = (start + maxTime) / 2;
				long checkCnt = 0;
				for (int i = 0; i < N; i++) {
					checkCnt += (mid / t[i]);
				}

				if (checkCnt < M) {
					start = mid + 1;
				} else {
					if (ans > mid) {
						ans = mid;
					}
					maxTime = mid - 1;
				}
			}

			System.out.println("#" + test_case + " " + ans);
		}
	}
}