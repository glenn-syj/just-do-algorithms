package boj_12100_2048easy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] deltas = new int[][] {{0, -1}, {-1, 0 }, {0, 1}, {1, 0}};
	static int maxBlock = 0;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N][N];
		
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<N; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int d=0; d<4; d++) {
			tilt(board, 0, d);
		}
		
		bw.write(String.valueOf(maxBlock));
		bw.close();
		
		
	}
	
	
	public static void tilt(int[][] board, int depth, int dir) {
		
		if (depth == 5) {
			int maxVal = -1;
			
			for (int row=0; row<N; row++) {
				for (int col=0; col<N; col++) {
					if (board[row][col] > maxVal) {
						maxVal = board[row][col];
					}
				}
			}
			
			if (maxVal > maxBlock) {
				maxBlock = maxVal;
			}
			
			return;
		}
		
		
		int[][] newBoard = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				newBoard[r][c] = board[r][c];
			}
		}
		
		int cPtr, rPtr;
		
		switch (dir) {
		// 왼쪽
		case 0:
			for (int c=1; c<N; c++) {
				for (int r=0; r<N; r++) {
					cPtr = c;
					while (cPtr >= 1 && newBoard[r][cPtr-1] == 0) {
						newBoard[r][cPtr-1] = newBoard[r][cPtr];
						newBoard[r][cPtr] = 0;
						cPtr--;
					} 
					
					if (cPtr == 0) {
						continue;
					}
					
					if (!visited[r][cPtr-1] && newBoard[r][cPtr-1] == newBoard[r][cPtr]) {
						newBoard[r][cPtr-1] *= 2;
						newBoard[r][cPtr] = 0;
						visited[r][cPtr-1] = true;
					}
				}
			}
			break;
		// 위쪽
		case 1:
			for (int r=1; r<N; r++) {
				for (int c=0; c<N; c++) {
					rPtr = r;
					while (rPtr >= 1 && newBoard[rPtr-1][c] == 0) {
						newBoard[rPtr-1][c] = newBoard[rPtr][c];
						newBoard[rPtr][c] = 0;
						rPtr--;
					}
					
					if (rPtr == 0) {
						continue;
					}
					
					if (!visited[rPtr-1][c] && newBoard[rPtr-1][c] == newBoard[rPtr][c]) {
						newBoard[rPtr-1][c] *= 2;
						newBoard[rPtr][c] = 0;
						visited[rPtr-1][c] = true;
					}
					
				}
			}
			break;
		// 오른쪽
		case 2:
			for (int c=N-2; c>=0; c--) {
				for (int r=0; r<N; r++) {
					cPtr = c;
					while (cPtr < N-1 && newBoard[r][cPtr+1] == 0) {
						newBoard[r][cPtr+1] = newBoard[r][cPtr];
						newBoard[r][cPtr] = 0;
						cPtr++;
					} 
					
					if (cPtr == N-1) {
						continue;
					}
					
					if (!visited[r][cPtr+1] && newBoard[r][cPtr+1] == newBoard[r][cPtr]) {
						newBoard[r][cPtr+1] *= 2;
						newBoard[r][cPtr] = 0;
						visited[r][cPtr+1] = true;
					}
				}
			}
			break;
		// 아래쪽
		case 3:
			for (int r=N-2; r>=0; r--) {
				for (int c=0; c<N; c++) {
					rPtr = r;
					while (rPtr < N-1 && newBoard[rPtr+1][c] == 0) {
						newBoard[rPtr+1][c] = newBoard[rPtr][c];
						newBoard[rPtr][c] = 0;
						rPtr++;
					} 
					
					if (rPtr == N-1) {
						continue;
					}
					
					if (!visited[rPtr+1][c] && newBoard[rPtr+1][c] == newBoard[rPtr][c]) {
						newBoard[rPtr+1][c] *= 2;
						newBoard[rPtr][c] = 0;
						visited[rPtr+1][c] = true;
					}
				}
			}
			break;
		}
		
		for (int d=0; d<4; d++) {
			tilt(newBoard, depth+1, d);
		}
		
		
	}
	
}