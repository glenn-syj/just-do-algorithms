package boj_1916_최소비용구하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
	int v;
	int w;

	Node() {

	}

	Node(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public String toString() {
		return "[v=" + v + ", w=" + w + "]";
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.w, o.w);
	}
}

public class Main {
	static int N, M;
	static final int INF = Integer.MAX_VALUE;
	static List<Node>[] adjList;
	static int[] dist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		adjList = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		dist = new int[N + 1];
		Arrays.fill(dist, INF);

		for (int i = 0; i < M; i++) {
			adjList[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));

		}
		
		int start = sc.nextInt();
		int end = sc.nextInt();

		dijkstra(start);

		System.out.println(dist[end]);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		dist[start] = 0;

		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (visited[curr.v])
				continue;
			visited[curr.v] = true;

			for (Node node : adjList[curr.v]) {
				if (!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
					dist[node.v] = dist[curr.v] + node.w;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}
	}
}
