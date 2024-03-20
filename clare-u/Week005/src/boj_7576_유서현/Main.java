package boj_7576_유서현;

import java.util.*;

public class Main {

	// 인접한 곳은 4방탐색에 있는 토마토
	// 토마토가 다 익는 최소 일수를 출력

	// 1을 4방탐색해서 방문하지 않은 곳이면 0->1로 만들기
	// -1은 냅둬야 함

	static int M, N, day;
	static int[][] tomatos;
	static int[][] visited;

	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();
		N = sc.nextInt();
		tomatos = new int[N][M];
		visited = new int[N][M];

		Queue<int[]> tomaQueue = new LinkedList<>(); // 시작점 찾는 용도의 큐 생성

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tomatos[i][j] = sc.nextInt();
				if (tomatos[i][j] == 1) {
					tomaQueue.add(toQueue(i, j));
				}
			}
		} // 1은 익은애, 0은 안익은애, -1은 안들어있는 칸

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = -1;
			}
		} // 기본값은 -1?

		bfs(tomaQueue);

		int max = -1;
		for1: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomatos[i][j] == 0) {
					max = -1;
//					System.out.println("i: " + i + " j: " + j);
//					System.out.println("탈출");
					break for1;
				}
				if (visited[i][j] > max) {
					max = visited[i][j];
				}
			}
		} // 1은 익은애, 0은 안익은애, -1은 안들어있는 칸

		System.out.println(max);

	} // main

	static void bfs(Queue<int[]> q) {
		Queue<int[]> queue = new LinkedList<>(); // 큐 생성

		int i = 0;
		int j = 0;

		// 방문했다는 표시를 하고 큐에 넣기
		while (!q.isEmpty()) {
			int[] qout = q.poll();
			i = qout[0];
			j = qout[1];

			visited[i][j] = 0;
			queue.add(toQueue(i, j));
		}

		// 큐가 비어있을떄까지
		while (!queue.isEmpty()) {
			// 큐에서 빼내서
			int[] out = queue.poll();
			i = out[0];
			j = out[1];

			for (int d = 0; d < 4; d++) { // 델타탐색
				// 토마토 -1은 탐색하지 않고, 0이면 1로 바꿈
				if (0 <= i + dr[d] && i + dr[d] < N && 0 <= j + dc[d] && j + dc[d] < M
						&& visited[i + dr[d]][j + dc[d]] == -1 && tomatos[i + dr[d]][j + dc[d]] != -1) {
					tomatos[i + dr[d]][j + dc[d]] = 1; // 익히고
					visited[i + dr[d]][j + dc[d]] = visited[i][j] + 1; // 날짜계산 // 큐에서 빼내면 day+1?
//					System.out.println(visited[i + dr[d]][j + dc[d]]);
					queue.add(toQueue(i + dr[d], j + dc[d])); // 큐에넣기
				}
			}
		}

	} // bfs

	static int[] toQueue(int i, int j) {
		int[] temp = { i, j };
		return temp;
	} // int[]로 큐에 넣기 위해서 메서드 생성

}