package boj_14501_유서현;

import java.util.Scanner;

public class Main {
	// dfs 하되, 상담을 아예 진행하지 않는 날도 있다고 가정하고 완탐해야함

	static int[] T, P;
	static int start, N, result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		T = new int[N];
		P = new int[N];

		for (int i = 0; i < N; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}

		for (int i = 0; i < N; i++) {
			start = i;
			dfs(0, 0);
		}

		System.out.print(result);

	} // main

	// peek
	static void dfs(int num, int num2) {

		// num2의 합이 n을 초과 할 경우 리턴
		if (num2 > N) {
			return;
		}
		// num2의 합이 n일인 경우 포함시킨다.
		if (num2 == N) {
			result = Math.max(result, num);
		}

		// j = n일의 기간 중, 중간 부터 시작일이 되는 경우, 지난 일 수 를 받아온다
		int j = start;
		for (int i = start + num2; i < N; i++, j++) {
			// 다음 날로 넘어가는 경우 j+1일을 포함시킨다
			dfs(num + P[i], num2 + T[i] + j);
			result = Math.max(result, num);
		}

	}

}
