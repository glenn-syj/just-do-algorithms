package boj_2580_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static List<int[]> blanks;
	static int[] rowCount;
	static int[] colCount;
	static int[][] sqrCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[9][9];
		blanks = new ArrayList<>();
		rowCount = new int[9];
		colCount = new int[9];
		sqrCount = new int[3][3];

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] == 0) {
					int[] blank = { r, c };
					// blanks.add(blank);
					rowCount[r]++;
					colCount[c]++;
					sqrCount[r / 3][c / 3]++;
				}
			}
		}

//		System.out.println(Arrays.toString(rowCount));
//		System.out.println(Arrays.toString(colCount));
//		System.out.println(Arrays.deepToString(sqrCount));

		for (int r = 0; r < 9; r++) {
			if (rowCount[r] == 1) {
				int blankR = 0;
				int blankC = 0;
				int[] count = new int[10];

				for (int c = 0; c < 9; c++) {
					if (map[r][c] == 0) {
						blankR = r;
						blankC = c;
						continue;
					}
					count[map[r][c]]++;
				}

				for (int i = 1; i < 10; i++) {
					if (count[i] == 0) {
						map[blankR][blankC] = i;
					}
				}

				rowCount[blankR]--;
				colCount[blankC]--;
				sqrCount[blankR / 3][blankC / 3]--;

			}
		}
//		printMap();

//		System.out.println(Arrays.toString(rowCount));
//		System.out.println(Arrays.toString(colCount));
//		System.out.println(Arrays.deepToString(sqrCount));

		for (int c = 0; c < 9; c++) {
			if (colCount[c] == 1) {
				int blankR = 0;
				int blankC = 0;
				int[] count = new int[10];

				for (int r = 0; r < 9; r++) {
					if (map[r][c] == 0) {
						blankR = r;
						blankC = c;
						continue;
					}
					count[map[r][c]]++;
				}

				for (int i = 1; i < 10; i++) {
					if (count[i] == 0) {
						map[blankR][blankC] = i;
					}
				}

				rowCount[blankR]--;
				colCount[blankC]--;
				sqrCount[blankR / 3][blankC / 3]--;

			}
		}

//		printMap();

//		System.out.println(Arrays.toString(rowCount));
//		System.out.println(Arrays.toString(colCount));
//		System.out.println(Arrays.deepToString(sqrCount));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sqrCount[i][j] == 1) {
					int blankR = 0;
					int blankC = 0;
					int[] count = new int[10];

					for (int r = i * 3; r < (i + 1) * 3; r++) {
						for (int c = j * 3; c < (j + 1) * 3; c++) {
							if(map[r][c]==0) {
								blankR = r;
								blankC = c;
								continue;
							}
							count[map[r][c]]++;
						}
					}
					
					for(int k = 1; k < 10; k++) {
						if(count[k] == 0) {
							map[blankR][blankC] = k;
						}
					}
					
					rowCount[blankR]--;
					colCount[blankC]--;
					sqrCount[blankR / 3][blankC / 3]--;
				}
			}
		}
		
		printMap();

//		System.out.println(Arrays.toString(rowCount));
//		System.out.println(Arrays.toString(colCount));
//		System.out.println(Arrays.deepToString(sqrCount));

	}

	public static void printMap() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
