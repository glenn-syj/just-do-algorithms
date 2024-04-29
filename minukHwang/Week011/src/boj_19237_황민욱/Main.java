package boj_19237_황민욱;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Shark {
	boolean isShark;
	int idx;
	int scent;
	int dir;
	int[] priorityR = new int[5];
	int[] priorityC = new int[5];

	Shark() {

	}

	Shark(boolean isShark, int idx, int scent) {
		this.isShark = isShark;
		this.idx = idx;
		this.scent = scent;
	}
}

public class Main {
	static int N, M, K;
	static List<Shark>[][] map;
	static int[][] sharkCoord;

	int[] deltaR = { 0, -1, 1, 0, 0 };
	int[] deltaC = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		map = new List[N][N];
		sharkCoord = new int[M + 1][2];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = new LinkedList<>();
				int idx = sc.nextInt();

				if (idx == 0) {
					continue;
				}

				map[r][c].add(new Shark(true, idx, K));

				// 좌표 일단 기억
				sharkCoord[idx][0] = r;
				sharkCoord[idx][1] = c;
			}
		}
		
		for(int i = 1; i < 5; i++) {
			int sharkR = sharkCoord[i][0];
			int sharkC = sharkCoord[i][1];
			
			map[sharkR][sharkC].get(0).dir = sc.nextInt();
		}

		for (int i = 1; i < 5; i++) {
			int sharkR = sharkCoord[i][0];
			int sharkC = sharkCoord[i][1];
			
			for (int j = 1; j < 5; j++) {
				
				for(int k = 1; k < 5; k++) {
					map[sharkR][sharkC].get(0);
					
				}
			}
		}
	}

}
