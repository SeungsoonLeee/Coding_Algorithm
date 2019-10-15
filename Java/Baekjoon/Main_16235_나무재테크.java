package com.ssafy.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16235_나무재테크 {
	static int N, M, K;
	static int[][] map;
	static int[][] nourishment;
	static int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static PriorityQueue<Tree> trees;
	static PriorityQueue<Tree> dieTrees;
	static PriorityQueue<Tree> remainTrees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		nourishment = new int[N][N];
		trees = new PriorityQueue<Tree>();
		dieTrees = new PriorityQueue<Tree>();
		remainTrees = new PriorityQueue<Tree>();

		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 5);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nourishment[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			trees.offer(new Tree(r, c, age));
		}

		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}

		System.out.println(trees.size());
	}

	private static void spring() {
		int size = trees.size();
		for (int i = 0; i < size; i++) {
			Tree curT = trees.poll(); // 나무들을 하나씩 보면서
			if (map[curT.r][curT.c] < curT.age) { // 영양분을 섭취할 수 없으면 죽은 나무에 offer
				dieTrees.offer(curT);
				continue;
			}
			map[curT.r][curT.c] -= curT.age; // 영양분을 섭취할 수 있다면 섭취하고 나이 증가
			curT.age++;
			remainTrees.offer(curT);
		}
		trees = remainTrees;
		remainTrees = new PriorityQueue<Tree>(); // 남은 나무 초기화
	}

	private static void summer() {
		while (!dieTrees.isEmpty()) { // 죽은 나무들 영양분화
			Tree curT = dieTrees.poll();
			map[curT.r][curT.c] += (curT.age / 2);
		}
	}

	private static void fall() {
		int size = trees.size();
		for (int i = 0; i < size; i++) { // 나무들을 하나씩 보면서
			Tree curT = trees.poll();
			if (curT.age % 5 == 0) { // 번식이 가능한 나무면
				for (int j = 0; j < 8; j++) { // 8방향 번식
					int nextR = curT.r + dir[j][0];
					int nextC = curT.c + dir[j][1];
					if (nextR >= N || nextC >= N || nextR < 0 || nextC < 0) // 경계 처리
						continue;
					remainTrees.offer(new Tree(nextR, nextC, 1)); // 나이가 1인 나무 생성
				}
			}
			remainTrees.offer(curT);
		}
		trees = remainTrees;
		remainTrees = new PriorityQueue<Tree>(); // 남은 나무 초기화
	}

	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += nourishment[i][j]; // 각 공간에 영양분 추가;
			}
		}
	}

}

class Tree implements Comparable<Tree> {
	int r;
	int c;
	int age;

	public Tree() {
	}

	public Tree(int r, int c, int age) {
		this.r = r;
		this.c = c;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Tree [r=" + r + ", c=" + c + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Tree o) {
		return Integer.compare(this.age, o.age);
	}
}
