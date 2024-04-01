package boj_14502_유서현;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 실 패 ~ 계속 이상한 값 나옴

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M, max;
	static int[][] board;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		max = 0;
		board = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				board[r][c] = sc.nextInt(); // 0은 빈칸, 1은 벽, 2는 바이러스
			}
		}

		wall(0);

		System.out.println(max);

	} // main

	static void wall(int wallCount) {
		// 벽을 카운트해서 3개 설치되면 bfs 탐색 시작
		if (wallCount == 3) {
			bfs();
			return;
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 0) {
					board[r][c] = 1; // 이 부분 이해 못함
					wall(wallCount + 1);
					board[r][c] = 0;
				}
			}
		}

	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		int[] temp = new int[2];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 2) {
					temp[0] = r;
					temp[1] = c;
					q.add(temp);
				}
			}
		}

		// 원본 연구소를 바꾸지 않기 위해 카피맵 사용 -> 이부분 이해못함
		int[][] copyBoard = new int[N][M];
		for (int i = 0; i < N; i++) {
			copyBoard[i] = board[i].clone(); // clone 메서드?
		}

		// bfs 탐색
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];

			for (int d = 0; d < 4; d++) {

				int nr = dr[d] + r;
				int nc = dc[d] + c;

				if (0 <= nr && nr < N && 0 <= nc && nc < M && copyBoard[nr][nc] == 0) {
					copyBoard[nr][nc] = 2;
					int[] a = { nr, nc };
					q.add(a);
				}
			}

		}

		int safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyBoard[i][j] == 0) {
					safeZone++;
				}
			}
		}
		if (max < safeZone) {
			System.out.println(safeZone);
			max = safeZone;
		}

	}

}
