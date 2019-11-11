package com.ssafy.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2577_회전초밥_이승순 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, d, k, c;
		N = Integer.parseInt(st.nextToken()); // 초밥 접시 수
		d = Integer.parseInt(st.nextToken()); // 초밥 가지 수
		k = Integer.parseInt(st.nextToken()); // 연속에서 먹는 접시 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] dishes = new int[N];
		int[] check = new int[d + 1];
		int cnt = 0, ans = 0;
		Queue<Integer> ate = new LinkedList<Integer>();

		for (int i = 0; i < N; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < k; i++) {
			ate.offer(dishes[i]);
			if (check[dishes[i]] == 0)
				cnt++;
			check[dishes[i]]++;
		}

		for (int i = k; i < N + k; i++) {
			int temp = ate.poll();

			check[temp]--;
			if (check[temp] == 0) {
				cnt--;
			}

			if (check[dishes[i % N]] == 0) {
				cnt++;
			}
			check[dishes[i % N]]++;

			ate.offer(dishes[i % N]);
			if (check[c] == 0) {
				int tempCnt = cnt + 1;
				ans = Math.max(ans, tempCnt);
			}
			ans = Math.max(ans, cnt);
		}

		System.out.println(ans);
	}
}
