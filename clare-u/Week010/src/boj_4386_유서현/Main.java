package boj_4386_유서현;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	static class Star {
		double x;
		double y;
		int idx;

		public Star(double x, double y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}

	}

	// peek: 수업 코드 peek
	static class Edge implements Comparable<Edge> {
		int A, B;
		double W;

		public Edge(int a, int b, double distance) {
			A = a;
			B = b;
			W = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.W, o.W);
		}

	}

	static int N;
	static Star[] stars;
	static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		stars = new Star[N];
		parent = new int[N];

		for (int i = 0; i < N; i++) {
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			stars[i] = new Star(x, y, i);
			parent[i] = i;
		}

		List<Edge> edges = new ArrayList<>();
		// 모든 별 사이의 거리를 계산하여 간선 리스트에 추가
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double distance = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
				edges.add(new Edge(i, j, distance));
			}
		}

		Collections.sort(edges); // 간선 가중치에 따라 오름차순 정렬

		double result = 0;
		for (Edge edge : edges) {
			if (find(edge.A) != find(edge.B)) {
				union(edge.A, edge.B);
				result += edge.W;
			}
		}

		System.out.println(result);

	}

	// peek
	static int find(int x) { // 서로소 집합의 대표자를 찾는 함수
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		// 두 집합을 합치는 함수
		a = find(a);
		b = find(b);
		if (a != b)
			parent[a] = b;
	}

}
