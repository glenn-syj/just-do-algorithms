package boj_1256_사전;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/* B1256. 사전
 * 
 * 1. 조건
 * 	1-1. N, M이 최대 100이므로 바로 조합 식 이용 불가
 * 		-> 조합에 대해서 dp를 이용해야 함
 * 	1-2. 사전
 * 		ptr = 0으로 초기화
 * 		(1) K가 ptr + N+M_C_N 보다 작으면 ptr 유지
 * 			sb.append('a')
 * 		(2) K가 ~ 보다 크면 ptr = ptr + N+M_C_N
 * 			sb.append('z')		
 * 	종료 조건: N or M이 0이 되면 종료
 * 2. 풀이
 */

public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N, M, K;
	static BigInteger[][] dp;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		dp = new BigInteger[N+M+1][];
		// K가 범위를 넘어서는지 확인하기 위해서, 우선적으로 dp를 작성해야 함
		for (int i=1; i<=N+M; i++) {
			dp[i] = new BigInteger[i+1];
			
			
			dp[i][0] = BigDecimal.valueOf(1).toBigInteger();
			dp[i][i] = BigDecimal.valueOf(1).toBigInteger();
		}
		
		// overflow가 일어나고 있었군...
		for (int left=2; left<=N+M; left++) {
			for (int right=1; right<left; right++) {
				dp[left][right] = dp[left-1][right-1].add(dp[left-1][right]);
			}
		}
		
		// 여기에서 검사
		
		BigInteger BIK = BigDecimal.valueOf(K).toBigInteger();
		
		if (dp[N+M][N].compareTo(BIK) == -1) {
			sb.append(-1);
			bw.write(sb.toString());
			bw.close();
			return;
		}
		
		BigInteger checker;
		
		
		while (N != 0 && M != 0) {
			
			checker = dp[N+M][N].multiply(BigDecimal.valueOf(N).toBigInteger());
			checker = checker.divide(BigDecimal.valueOf(N+M).toBigInteger());
			if (BIK.compareTo(checker) <= 0) {
				sb.append('a');
				N--;
			} else {
				BIK = BIK.subtract(checker);
				sb.append('z');
				M--;
			}
				
		}
		
		while (N > 0) {
			sb.append('a');
			N--;
		}
		
		while (M > 0) {
			sb.append('z');
			M--;
		}
				
		bw.write(sb.toString());
		bw.close();
		
	}
	
}