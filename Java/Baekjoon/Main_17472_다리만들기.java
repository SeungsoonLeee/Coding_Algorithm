package com.ssafy.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] check;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[] parents;
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new boolean[N][M];
		edges = new ArrayList<Edge>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					check[i][j] = true;
			}
		}

		// 각 섬들 넘버링
		int islandNum = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (check[i][j])
					continue;
				setNumber(i, j, islandNum++);
			}
		}
		parents = new int[islandNum];
		Arrays.fill(parents, -1);

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		// 섬마다 최소 거리의 다리 간선화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					continue;
				setEdges(i, j);
			}
		}

		int ans = 0;
		int cnt = 0;
		boolean isSuccess = false;
		Collections.sort(edges);
		for (Edge e : edges) {
			if (union(e.from, e.to)) {
//				System.out.println(e);
				ans += e.w;
				cnt++;
			}
			if (cnt == islandNum - 2) {
				isSuccess = true;
				break;
			}
		}

		System.out.println(isSuccess ? ans : -1);
	}

	private static void setEdges(int r, int c) {
		Queue<Integer> queR = new LinkedList<Integer>();
		Queue<Integer> queC = new LinkedList<Integer>();
		int from = map[r][c];
		int to = -1;
		queR.offer(r);
		queC.offer(c);

		while (!queR.isEmpty()) {
			int curR = queR.poll();
			int curC = queC.poll();
			for (int i = 0; i < 4; i++) {
				int nextR = curR + dir[i][0];
				int nextC = curC + dir[i][1];
				if (nextR >= N || nextC >= M || nextR < 0 || nextC < 0)
					continue;
				if (map[nextR][nextC] == 0) {
					int distance = 0;
					while (true) {
						distance++;
						nextR += dir[i][0];
						nextC += dir[i][1];
						if (nextR >= N || nextC >= M || nextR < 0 || nextC < 0)
							break;
						if (map[nextR][nextC] != 0) {
							if (distance >= 2) {// 다리가 만들어지는 경우
								boolean isExisted = false;
								to = map[nextR][nextC];
								for (Edge e : edges) {
									if (e.from == from && e.to == to) {
										isExisted = true;
										if (e.w > distance)
											e.w = distance;
										break;
									}
								}
								if (!isExisted) {
									edges.add(new Edge(from, to, distance));
								}
//								System.out.println(from + " " + to + " " + distance);
							}
							break;
						}
					}
				}
			}
		}
	}

	private static void setNumber(int r, int c, int temp) {
		Queue<Integer> queR = new LinkedList<Integer>();
		Queue<Integer> queC = new LinkedList<Integer>();
		queR.offer(r);
		queC.offer(c);
		map[r][c] = temp;
		check[r][c] = true;

		while (!queR.isEmpty()) {
			int curR = queR.poll();
			int curC = queC.poll();
			for (int i = 0; i < 4; i++) {
				int nextR = curR + dir[i][0];
				int nextC = curC + dir[i][1];
				if (nextR >= N || nextC >= M || nextR < 0 || nextC < 0)
					continue;
				if (check[nextR][nextC])
					continue;
				queR.offer(nextR);
				queC.offer(nextC);
				map[nextR][nextC] = temp;
				check[nextR][nextC] = true;

			}
		}
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			parents[rootB] = rootA;
			return true;
		}
		return false;
	}

	private static int find(int f) {
		if (parents[f] < 0)
			return f;
		return parents[f] = find(parents[f]);
	}
}

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int w;

	public Edge() {
	}

	public Edge(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.w, o.w);
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", w=" + w + "]";
	}
}