# 문제 정보
난이도: Gold III
분류 : 그래프, 최소 스패닝 트리
url: https://www.acmicpc.net/problem/4386

# 고찰
문제를 보고 최소 신장 트리를 이용해야겠다는 생각이 들었습니다.
그 중 크루스칼을 이용해 보기로 하였고, 우선순위 큐와 크루스칼에 대한 학습을 하였습니다.

# 로직
별을 잇는 모든 간선에 대해서 최소 비용을 찾아봐야 하므로, 간선 배열을 이용해 모든 간선의 경우에 대해 비용을 입력하고, 크루스칼 알고리즘으로 최소 신장 트리를 구한다.

**[크루스칼]**
1. 간선 배열을 만들고 정렬을 하기 위해 Comparable Edge 클래스와 우선순위 큐를 생성한다.
2. 간선 비용 기준 오름차순으로 정렬한다.
3. 
TBA..

# What I Learned
최소 신장 트리에 관한 문제를 풀며 크루스칼의 원리와 로직을 복습하였고, 간선 배열을 이용한 그래프 구현에 대한 연습을 하였습니다.
우선순위큐에 대해 반복 학습 하였습니다.

# Code
```java
package boj_4386_박건택;

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

```