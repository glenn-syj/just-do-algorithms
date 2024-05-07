# 문제 정보
난이도: 골드 V
분류 : 그래프, 다익스트라
url: https://www.acmicpc.net/problem/1916

# 고찰
그래프의 비용이 주어지고, 최소 비용을 구하는 문제이다. 시작점과 끝점이 주어지므로 다익스트라 알고리즘을 활용하여 문제를 풀어야 한다. 다익스트라 알고리즘이 

# Logic
1. 0에서 부터 bfs를 시작하여 이어진 칸들의 개수를 세고, visited 배열에 연결된 칸들에 대해 그룹id를 저장하고, countMap에 그룹 id에 해당하는 칸의 수를 저장한다.
2. 벽에서 사방 탐색을 하여 사방에 어떤 그룹 id가 있는지 visited 배열을 통해 탐색하고, 인접한 그룹 id를 Set으로 저장한다.
3. set에 저장한 그룹 id에 대해서 countMap에 해당 그룹 id의 칸 수를 찾아서 더한다.
4. 해당 벽 칸에 칸 수 정보를 대치한다.


# What I learned
- 발상의 전환으로 벽에서 bfs를 시작하는 것이 아니라 0에서 bfs를 한다.
- bfs 방법에 대해 더욱 단단히 익혔습니다.
- Map을 이용해서 해당 칸의 id와 연결된 칸의 개수로 이어진 부분들을 하나의 그룹으로 묶는 방법을 배웠습니다.

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
