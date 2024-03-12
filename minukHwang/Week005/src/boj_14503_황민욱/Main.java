package boj_14503_황민욱;

import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	// 1이면 벽, 0이면 청소 필요한 빈 공간, 2면 청소 완료.

	static int robotR;
	static int robotC;
	static int robotD;

	// 북, 동, 남, 서 -> 시계 방향
	static int[] deltaR = { -1, 0, 1, 0 };
	static int[] deltaC = { 0, 1, 0, -1 };

	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		robotR = sc.nextInt();
		robotC = sc.nextInt();
		robotD = sc.nextInt();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		count = 0;
		clean(robotR, robotC, robotD);
		System.out.println(count);
	}

	public static void clean(int r, int c, int d) {
		while(true) {			
			// 1. 현재 칸이 청소가 안되었다면, 현재 칸 청소
			if (map[r][c] == 0) {
				map[r][c] = 2;
				count++;
			}

			
			// 2. 현재 칸 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			if (!isNotCleaned(r, c)) {
				// 1. 바라보는 방향을 유지 | 한칸 후진 가능 -> 후진,
				int nextR = r - deltaR[d];
				int nextC = c - deltaC[d];
				
				// 2. 후진 불가? -> 작동 그만
				if(map[nextR][nextC] == 1) {
					break;
				} else {
					r = nextR;
					c = nextC;
				}
			} 
			
			// 3. 현재 칸 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			else {
				while(true) {					
					// 1. 반시계 방향으로 90도 회전
					d = (d + 3) % 4;
					
					// 2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸 -> 한 칸 전진.
					int nextR = r + deltaR[d];
					int nextC = c + deltaC[d];
					
					if(map[nextR][nextC] == 0) {
						r = nextR;
						c = nextC;
						break;
					}
				}
			}
		}
	}

	public static boolean isNotCleaned(int r, int c) {
		boolean isAvailable = false;

		for (int i = 0; i < 4; i++) {
			int nextR = r + deltaR[i];
			int nextC = c + deltaC[i];

			// 청소가 안된 공간이 존재한다면
			if (map[nextR][nextC] == 0) {
				isAvailable = true;
			}
		}

		return isAvailable;
	}

	public static void printMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
