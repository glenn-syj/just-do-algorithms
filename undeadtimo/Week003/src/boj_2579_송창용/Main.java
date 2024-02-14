package boj_2579_송창용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 기능은 제대로 구현한 것 같은데, 너무 초반에 틀린다.
// 입력 개수가 하나이거나 둘인 경우를 생각해서 조건을 추가해주자.

// StringTokenizer
// BufferedReader, InputStreamReader
// BufferedWriter, OutputStreamWriter
// 이들을 사용해보자.
// 버퍼드 리더, 버퍼드 라이터의 경우, swea에서 또는 알고리즘 시험 때 에러가 일어날 가능성이 있지만,
// 해당 방식을 이해하고 쓰지 않는 것과 이해하지 못했지만 일단 쓰지 않는 것은 다르기에,
// 우선 문제들을 풀면서 버퍼드 리더, 버퍼드 라이터에 익숙해지도록 하겠다.

public class Main {

	static StringTokenizer st;
	static Integer[] dp;
	static int[] stairScore;
	static int stairCnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		stairCnt = Integer.parseInt(st.nextToken());

		stairScore = new int[stairCnt + 1];

		dp = new Integer[stairCnt + 1];

		for (int i = 1; i <= stairCnt; i++) {
			st = new StringTokenizer(br.readLine());
			stairScore[i] = Integer.parseInt(st.nextToken());
		}
		
		if(stairCnt == 1) {
			System.out.println(stairScore[1]);
			return;
		}else if(stairCnt == 2) {
			System.out.println(stairScore[1] + stairScore[2]);
			return;
		}
		

		// bottomUp 확인
		int ans = bottomUp();
		System.out.println(ans);

		// topDown 확인
//		topDown();
//		int ans = recur(stairCnt);
//		System.out.println(ans);

	}

	public static int bottomUp() {
		dp[0] = 0;
		dp[1] = stairScore[1];
		if (stairCnt >= 2) {
			dp[2] = stairScore[1] + stairScore[2];
			dp[3] = Math.max(stairScore[1], stairScore[2]) + stairScore[3];
		}

		for (int i = 4; i <= stairCnt; i++) {

			if (i < stairCnt) {
				dp[i] = Math.max(Math.max(dp[i - 2] + stairScore[i], dp[i - 3] + stairScore[i - 1] + stairScore[i]),
						dp[i - 1]);
			} else if (i == stairCnt)
			// 마지막 계단은 밟도록 하는 조건
			{
				dp[i] = Math.max(dp[i - 2] + stairScore[i], dp[i - 3] + stairScore[i - 1] + stairScore[i]);
			}

		}

		return dp[stairCnt];
	}

	public static int topDown() {
		dp[0] = 0;
		dp[1] = stairScore[1];

		if (stairCnt > 2) {
			dp[2] = stairScore[1] + stairScore[2];
		}

		return 0;
	}

	public static int recur(int stairCnt) {

		if (dp[stairCnt] == null && stairCnt < dp.length - 1) {
			dp[stairCnt] = Math.max(
					Math.max(recur(stairCnt - 2) + stairScore[stairCnt],
							recur(stairCnt - 3) + stairScore[stairCnt - 1] + stairScore[stairCnt]),
					recur(stairCnt - 1));
		} else if (dp[stairCnt] == null && stairCnt == dp.length - 1) {
			// 마지막 계단을 밟도록 하는 조건
			dp[stairCnt] = Math.max(recur(stairCnt - 2) + stairScore[stairCnt],
					recur(stairCnt - 3) + stairScore[stairCnt - 1] + stairScore[stairCnt]);
		}
		return dp[stairCnt];

	}
}
