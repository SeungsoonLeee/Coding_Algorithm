//영준이는 학생들의 시험을 위해 N개의 문제를 만들었다.
//각 문제의 배점은 문제마다 다를 수 있고, 틀리면 0점 맞으면 배점만큼의 점수를 받게 된다.
//학생들이 받을 수 있는 점수로 가능한 경우의 수는 몇 가지가 있을까?
//예를 들어, 첫 번쨰 Testcase의 경우, 총 문제의 개수는 3개이며 각각의 배점은 2, 3, 5점이다
// 가능한 시험 점수의 경우의 수를 살펴보면 0, 2, 3, 5, 7, 8, 10의 7가지가 있다.
//두 번째 Testcase의 경우, 총 문제의 개수는 10개이며 각각의 배점은 모두 1점이다.
//가능한 시험점수의 경우의 수를 살펴보면 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10으로 모두 11가지이다.
//
//[입력]
//첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
//각 테스트 케이스의 첫 번째 줄에는 자연수 N(1 ≤ N ≤ 100)이 주어진다.
//두 번째 줄에는 각 문제의 배점을 의미하는 N개의 자연수가 공백으로 구분되어 주어진다. 배점은 1이상 100이하의 자연수이다.
//
//[출력]
//각 테스트 케이스마다 학생들이 받을 수 있는 점수의 경우의 수를 출력한다. 

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	static int ans;
	static int N;
	static int[] nums;
	static ArrayList<Integer> al;
	static boolean[] checked;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			N = sc.nextInt();
			nums = new int[N];
			al = new ArrayList<Integer>();
			checked = new boolean[10001];
			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}

			func();

			System.out.printf("#%d %d\n", test_case, al.size() + 1);
		}

	}

	public static void func() {
		for (int i = 0; i < N; ++i) {
			for (int j = al.size() - 1; j >= 0; --j) {
				if (!checked[al.get(j) + nums[i]]) {
					checked[al.get(j) + nums[i]] = true;
					al.add(al.get(j) + nums[i]);
				}
			}
			if (!checked[nums[i]]) {
				checked[nums[i]] = true;
				al.add(nums[i]);
			}
		}
	}
}
