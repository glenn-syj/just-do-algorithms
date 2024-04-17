package boj_13458_유서현;

import java.util.Scanner;

public class Main {
	// input
	// N
	// A1 A2 A3 ... AN
	// B C

	// output
	// 필요한 감독관의 최소 수

	// 1. B <= C면 C로만 배치
	// 인원수/C
	// if 나머지가 0이 아니라면 +1

	// 2. else
	// 각 방에 B 한명만 두고 나머지 인원을 C로..

	// 문제를 잘못 읽음 -> 총감독은 항상 최소 하나 있어야 하므로 1번을 삭제하고 2번만으로 로직 돌리면 됨
	// 틀렸다고 떠서 질문게시판을 확인해 보니 long 자료형을 이용해야 함
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] rooms = new int[n];
		for (int i = 0; i < n; i++) {
			rooms[i] = sc.nextInt();
		}
		int b = sc.nextInt();
		int c = sc.nextInt();

		long sum = 0; // 답 저장

		for (int i = 0; i < n; i++) {
			int temp = rooms[i] - b;
			if (temp < 0) { // 음수가 될 경우 0으로 해주는
				temp = 0;
			}

			sum += temp / c + 1; // +1은 총감독관
			if (temp % c != 0) {
				sum += 1;
			}
		}

		System.out.println(sum);

	}

}
