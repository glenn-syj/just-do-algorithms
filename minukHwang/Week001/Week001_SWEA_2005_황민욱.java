/*
 *  [파스칼의 삼각형]
 */

import java.util.Scanner;

/* 
 * 문제 풀이
 * 1. 2차원 배열 생성
 * 2. 삼각형 만들기
 * 	2-1. 2차원 배열에서 삼각형 생성을 위해 각각의 행들을 탐색할 때 0에서 r번째 index만 고려한다.
 * 	2-2. 각 행들을 탐색할 때 [행][0] = 1로 그 외는 [행-1][본인 열-1] + [행-1][본인 열]로 계산하면서 삼각형을 만든다.
 * 3. 출력
 * 	3-1 0일 경우는 스킵! (삼각형 만들 때 범위랑 똑같으면 됨)
 * 	3-2 나머지는 본인 숫자 출력하기.  
 *  (출력 시 주의 : 숫자들 사이에 빈칸 출력 해야함.)
 */

public class Week001_SWEA_2005_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt();

			int[][] pascalTri = new int[n][n];

			for (int r = 0; r < n; r++) {
				for (int c = 0; c <= r; c++) {
					if (c == 0) {
						pascalTri[r][c] = 1;
					} else {
						pascalTri[r][c] = pascalTri[r - 1][c - 1] + pascalTri[r - 1][c];
					}

				}
			}

			System.out.printf("#%d%n", t);
			for (int r = 0; r < n; r++) {
				for (int c = 0; c <= r; c++) {
					if(c == r) {
						// 마지막은 공백 없이 출력하기!
						System.out.print(pascalTri[r][c]);
					} else {						
						System.out.print(pascalTri[r][c] + " ");
					}
				}
				System.out.println();
			}
		}
	}

}
