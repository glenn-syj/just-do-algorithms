package boj_2580_유서현;

import java.util.Scanner;

public class Main {

	static int[][] board;
	static boolean[] filled;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		board = new int[9][9]; // 스도쿠판 생성

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		// 빈칸(0) 에서
		// 행 / 열 / 사각형 으로 탐색해서
		// 그 중 하나라도 빈칸 빼고 다른 칸이 다 차있으면
		// 알맞은 수로 채워야 함

		// 만약 2칸 이상 비어 있으면
		// 일단 넘어가고 나중에 다시 확인해야함 // 이 맞나

	} // main

	// 행 우선탐색

	static void sleep(int r, int c) {

		for (int i = 0; i < 9; i++) {
			filled = new boolean[10]; // 1~9인덱스 이용
			// 초기화를 어디서 해야하지?
			for (int j = 0; j < 9; j++) {
				filled[board[i][j]] = true;
			}
		}
	}
}