package boj_14501_퇴사;

import java.io.BufferedReader;

/* B14501. 퇴사
 * 
 * 1. 조건
 * 	1-1. 각각의 상담은 완료하는데 걸리는 기간과 받을 수 있는 금액이 있음
 * 	1-2. N+1일 째에는 회사에 없기에, 현재 지난 날짜 + 상담에 걸리는 시간이 N보다 큰지 확인
 * 	1-3. 최대 수익 구하기
 * 
 * 2. 풀이
 * 	2-1. dp 이용 -> 어떻게?
 * 	2-2. 각 날짜에 대해서 하는 경우와, 하지 않는 경우에 대해 고려해보자
 * 		-> 상담이 끝난 날짜에 대해 하는 경우/하지 않는 경우의 값이 이전보다 크면 업데이트
 * 	2-3. 해당 날짜에 상담을 하지 않으면, +1 된 날짜에 대해서 이전의 하지 않음 값을 기본으로 가져옴
 * 
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int board[][];
	static int dp[][];
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][2];
		dp = new int[N+1][2];
		
		for (int i=1; i<=N; i++) {
			// board 초기화 [0]은 걸리는 날 [1]은 이익
			st = new StringTokenizer(br.readLine());
			board[i][0] = Integer.parseInt(st.nextToken());
			board[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 기본적으로 각 날짜에 대한 순회: 선택하지 않는 경우를 위해
		// 재귀적으로 도착하는 날짜에 대해서 우선적으로 검사
		// [0]은 상담하는 경우, [1]은 상담하지 않는 경우
		
		counsel(0, 1);
		for (int i=1; i<=N; i++) {
			if (dp[i][1] < dp[i-1][1]) {
				dp[i][1] = dp[i-1][1];
			}
			if (dp[i][1] + board[i][1] >= dp[i][0]
					&& i + board[i][0] <= N+1) {
				dp[i][0] = dp[i][1] + board[i][1];
			} else {
				dp[i][0] = dp[i-1][1];
			}
			counsel(i, i+board[i][0]);
			
		}
//		
//		for (int[] values: dp) {
//			System.out.println(Arrays.toString(values));
//		}

		int maxVal = Integer.MIN_VALUE;
		
		for (int i=1; i<=N; i++) {
			if (Math.max(dp[i][0], dp[i][1]) > maxVal) {
				maxVal = Math.max(dp[i][0], dp[i][1]);
			}
		}
		
		sb.append(maxVal);
		bw.write(sb.toString());
		bw.close();
		
	}
	
	
	public static void counsel(int before, int current) {
		if (current > N) {
			return;
		}
		
		int passed = dp[before][0];
		
		if (passed > dp[current][1]) {
			dp[current][1] = passed;
		}
		
		int checked = dp[current][1] + board[current][1];
		
		if (checked >= dp[current][0]
				&& current + board[current][0] <= N+1) {
			dp[current][0] = checked;
		} else {
			
			dp[current][0] = dp[current][1];
		}
		
		counsel(current, current+board[current][0]);
		
	}
}