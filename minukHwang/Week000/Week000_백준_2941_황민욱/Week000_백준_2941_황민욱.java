package p2941;

/**
 * <풀이 방법>
 * 1. 2차원 배열에 크로아티아 문자들 넣어주기
 * 2. 받은 단어에서 첫 글자들을 비교하기
 * 3. 일치하는 것이 있으면 다음 문자 비교
 * 4. 카운트 ++
 */


import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[][] alpha = { { 'c', '=' }, { 'c', '-' }, { 'd', 'z', '=' }, { 'd', '-' }, { 'l', 'j' }, { 'n', 'j' },
				{ 's', '=' }, { 'z', '=' } };

		int count = 0;
		int index = 0;
		String word = sc.next();

		char[] letter = word.toCharArray();

		while (index < letter.length) {

			// 첫 글자 비교
			for (int i = 0; i < alpha.length; i++) {
				if (letter[index] == alpha[i][0] && index <= letter.length - alpha[i].length) {

					int same = 1;

					// 그 다음 비교
					for (int j = 1; j < alpha[i].length; j++) {
						if (letter[index + j] == alpha[i][j]) {
							same++;
						}
					}

					if (same == alpha[i].length) {
						index += alpha[i].length - 1;
						break;
					}

				}
			}

			index++;
			count++;
		}

		System.out.println(count);
	}
}