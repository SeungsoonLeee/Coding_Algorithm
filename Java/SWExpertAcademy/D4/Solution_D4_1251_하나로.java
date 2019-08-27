import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {
	static int V, cnt;
	static double totalWeight;
	static double E;
	static Point[] points;
	static Vertex[] vertexs;
	static int[] parents;

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; ++test_case) {
			totalWeight = 0;
			cnt = 0;
			V = sc.nextInt();
			int size = (V) * (V - 1) / 2;
			points = new Point[V];
			parents = new int[V];
			vertexs = new Vertex[size];

			for (int i = 0; i < V; i++) {
				points[i] = new Point();
				points[i].X = sc.nextInt();
			}
			for (int i = 0; i < V; i++)
				points[i].Y = sc.nextInt();
			E = sc.nextDouble();

			// kruskal 알고리즘
			// 1. 모든 정점간 가중치 계산
			int idx = 0;
			for (int i = 0; i < V - 1; i++) {
				for (int j = i + 1; j < V; j++) {
					double temp = ((Math.pow((points[i].X - points[j].X), 2) + Math.pow((points[i].Y - points[j].Y), 2))
							* E);
					// System.out.println((double) Math.pow((points[i].X - points[j].X), 2) + " + "
					// + (double) Math.pow((points[i].Y - points[j].Y), 2) + " * " + E + " = " +
					// temp);
					vertexs[idx++] = new Vertex(i, j, temp);
				}
			}

			// 2. 간선의 가중치 오름차순으로 정렬
			Arrays.sort(vertexs, new wComparator());

			// 2. 가중치가 낮은 간선부터 트리 연결 & 확장
			// 3. 사이클 여부 판단을 위한 disjoint_set 구현
			// 4. 정점수-1 개의 간선이 선택될 때까지 반복
			Arrays.fill(parents, -1);
			for (Vertex v : vertexs) {
				if (disjointSet(v.from, v.to)) {
					totalWeight += v.weight;
					cnt++;
				}
				if (cnt == V - 1)
					break;
			}

			System.out.printf("#%d %d\n", test_case, Math.round(totalWeight));
		}
	}

	public static boolean disjointSet(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	public static int find(int f) {
		if (parents[f] < 0)
			return f;
		return parents[f] = find(parents[f]);
	}

}

class wComparator implements Comparator<Vertex> {
	public int compare(Vertex o1, Vertex o2) {
		return Double.compare(o1.weight, o2.weight);
	}
}

class Vertex {
	int from;
	int to;
	double weight;

	public Vertex() {
	}

	public Vertex(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

}

class Point {
	public int X;
	public int Y;

	public Point() {
	}

	public Point(int x, int y) {
		X = x;
		Y = y;
	}
}
