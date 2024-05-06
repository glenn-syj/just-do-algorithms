package boj_1107_리모컨;

import java.util.Scanner;

public class Main {

	static int N, M;
	static boolean[] broken;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		broken = new boolean[10]; // 고장난 버튼 확인용도
		for (int i = 0; i < M; i++) {
			broken[sc.nextInt()] = true;
		}

		int curr = 100;

		if (N == curr) {
			System.out.println(0);
		} else {
			int result = Math.abs(N - curr);
			// 냅다 0부터 999999까지 완탐
			for (int i = 0; i <= 999999; i++) {
				String num = String.valueOf(i); // 숫자를 문자열 num에 저장

				boolean isBreak = false;
				for (int j = 0; j < num.length(); j++) {
					if (broken[num.charAt(j) - '0']) {
						// 고장난 버튼을 눌러야 하면 멈춘다.
						isBreak = true;
						break;
					}
				}
				if (!isBreak) {
					// 버튼을 누르는 횟수 중 가장 적은 횟수를 result에 담는다.
					int min = Math.abs(N - i) + num.length(); // 이 부분 peek
					result = Math.min(min, result);
				}
			}
			System.out.println(result);
		}

	}

}
