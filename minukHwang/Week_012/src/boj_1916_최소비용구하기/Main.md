# BOJ 1916. 최소비용구하기

## ✓ Difficult Point

- 다익스트라의 개념만 이해하고 있다면 충분히 쉽게 풀 수 있는 문제였다.

<br/>

## ✓ Logic

1. 정점, 간선의 정보를 받기 위해서 Node 클래스를 만든다.

	- Priority Queue를 활용하여 우선순위를 나눌 것이기 때문에, Comparable을 implement하여 간선의 비용을 기준으로 정렬되도록 한다.

2. 인접 리스트 방식으로 정점, 간선을 입력 받는다.

3. dist 배열을 정점의 수 만큼의 크기로 만들어 매우 큰 숫자로 채워 초기화를 한다.

4. 출발 정점을 입력 받은 후, 해당 정점을 시작점으로 하기 위해 dist 배열에서 0으로 만들고 pq에 넣는다.

5. pq가 비워질 때까지 다음을 반복한다. 

	1. pq에서 우선순위 (가장 최소의 비용을 가진) node를 뽑는다.

	2. 만약 해당 노드가 향하고 있는 정점을 방문한 적이 있다면 pass

	3. 방문한 적이 없다면 방문 처리를 하고, 뽑은 노드가 향하는 정점들에 대해서 반복문 수행

		- 만약 향하는 정점을 방문한 적이 없고, 현재 뽑은 node를 거쳐서 해당 정점으로 가는 비용이 바로 해당 정점으로 가는 것보다 적다면, dist 배열을 현재 뽑은 node를 거쳐서 해당 정점으로 가는 비용으로 바꿔준다.

		- pq에 향하는 정점, 그리고 최소 비용으로 간선을 추가해준다.
	
6. 다익스트라 알고리즘을 수행하면, dist 배열에 시작점에서 각각의 정점까지의 최단 거리 배열을 얻을 수 있다. 해당 배열을 통해 문제에서 요구한 도착점까지의 최단 거리를 출력한다.


<br/>

## ✓ Code
```java
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
```