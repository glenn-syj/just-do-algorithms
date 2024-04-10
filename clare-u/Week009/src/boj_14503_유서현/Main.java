package boj_14503_유서현;

import java.util.Scanner;

public class Main {

	static int[] dr = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dc = { 0, 1, 0, -1 };

	static int N, M, count;
	static int[][] room;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		int startR = sc.nextInt();
		int startC = sc.nextInt();
		int startD = sc.nextInt();

		room = new int[N][M];
		visited = new boolean[N][M];
		count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				room[i][j] = sc.nextInt();
			}
		} // 입력받고 초기화 완료

		clean(startR, startC, startD);
		System.out.println(count);

	}

	private static void clean(int r, int c, int startD) {

		if (room[r][c] == 0 && !visited[r][c]) {
			count++;
			visited[r][c] = true; // 청소
		}

		// 상하좌우 4칸 탐색
		for (int i = 0; i < 4; i++) {
			startD = (startD + 3) % 4;
			int nr = r + dr[startD];
			int nc = c + dc[startD];

			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				if (room[nr][nc] == 0 && !visited[nr][nc]) {
					clean(nr, nc, startD);
					return;
				}
			}
		}

		// 청소 안된 칸이 4칸 중에 없으면
		int br = r + dr[(startD + 2) % 4];
		int bc = c + dc[(startD + 2) % 4];
		if (0 <= br && br < N && 0 <= bc && bc < M) {
			if (room[br][bc] == 0) {
				// 벽이아니면 뒤로가서 청소
				clean(br, bc, startD);
			} else {
				// 벽이면 멈추기
				return;
			}
		}

	}

}
