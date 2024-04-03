package boj_17404_박건택;
/*
 * 재귀 조합으로 푸니 시간  초과가 났다.
 * ㄷㅇ이가 DP라는 정보를 주어서 DP로 풀어보자
 * 
 * RGB 1을 먼저 풀어서 각 배열의 칸마다 DP 값을 대입해야 한다는 것을 알았다.
 * 
 * DP 배열을 2차원 배열루 int[N][3] 으로 만들고, 
 * DP 기저조건(?)인 0행에 대해서 값을 대입 하려고 했는데,
 * 
 * 0행에서 선택한 인덱스가 마지막 행에 영향을 미치므로, 한번에 다 넣지 않고 한 열씩 선택하며 대입하였다.
 * 0행에서 선택한 열 인덱스를 first 변수로 저장한 후, 나머지 값들은 전부 정수의 MAX_VALUE로 채워 넣는다.
 * 다음 행에서는 이전 행에서 DP의 최소값 + 현재 value 로 DP 배열 대입
 * 
 *  마지막 행이 되면, first인 경우 스킵 하고, 나머지 열에 대해서 마찬가지로 이전 행 DP의 최소값 + 현재 value 대입
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int sum;
	static int N;
	static int ans = Integer.MAX_VALUE;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N][3];
		dp = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int t = 0; t < 3; t++) {
				arr[i][t] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int first = 0; first < 3; first++) {
			
			for(int i =0 ; i < N ; i++) {
				for(int t= 0 ; t < 3; t++) {
					dp[i][t] = 987654321;
				}
			}
			
			int[] idx = new int[3];
			
			// 1. 첫행 열을 선택
			dp[0][first] = arr[0][first];
			
			//그 다음 행 부터 N-2 행 까지 DP 
			for(int i = 1; i < N-1; i++) {
				for(int t= 0 ; t<3; t++) {
					if(dp[i-1][(t+1)%3] > dp[i-1][(t+2)%3]) {
						idx[t] = (t+2)%3;
					}else {
						idx[t] = (t+1)%3;
					}
					dp[i][t] = dp[i-1][idx[t]] + arr[i][t];
				}
				
			}
			
			//마지막 행 DP
			for(int i = 0; i < 3; i++) {
				if(i == first || i == idx[i]) {
					continue;
				}else {
					if(dp[N-2][(i+1)%3] > dp[N-2][(i+2)%3]) {
						idx[i] = (i+2)%3;
					}else {
						idx[i] = (i+1)%3;
					}
					dp[N-1][i] = dp[N-2][idx[i]] + arr[N-1][i];
				}

				//마지막 행의 최소값 찾아서 ans에 갱신
				if(dp[N-1][i] < ans) {
					ans = dp[N-1][i];
				}
			}
		}
		
		System.out.println(ans);
		
	}
}
