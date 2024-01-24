package p7272;

/**
 * <풀이 방법>
 * 1. 단어 두개 받아오기
 * 2. 단어를 순차 탐색하면서 A류, B류, C류로 구분하여 통일 시켜주기.
 * 3. 변동된 두 단어를 비교한다.
 */

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String letter = "ADOPQR";

		char[] hole = letter.toCharArray();

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			boolean same = true;
			String word1 = sc.next();
			String word2 = sc.next();

			char[] letter1 = word1.toCharArray();
			char[] letter2 = word2.toCharArray();

			// 길이가 다르면 무조건 다른 단어로 인식
			if (letter1.length != letter2.length) {
				same = false;
			} else {
				// 단어 탐색 후 구멍 0개 1개 2개 있는 문자들 통일시키기.
				for (int i = 0; i < letter1.length; i++) {
					for (int j = 0; j < hole.length; j++) {
						if (letter1[i] == hole[j]) {
							letter1[i] = 'A';
						}
					}

					if (letter1[i] == 'B') {
						continue;
					} else if(letter1[i] != 'A') {
						letter1[i] = 'C';
					}
					
					for (int j = 0; j < hole.length; j++) {
						if (letter2[i] == hole[j]) {
							letter2[i] = 'A';
						}
					}

					if (letter2[i] == 'B') {
						continue;
					} else if(letter2[i] != 'A') {
						letter2[i] = 'C';
					}
				}

				// 두 단어 비교하기
				for (int i = 0; i < letter1.length; i++) {
					if (letter1[i] != letter2[i]) {
						same = false;
					}
				}
			}

			System.out.printf("#%d %s%n", t, same ? "SAME" : "DIFF");

		}
	}
}
