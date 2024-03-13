package boj_2580_스도쿠;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* B2580. 스도쿠
 * 
 * 1. 조건
 * 	1-1. 백트래킹 이용
 * 	1-2. 열/행에서 다른 0의 개수가 적은 좌표부터 차근차근 채워나가기
 * 		-> 이게 시간초과가 나는 문제였다...
 * 2. 풀이
 * 	2-1. 비트마스킹 이용
 * 	2-2. foundAns를 이용해 답이 구해지면 바로 return;
 * 
 */

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;
	
	static int[][] board = new int[9][9];
	static int[] rows = new int[9];
	static int[] cols = new int[9];
	static int[][] windows = new int[3][3];
	static List<int[]> blanks = new ArrayList<int[]>();
	static int[][] possibles = new int[9][9];
	static boolean foundAns = false;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int row=0; row<9; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<9; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				if (board[row][col] == 0) {
					blanks.add(new int[] {row, col, 0});
				}
			}
			
		}
		
		for (int row=0; row<9; row++) {
			for (int col=0; col<9; col++) {
				if (board[row][col] != 0) {
					rows[row] += (1 << board[row][col]);
					cols[col] += (1 << board[row][col]);
					windows[row/3][col/3] += (1 << board[row][col]);
				}
			}
		}
		fillCandidate(0);
		
		for (int row=0; row<9; row++) {
			for (int col=0; col<8; col++) {
				sb.append(board[row][col]).append(' ');
			}
			sb.append(board[row][8]).append('\n');
		}
		
		bw.write(sb.toString());
		bw.close();
		
		
	}
	
	public static void fillCounts() {
		
		int curRow, curCol;
		
		for (int[] cursor: blanks) {
			curRow = cursor[0];
			curCol = cursor[1];
			for (int[] blank: blanks) {
				if (cursor[0] != blank[0] && cursor[1] == blank[1]) {
					cursor[2]++;
				} else if (cursor[0] == blank[0] && cursor[1] != blank[1]) {
					cursor[2]++;
				}
			}
		}
		
	}
	
	public static void fillCandidate(int blankIndex) {
		
		if (blankIndex == blanks.size()) {
			foundAns = true;
			return;
		}
		
		
		int row = blanks.get(blankIndex)[0];
		int col = blanks.get(blankIndex)[1];
		
		int bitmask = rows[row] & cols[col];
		
		for (int i=1; i<=9; i++) {
			if ((bitmask & (1 << i)) == 0) {
				board[row][col] = i;
				if (valCheck(row, col)) {
					rows[row] += (1 << i);
					cols[col] += (1 << i);
					windows[row/3][col/3] += (1 << i);
					fillCandidate(blankIndex+1);
					rows[row] -= (1 << i);
					cols[col] -= (1 << i);
					windows[row/3][col/3] -= (1 << i);
				} else {
					board[row][col] = 0;
				}
			}
			if (foundAns) {
				return;
			}
		}
		
		
		
		board[row][col] = 0;
		
	}
	
	public static boolean valCheck(int row, int col) {
		
		if ((rows[row] & (1 << board[row][col])) != 0) {
			return false;
		}
		if ((cols[col] & (1 << board[row][col])) != 0) {
			return false;
		}
		if ((windows[row/3][col/3] & (1 << board[row][col])) != 0) {
			return false;
		}
		
		return true;
		
	}
		
}