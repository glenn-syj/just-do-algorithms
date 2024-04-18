package boj_4386_황민욱;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N;
	static double[][] coord;
	static List<Edge>[] graph;

	static class Edge implements Comparable<Edge> {
		int st;
		int ed;
		double d;

		Edge() {

		}

		Edge(int st, int ed, double x1, double y1, double x2, double y2) {
			this.st = st;
			this.ed = ed;
			this.d = getDistance(x1, y1, x2, y2);
		}

		double getDistance(double x1, double y1, double x2, double y2) {
			return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		}

		@Override
		public String toString() {
			return  st + " -> " + ed + " :" + d;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.d, o.d);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		coord = new double[N][2];

		for (int i = 0; i < N; i++) {
			coord[i][0] = sc.nextDouble();
			coord[i][1] = sc.nextDouble();
		}

		graph = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			double x1 = coord[i][0];
			double y1 = coord[i][1];
			
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				
				double x2 = coord[j][0];
				double y2 = coord[j][1];
				
				graph[i].add(new Edge(i, j, x1, y1, x2, y2));
			}
		}
		
		boolean[] visited = new boolean[N];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		visited[0] = true;
		
		pq.addAll(graph[0]);
		
		int pick = 1;
		double ans = 0;
		
		while(pick != N) {
			Edge e = pq.poll();
			
			if(visited[e.ed]) continue;
			
			ans += e.d;
			visited[e.ed] = true;
			pick++;
			
			pq.addAll(graph[e.ed]);
		}
		
		System.out.printf("%.2f", ans);
	}
}
