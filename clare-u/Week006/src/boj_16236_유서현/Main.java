package boj_16236_유서현;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int size, N;
	static int[][] length, board;
	static int[] dr = {};
	static int[] dc = {};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		// 아기상어 좌표
		int r = 0;
		int c = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (board[i][j] == 9) {
					r = i;
					c = j;
				}
			}
		}
		
		
		size=2;
		bfs(r, c); // 아기상어 좌표에서 길이 저장하는 bfs
		
		// 미완성
		
		
	}

	static void bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		length[i][j] = 0; // 시작점은 0부터 시작
		int[] temp = { i, j };
		queue.add(temp);

		while (!queue.isEmpty()) {
			int[] out = queue.poll();
			i = out[0];
			j = out[1];

//			System.out.println("i: " + i + " j: " + j);

			for (int d = 0; d < 4; d++) { // 델타탐색
				if (0 <= i + dr[d] && i + dr[d] < N && 0 <= j + dc[d] && j + dc[d] < N
						&& length[i + dr[d]][j + dc[d]] == -1 && board[i + dr[d]][j + dc[d]] == 1) {
					length[i + dr[d]][j + dc[d]] = length[i][j] + 1;

//					System.out.println(length[i + dr[d]][j + dc[d]]);

					int[] temp2 = { i + dr[d], j + dc[d] };
					queue.add(temp2);
				}

			}

		}

	} // bfs()

}
