// 가장 높은 곳에서 가장 낮은곳으로 블럭을 하나씩 옮기며 평탄화 작업을 한다.
// 평탄화 작업 횟수 적용 후 가장 높은곳과 가장 낮은곳의 차이를 구하라.
// 단, 평탄화 최고 최저 차이가 1차이가 되면 더이상 하지 않는다.

import java.util.Scanner;

public class Solution_D3_1208_Flatten {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; ++test_case) {
			int result = 0;
			int[] height = new int[100];
			int dump = sc.nextInt();

			for (int i = 0; i < 100; ++i)
				height[i] = sc.nextInt();

			int minmaxIdx[] = new int[2];
			minmaxIdx = getMinMaxIdx(height);

			for (int i = 0; i < dump; ++i) {
				if (height[minmaxIdx[1]] - height[minmaxIdx[0]] <= 1)
					break;
				++height[minmaxIdx[0]];
				--height[minmaxIdx[1]];
				minmaxIdx = getMinMaxIdx(height);
			}
			
			result = height[minmaxIdx[1]] - height[minmaxIdx[0]];
			System.out.printf("#%d %d\n", test_case, result);
		}
	}

	public static int[] getMinMaxIdx(int[] arr) {
		int min = 987987987, max = -1;
		int[] minmaxIdx = new int[2];

		for (int i = 0; i < arr.length; ++i) {
			if (min > arr[i]) {
				min = arr[i];
				minmaxIdx[0] = i;
			}
			if (max < arr[i]) {
				max = arr[i];
				minmaxIdx[1] = i;
			}
		}

		return minmaxIdx;
	}
}
