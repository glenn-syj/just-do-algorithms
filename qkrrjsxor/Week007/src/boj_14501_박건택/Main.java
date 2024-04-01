package boj_14501_박건택;
/*
 * DP를 공부하고 풀어보자
 * 
 * N+1 날짜(퇴사날)에 얻는 이익은.. 
 * 
 * 아니다 백트래킹으로 풀어보자!
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, sum, temp;
	static int[] T;
	static int[] P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		sum = 0;
		temp = 0;

		T = new int[N+1]; // 상담에 걸리는 시간
		P = new int[N+1]; // 상담에 얻는 돈

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		backTracking(1, 0);
		System.out.println(sum);

	}

	private static void backTracking(int day, int interval) {
		//base case
		//현재 날짜가 퇴사일 이상이면 return
		if(day > N) {
			sum = Math.max(sum, temp);
			return;
		}
		
		sum = Math.max(sum, temp);
		for(int i = day; i < day+T[day] && i <= N; i++) {
			if(i + T[i] <= N+1) {		//현재 날짜 + 상담 일수가 퇴사일을 넘기지 않으면 더 재귀
				temp += P[i];
//				System.out.println("day :" + i + " temp : " + temp + " Sum : " + sum + " interval : " + T[i]);
				backTracking(i+T[i], T[i]);
				temp -= P[i];
			}
		}
	}



}
