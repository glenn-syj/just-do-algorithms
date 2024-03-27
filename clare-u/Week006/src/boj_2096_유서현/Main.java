package boj_2096_유서현;

import java.util.*;

public class Main {
	// Peek해서 풀이 했는데 상당히 코드가 짧다.
	// Math.max(a, b) -> 두 인자 중 큰 값을 리턴

	static int N;
	static int[][] board;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		board = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		// DP
		int[][] minDp = new int[N + 1][3];
		int[][] maxDp = new int[N + 1][3];

		// 0번 index에는 0, 1에서 올 수 있고
		// 1번 index에는 0, 1, 2 모든 곳
		// 2번 index에는 1, 2에서
		for (int i = 1; i <= N; i++) {
			maxDp[i][0] += Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + board[i][0];
			maxDp[i][1] += Math.max(Math.max(maxDp[i - 1][0], maxDp[i - 1][1]), maxDp[i - 1][2]) + board[i][1];
			maxDp[i][2] += Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + board[i][2];

			minDp[i][0] += Math.min(minDp[i - 1][0], minDp[i - 1][1]) + board[i][0];
			minDp[i][1] += Math.min(Math.min(minDp[i - 1][0], minDp[i - 1][1]), minDp[i - 1][2]) + board[i][1];
			minDp[i][2] += Math.min(minDp[i - 1][1], minDp[i - 1][2]) + board[i][2];
		}

		// 최대, 최소값 찾기
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < 3; i++) {
			min = Math.min(min, minDp[N][i]);
			max = Math.max(max, maxDp[N][i]);
		}
		System.out.println(max + " " + min);
	}
}
