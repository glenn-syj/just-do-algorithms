package boj_4386_박건택;

/*
 * 별을 잇는 모든 경우에 대해서 신장 하여야 하므로 간선 배열에 모두 넣자
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Star {
	double x;
	double y;

	public Star(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> {

	int start;
	int end;
	double cost;

	public Edge(int start, int end, double cost) {
		super();
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return o.cost >= this.cost ? -1 : 1;
	}

}

public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Star[] star = new Star[N];
		parent = new int[N+1];
		
		for(int i = 0 ;i < N; i++) {
			parent[i] = i;
		}
		//별들 좌표 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			double start = Double.parseDouble(st.nextToken());
			double end = Double.parseDouble(st.nextToken());

			star[i] = new Star(start, end);
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		//모든 노드에 대해서 간선 비용 입력하기
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				pq.offer(new Edge(i, j,
						Math.sqrt(Math.pow(star[i].x - star[j].x, 2) + Math.pow(star[i].y - star[j].y, 2))));
			}
		}
		
		double cost = 0;
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (find(now.start) != find(now.end)) {
				union(now.start, now.end);
				cost += now.cost;
			}
		}
		
		System.out.println(Math.round(cost * 100)/100.0);
	}
	
	//find parent
	public static int find(int x) {
		if (x == parent[x])
			return x;

		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			if (x < y) {
				parent[y] = x;
			} else {
				parent[x] = y;
			}
		}
	}
}
