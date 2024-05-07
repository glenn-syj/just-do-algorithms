# 문제 정보
난이도: 골드 V
분류 : 그래프, 다익스트라
url: https://www.acmicpc.net/problem/1916

# 고찰
그래프의 비용이 주어지고, 최소 비용을 구하는 문제이다. 시작점과 끝점이 주어지므로 다익스트라 알고리즘을 활용하여 문제를 풀어야 한다. 다익스트라 알고리즘이 익숙하지 못하여 다익스트라 알고리즘 복습을 하며 Peek 하여 알고리즘을 익혔습니다.

# Logic
[다익스트라 알고리즘]
0. 우선순위 큐를 이용하기 위한 Comparable Node, 최단 거리를 저장할 dist 배열, 방문 처리를 할 visit 배열 생성
1. dist 배열을 최대값으로 채워넣는다.
2. 시작 정점을 방문처리, dist값을 0으로 설정하고 우선순위 큐에 넣는다.
3. 우선순위 큐에서 앞 노드를 꺼내어 해당 노드에 인접한 노드 중 미방문 상태이면서 현재까지 해당 노드에 가는 가중치가 적은 경우, dist를 갱신하고 우선순위 큐에 삽입한다.
4. 큐가 빌 때 까지 3번을 반복한다.

# What I learned
- 다익스트라 알고리즘에 대해 단계별로 어떻게 이루어지는지 자세하게 학습하였고, 우선순위 큐를 이용한 다익스트라 구현을 연습할 수 있었습니다.
- ArrayList를 이용한 인접 리스트 그래프를 구현하는 연습을 할 수 있었습니다.

# Java Code
```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static int N, M;
	static ArrayList<ArrayList<Node>> adjList;
	static int[] dist;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		   
		N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        adjList = new ArrayList<ArrayList<Node>>();
        dist = new int[N + 1];
        visit = new boolean[N + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }//초기화
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
 
            adjList.get(start).add(new Node(end, weight));
        }
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        System.out.println(dijkstra(start, end));
	}
	
	public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;
 
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.v;
 
            if (!check[cur]) {
                check[cur] = true;
 
                for (Node node : adjList.get(cur)) {
                    if (!check[node.v] && dist[node.v] > dist[cur] + node.w) {
                        dist[node.v] = dist[cur] + node.w;
                        pq.add(new Node(node.v, dist[node.v]));
                    }
                }
            }
        }
        return dist[end];
    }
}

```
