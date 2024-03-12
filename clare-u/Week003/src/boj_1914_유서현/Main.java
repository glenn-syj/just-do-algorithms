package boj_1914_유서현;

import java.util.Scanner;

public class Main {

	// boj_1914_하노이탑
	// 재귀를 이해하고 풀어야 하는 문제

	static int N;

	public static void main(String[] args) {
		// 입력받기
		// N (1<= N <= 100)
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		// 출력
		// 옮긴 횟수 K
		// 수행 과정 (N>20이면 출력 안해도됨) (N<=20이면 출력)

		System.out.println(hanoi(N)); // 옮긴 횟수 K

		// 수행 과정 출력은 어떻게 해야하지?
		// - 도저히 수행 과정을 어떻게 출력할지 상상이 안간다.
		// - 일단 점화식으로 수행횟수라도 출력해보자.
		// 실제 하노이탑은 영재반급으로 잘하는데 알고리즘문제는 왜이렇게 어렵지


	}

	static int hanoi(int block) {// 하노이탑 메서드를 만들어보자
		// 현재위치 (시작점) 1
		// 거쳐가는곳 2
		// 목적지 3

		if (block == 0) {
			return block; // 기저조건?
			// 이메서드 왜 작동돼지 기저조건 어떻게거는지 모르겠는데
		}

		// 점화식은 hanoi(1) = 1
		// hanoi(2) = 3
		// hanoi(3) = 7
		// hanoi(4) = 15
		// 이므로 전에거*2 -1 = 지금거
		int ans = (hanoi(block - 1) * 2) + 1;
		return ans;
	} // hanoi()

}