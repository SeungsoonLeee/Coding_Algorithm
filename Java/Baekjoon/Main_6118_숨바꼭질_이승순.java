import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Vertex>[] nodes = new ArrayList[N + 1];
		int[] D = new int[N + 1];
		boolean[] check = new boolean[N + 1];
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		Arrays.fill(D, Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<Vertex>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes[r].add(new Vertex(c, 1));
			nodes[c].add(new Vertex(r, 1));
		}

		D[1] = 0;
		pq.offer(new Vertex(1, 0));

		while (!pq.isEmpty()) {
			Vertex curNode = pq.poll();
			if (check[curNode.next])
				continue;
			check[curNode.next] = true;

			for (int i = 0; i < nodes[curNode.next].size(); i++) {
				Vertex nextNode = nodes[curNode.next].get(i);
				if (check[nextNode.next])
					continue;
				if (D[nextNode.next] > curNode.w + nextNode.w) {
					D[nextNode.next] = curNode.w + nextNode.w;
					pq.add(new Vertex(nextNode.next, D[nextNode.next]));
				}
			}
		}

		int ansNum = 0;
		int ansDistance = 0;
		int ansCount = 0;

		for (int i = 1; i <= N; i++) {
			if (ansDistance < D[i]) {
				ansNum = i;
				ansDistance = D[i];
				ansCount = 0;
				ansCount++;
			} else if (ansDistance == D[i]) {
				ansCount++;
			}
		}

		System.out.println(ansNum + " " + ansDistance + " " + ansCount);
	}
}

class Vertex implements Comparable<Vertex> {
	int next;
	int w;

	public Vertex() {
	}

	public Vertex(int next, int w) {
		this.next = next;
		this.w = w;
	}

	@Override
	public String toString() {
		return "Vertex [next=" + next + ", w=" + w + "]";
	}

	@Override
	public int compareTo(Vertex o) {
		return Integer.compare(this.w, o.w);
	}
}