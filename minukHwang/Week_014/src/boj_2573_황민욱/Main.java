package boj_2573_황민욱;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] temp;
	static int[][] visited;
	static int count;
	static int[] deltaR = { -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		temp = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
				temp[r][c] = map[r][c];
			}
		}

		int time = 0;
		boolean isAvailable = false;

		out: while (true) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (temp[r][c] == 0) {
						continue;
					}
					
					for(int d = 0; d < 4; d++) {
						int nr = r + deltaR[d];
						int nc = c + deltaC[d];
						
						if(isNotOutBound(nr, nc) && temp[nr][nc] == 0 && map[r][c] > 0) {
							map[r][c]--;
						}
					}
				}
			}
			
			int water = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == 0) {
						water++;
					}
					temp[r][c] = map[r][c];
				}
			}
			
			
			time++;
			
			if(water == N * M) {
				break out;
			}
			
			visited = new int[N][M];
			count = 1;
			
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == 0) {
						continue;
					}
					
					if(map[r][c] != 0 && visited[r][c] == 0) {						
						bfs(r, c);
						count++;
					}
				}
			}
			
			if(count > 2) {
				isAvailable = true;
				break out;
			}
		}
		
		System.out.println(isAvailable? time : 0);
	}
	

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		
		int[] start = {r, c};
		visited[r][c] = count;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current[0] + deltaR[d];
				int nc = current[1] + deltaC[d];
				
				if(isNotOutBound(nr, nc) && map[nr][nc] != 0 && visited[nr][nc] == 0) {
					visited[nr][nc] = count;
					
					int[] next = {nr, nc};
					queue.add(next);
				}
			}
		}
		
	}
	
	private static boolean isNotOutBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	public static void printMap(int[][] list) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				System.out.print(list[r][c] + " ");
			}
			System.out.println();
		}
	}
}
