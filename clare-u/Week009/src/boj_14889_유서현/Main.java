package boj_14889_유서현;

import java.util.Scanner;

public class Main {

	static int N, min;
	static int[][] board;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		board = new int[N][N];
		visited = new boolean[N];

		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		// 두개의 팀을 이루는 경우의 수를 구하고
		// 구한 팀의 조합으로 앞팀과 뒷팀의 점수를 구해 차이를 계산

		findTeam(0, 0);

		System.out.println(min);

	} // main

	static void findTeam(int idx, int sidx) {
		if (sidx == N / 2) {
			// 팀을 두개로 나누면 능력치의 최소값을 구하는..
			// visited가 true면 A, 아니면 B
			int Ateam = 0;
			int Bteam = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i] && visited[j]) {
						Ateam += board[i][j];
					} else if (!visited[i] && !visited[j]) {
						Bteam += board[i][j];
					}
				}
			}

			min = Math.min(min, Math.abs(Ateam - Bteam));
			// 최소값 구하기 (음수여도 절대값으로)
			return;
		}

		if (idx == N) {
			return;
		}

		for (int i = idx; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				findTeam(i + 1, sidx + 1);
				visited[i] = false;
			}
		}
	} // findTeam()

}
