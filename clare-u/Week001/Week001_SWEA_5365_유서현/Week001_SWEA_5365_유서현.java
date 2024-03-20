import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);

		// 테스트 케이스의 수
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) { // T=2면 2번 돌림
			System.out.print("#" + t + " "); // 테케번호 출력

			// 5x15사이즈 char 배열 생성
			char[][] cArr = new char[5][15];

			for (int i = 0; i < 5; i++) { // 5번
				// 1줄 string으로 입력받기
				String temp = sc.next();
				// cArr에 넣어주기
				for (int j = 0; j < temp.length(); j++) {
					cArr[i][j] = temp.charAt(j);

				}
			}

			// cArr 출력 ( == 0, 즉 초기값이면 건너뛰게)
			for (int row = 0; row < 15; row++) {
				for (int col = 0; col < 5; col++) {
					if (cArr[col][row] == 0)
						continue;
					System.out.print(cArr[col][row]);
				}
			}
			System.out.println(); // 테케 끝날 때 개행문자
		}

	}
}