package swea_1220_황민욱;

/*
 * 풀이 방법
 * 1. 2차원 배열을 통해서 자석의 정보를 받아온다.
 * 2. 열 우선 탐색을 통해서 교착이 있는지 확인한다.
 * 	- 패턴 확인 => 만약 탐색을 하는 중 1(N극)이 확인되면, 그 다음 2(S극)이 존재할 때 교착이 일어난다.
 * 	- 무조건 1이 나오고 그 다음 2가 나와야 교착이 일어나기 때문에 이에 대한 지표를 위해서 Boolean 활용.
 * 	- [1] 확인 -> 교착 가능성 true -> [2] 확인 -> count++(교착) 교착이 이미 되었기 때문에 교착 가능성 false.
 * 	- 이러한 사이클로 진행하면 1이 두번 나오고 2가 나오는 경우, 그리고 [1,2,1,2]와 같이 교착이 2번 나오는 경우를 모두 커버 가능.
 * 3. 카운트 출력.
 */

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int t = 1; t <= 10; t++) {
			// 입력 받기.
			int n = sc.nextInt();
			int[][] map = new int[n][n];
			
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
			// 교착 확인
			int count = 0;

			for (int c = 0; c < n; c++) {
				boolean isAvailable = false;
				
				for (int r = 0; r < n; r++) {
					// 만약 N극이라면 교착 가능성 true.
					if (map[r][c] == 1) {
						isAvailable = true;
					}

					// 교착 가능성이 있을 때 (1이 나오고 2가 나올 때) S극이라면 교착!
					// count 추가해주고, 이미 교착이기 때문에 가능성은 다시 false.s
					if (map[r][c] == 2 && isAvailable == true) {
						count++;
						isAvailable = false;
					}
				}
			}
			
			System.out.printf("#%d %d\n", t, count);
		}
		
		sc.close();
	}
}
