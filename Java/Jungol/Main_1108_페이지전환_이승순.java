package ssafy.com.jungol;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1108_페이지전환_이승순 {
	static int N;
	static Edge[] edges;
	static int[] D, from, to;
	static int[][] matrix; // 정점 정보들을 인접행렬로 표현
	static boolean[] visited; // 정점 방문여부
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) {
		double total = 0;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		pq = new PriorityQueue<Edge>();
		from = new int[N];
		to = new int[N];
		int maxPageNum = 0;
		for (int i = 0; i < N; i++) {
			from[i] = sc.nextInt() - 1;
			to[i] = sc.nextInt() - 1;
			if (maxPageNum < Math.max(from[i], to[i]))
				maxPageNum = Math.max(from[i], to[i]);
		}
		int maxIdx = maxPageNum + 1;
		edges = new Edge[maxIdx];
		matrix = new int[maxIdx][maxIdx];
		visited = new boolean[maxIdx];
		D = new int[maxIdx];

		for (int i = 0; i < N; i++) {
			matrix[from[i]][to[i]] = 1;
			edges[from[i]] = new Edge(to[i], 1);
		}

		for (int i = 0; i < maxIdx; i++) {
			Arrays.fill(D, Integer.MAX_VALUE);
			Arrays.fill(visited, false);
			D[i] = 0; // 출발점
			pq.offer(new Edge(i, D[i])); // 출발점 지정
			while (!pq.isEmpty()) {
				Edge current = pq.poll();
				if (visited[current.to])
					continue;
				visited[current.to] = true;

				if (current.to == maxIdx)
					break;

				for (int j = 0; j < maxIdx; j++) {
					if (!visited[j] && matrix[current.to][j] != 0 && current.w + matrix[current.to][j] < D[j]) {
						D[j] = current.w + matrix[current.to][j];
						pq.offer(new Edge(j, D[j]));
					}
				}
			}
			for (int j = 0; j < maxIdx; j++) {
//				System.out.print(D[j] + " ");
				total += D[j];
			}
//			System.out.println(total);
		}

		double value = total / (maxIdx * (maxIdx - 1));
		double ans = Math.round(value * (double) 1000) / (double)1000;
		System.out.printf("%.3f", ans);
	}
}

class Edge implements Comparable<Edge> {
	int to;
	int w;

	public Edge() {
	}

	public Edge(int to, int w) {
		this.to = to;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.w, o.w);
	}
}