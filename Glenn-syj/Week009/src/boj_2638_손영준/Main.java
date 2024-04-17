package boj_2638_치즈;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* B2638. 치즈
 * 
 * 1. 조건
 * 	1-1. 모눈종이의 맨 가장자리에는 치즈 X
 * 		-> cheese에 대해서는 rangeCheck가 필요없다는 점!
 * 	1-2. 두 변 이상이 공기와 맞닿으면 치즈 
 * 
 * 2. 풀이
 * 	2-0. 모든 치즈의 좌표를 저장하기
 * 		-> 이후 해당 좌표들에 대해 사방을 검사
 * 	2-1. 좌측 상단에서 BFS를 시작해서, 0이었던 점을 탐색하며 -1로 바꿈
 * 		-> 추가적으로: 치즈 자리를 0으로 만들고!
 * 		-> 해당 자리를 BFS 탐색할 큐에 다시 넣어두면 됨
 *		-> 그러면 -1인 점에 대해서는 다시 탐색할 필요가 없음
 *		-> visited 쓸 필요도 없음!
 */

public class Main {
	
	static int N, M, count, cheeseCount;
	static int[][] board;
	static ArrayList<int[]> cheeses;
	static Queue<int[]> queue;
	static int[][] deltas = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		queue = new LinkedList<>();
		count = 0;
		cheeseCount = 0;
		cheeses = new ArrayList<>();
		
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<M; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				if (board[row][col] == 1) {
					cheeses.add(new int[] {row, col});
					cheeseCount++;
				}
			}
		}
		
		queue.add(new int[] {0, 0});
		
		while (cheeseCount > 0) {
			bfs();
		}
		
		sb.append(count);
		bw.write(sb.toString());
		bw.close();
		
		
	}
	
	public static void bfs() {
		
		int[] curr;
		int sum;
		int nextRow, nextCol;
		
		while (!queue.isEmpty()) {
			
			curr = queue.poll();
			board[curr[0]][curr[1]] = -1;
			sum = 0;
			
			for (int[] delta: deltas) {
				nextRow = curr[0] + delta[0];
				nextCol = curr[1] + delta[1];
				
				if (rangeCheck(nextRow, nextCol) 
						&& board[nextRow][nextCol] == 0) {
					queue.add(new int[] {nextRow, nextCol});
					board[nextRow][nextCol] = -1;
				}
			}
			
		}
		
		int[] cheese;
		int cheeseSum;
		for (int idx=0; idx<cheeses.size(); idx++) {
			cheese = cheeses.get(idx);
			if (cheese != null) {
				cheeseSum = 0;
				for (int[] delta: deltas) {
					if (board[cheese[0]+delta[0]][cheese[1]+delta[1]] == -1) {
						cheeseSum--;
					}
				}
				
				if (cheeseSum <= -2) {
					queue.add(new int[] {cheese[0], cheese[1] });
					board[cheese[0]][cheese[1]] = 0;
					cheeses.set(idx, null);
					cheeseCount--;
				}
			}
			
		}
		count++;
	}
	
	public static boolean rangeCheck(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < M);
	}
}