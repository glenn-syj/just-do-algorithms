package boj_19236_청소년상어;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/* B19236. 청소년상어
 * 
 * 1. 조건
 * 	1-1. 4*4 크기 공간
 * 	1-2. 번호는 1~16이나, 상어는 0,0에 있는 물고기를 먹고 해당 이동방향 획득
 * 	1-3. 빈 칸, 다른 물고기가 있는 칸에는 이동가능 / 상어 칸이나 경계를 넘으면 이동 불가
 * 	1-4. 이동할 수 있는 칸을 찾을 때까지 방향을 45도 '반시계' 회전
 * 	1-5. 물고기의 이동이 모두 끝나면 상어가 이동함
 * 		-> 상어는 방향에 
 * 2. 풀이
 * 	2-1. 상어가 먹을 수 있는 물고기 번호의 최댓값
 * 		-> 각 board마다, 해당 방향에 있는 위치는 모두 방문가능
 * 		-> 어쩔수 없이 int[][] 배열 복사해야할듯?
 * 		-> 이러한 가정 하에 dfs 돌릴 필요가 있음
 */

public class Main {

	static int N;
	static int[][] deltas = new int[][]
			{ {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1} };
	static int maxVal = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = 4;
		int[][] board = new int[N][N];
		int[] directions = new int[17];
		
		int dir;
		
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<N; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());
				directions[board[row][col]] = (dir+7)%8;
			}
		}
		
		int sum = board[0][0];
		directions[0] = directions[sum];
		directions[sum] = -1;
		board[0][0] = 0;
		dfs(board, directions, sum);
		
		sb.append(maxVal);
		bw.write(sb.toString());
		bw.close();
		
		
	}

	public static void dfs(int[][] board, int[] directions, int sum) {
		
		if (sum > maxVal) {
			maxVal = sum;
		}
		
		int[][] currBoard = new int[N][N];
		int[] currDirections = new int[17];
		int[][] positions = new int[17][2];
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				currBoard[r][c] = board[r][c];
				if (currBoard[r][c] < 0) {
					continue;
				}
				positions[currBoard[r][c]] = new int[] {r, c};
			}
		}
		
		int sharkRow = positions[0][0];
		int sharkCol = positions[0][1];
		
		for (int i=0; i<=16; i++) {
			currDirections[i] = directions[i];
		}
		
		int nextRow, nextCol;
		
		for (int i=1; i<=16; i++) {
			
			if (currDirections[i] < 0) {
				continue;
			}
			
			
			for (int d=currDirections[i]; d<currDirections[i]+8; d++) {
				d = d%8;
				nextRow = positions[i][0]+deltas[d][0];
				nextCol = positions[i][1]+deltas[d][1];
				
				if (rangeCheck(nextRow, nextCol, sharkRow, sharkCol)) {
					
					currDirections[i] = d;
					
					int value = currBoard[nextRow][nextCol];
					
					if (value < 0) {
						currBoard[positions[i][0]][positions[i][1]] = value;
						currBoard[nextRow][nextCol] = i;
						
						positions[i][0] = nextRow;
						positions[i][1] = nextCol;
					} else {
						currBoard[positions[i][0]][positions[i][1]] = value;
						currBoard[nextRow][nextCol] = i;

						positions[value][0] = positions[i][0];
						positions[value][1] = positions[i][1];
						
						positions[i][0] = nextRow;
						positions[i][1] = nextCol;
					
					}
					
					break;
					
					
				}
			}
		
			
		}
		
		int orgSharkDir = currDirections[0];
		int orgFishVal;
		for (int i=1; i<=3; i++) {
			
			nextRow = sharkRow + i * deltas[orgSharkDir][0];
			nextCol = sharkCol + i * deltas[orgSharkDir][1];
			
			
			if (rangeCheck(nextRow, nextCol, sharkRow, sharkCol)) {
				
				
				orgFishVal = currBoard[nextRow][nextCol];
				
				if (orgFishVal < 0) {
					continue;
				}
				
				currDirections[0] = currDirections[orgFishVal];
				currDirections[orgFishVal] = -1;
				currBoard[nextRow][nextCol] = 0;
				currBoard[sharkRow][sharkCol] = -1;			
				dfs(currBoard, currDirections, sum+orgFishVal);
				currBoard[nextRow][nextCol] = orgFishVal;
				currBoard[sharkRow][sharkCol] = 0;
				currDirections[orgFishVal] = currDirections[0];
				currDirections[0] = orgSharkDir;
				
			}
			
		}
		
		
	}
	
	public static boolean rangeCheck(int row, int col, int sharkRow, int sharkCol) {
		
		return (row >= 0 && row < N && col >= 0 && col < N && (row != sharkRow || col != sharkCol));
		
	}
	
}