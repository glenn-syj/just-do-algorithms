package boj_14502_황민욱;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 1. 어쩔 수 없이 조합으로 1이 갈 수 있는 위치를 구한다.
// 2. 일단 3개를 다 구하면 2를 퍼트리를 시뮬레이션 구현 (bfs)
// 3. 완탐해서 최댓값 계속 갱신

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] temp;
	static boolean[][] selected;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int max;

	static int[] deltaR = { -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		selected = new boolean[N][M];
		max = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();

				// 아래에서 백트랙을 위해
				if (map[r][c] != 0) {
					selected[r][c] = true;
				}
			}
		}

		com(0, 0);
//		System.out.println(Arrays.deepToString(map));
//		System.out.println(Arrays.deepToString(visited));

		System.out.println(max);
	}

	private static void com(int start, int depth) {
		if (depth == 3) {

			// deepcopy 및 bfs 큐 준비
			temp = new int[N][M];
			visited = new boolean[N][M];
			queue = new LinkedList<>();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					temp[r][c] = map[r][c];
					visited[r][c] = selected[r][c];

					if (map[r][c] == 2) {
						int[] coord = { r, c };
						queue.add(coord);
					}
				}
			}

			max = Math.max(bfs(), max);

			return;
		}

		// 2차원 배열에서 조합 구하는 법! (TIL 적기)
		for (int i = start; i < N * M; i++) {
			int r = i / M;
			int c = i % M;

			if (!selected[r][c]) {
				selected[r][c] = true;
				map[r][c] = 1;

				com(i + 1, depth + 1);

				selected[r][c] = false;
				map[r][c] = 0;
			}
		}
	}

	private static int bfs() {
		int count = 0;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nextR = current[0] + deltaR[d];
				int nextC = current[1] + deltaC[d];

				if (isNotOutBound(nextR, nextC) && !visited[nextR][nextC]) {
					visited[nextR][nextC] = true;
					temp[nextR][nextC] = 2;
					int[] coord = { nextR, nextC };
					queue.add(coord);
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (temp[r][c] == 0) {
					count++;
				}
			}
		}

		return count;
	}

	private static boolean isNotOutBound(int nextR, int nextC) {
		return nextR >= 0 && nextR < N && nextC >= 0 && nextC < M;
	}
}
