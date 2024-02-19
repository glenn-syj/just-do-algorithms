package swea_1221_GNS;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/* S1221. GNS
 * 
 * 1. 조건
 * 	1-1. ZRO, ONE, ...
 * 		-> 0~9값을 나타내는 문자열을 작은 수부터 차례로 정렬
 * 	1-2. 테스트 케이스의 번호가 주어지고, 공백 문자후 텍스트의 길이
 * 		-> 다음 줄 부터 테스트케이스
 * 		-> 출력 역시 테스트 케이스 출력하고, 개행 문자 후 정렬 문자열 출력
 * 2. 풀이
 * 	2-1. HashMap를 countingArr처럼 이용
 * 		-> String[] -> { "ZRO", "ONE", "TWO", ..., "NIN" };
 * 		-> key를 "ZRO", "ONE" 등으로 이용하고
 * 		-> value는 문자열에서 나온 횟수
 * 	2-2. 근데 여기서 enum Class를 이용해보면 좋을듯!
 * 		-> 그럼 countingArr로 바로!
 * 		-> 근데 해보니 그냥 HashMap 쓰는 게 나은듯... 탐색에 O(1)이라..
 */

public class Solution {
	
	static int T;
	static int N;
	static String[] strNums = { "ZRO", "ONE", "TWO",
								"THR", "FOR", "FIV",
								"SIX", "SVN", "EGT", "NIN" };
	
	static HashMap<String, Integer> hashMap;
	static StringBuilder sb;
	static StringTokenizer st;
	static Scanner sc;
	static String input;
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("./src/swea_1221_GNS/input.txt");
		sc = new Scanner(file);
		
//		sc = new Scanner(System.in);
		
		T = Integer.parseInt(sc.nextLine());
		sb = new StringBuilder();
		
		for (int tc=1; tc<=T; tc++) {
			hashMap = new HashMap<>();
			
			for (String strNum : strNums) {
				hashMap.put(strNum, 0);
			}
			
			st = new StringTokenizer(sc.nextLine());
			System.out.println(st.nextToken());
			
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(sc.nextLine());
			
			for (int i=0; i<N; i++) {
				input = st.nextToken();
				hashMap.put(input, hashMap.get(input)+1);
			}
						
			// 음~ ZRO와 ONE가 붙어있음...
			// 그냥 다 ' '붙여서 하고, 마지막 sb에서 제거시키면 될듯?
			// 아니 input에 마지막에 ' '가 붙어있네...
			for (String strNum: strNums) {
				for (int val=1; val<=hashMap.get(strNum); val++) {
					sb.append(strNum).append(' ');
				}
			}
			System.out.println(sb.toString());
			sb.setLength(0);
			
		}
		
		
	}
}