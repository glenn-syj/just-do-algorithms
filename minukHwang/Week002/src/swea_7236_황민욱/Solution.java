package swea_7236_황민욱;

import java.util.Scanner;

/*
 * 풀이방법
 * 1. 2차원 배열을 만들어서 정보 값을 입력받는다.
 * 2. d배열을 만들어 8방 탐색을 할 수 있도록 한다.
 * 3. 중심점에 따라서 이동하면서 8방 탐색을 하며 W의 개수를 계산한다.
 * 4. w의 개수를 계산하면서 해당 중심점에서 최대값인지 아닌지 판별한다.
 * 5. 최대의 값을 구해서 출력하면 끝.
 */

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt();

			String[][] map = new String[n][n];

			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					map[r][c] = sc.next();
				}
			}

			int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
			int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

			int max = 0;

			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					int count = 0;
					for (int i = 0; i < 8; i++) {
						int indexR = r + dr[i];
						int indexC = c + dc[i];

						if (indexR >= 0 && indexR < n && indexC >= 0 && indexC < n) {
							if (map[indexR][indexC].equals("W")) {
								count++;
							}
						}
					}
					if (count > max) {
						max = count;
					}
				}
			}

			System.out.printf("#%d %d\n", t, max);

		}
	}
}

