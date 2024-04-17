# 문제 정보
난이도: Gold III
분류 : 그래프, 최소 스패닝 트리
url: https://www.acmicpc.net/problem/4386

# 고찰
문제를 보고 최소 신장 트리를 이용해야겠다는 생각이 들었습니다.
그 중 크루스칼을 이용해 보기로 하였고, 우선순위 큐와 크루스칼에 대한 학습을 하였습니다.

# 로직
그래프 자료구조에서 최소 스패닝 트리를 구하기 위한 알고리즘.
즉, 그래프의 모든 정점들을 가장 적은 비용으로 연결하는 알고리즘이다.

**신장 트리**는 그래프의 모든 간선을 연결하며, **사이클이 없는** 구조 입니다. 따라서, N개의 정점이 있을 때 간선은 N-1개 존재합니다. 
이중에서 **최소 신장 트리**는 여러 신장 트리의 경우 중 가장 적은 가중치의 합을 가지는 신장 트리를 말합니다.

## 로직
크루스칼 알고리즘은 그리디 알고리즘의 일종입니다.

크루스칼 알고리즘을 위해서는 모든 간선들을 가중치가 낮은 순으로 정렬을 해야합니다. 따라서 Comparable 간선 배열을 이용합니다. (java 언어 기준)

1. 모든 간선을 가중치가 낮은 순서로 오름차순 정렬한다.

2. 가중치가 작은 순서로 선택을 하며 비용의 합을 구한다.

3. 간선을 선택했을 때, 사이클이 형성 된다면 해당 간선은 건너뛴다.

4. N개의 정점이 있을 때, N-1개의 간선을 선택할 때 까지 반복한다.

### 사이클이 형성되었는 지 판단하기
**Union-Find** 를 활용한다.
- Union-Find 란?
Disjoint Set (서로소 집합) 을 표현하는 자료구조
서로 다른 두 집합을 병합하는 Union 연산, 집합 원소가 어떤 집합에 속해있는지 찾는 Find 연산

서로소 집합을 이용하여 사이클을 방지하는 방법은, 만약 두 정점 사이의 간선을 선택했을 때, 두 정점의 부모가 같으면 해당 간선을 이엇을 시 사이클이 형성되므로 이러한 경우에 대해 검열을 한다.

# What I Learned
최소 신장 트리에 관한 문제를 풀며 크루스칼의 원리와 로직을 복습하였고, 간선 배열을 이용한 그래프 구현에 대한 연습을 하였습니다.
우선순위큐에 대해 반복 학습 하였습니다.

# Code
```java

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

		//별들의 위치 정보와 union find 하기 위한 부모 노드 배열
		Star[] star = new Star[N];
		parent = new int[N+1];
		
		for(int i = 0 ;i < N; i++) {
			parent[i] = i;
		}	//make set 초기화
		
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
		int pick=0;
//		while (!pq.isEmpty()) {
//			Edge now = pq.poll();
//
//			if (find(now.start) != find(now.end)) {
//				union(now.start, now.end);
//				cost += now.cost;
//			}
//		}
		
		for(int i = 0; i < N*(N-1)/2; i++) {
			Edge now = pq.poll();
			
			int start = now.start;
			int end = now.end;
			if(find(start) != find(end)) {
				//부모가 다르면 간선을 이어주기
				union(start,end);
				cost += now.cost;
				pick++;
			}
			if(pick == (N-1)) {
				break;
			}
		}
		
		System.out.println(Math.round(cost * 100)/100.0);
	}
	
	//find parent
	public static int find(int x) {
		if (x == parent[x])
			return x;

		return find(parent[x]);
	}
	
	public static void union(int x, int y) {
		parent[find(y)] = find(x);
	}
}


```