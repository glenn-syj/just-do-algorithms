package p2810;

/**
 * <풀이 방법>
 * 1. 기본 1 자리 옆 -> 1개
 * 2. 커플 2 자리 옆 -> 1개
 * 3. S는 1로 L은 0.5로 값 더해주기.
 */

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		String str = sc.next();
		double seats = 1;

		char[] arr = str.toCharArray();

		for (int i = 0; i < n; i++) {
			if (arr[i] == 'S') {
				seats += 1;
			} else {
				seats += 0.5;
			}

			if (seats == n) {
				break;
			}
		}

		System.out.println((int) seats);

	}
}