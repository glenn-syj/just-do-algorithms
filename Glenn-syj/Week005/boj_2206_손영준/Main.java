package boj_2206_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* B2206. 벽부수고 이동하기
 * 
 * 1. 조건
 * 	1-1. BFS 적용
 * 	1-2. 가는 도중 1번 벽을 부술 수 있음
 * 2. 풀이
 * 	2-1. 특정 position에 대해, 벽울 부순 경우와 아닌 경우의 visited를 따로 계산해야 함
 * 		-> 3차원 배열 이용
 * 	2-2. moveCount = queue에 추가된 횟수가 모두 소진이 되면 depth 1 증가
 * 		-> board[N-1][M-1] 
 */

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;
	
	static int N, M;
	static int[][] board;
	static Queue<int[]> queue;
	static int depthCount, moveCount;
	static int[][] deltas = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		queue = new LinkedList<int[]>();
		visited = new boolean[N][M][2];
		
		String input;
		
		for (int row=0; row<N; row++) {
			input = br.readLine().trim();
			for (int col=0; col<M; col++) {
				board[row][col] = input.charAt(col) - '0';
			}
		}
		
		queue.add(new int[] {0, 0, 0});
		visited[0][0][0] = true;
		moveCount = queue.size();
		depthCount = 1;
		int[] cursor;
		int nextRow, nextCol;
		int dist = Integer.MAX_VALUE;
		
		while (!queue.isEmpty()) {
			cursor = queue.poll();
			
			visited[cursor[0]][cursor[1]][cursor[2]] = true;
			if (cursor[0] == N-1 && cursor[1] == M-1 && depthCount < dist) {
				dist = depthCount;
			}
				
			moveCount--;
			
			
			for (int[] delta: deltas) {
				nextRow = cursor[0]+delta[0];
				nextCol = cursor[1]+delta[1];
				if (rangeCheck(nextRow, nextCol)&&!visited[nextRow][nextCol][cursor[2]]) {
					if (board[nextRow][nextCol] == 0) {						
						queue.add(new int[] {nextRow, nextCol, cursor[2]});
						visited[nextRow][nextCol][cursor[2]] = true;
					} else if (cursor[2] == 0) {
						queue.add(new int[] {nextRow, nextCol, cursor[2]+1});
						visited[nextRow][nextCol][cursor[2]+1] = true;

					}
					
				}
			}
			
			if (moveCount == 0) {
				depthCount++;
				moveCount = queue.size();
			}
			
		}
		
		if (dist == Integer.MAX_VALUE) {
			dist = -1;
		}
		
		sb.append(dist);
		bw.write(sb.toString());
		bw.close();
		
		
		
	}
	
	
	
	
	public static boolean rangeCheck(int row, int col) {
		
		return (row >= 0 && row < N && col >=0 && col < M);
		
	}
}