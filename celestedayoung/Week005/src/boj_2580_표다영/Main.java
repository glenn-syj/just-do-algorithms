package boj_2580_스도쿠;

/*	[Point]
 *	- System.exit(0)을 이용한 강제 종료를 하지 않으면 index 초과 에러 발생
 *  
 *  [Logic]
 *  1. 행이 다 채워졌을 경우 다음 행의 첫 번째 열로 이동
 *  2. 행과 열이 다 채워졌을 경우 출력 후 종료
 *  3. 해당 위치 값이 0이라면 1부터 9 중에서 가능한 수를 검사
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		
		board = new int[9][9];
		for (int r = 0; r < 9; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 9; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		sudoku(0, 0);

	}
	
	static boolean voidNum (int row, int col, int number) {

		for(int i = 0 ; i < 9 ; i ++) {
			if(board[row][i] == number) {
				return false;
			}
		}
		
		for(int i = 0 ; i < 9 ; i ++) {
			if(board[i][col] == number) {
				return false;
			}
		}
		
		int miniR = (row / 3) * 3;
		int miniC = (col / 3) * 3;
		
		for(int i = miniR ; i < miniR + 3 ; i ++) {
			for(int j = miniC ; j < miniC +3 ; j++) {
				if(board[i][j] == number)
					return false;
			}
		}
		
		return true;
	}
	
	static void sudoku(int row, int col) {
		
		if (col == 9) {
			sudoku(row + 1, 0);
			return;
		}
		
		if (row == 9) {
			 for (int r = 0; r < 9; r++) {
				 for (int c = 0; c < 9; c++) {
					 System.out.print(board[r][c] + " ");
				 }
				 System.out.println();
			 }
			 System.exit(0);
		 }
		
		if (board[row][col] == 0) {
			for(int i = 1 ; i <= 9 ; i++) {
				if(voidNum(row, col, i)) {
					board[row][col] = i;
					sudoku(row, col+1);
				}
			}
			board[row][col]=0;
			return;
		}
		
		sudoku(row, col + 1);
	
	}
}
