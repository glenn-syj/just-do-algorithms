package boj_1806_박건택;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 일단 부분합 전체를 길이 1인	 것 부터 완전 탐색하며 해보자. 시간 오버 될것 같긴 하지만
 * -> 결과 : 시간 초과~
 * 
 * 2. 질문답변에서 누적합을 이용해보라는 답변을 보아서 누적합으로 시도해봤습니다.
 * -> 누적합으로 투 포인터를 이용해서 0부터 탐색 하니 또 시간초과!!
 * 
 * 3. 그렇다면 이진 탐색처럼 해본다면??
 * -> 통과...
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// N : 문자열 길이, S : 목표한 합 값
		String str = br.readLine();
		st = new StringTokenizer(str);
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int min = Integer.MAX_VALUE;

		// 숫자들을 배열에 넣어주기
		str = br.readLine();
		st = new StringTokenizer(str);

		int[] numArr = new int[N + 1];
		int i = 1;

		while (st.hasMoreTokens()) {
			numArr[i] = Integer.parseInt(st.nextToken());

			numArr[i] = numArr[i] + numArr[i - 1];
			i++;
		}

		int start = 0;
		int end;
		int temp1 = N;
		int temp2 = start;
		
		if(numArr[N] - numArr[N-1] >= S) {
			System.out.println(1);
			System.exit(0);
		}
		for (; start < N; start++) {

			end = (start + N) / 2;
			temp1 = N+1;
			temp2 = start;
			
			while (end != temp1 && end != temp2) {

				int sum = numArr[end] - numArr[start];
				System.out.println("start : " + start + " end : " + end + " temp1 : " + temp1 + " temp 2 : " + temp2);
				if (sum < S) {

					temp2 = end;
					end = (temp2 + temp1) / 2;
					
				} else {

					if ((end - start) < min) {
						min = end - start;
					}
					
					temp1 = end;
					end = (temp2 + temp1) / 2;

				}

			}
		}

		// 출력
		if (min == Integer.MAX_VALUE) {

			System.out.println(0);
		} else {

			System.out.println(min);
		}
	}

}
