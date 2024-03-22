package boj_2096_내려가기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/* B2096. 내려가기
 * 
 * 1. 조건
 * 	1-1. N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있음
 * 	1-2. idx 0은 1,2를, 1은 0,1,2를, 2는 1,2를 다음에 선택 가능
 * 	1-3. 숫자표가 주어져있을 때
 * 		-> 얻을 수 있는 최대 점수, 최소 점수를 구하기
 * 2. 풀이
 * 	2-1. 최대 점수를 얻는 로직과, 최소 점수를 얻는 로직을 따로 구현
 * 	2-2. 최대 점수 구하기
 * 		- 현재 줄이 아니라, 다음 줄을 기준으로 생각하면 됨
 * 		- 다음 줄의 0번을 최대로 하려면: 이전 0번과 1번 중 더 큰 수를 선택
 * 		- 다음 줄의 1번을 최대로 하려면: 이전 0번 1번 2번 중 더 큰 수를 선택
 * 		- 다음 줄의 2번을 최대로 하려면: 이전 1번 2번 중 더 큰 수를 선택
 * 		-> 이 모든 것은 n-2번째 선택이 n번째 선택에 영향을 미치지 않기 때문 (전체 고려 시)
 * 		=> 이러한 방식으로 마지막까지 0번~2번을 저장하는 int[3] 생성 후 한 줄 지날 때마다 할당
 * 			(최소는 반대)	
 * 2-3. edge case
 * 		- N==1: 최대 점수와 최대 점수를 바로 찾아서 리턴 (조건화)
 * 		- 이후: 위 로직 대로 구현
 * 		=> 다시 생각해보니 N==1일때도 result에 넣어두면 상관 없을듯
 */


public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;
	
	static int N;
	static int[] maxResult = new int[3];
	static int[] minResult = new int[3];
	static int idx0, idx1, idx2;
	static int max0, max1, max2, min0, min1, min2 ;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		idx0 = Integer.parseInt(st.nextToken());
		idx1 = Integer.parseInt(st.nextToken());
		idx2 = Integer.parseInt(st.nextToken());
		
		maxResult[0]=idx0;
		maxResult[1]=idx1;
		maxResult[2]=idx2;
		
		minResult[0]=idx0;
		minResult[1]=idx1;
		minResult[2]=idx2;
		
		for (int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			idx0 = Integer.parseInt(st.nextToken());
			idx1 = Integer.parseInt(st.nextToken());
			idx2 = Integer.parseInt(st.nextToken());
			
			max0 = Math.max(maxResult[0], maxResult[1]) + idx0;
			max1 = Math.max( Math.max(maxResult[0], maxResult[1]), maxResult[2]) + idx1;
			max2 = Math.max(maxResult[1], maxResult[2]) + idx2;
		
			maxResult[0] = max0;
			maxResult[1] = max1;
			maxResult[2] = max2;
			
			min0 = Math.min(minResult[0], minResult[1]) + idx0;
			min1 = Math.min( Math.min(minResult[0], minResult[1]), minResult[2]) + idx1;
			min2 = Math.min(minResult[1], minResult[2]) + idx2;

			minResult[0] = min0;
			minResult[1] = min1;
			minResult[2] = min2;
			
		}
		
		int maxVal = Integer.MIN_VALUE;
		int minVal = Integer.MAX_VALUE;
		
		for (int i=0; i<3; i++) {
			if (maxResult[i] > maxVal) {
				maxVal = maxResult[i];
			}
			if (minResult[i] < minVal) {
				minVal = minResult[i];
			}
		}
		
		sb.append(maxVal).append(' ').append(minVal);
		bw.write(sb.toString());
		bw.close();
		
	}
}