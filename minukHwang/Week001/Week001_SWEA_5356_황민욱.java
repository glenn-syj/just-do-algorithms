/*
 * 의석이의 세로로 말해요
 */

import java.util.Scanner;

/*
 * 풀이 방법
 * 1. 2차원 배열을 생성한다.
 * 2. 배열에 입력받은 문자열들을 행으로 나누어서 넣는다.
 * 3. 열 우선 탐색으로 문자열들을 출력한다.
 * 	3-1 행 우선 탐색시에 만약 2차원 배열에 입력받은 문자가 들어있지 않다면 스킵한다.
 */

public class Week001_SWEA_5356_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		// 1. 테스트 케이스만큼 반복
		for (int t = 1; t <= T; t++) {

			// 2. 2차원 배열 생성해주기.
			// 문제에서 문자열은 5줄 각각 길이가 15가 최대라고 하였으니 해당 범위에 맞게 생성.
			char[][] map = new char[5][15];

			// 3. 문자열 입력 받기
			for (int i = 0; i < map.length; i++) {
				char[] letters = sc.next().toCharArray();

				// 각각의 문자열들의 길이에 따라서 제한을 걸고 map의 같은 행에 넣는다.
				for (int j = 0; j < letters.length; j++) {
					map[i][j] = letters[j];
				}
			}

			// 4. 열 우선 탐색으로 정답 문자열 생성하기
			String answer = "";

			out: for (int c = 0; c < 15; c++) {
				// 빈칸 개수 파악
				int nullCount = 0;

				for (int r = 0; r < 5; r++) {
					// 만약 빈칸이라면 빈칸 수 ++ 그리고 스킵
					if (map[r][c] == '\u0000') {
						nullCount++;
						continue;
					}
					answer += map[r][c];
				}

				// 모든 열(빈칸 수 5개)이 빈칸이라면 반복문 종료.
				if (nullCount == 5) {
					break out;
				}
			}

			// 5. 출력!
			System.out.printf("#%d %s\n", t, answer);
		}
	}
}
