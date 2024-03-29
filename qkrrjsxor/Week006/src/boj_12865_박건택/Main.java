package boj_12865_박건택;

/*
 * 수업시간에 했던 조합을 이용해서 풀어보겠습니다.
 * 재귀함수를 이용한 조합, select 배열 이용
 * -> 시간 초과
 * 
 * DP를 공부해서 풀어보았습니다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String str;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		
		int N = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][limit+1];
		int[] weight = new int[N+1];
		int[] value = new int[N+1];
		
		for(int i = 1; i<= N; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i<= N; i++) {
			for(int j = 1; j<= limit; j++) {
				
				// 
				if(weight[i] > j) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]]+value[i]);
				}
				
			}
		}
		
		System.out.println(dp[N][limit]);
	}
}
