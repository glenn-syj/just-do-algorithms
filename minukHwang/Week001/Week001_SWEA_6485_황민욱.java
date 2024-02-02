/*
 * 삼성시의 버스노선
 */

import java.util.Scanner;

/*
 * 문제 풀이
 * 1. 버스 노선을 입력 받아서 범위를 설정한다.
 * 2. 마을의 정류장 정보를 입력받고, 해당 정류장의 빈도수를 측정하기 위해서 최소~최대(정류장 번호) 범위의 리스트를 생성한다.
 * 3. 버스 노선이 지나는 정류장 번호를 빈도수 배열의 인덱스 번호에 맞게 더해주며 계산한다.
 * 4. 출력한다.
 * 
 * (주의)
*  5000개의 버스 정류장이 있다고 언급되어있다. (문제 잘 읽자...)
* 문제에서 c가 서로 다르다고 하지 않았다.
 * c가 순서대로 배열되어 있다고 하지 않았다.
 * 버스가 지나가는 노선에만 정류장이 있다고 하지도 않았다.
 * => 따라서 버스 정류장은 문제의 조건 중 하나인 a,b,c의 최대 범위인 1~5000으로 생각하고 빈도수를 측정해야한다.
 *
 */

public class Week001_SWEA_6485_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt();

			// 1. 버스 노선 입력받기
			int[][] routes = new int[n][2];

			// *주의 사항 참고*
			// 강조를 위해서 변수로 만들었다.
			int maxNum = 5001;

			for (int i = 0; i < n; i++) {
				routes[i][0] = sc.nextInt();
				routes[i][1] = sc.nextInt();
			}

			// 2. 정류장 번호 입력 받기.
			int p = sc.nextInt();

			// 해당 정류장 번호를 담을 리스트와 최대로 큰 정류장 번호를 담을 변수 선언
			int[] cNums = new int[p];

			for (int i = 0; i < p; i++) {
				cNums[i] = sc.nextInt();
			}

			// 3. 정류장 번호 빈도수 입력을 위한 배열 생성
			int[] busStations = new int[maxNum];

			// 4. 노선 탐색하면서 정류장 번호 빈도수 측정
			for (int i = 0; i < n; i++) {
				for (int j = routes[i][0]; j <= routes[i][1]; j++) {
					busStations[j] += 1;
				}
			}

			// 5. 출력
			System.out.printf("#%d", t);
			for (int i = 0; i < p; i++) {
				System.out.print(" " + busStations[cNums[i]]);
			}
			System.out.println();
		}

	}
}