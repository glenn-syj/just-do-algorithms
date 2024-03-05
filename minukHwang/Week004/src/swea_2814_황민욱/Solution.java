package swea_2814_황민욱;

import java.util.Arrays;

/*
 * [문제 풀이 방법]
 * 1. 정점과 간선 정보를 입력 받는다.
 * 	- 인접 행렬을 활용한다. (범위가 작아서 괜찮음)
 * 	- 인접 행렬의 행의 인덱스는 입력 받은 정점이다.
 * 	- 인접 행렬의 각 행에 들어 있는 배열은 해당 정점의 간선 정보이다.
 * 2. 입력 받은 그래프 정보를 가지고 dfs를 진행한다. (여기서 루트 노드의 위치가 중요함)
 * 3. 최대의 길이를 출력 
 * 	- 최대의 길이는 dfs를 진행하면서 해당 정점에 들어갔을 때 카운트를 진행.
 */

import java.util.Scanner;

public class Solution {

	static boolean[] visited;
	static int[][] graph;
	static int n;
	static int m;

	static int maxDepth;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {

			n = sc.nextInt();
			m = sc.nextInt();

			graph = new int[n + 1][n + 1];

			for (int i = 0; i < m; i++) {
				int node1 = sc.nextInt();
				int node2 = sc.nextInt();

				graph[node1][0] = node1;
				graph[node2][0] = node2;
				graph[node1][node2] = 1;
				graph[node2][node1] = 1;
			}


			int maxCount = 0;

			for (int i = 1; i < n + 1; i++) {
				visited = new boolean[n + 1];
				maxDepth = 0;

				
				dfs(i, 1);

				
				for(int j = 1; j < n+1; j++) {
					if (maxDepth > maxCount) {
						maxCount = maxDepth;
					}
				}
			}

			System.out.printf("#%d %d\n", t, maxCount);
		}
	}

	public static void dfs(int node, int depth) {
		visited[node] = true;

		for (int i = 1; i < n + 1; i++) {
			if (graph[node][i] == 1 && !visited[i]) {
				dfs(i, depth+1);
				visited[i]=false;
			}
		}
		
		
		if(maxDepth < depth) {
			maxDepth = depth;
		}
	}
	
	
}
