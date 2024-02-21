package boj_9663_황민욱;

import java.util.Scanner;

public class Main {

	static int N;
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static boolean[][] visited;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		int[][] map = new int[N][N];
		visited = new boolean[N][N];
		count = 0;
		
		nQueen(0, 0, 0);
		System.out.println(count);
	}

	public static void nQueen(int queenR, int queenC, int depth) {
		if(depth == N) {
			count++;
			return;
		}
		
		boolean[][] reverse = visited;

		// 퀸 배치
		visited[queenR][queenC] = true;
		int nextR = queenR;
		int nextC = queenC;
		
		System.out.println("start" + nextR + " " + nextC);
		
		// 퀸의 이동 경로는 못감
		int idx = 1;
		while(idx < N) {
			for(int i = 0; i < 8; i++) {
				nextR = queenR + dr[i] * idx;
				nextC = queenC + dc[i] * idx;
				System.out.println("next" + nextR + " " + nextC);
				if(nextR >= 0 && nextR < N && nextC >= 0 && nextC < N) {				
					visited[nextR][nextC] = true;
				}
			}
			idx++;
			System.out.println(idx);
		}
		
		System.out.println("#"+depth+"-------------------------");
		printMap2(visited);
		
		// 다음줄 탐색
		for(int i = 0; i < N; i++) {
			if (queenR+1 >= 0 && queenR+1 < N && !visited[queenR+1][i]) {
				nQueen(queenR + 1, i, depth+1);
				visited = reverse;
			}
		}
	}
	
	public static void printMap(int[][] map) {
		for(int r = 0; r < N; r++) {
			for(int c= 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
	
	public static void printMap2(boolean[][] map) {
		for(int r = 0; r < N; r++) {
			for(int c= 0; c < N; c++) {
				System.out.printf("%5b ", map[r][c]) ;
			}
			System.out.println();
		}
	}
}
