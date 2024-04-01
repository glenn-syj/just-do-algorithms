package boj_17141_연구소2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* B17141. 연구소2
 * 
 * 1. 조건
 * 	1-1. 연구소의 특정 위치에 바이러스 M개 (1 <= M <= 10)
 * 		-> 동시에 바이러스는 퍼짐
 * 	1-2. 연구소는 크기가 N*N인 정사각형으로... (5 <= N <= 50)
 * 	1-3. 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸
 * 	1-4. 2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수
 * 	1-5. 모든 빈 칸에 바이러스를 퍼뜨릴 수 없으면 -1 출력
 * 
 * 2. 풀이
 * 	2-1. 각 칸들에 대해 먼저 queue에 넣어두고, bfs를 이용한 탐색 시행
 * 	2-2. 매번 다시 모두를 탐색할 필요 없이, 처음에 0의 개수를 정해두고 들어가면 됨!
 * 	2-3. 2인 곳들 중에서 M개를 골라야 함
 * 		-> 조합을 이용하면서, sidx를 만족할 시 bfs를 실행해서 검사
 * 	2-4. bfs 메서드, 조합 메서드 작성 필요
 * 	2-5. 매번 board에 대해서 deepcopy와 같이 복사해야하며, 참조값으로 하면 안 된다
 */

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int[][] board, currBoard;
	static int N, M;
	static int zeroCount = 0;
	static int minValue = Integer.MAX_VALUE;
	static List<int[]> vessels = new ArrayList<>();
	static Queue<int[]> que;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<N; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				if (board[row][col] == 0) {
					zeroCount++;
				} else if (board[row][col] == 2) {
					vessels.add(new int[] {row, col, 0});
				}
			}
		}
		
		getResultFromComb(new int[M], 0, 0);
		
		if (minValue == Integer.MAX_VALUE) {
			minValue = -1;
		}
		
		sb.append(minValue);
		bw.write(sb.toString());
		bw.close();
		
		
		
	}
	
	public static void getResultFromComb(int[] result, int idx, int sidx) {
		
		if (sidx >= M) {
			
			int localMax = Integer.MIN_VALUE;
			
			// currBoard 초기화
			currBoard = new int[N][N];
			for (int row=0; row<N; row++) {
				for (int col=0; col<N; col++) {
					currBoard[row][col] = board[row][col];
				}
			}
			
			// vessel은 row, col, dist=0;
			que = new LinkedList<>();
			
			for (int i=0; i<M; i++) {
				que.add(vessels.get(result[i]));
			}
			
			for (int i=0; i<vessels.size(); i++) {
				boolean check = false;
				for (int num: result) {
					if (i == num) {
						check = true;
						break;
					}
				}
				if (!check) {
					currBoard[vessels.get(i)[0]][vessels.get(i)[1]] = 0;
				}
			}
			
			
			int zeros = zeroCount + vessels.size() - M;
			int[] curr;
			int nextRow, nextCol;
			
			while (!que.isEmpty()) {
				
				curr = que.poll();
				localMax = curr[2];
				for (int[] delta: deltas) {
					nextRow = curr[0] + delta[0];
					nextCol = curr[1] + delta[1];
					if (rangeCheck(nextRow, nextCol)
							&& currBoard[nextRow][nextCol] == 0) {
						currBoard[nextRow][nextCol] = curr[2] + 1;
						zeros--;
						que.add(new int[] {nextRow, nextCol, curr[2]+1});
					}
				}
			}
			
			if (zeros == 0
					&& localMax < minValue) {
				minValue = localMax;
			}
			
			return;
		}
		
		if (idx >= vessels.size()) {
			return;
		}
		
		result[sidx] = idx;
		getResultFromComb(result, idx+1, sidx+1);
		getResultFromComb(result, idx+1, sidx);
		
	}

	public static boolean rangeCheck(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < N);
	}
	
}