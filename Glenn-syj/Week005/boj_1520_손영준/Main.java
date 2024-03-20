package boj_1520_내리막길;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/* B1520. 내리막길
 * 
 * 1. 조건
 * 	1-1. dfs 이용
 * 	1-2. 더 작은 곳으로만 가니 visited 배열 따로 필요 X
 * 2. 풀이
 * 	2-1. DFS 이용해서 (M, N)에 도달하면 종료
 * 		-> 당연히 시간초과
 * 	2-2. 예전에 시도했었던 방식(좌측과 상측 모두보다 작으면 둘의 합)
 * 		-> 이러한 방식에서, 
 *
 */

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br;
	static BufferedWriter bw;
	
	static int M, N;
	static int[][] board;
	static int[][] ways, visited;
	static int count = 0;
	static int[][] deltas = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static int[][] deltas2 = new int[][] { {-1, 0}, {0, -1} };
	static int rowBef, colBef;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		board = new int[M][N];
		ways = new int[M][N];
		visited = new int[M][N];
		
		for (int row=0; row<M; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<N; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int row=1; row<M; row++) {
			if (board[row-1][0] > board[row][0]) {
				visited[row][0] = 2;
				ways[row][0] = 1;
			} else {
				break;
			}
		}
		
		for (int col=1; col<N; col++) {
			if (board[0][col-1] > board[0][col]) {
				visited[0][col] = 2;
				ways[0][col] = 1;
			} else {
				break;
			}
		}
		int val;
		for (int row=1; row<M; row++) {
			for (int col=1; col<N; col++) {
				val = board[row][col];
				
				if (val < board[row-1][col] 
						&& val < board[row][col-1]) {
					ways[row][col] = ways[row-1][col] + ways[row][col-1];
					visited[row][col] = 2;
				} else if (val < board[row-1][col]
						&& val >= board[row][col-1]) {
					ways[row][col] += ways[row-1][col];
					visited[row][col] = 1;
				} else if (val < board[row][col-1] 
						&& val >= board[row-1][col]) {
					ways[row][col] += ways[row][col-1];
					visited[row][col] = 1;
				}
			}
		}
		
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("----");
		
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(ways[i][j] + " ");
			}
			System.out.println();
		}
				
		
		
		
		count += ways[M-1][N-1];
		
		for (int row=1; row<M; row++) {
			for (int col=1; col<N; col++) {
				if (visited[row][col] == 1) {
					rowBef = row;
					colBef = col;
					dfs(row, col, true);
				}
			}
		}
		
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("----");
		
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(ways[i][j] + " ");
			}
			System.out.println();
		}

		sb.append(count);
		bw.write(sb.toString());
		bw.close();
		
	}
	
	public static void dfs(int row, int col, boolean start) {
		
		System.out.println("row: " + row + " col: " + col);
		
		if (row == M-1 && col == N-1) {
			return;
		}
		
		
		
		if (visited[row][col] == 2) {
			ways[rowBef][colBef] += 1;
			count++;
			System.out.println(count);
			return;
		} else {
			visited[row][col]++;
			ways[row][col] = ways[rowBef][colBef];
		}
		
		
		rowBef = row;
		colBef = col;
		
		
		if (start) {			
			for (int[] delta: deltas2) {
				if (rangeCheck(row+delta[0], col+delta[1]) 
						&& board[row+delta[0]][col+delta[1]] < board[row][col]) {
					dfs(row+delta[0], col+delta[1], false);
				}
			}
		} else {
			for (int[] delta: deltas) {
				if (rangeCheck(row+delta[0], col+delta[1]) 
						&& board[row+delta[0]][col+delta[1]] < board[row][col]) {
					dfs(row+delta[0], col+delta[1], false);
				}
			}
		}
		
		
	}
	
	public static boolean rangeCheck(int row, int col) {
		return (row >= 0 && row < M && col >= 0 && col < N);
	}
}