package boj_9663_N_Queen;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static int[] arr;
	public static int N, count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 체스판의 크기
		arr = new int[N]; // 퀸이 몇행인지 위치 표시할 배열 생성

		count = 0;
		findQueen(0);
		System.out.println(count);

	} // main

	public static void findQueen(int depth) {
		if (depth == N) {
			count++;
//			System.out.println(Arrays.toString(arr));
			return;
		}

		for (int i = 0; i < N; i++) {
			arr[depth] = i;
			
			// 해당 depth에서 i에 놓을 수 있는지 검사
			if (can(depth)) {
				findQueen(depth + 1);
			}
		}
	}

	public static boolean can(int col) {
		for (int i = 0; i < col; i++) {

			// 같은 행일 경우
			if (arr[col] == arr[i]) {
				return false;
			}

			// 대각선인 경우
			// 열의 차와 행의 차가 같을 경우가 대각선인 경우
			else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
				return false;
			}

		}
		return true;
	}

}
