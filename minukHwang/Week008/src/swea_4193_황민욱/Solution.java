package swea_4193_황민욱;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 소용돌이는 %3 == 2일 때만 지나갈 수 있다.
// 도착할 수 없다면 -1을 출력한다.

class Samsung {
	int r;
	int c;
	int time;

	Samsung() {

	}

	Samsung(int r, int c, int time) {
		this.r = r;
		this.c = c;
		this.time = time;
	}
}

public class Solution {
	static final int INF = Integer.MAX_VALUE;
	static int T, N;
	static int[][] map;
	static boolean[][] visited;

	static int minTime;

	static int[] start;
	static int[] end;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {

			N = sc.nextInt();
			map = new int[N][N];
			visited = new boolean[N][N];
			minTime = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			start = new int[] { sc.nextInt(), sc.nextInt() };
			end = new int[] { sc.nextInt(), sc.nextInt() };

			if(!bfs(start)) {
				minTime = -1;
			}

			System.out.println("#" + t + " " + minTime);
		}
	}

	static int[] deltaR = { -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, -1, 1 };

	private static boolean bfs(int[] start) {
		Queue<Samsung> queue = new LinkedList<>();
		int startR = start[0];
		int startC = start[1];

		queue.add(new Samsung(startR, startC, 0));
		visited[startR][startC] = true;

		while (!queue.isEmpty()) {
			Samsung curr = queue.poll();

			int currR = curr.r;
			int currC = curr.c;
			int currT = curr.time;

			if (currR == end[0] && currC == end[1]) {
				minTime = currT;
				return true;
			}

			for (int i = 0; i < 4; i++) {
				int nextR = currR + deltaR[i];
				int nextC = currC + deltaC[i];

				if (isNotOutBound(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] != 1) {
					// 만약 소용돌이라면?
					if (map[nextR][nextC] == 2 && currT % 3 != 2) {
						// 소용돌이 못지나감
						// 일단 원래 좌표로 기다리기
						nextR = currR;
						nextC = currC;
					}

					visited[nextR][nextC] = true;
					queue.add(new Samsung(nextR, nextC, currT + 1));
				}
			}
		}
		
		return false;
	}

	private static boolean isNotOutBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
