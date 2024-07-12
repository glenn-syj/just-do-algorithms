package boj_14940_쉬운최단거리;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int n, m;
	static int[][] board, distance;
	static boolean[][] visited;

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		board = new int[n][m];
		distance = new int[n][m]; // 거리 저장
		visited = new boolean[n][m];

		int vr = 0;
		int vc = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = sc.nextInt();
				if (board[i][j] == 2) { // 목표지점
					vr = i;
					vc = j;
				} else if (board[i][j] == 0) {
					visited[i][j] = true; // 갈 수 없는 땅
				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(board[i][j]);
//			}
//			System.out.println();
//		}

		bfs(vr, vc);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) { // 도달 불가한 위치라면
					distance[i][j] = -1;
				}
				System.out.print(distance[i][j] + " ");
			}
			System.out.println();
		}

	} // main

	static void bfs(int vr, int vc) {
		// 큐 생성
		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { vr, vc }); // 큐에 넣고
		visited[vr][vc] = true; // 방문처리

		while (!queue.isEmpty()) { // bfs탐색
			int[] curr = queue.poll();

			for (int d = 0; d < 4; d++) {
				int r = curr[0] + dr[d];
				int c = curr[1] + dc[d];
				if (0 <= r && r < n && 0 <= c && c < m) {
					if (!visited[r][c] && board[r][c] == 1) {
						visited[r][c] = true; // 방문처리
						distance[r][c] = distance[curr[0]][curr[1]] + 1; // 현 위치에서 +1
						queue.add(new int[] { r, c }); // 큐에 넣기
					}
				}
			}
		}

	}

}
