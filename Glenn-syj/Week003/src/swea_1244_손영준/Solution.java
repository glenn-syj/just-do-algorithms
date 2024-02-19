package swea_1244_최대상금;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/* S1244. 최대상금
 * 
 * 
 * 1차 시도 15분 / 30분 (실패)
 * 2차 시도 10분 / 20분 (실패)
 * 3차 시도 10분 / 30분 (성공)
 * 
 * 
 * - 1차 -
 * 1. 조건
 * 	1-1. 숫자판이 주어지고, 정해진 횟수만큼 서로의 자리를 위치 교환
 * 		-> 일종의 정렬 swap과 비슷함
 * 	1-2. 숫자판의 오른쪽 끝에서부터 1원이고 왼쪽으로 한 자리 씩 갈수록 10의 배수만큼 커진다
 * 		-> 내림차순 정렬이 필요하다		
 * 	1-3. 정해진 횟수만큼 교환을 해야함
 * 		-> 반드시 횟수만큼 교환이 이루어져야 하면서, 동일한 위치의 교환이 중복되어도 됨
 * 		-> 예시에서는 8,8의 중복 교환으로 아무 변화 없는 것 같지만
 * 		-> 실제로는 최대값과 두 번째 최대값을 교환한 것
 * 		=> 문제에서는 마지막 두 최솟값을 교환해줘야함 (이게 아니었음!!!)
 * 2. 풀이
 * 	2-1. 교환을 k번 해야한다고 했을 때,
 * 		(1) k번만에 정렬이 되거나 k번만에 정렬을 못한 경우
 * 			-> sorted array에서 그대로 값 구하기
 * 		(2) k번보다 적은 횟수만에 정렬이 완료된 경우
 * 			-> 남은 횟수가 홀수라면 첫째 최소값 둘째 최소값 교환
 * 			-> 남은 횟수가 짝수라면 sorted array에서 그대로 값 구하기ㅇ
 * 
 * - 2차 - (3차는 그냥 코드 다시 보고 로직 수정하기)
 * 2. 풀이
 * 	2-1. 1차 시도에서의 조건이 만족하지 않으므로, 자신보다 큰 수에 대해 교환한 값들에 대해 백트래킹
 * 		-> StringBuiilder를 이용해서... 백트래킹 시도 (그냥 String은 연산을 너무 많이 먹는다...)
 * 		-> StringBuilder 초기화 할때도 연산을 너무 많이먹어서...
 * 		-> 그냥 charArray 이용하는 게 나은듯
 * 	2-2. 아니 메모리도 너무 많이 먹고 시간도 너무 오래걸리는데...
 * 		-> memoization?
 * 		=> 풀이1에서의 중간 종료 조건을 이용...
 * 		=> descendant하면 더 이상 백트래킹을 진행할 필요가 없음
 * 			(이럴 때 static 필드가 굉장히 유용하구나...)
 * 	
 * 
 */

public class Solution {
	
	static StringTokenizer st;
	static Scanner sc;
	static int T;
	static String digits;
	static int exchange;
	static int maxValue;
	static boolean isDescendant;
	static boolean hasSameToken;
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("./src/swea_1244_최대상금/input.txt");
		sc = new Scanner(file);
//		sb = new StringBuilder();
//		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		
		for (int tc=1; tc<=T; tc++) {
			maxValue = Integer.MIN_VALUE;
			st = new StringTokenizer(sc.nextLine());
			digits = st.nextToken();
			exchange = Integer.parseInt(st.nextToken());
			hasSameToken = checkSameToken(digits.toCharArray());
			isDescendant = checkDescendant(digits.toCharArray());
		
			
			track(digits.toCharArray(), exchange);
			System.out.print("#" + tc + ' ');
			System.out.println(maxValue);
			
		}
		
		
		
		
	}
	
	// 그냥 
	public static void track(char[] arr, int check) {
		
		
		if (check == 0) {
			int val = parseInt(arr);
			if (maxValue < val) {
				maxValue = val;
			}
			return;
		}
		
		if (checkDescendant(arr)) {
			if (hasSameToken||check%2 == 0) {
				maxValue = parseInt(arr);
			} else {
				swap(arr, digits.length()-2, digits.length()-1);
				maxValue = parseInt(arr);
			}
			isDescendant = true;
			return;
		}
		
		if (isDescendant) {
			return;
		}
		
		int newCheck = check-1;
		
		for (int i=0; i<digits.length()-1; i++) {
			for (int j=i+1; j<digits.length(); j++) {
				if (arr[i] <= arr[j]) {
					char[] tempArr = arr.clone();
					swap(tempArr, i, j);
					track(tempArr, newCheck);
				}
			}
		}
		
		
	}
	
	
	public static void swap(char[] arr, int a, int b) {
		
		char tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	public static boolean checkDescendant(char[] arr) {
		
		for (int i=0; i<arr.length-1; i++) {
			if (arr[i] < arr[i+1]) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public static int parseInt(char[] arr) {
		return Integer.parseInt(String.valueOf(arr));
	}
	
	public static boolean checkSameToken(char[] arr) {
		ArrayList<Character> tokens = new ArrayList<>();
		for (int i=0; i<digits.length(); i++) {
			if (tokens.contains(arr[i])) {
				return true;
			}
			tokens.add(arr[i]);
		}
		return false;
	}
}