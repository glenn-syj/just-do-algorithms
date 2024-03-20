package boj_14053_유서현;

import java.util.Scanner;

public class Main {

	static int N, M, r, c, d;
	static int[][] room;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 }; // 상 좌 하 우

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // r크기
		M = sc.nextInt(); // c크기
		r = sc.nextInt(); // 시작점 r좌표
		c = sc.nextInt(); // 시작점 c좌표
		d = sc.nextInt(); // 바라보는 방향
		room = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				room[i][j] = sc.nextInt();
			}
		}

		// 0이면 청소안된칸, 1이면 벽

		// 바라보는 방향이 단순 델타탐색이 아닌거같은데 어떻게 구현하지?

	}

}