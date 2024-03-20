package prgm_2023k미_황민욱;

import java.util.*;

//제한 조건을 줘야할 것 같다. (백트래킹)
/*
*(0,0)을 왼쪽 상단으로 보정할 것이기에 다음과 같이 표현*

예시1)
. . E
. . .
S . . 

최소한 이동하는데 필요한 거리 4

S -> (2, 0)
E -> (0, 2)

2 - 0 , 0 - 2 의 합의 절댓값


예시2)
S .
. .
. E 

최소한 이동하는데 필요한 거리 3

S -> (0, 0)
E -> (2, 1)

2 - 0, 1 - 0 합의 절댓값
*/

class Solution {
	static int rLength;
	static int cLength;
	static int startR;
	static int startC;
	static int endR;
	static int endC;
	static int length;

	static int[] deltaR = { 1, 0, 0, -1 };
	static int[] deltaC = { 0, -1, 1, 0 };
	static String[] dir = { "d", "l", "r", "u" };

	static StringBuilder sb;
	static String[] word;
	static List<String> results;

	static String answer;

	public String solution(int n, int m, int x, int y, int r, int c, int k) {
		// 활용하기 쉽게 초기화
		rLength = n;
		cLength = m;
		startR = x - 1;
		startC = y - 1;
		endR = r - 1;
		endC = c - 1;
		length = k;

		// result 저장할 배열과 리스트
		word = new String[length];
		results = new ArrayList<>();

		// dfs 실행 준비
		dfs(startR, startC, 0, length);
		answer = results.size() == 0 ? "impossible" : results.get(0);
		return answer;
	}

	public static void dfs(int r, int c, int depth, int left) {
		// 기저 조건
		if (left == 0) {
			sb = new StringBuilder();
			for (int i = 0; i < length; i++) {
				sb.append(word[i]);
			}
			String str = sb.toString();
			results.add(str);
			return;
		}

		// 재귀 조건
		for (int i = 0; i < 4; i++) {
			int nextR = r + deltaR[i];
			int nextC = c + deltaC[i];

			if (isNotOutBound(nextR, nextC) && isAvailable(nextR, nextC, left - 1)) {
				word[depth] = dir[i];
				dfs(nextR, nextC, depth + 1, left - 1);
			}
		}
	}

	public static boolean isAvailable(int r, int c, int left) {
		int minLength = Math.abs(endR - r) + Math.abs(endC - c);
		return minLength <= left && ((minLength % 2 == 0 && left % 2 == 0) || (minLength % 2 == 1 && left % 2 == 1));
	}

	public static boolean isNotOutBound(int r, int c) {
		return r >= 0 && r < rLength && c >= 0 && c < cLength;
	}
}