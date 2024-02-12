package swea_4408_자기방으로돌아가기;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/* S4408. 자기 방으로 돌아가기
 * 
 * 5분 / 10분
 * 
 * 1. 조건
 * 	1-1. i = [1, 200] 구간에 대해서 방 번호는 2n과 2n-1로 구성
 * 	1-2. 따라서 출발지~도착지에 대해
 * 		-> int[201] 이 있다고 가정할 때,
 * 		-> (출발지+1)/2 ~ (도착지+1)/2에 해당하는 index 구간을 못 씀
 * 	1-3. 방 이동에 걸리는 시간은 무조건 1단위시간
 * 	1-4. 해당 int 배열의 최대값이 바로 필요한 시간
 * 
 * 2. 풀이
 * 	2-1. 방과 방 사이의 통로를 element로 하는 배열 corridors
 * 	2-2. (start+1)/2 부터 (end+1)/2 까지 두 수를 포함하여 1씩 모두 더하기
 * 	2-3. 배열의 최대값 구하기
 */


public class Solution {
	
	static int T;
	static int N;
	static int start;
	static int end;
	static int[] corridors;
	static StringTokenizer st;
	static StringBuilder sb;
	static Scanner sc;
	
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/swea_4408_자기방으로돌아가기/input.txt");
//		sc = new Scanner(file);
		
		sc = new Scanner(System.in);
		
		sb = new StringBuilder();
		
		T = Integer.parseInt(sc.nextLine());
		for (int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(sc.nextLine());
			corridors = new int[201];
			for (int i=0; i<N; i++) {
				
				st = new StringTokenizer(sc.nextLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				
				getPassedCorridors(start, end);
				
			}
			
			sb.append('#').append(tc).append(' ').append(getMax(corridors));
			System.out.println(sb.toString());
			sb.setLength(0);
			
		}
		
	}
	
	public static void getPassedCorridors(int startIdx, int endIdx) {
		
		if (startIdx > endIdx) {
			int tmp = endIdx;
			endIdx = startIdx;
			startIdx = tmp;
		}
		
		for (int i=(startIdx+1)/2; i<=(endIdx+1)/2; i++) {
			corridors[i]++;
		}
		
	}
	
	public static int getMax(int[] arr) {
		
		int max = Integer.MIN_VALUE;
		
		for (int num: arr) {
			if (num > max) {
				max = num;
			}
		}
		
		return max;
		
	}
}