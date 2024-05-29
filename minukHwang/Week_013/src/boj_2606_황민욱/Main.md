# BOJ 2606. 바이러스

## ✓ Difficult Point

- DFS 복습을 위해서 푼 문제! (매우 쉬운데, DFS 구현을 복습하기 위해 풀어보았다.)

<br/>

## ✓ Logic

1. 바이러스들이 점염 되기 위해서는 간선들이 연결되어야하기 때문에 일단 인접 리스트 방식으로 그래프를 구성하였다.
2. 구성된 그래프를 DFS 탐색을 통해서 1번을 출발점으로 서로 연결되어 있는지를 확인하며 count를 갱신해준다.
3. 최종적으로 구해진 count는 1을 포함한 연결되어 있는 정점의 수와 같기 때문에 count에서 1을 빼주고 출력한다.


<br/>

## ✓ Code
```java
package boj_2606_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, M;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		graph = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();

			graph[node1].add(node2);
			graph[node2].add(node1);
		}

		visited = new boolean[N + 1];
		
		count = 0;

		dfs(1);
		
		System.out.println(count - 1);
	}

	private static void dfs(int current) {
		count++;
		visited[current] = true;
		
		for(int node: graph[current]) {
			if(!visited[node]) {
				dfs(node);
			}
		}
		
	}
	
	
}
```