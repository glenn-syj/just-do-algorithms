package boj_19237_황민욱;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Shark {
	boolean isShark;
	int r;
	int c;
	int idx;
	int scent;
	int dir;
	int[][] priorDir = new int[5][5];

	Shark() {

	}

	Shark(boolean isShark, int r, int c, int idx, int scent) {
		this.isShark = isShark;
		this.r = r;
		this.c = c;
		this.idx = idx;
		this.scent = scent;
	}

	@Override
	public String toString() {
		return "Shark [r=" + r + ", c=" + c + ", idx=" + idx + ", scent=" + scent + ", dir=" + dir + ", priorDir="
				+ Arrays.deepToString(priorDir) + "]";
	}
}

public class Main {
	static int N, M, K;
	static List<Shark>[][] map;
	static int[][] scent;
	static int[][] visited;
	static int[][] sharkCoord;

	static Queue<Shark> queue;

	static int[] deltaR = { 0, -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		map = new List[N][N];
		visited = new int[N][N];
		sharkCoord = new int[M + 1][2];
		queue = new LinkedList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = new LinkedList<>();
				int idx = sc.nextInt();

				if (idx == 0) {
					continue;
				}
				
				visited[r][c] = idx;
				scent[r][c] = K;
				
				queue.add(new Shark(true, r, c, idx, K));
			}
		}
		
		System.out.println(queue);

		for (int i = 0; i < M; i++) {
			Shark shark = queue.poll();

			shark.dir = sc.nextInt();
			
			queue.add(shark);
		}
		
		System.out.println(queue);

		for (int i = 0; i < M; i++) {
			Shark shark = queue.poll();
			
			for (int j = 1; j < 5; j++) {

				for (int k = 1; k < 5; k++) {
					shark.priorDir[j][k] = sc.nextInt();
				}
			}

			queue.add(shark);
			
			map[shark.r][shark.c].add(shark);
		}
		
		System.out.println(queue);
		
		printMap();

		int time = 1;

		while (time++ < 1000) {
			for (int i = 0; i < M; i++) {
				Shark shark = queue.poll();

				int idx = shark.idx;
				int currR = shark.r;
				int cuurC = shark.c;
				int dir = shark.dir;
				int[] priorDir = shark.priorDir[dir];
				
				// 앞으로 갈 방향
				
				for(int d = 0; d < 4; d++) {
					int nextD = priorDir[d];
					int nextR = shark.r + deltaR[nextD];
					int nextC = shark.c + deltaC[nextD];
					
					if(isNotOutBound(nextR, nextC)) {
						List<Shark> temp = map[nextR][nextC];
						
						if(visited[nextR][nextC] == 0) {
							map[currR][cuurC].remove(0);
							map[nextR][nextC].add(shark);
							break;
						} 
					}
				}
			}
		}

	}

	private static boolean isNotOutBound(int nextR, int nextC) {
		return nextR >= 0 && nextR < N && nextC >= 0 && nextC < N;
	}

	public static void printMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printMap2() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(visited[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
