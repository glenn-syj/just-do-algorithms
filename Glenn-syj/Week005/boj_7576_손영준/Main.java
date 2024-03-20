package boj_7576_토마토;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* B7576. 토마토
 * 
 * 1. 조건
 * 	1-1. 2 <= M, N <= 1000
 * 	1-2. 익은 토마토는 최소 1개 주어지고, 익지 않은 토마토는 없을 수 있음 -> edge case 고려 (all 1...)
 * 	1-3. 토마토가 모두 익는 최소 일수 -> 0의 개수가 0이 되거나, 0이 남거나
 * 		-> 최소 일수 = bfs에 대해서 한 day가 모두 지날 때 (count 이용)
 * 2. 풀이
 * 	2-1. 반복문을 이용한 bfs
 * 		-> 1000*1000이므로 혹시 모른다...
 * 	2-2. daySpent에 대해서 깔끔하게 한 cycle이 돌 때 특정 변수의 값을 줄여주고
 * 		-> 그 값이 0이 되면 다시 queue에 남은 이후 날들에 대해서 특정 변수를 재할당
 * 		-> 그 값이 0이 되면 다시 ...
 * 		=> 이런 식으로 queue가 빌 때 까지 필요한 최소 날짜 구할 수 있음
 * 
 */


public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int M, N, zeroCount;
	static int[][] board;
	static Queue<int[]> queue;
	static boolean[][] visited;
	static int[][] deltas = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
//	static List<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		queue = new LinkedList<int[]>();
		zeroCount = 0;
		
		
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<M; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				if (board[row][col] == 0) {
					zeroCount++;
				} else if (board[row][col] == 1) {
					queue.add(new int[] { row, col });
					visited[row][col] = true;
				}
			}
		}
		
		if (zeroCount == 0) {
			sb.append(0);
			bw.write(sb.toString());
			bw.close();
			return;
		}
		
		int checkCount = queue.size();
		int daySpent = -1;
		int[] cursor;
		
		zeroCount += queue.size();
		
		while (!queue.isEmpty()) {
			cursor = queue.poll();
			
			checkCount--;
			zeroCount--;
			
			for (int[] delta : deltas) {
				if (posCheck(cursor[0]+delta[0], cursor[1]+delta[1])) {
					queue.add(new int[] {cursor[0]+delta[0], cursor[1]+delta[1]});
					visited[cursor[0]+delta[0]][cursor[1]+delta[1]] = true;
				}
			}
			
			if (checkCount == 0) {
				daySpent++;
				checkCount = queue.size();
			}
			
		}
				
		if (zeroCount > 0) {
			sb.append(-1);
		} else {
			sb.append(daySpent);
		}
		
		bw.write(sb.toString());
		bw.close();
	}
		
	public static boolean posCheck(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < M 
				&& board[row][col] == 0 
				&& !visited[row][col]);
	}
}