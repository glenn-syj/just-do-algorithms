package swea_1953_황민욱;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Coord {
	int r;
	int c;
	int step;
	int[] tunnel;

	Coord() {

	}

	Coord(int r, int c, int step, int[] tunnel) {
		this.r = r;
		this.c = c;
		this.step = step;
		this.tunnel = tunnel;
	}

	@Override
	public String toString() {
		return "Coord [r=" + r + ", c=" + c + ", step=" + step + ", tunnel=" + Arrays.toString(tunnel) + "]";
	}
}

public class Solution {
	static int T, N, M, R, C, L;
	static int[][] map;

	static int count;
	static Queue<Coord> queue;
	static boolean[][] visited;

	static int[] deltaR = { -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, -1, 1 };
	
	// 0 상 1 하 2 좌 3 우
	static int[][] tunnels = { {}, { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 }, { 0, 2 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
//			System.out.println(Arrays.deepToString(map));

			count = bfs();
//			System.out.println(count);
			sb.append(count).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs() {
		int total = 1;
		
		queue = new LinkedList<>();
		queue.add(new Coord(R, C, 1, tunnels[map[R][C]]));

		visited[R][C] = true;

		while (!queue.isEmpty()) {
//			System.out.println(queue);
			Coord curr = queue.poll();
			
			for(int i = 0; i < curr.tunnel.length; i++) {
				int d = curr.tunnel[i];
				
				int nextR = curr.r + deltaR[d];
				int nextC = curr.c + deltaC[d];
				int nextStep = curr.step + 1;
				
				if(isNotOutBound(nextR, nextC) && !visited[nextR][nextC] && nextStep <= L) {
					int[] nextTunnel = tunnels[map[nextR][nextC]];
					boolean isAvailable = false;
					
					for(int j = 0; j < nextTunnel.length; j++) {
						int dir = nextTunnel[j];
						
						// 0이면 1, 1이면 0, 2이면 3, 3이면 2
						switch(d) {
						case 0:
							if(dir == 1) {
								isAvailable = true;
							}
							break;
						case 1:
							if(dir == 0) {
								isAvailable = true;
							}
							break;
						case 2:
							if(dir == 3) {
								isAvailable = true;
							}
							break;
						case 3:
							if(dir == 2) {
								isAvailable = true;
							}
							break;
						}

						if(isAvailable == true) {
							visited[nextR][nextC] = true;
							queue.add(new Coord(nextR, nextC, nextStep, nextTunnel));
							
//							System.out.println(d + " "+ dir + " " + Arrays.toString(nextTunnel) + " " + "("+nextR + ", " + nextC + ") " + nextStep);
							
							total++;
							
							break;
						}
					}
				}
			}
		}
		
		return total;
	}

	private static boolean isNotOutBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
