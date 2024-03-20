package swea_4408_유서현;

import java.util.Scanner;

public class Solution {
	// swea_4408_자기방으로돌아가기

//	1. 복도 사이즈가 200까지로 고정됨. 그 사이즈 + 1의 count배열을만든다 (201)
//	2. 각 학생 방번호를 복도번호로 수정한다
//	    - 홀수 방번호는  +1  해주고 /2
//	    - 짝수 방번호는  바로 /2
//	3. 이동해야하는 복도번호를 학생별로, 반복문으로 카운트한다
//	    - 반복문 시작조건 = 원래있는방번호 -> 가야할방번호에서 끝나게
//	    - count++ 해준다 학생별로
//	4. 모든학생들을 각각 반복문 돌렸으면, 카운트배열에 카운트된 숫자 중 max값을 찾는다
//	5. 그 max값이 이동에 필요한 단위시간이니 출력한다 (인덱스가아니라 값자체임 주의)

	public static void main(String[] args) {

		// 인풋받기
		// 모두 구분된 수로 주어지므로 nextInt로 받는다
		Scanner sc = new Scanner(System.in);
		// T : 테스트케이스 수
		int T = sc.nextInt();
		// T번 반복
		for (int t = 1; t <= T; t++) {

			// 카운트배열 생성
			int[] countArr = new int[201]; // 각 테케마다 초기화되도록 t 반복문 안으로 넣기

			int N = sc.nextInt(); // N : 학생수

			// 현재 방, 돌아갈 방
			// 입력받을 변수를 N*2사이즈 배열에 저장
			int[] roomArr = new int[N * 2];

			// 반복문으로 입력받으면서 방번호를 복도번호로 수정
			for (int i = 0; i < roomArr.length; i++) {
				int temp = sc.nextInt();

				if (temp % 2 == 1) { // 홀수 방번호는 -> +1 해주고 /2
					roomArr[i] = (temp + 1) / 2;
				} else { // 짝수 방번호는 -> 바로 /2
					roomArr[i] = temp / 2;
				}
			}

			// [5, 10, 15, 20, 25, 30, 35, 40]
			// 0 1 2 3 4 5 6 7

			// 3. 이동해야하는 복도번호를 학생별로, 반복문으로 카운트한다

			// count++ 해준다 학생별로
			for (int n = 0; n < N * 2; n += 2) { // 1~N명 학생수만큼
				int J;
				if (roomArr[n + 1] > roomArr[n]) {
					J = roomArr[n + 1] - roomArr[n];
//					System.out.print("멀쩡 ");
//					System.out.println(J);
				} else {
					J = roomArr[n] - roomArr[n + 1];
//					System.out.println(J);
				}
				// ex) 5, 10 으로 이동하는 학생이면 J는 10-5=5
				// 10-> 5처럼 이동하는 경우도 고려해서 절대값 처리 해주기 위해 Math.abs() 사용
				// 하려 했는데 런타임 에러가 뜸;;
				// 걍 바보식으로 if else로 양수만 나오게 수정 -> 얘도 런타임 에러
				// -> 혹시나해서 테케 거꾸로해서 돌려보니 테케도 틀리고있었음

				for (int j = 0; j <= J; j++) { // 이 부분을 최종적으로 수정함
					for (int count = 1; count <= countArr.length; count++) { // 카운트배열 전체 반복문
						// 원래있는방번호 -> 가야할방번호에서 끝나게
						// 0~1, 2~3, 4~5, 6~7 범위

						int temp; // 얘도 수정해줘야 올바른 범위만큼 반복함
						if (roomArr[n + 1] > roomArr[n]) {
							temp = n;
						} else {
							temp = n + 1;
						}

						if (roomArr[temp] + j == count) {
							// 5+0, 5+1, 5+2, 5+3, 5+4, 5+5 까지 총 5회반복
							countArr[count]++;
						}
					}
				}
			}

//			System.out.println(Arrays.toString(countArr)); // 디버깅용 출력

			// 4. 카운트배열에 카운트된 숫자 중 max값을 찾는다
			int max = Integer.MIN_VALUE; // max값
			for (int i = 0; i < countArr.length; i++) {
				if (countArr[i] > max) {
					max = countArr[i];
				}
			}

			// 5. 그 max값이 이동에 필요한 단위시간이니 출력
			System.out.println("#" + t + " " + max);

		} // T for문

		sc.close();

	} // main

}