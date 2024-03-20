package boj_2580_황민욱;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static List<int[]> blanks;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[9][9];
		blanks = new ArrayList<>();

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] == 0) {
					int[] blank = { r, c };
					blanks.add(blank);
				}
			}
		}

		sudoku(0);
	}

	public static void sudoku(int index) {
		// 기저 조건
		if (index == blanks.size()) {
			printMap();
			System.exit(0);
		}

		// 재귀 조건
		// 한 행씩 확인하기
		int blankR = blanks.get(index)[0];
		int blankC = blanks.get(index)[1];

		for (int num = 1; num < 10; num++) {
			// 만약 조건에 맞는다면
			if (check(blankR, blankC, num)) {
				map[blankR][blankC] = num;
				sudoku(index + 1);
				map[blankR][blankC] = 0;
			}
		}
		
	}

	// 가능 여부 파악
	public static boolean check(int rIndex, int cIndex, int value) {

		// 행 확인
		for (int c = 0; c < 9; c++) {
			if (map[rIndex][c] == value) {
				return false;
			}
		}

		// 열 확인
		for (int r = 0; r < 9; r++) {
			if (map[r][cIndex] == value) {
				return false;
			}
		}

		// 3*3 확인
		for (int r = (rIndex / 3) * 3; r < (rIndex / 3 + 1) * 3; r++) {
			for (int c = (cIndex / 3) * 3; c < (cIndex / 3 + 1) * 3; c++) {
				if (map[r][c] == value) {
					return false;
				}
			}
		}

		return true;
	}

	public static void printMap() {
		StringBuilder sb = new StringBuilder();

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (c == 8) {
					sb.append(map[r][c]).append("\n");
					break;
				}
				sb.append(map[r][c]).append(" ");
			}
		}

		System.out.println(sb);
	}
}
