package com.ssafy.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2634_사냥꾼_이승순 {
	static int M, N, L, ans;
	static int[] shotPoint;
	static Animal[] animals;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 사대수
		N = Integer.parseInt(st.nextToken()); // 동물수
		L = Integer.parseInt(st.nextToken()); // 사거리
		ans = 0;
		shotPoint = new int[M];
		animals = new Animal[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			shotPoint[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(shotPoint);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Animal a = new Animal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			animals[i] = a;
			int shotPointX = search(0, shotPoint.length - 1, animals[i].x);
			if (Math.abs(shotPointX - animals[i].x) + animals[i].y <= L) {
				ans++;
			}
		}

		System.out.println(ans);
	}

	public static int search(int start, int end, int find) {
		if (end - start < 2) {
			if (Math.abs(find - shotPoint[start]) < Math.abs(find - shotPoint[end]))
				return shotPoint[start];
			return shotPoint[end];
		}
		int mid = (start + end) / 2;
		if (find == shotPoint[mid])
			return shotPoint[mid];
		else if (find > shotPoint[mid])
			return search(mid, end, find);
		else
			return search(start, mid, find);
	}
}

class Animal {
	int x;
	int y;

	public Animal() {
	}

	public Animal(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Animal [x=" + x + ", y=" + y + "]";
	}

}