/*
 * 4789. 성공적인 공연 기획
 */

import java.util.Scanner;

/*
 * 풀이 방법
 * 1. 앞에서부터 순차 탐색.
 * 2. 본인의 Index와 본인전까지의 합이 같은지 판별하면서 탐색한다.
 * 	2-1. 만약 같지 않다면, 부족한 수만큼 사람을 고용해야하는 것이기 때문에 해당 차이를 더해가며 계산한다.
 * 3. 최종적으로 총 부족한 인원값을 출력한다.
 */

public class Week001_SWEA_4789_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 1.테스트 케이스만큼 반복하기.
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			// 2. 사람들의 정보값 받아오기.
			String[] info = sc.next().split("");
			
			// 3. 형변환 해주기. (String -> int)
			int[] infoNum = new int[info.length];

			for (int i = 0; i < info.length; i++) {
				infoNum[i] = Integer.parseInt(info[i]);
			}

			// 4. 탐색하며 부족한 인원 구하기.
			int sum = 0;
			int count = 0;

			for (int i = 0; i < info.length; i++) {
				if (i > sum) {
					count += i - sum;
					infoNum[i] += i - sum;
					// 여기서 배열의 해당 인덱스의 값을 차이만큼으로 바꿔줘야한다.
					// 필요 인원을 계산하고 그 다음 순번에서는 계산된 인원을 제외하고 추가해줘야하기 때문.
				}
				sum += infoNum[i];
			}

			System.out.printf("#%d %d\n", t, count);
		}

	}
}