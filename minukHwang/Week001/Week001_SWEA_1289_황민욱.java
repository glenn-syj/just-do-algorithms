/*
 * 원재의 메모리 복구하기
 */

import java.util.Scanner;

/*
 * 문제 풀이
 * 1. 맨 앞부터 생각하기.
 * 2. 맨 앞에서부터 결과에 맞게 바꾸는 과정을 반복한다.
 * 	2-1. 100을 예시로 맨 앞에 1이니 맨 앞부터 맨뒤까지 다른 부호로 변환
 * 	2-2. 111로 변환이 되면 그 다음 인덱스로 넘어간다.
 * 		 (111 -> 100 -> 끝)
 * 3. 횟수를 카운트하여 출력한다.
 */

public class Week001_SWEA_1289_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		// 1. 테스트 케이스만큼 반복
		for (int t = 1; t <= T; t++) {
			// 2. 원래 메모리 리스트 만들기
			String[] str = sc.next().split("");

			int[] memory = new int[str.length];

			for (int i = 0; i < str.length; i++) {
				memory[i] = Integer.parseInt(str[i]);
			}

			// 3. 초기 메모리 리스트 만들기
			int[] init = new int[str.length];

			// 4. 앞에서부터 순차적으로 탐색하며 초기 메모리 변환하기
			int count = 0;

			for (int i = 0; i < memory.length; i++) {
				if (init[i] == memory[i]) {
					continue;
				}

				init = exchangeMemory(init, i, memory[i]);
				count++;
			}

			// 5. 출력
			System.out.printf("#%d %d\n", t, count);
		}
	}

	// 메모리 변환 함수
	public static int[] exchangeMemory(int[] memory, int start, int num) {
		// 특정 인덱스 값을 받으면 그 이후는 모두 동일한 값으로 바꿔주는 메소드
		for (int i = start; i < memory.length; i++) {
			memory[i] = num;
		}
		return memory;
	}
}
