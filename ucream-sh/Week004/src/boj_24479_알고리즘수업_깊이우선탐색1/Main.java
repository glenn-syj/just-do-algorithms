package boj_24479_알고리즘수업_깊이우선탐색1;

import java.util.Scanner;

public class Main {

//	1. 2차원배열로 입력받는다.
//	2. DFS 탐색을 하기 위해 스택 구조를 이용한다.
//	- 스택을 구현하는 걸 다시 연습한다. -> 라고생각했는데 굳이 안써도될듯?
//	3. 방문한 곳의 정보는 1차원배열에 저장한다. int[] visited
//	4. 방문하면 출력
//	5. 모두 방문해서 갈 곳이 없으면 종료

	// 테케는 맞게 출력되었으나 제출시 메모리초과.. 2차원배열로 구현하면 안되는듯

	static int[][] graph;
	static int[] visited;

	static int N;
	static int M;
	static int R;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 정점 수 = 1, 2차원배열 사이즈
		M = sc.nextInt(); // 간선 수 = 입력받을 횟수
		R = sc.nextInt(); // 시작점

		graph = new int[N + 1][N + 1];
		visited = new int[N + 1];

		for (int i = 1; i <= M; i++) {
			// 방문가능한 경로를 2차원배열에 저장
			int row = sc.nextInt();
			int col = sc.nextInt();
			graph[row][col] = 1;
			graph[col][row] = 1;
		}

//		for (int i = 0; i < N + 1; i++) {
//		for (int j = 0; j < N + 1; j++) {
//			System.out.print(graph[i][j] + " ");
//		}
//		System.out.println();
//	}

		// R부터 시작해서 오름차순으로 깊이우선탐색
		DFS(R);

		// 방문할 수 없는 곳은 0을 출력하고 종료 (하는게 맞나)
		for (int i = 1; i <= M; i++) {
			if (visited[i] == 0) {
				System.out.println("0");
				break;
			}
		}

	} // main

	// DFS 하는 메서드
	static void DFS(int r) {
		// visited 있으니까 종료조건 안해도되는게 맞나원래?

		// 출력
		if (visited[r] == 0) {
			System.out.println(r);
			visited[r]++;
		}

		for (int i = 1; i <= M; i++) {
			if (graph[r][i] == 1) {
				if (visited[i] == 0) {
					DFS(i);
				}
			}
		}
	}

}