package boj_19237_벽부수고이동하기4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* B19237. 벽 부수고 이동하기4
 * 
 * 1. 조건
 * 	1-1. 매번 1을 없앴다가 BFS를 돌리면 비효율적임
 * 		-> N*M 번의 연산 (새로 board 생성) + 동일 자리 bfs 반복
 * 	1-2. 메모이제이션처럼, i번 영역에 대한 좌표값들을 저장하는 List
 * 		-> 따로 더 저장할 필요 없이 size()를 이용하면 된다!
 * 		-> 좌표값 대신 List<Integer>에 대한 번호를 저장!
 * 
 * 2. 풀이
 * 	2-1. 0인 점에 대해서 BFS를 모두 수행하고 List에 넣기
 * 		-> 0이었던 값들은 index 번호로 바꾼다 (2번부터시작)
 * 		-> 같은 index번호라면 추가하지 않으면 된다!
 * 
 */


public class Main {

	static int[][] board, newBoard;
	static boolean[] visited;
	static int N, M;
	static int countPtr = 2;
	static List<Integer> counts;
	static Queue<int[]> queue;
	static List<int[]> tmpCoors;
	static int[][] deltas = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		newBoard = new int[N][M];
		counts = new ArrayList<>();
		
		String input;
		for (int row=0; row<N; row++) {
			input = br.readLine().trim();
			for (int col=0; col<M; col++) {
				board[row][col] = input.charAt(col) - '0';
			}
		}
		input = null;
		
		for (int row=0; row<N; row++) {
			for (int col=0; col<M; col++) {
				if (board[row][col] == 0) {
					bfs(row, col);
				}
			}
			
		}
		
		
		
		int nextRow, nextCol;
		
		for (int row=0; row<N; row++) {
			for (int col=0; col<M; col++) {
				if (board[row][col] == 1) {
					visited = new boolean[counts.size()+2];
					for (int[] delta: deltas) {
						nextRow = row + delta[0];
						nextCol = col + delta[1];
						if (rangeCheck(nextRow, nextCol) 
								&& board[nextRow][nextCol] >= 2
								&& !visited[board[nextRow][nextCol]]) {
							newBoard[row][col] += counts.get(board[nextRow][nextCol]-2);
							visited[board[nextRow][nextCol]] = true;
						}
					}
					newBoard[row][col]++;
				}
			}
		}
		
		for (int row=0; row<N; row++) {
			for (int col=0; col<M; col++) {
				sb.append(newBoard[row][col] % 10);
			}
			sb.append('\n');
		}
		
		
		bw.write(sb.toString());
		bw.close();
	
	}
	
	public static void bfs(int row, int col) {
		
		queue = new LinkedList<>();
		int count = 0;
		queue.add(new int[] {row, col});
		board[row][col] = countPtr;
		
		int[] curr;
		int nextRow, nextCol;
		while (!queue.isEmpty()) {
			
			curr = queue.poll();
			count++;
			
			for (int[] delta: deltas) {
				nextRow = curr[0] + delta[0];
				nextCol = curr[1] + delta[1];
				if (rangeCheck(nextRow, nextCol) 
						&& board[nextRow][nextCol] == 0) {
					
					queue.add(new int[] {nextRow, nextCol});
					board[nextRow][nextCol] = countPtr;
				}
				
			}
			
		}
		
		counts.add(count);
		countPtr++;
		
		
		
	}
	
	public static boolean rangeCheck(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < M);
	}
	
}