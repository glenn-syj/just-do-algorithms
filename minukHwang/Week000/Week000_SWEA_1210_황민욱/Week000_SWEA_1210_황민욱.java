package p1210;

/*
 * <문제풀이>
 * 1. 사다리 입력 받기
 * 2. 사다리의 세로 라인 위치만 따로 지정
 * 3. (목적지)밑에서부터 위로 탐색
 * 4. 해당 세로라인 양 옆으로 만약에 1이 존재한다면 해당 방향으로 세로 라인 index 변경
 * 5. 최종적으로 더 이상 올라갈 곳이 없다면 끝내기. 
 */

import java.util.Scanner;

public class Week0_SWEA_1210_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 0; t < 10; t++) {
			// 케이스 넘버 받기
			int caseNum = sc.nextInt();

			// 사다리 초기화
			int[][] ladder = new int[100][100];
			int cnt = 0;
			int cnt2 = 0;

			// 라인 위치 찾기 + 사다리 입력
			for (int j = 0; j < 100; j++) {
				ladder[0][j] = sc.nextInt();
				if (ladder[0][j] == 1) {
					cnt++;
				}
			}

			int[] lines = new int[cnt];
			int colIndex = 0;

			for (int i = 1; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = sc.nextInt();

					if (i == 99) {
						if (ladder[i][j] == 1) {
							lines[cnt2] = j;
							cnt2++;
						} else if (ladder[i][j] == 2) {
							lines[cnt2] = j;
							colIndex = cnt2;
							cnt2++;
						}
					}
				}
			}

			int indexC;
			int indexR = 99;

			for (int i = 0; i < 100; i++) {
				indexC = lines[colIndex];

				if (indexC - 1 >= 0 && ladder[indexR][indexC - 1] == 1) {
					colIndex--;
				} else if (indexC + 1 < 100 && ladder[indexR][indexC + 1] == 1) {
					colIndex++;
				}

				indexR--;
			}

			// 출력
			System.out.printf("#%d %d%n", caseNum, lines[colIndex]);

		}
	}
}
