/*
 * 0. 매매가 입력 받기
 * 1. 뒤에서 부터 출발
 * 	1-1. max값 설정
 * 	1-2. 이익 계산
 */

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		// 0. 매매가 입력 받기
		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			long[] money = new long[N];
			
			for (int n = 0; n < N; n++) {
				money[n] = sc.nextLong();
			}
			
			long max = money[N - 1];
			long answer = 0;
			
			// 1. 뒤에서 부터 출발
			for (int i = N - 1; i >= 0; i--) {
				// 1-1. max값 설정
				if (max > money[i]) {
					// 1-2. 이익 계산
					answer += max - money[i];
				} else {
					max = money[i];
				}
			}
			System.out.printf("#%d %d%n", t, answer);
		}
	}
}