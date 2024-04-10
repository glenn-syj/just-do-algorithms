package swea_1949_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
	static int T, N, K;
	static int[][] map;
	static List<int[]> start;
	static int maxLength;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = sc.nextInt();
			K = sc.nextInt();

			map = new int[N][N];
			start = new ArrayList<>();

			int max = -1;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();

					max = Math.max(max, map[r][c]);
				}
			}
			
			maxLength = 0;
			visited = new boolean[N][N];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == max) {
						int[] coord = { r, c };
						dfs(r, c, 1, false);
					}
				}
			}
			
			sb.append(maxLength).append("\n");
		}
		
		System.out.println(sb);
	}

	static int[] deltaR = { -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, -1, 1 };

	private static void dfs(int r, int c, int sum, boolean isCut) {
		visited[r][c] = true;
//		printMap();
		
		
		for(int d = 0; d < 4; d++) {
			int nextR = r + deltaR[d];
			int nextC = c + deltaC[d];
			
			if(isNotOutBound(nextR, nextC) && !visited[nextR][nextC] && map[r][c] > map[nextR][nextC]) {
				dfs(nextR, nextC, sum + 1, isCut);
			}
			
			if(isNotOutBound(nextR, nextC) && !visited[nextR][nextC] && map[r][c] <= map[nextR][nextC] && map[r][c] > map[nextR][nextC] - K && !isCut) {
				int originalHeight = map[nextR][nextC];
				map[nextR][nextC] = map[r][c] - 1;
				dfs(nextR, nextC, sum + 1, true);
				map[nextR][nextC] = originalHeight;
			}
		}
		
		visited[r][c] = false;
//		System.out.println("r: " + r + " c: " + c);
		maxLength = Math.max(maxLength, sum);
//		System.out.println("#"+maxLength);
//		printMap();
	}

	private static boolean isNotOutBound(int nextR, int nextC) {
		return nextR >= 0 && nextR < N && nextC >= 0 && nextC < N;
	}
	
	
	public static void printMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(visited[r][c]) {
					System.out.print(1 + " ");
				} else {
					System.out.print(0 + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
