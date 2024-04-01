package boj_12851_박건택;
/*
 * DP를 이용해서 풀어보자! 라는 직감이 들었습니다.
 * 
 * 1. 수빈의 위치를 X, 동생의 위치를 Y 라고하면
 * 2. DP 배열을 만들어보자
 * Y가 짝수인 경우
 *  DP(Y) = MIN(DP(Y-1), DP(Y+1), DP(Y/2)) + 1
 * 
 * Y가 홀수인 경우
 * 	DP(Y) = MIN(DP(Y-1), DP(Y+1), DP(Y-1/2), DP(Y+1/2)) + 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] location = new int[11];
		int[] dp = new int[11];

		st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		dp[x] = 0;
		dp[x + 1] = 1;
		dp[x * 2] = 1;
		for (int i = x - 1; i >= 0; i--) {
			dp[i] = x-i;
		}
		System.out.println(Arrays.toString(dp));

		int min;
		for(int i = x+1; i < 10-1; i++) {
			if(i%2==0) {
				if(dp[i-1] > dp[i+1]) {
					min = dp[i+1];
				}else {
					min = dp[i-1];
				}
				dp[i] = Math.min(min, dp[i/2]) +1;
			}
			else if(i%2==1){
				dp[i] = Math.min(dp[i-1], dp[i+1])+1;
			}
		
		}
		dp[10] = Math.min(dp[9], dp[5]) +1;		
		
		System.out.println(Arrays.toString(dp));
	}
}
