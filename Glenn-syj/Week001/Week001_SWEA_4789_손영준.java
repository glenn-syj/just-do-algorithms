import java.util.*;

/* 4789. 성공적인 공연 기획
 * 
 * 1. 조건
 * 	1-1. i번째 글자는 기립박수를 하고 있는 사람이 i-1명 이상일 때 기립 박수를 하는 사람의 수
 * 		-> 1번째 글자는 기립박수자 0명 이상일 때 기립박수를 하는 사람의 수
 * 		-> 2번째 글자는 기립박수자 1명 이상일 때 기립박수를 하는 사람의 수
 * 		-> n번째 글자는 기립박수자 n-1명 이상일 때 기립박수를 하는 사람의 수
 * 	1-2. 0에서 9사이의 문자만으로 이루어진 문자열, 1001 이하
 * 		-> 999...9 : 최대 9009 (int/Integer) OK
 * 		-> 문자열의 길이가 length라면, length-1까지 chain처럼 연쇄적으로 박수가 이어져야 함
 * 	1-3. 가장 마지막 문자는 '0'이 아님 (적어도 한 명의 관객이 있음)
 * 		-> 마지막 문자를 빼고 계산하는 edge case 없음
 * 2. 풀이
 * 	2-1. 입력으로 들어올 문자열을 int로 바꿔서 저장할 변수 int[] applSequence
 * 		-> 문자열 길이 + 1의 크기로 만들고, 이후 applSequence[1] 부터 시작
 * 		=> 그런데 StringTokenizer로 받아서, 매 Token마다 확인하는 편이 더 빠를듯?
 * 		=> (X) 다시보니 입력이 공백으로 구분되지 않아 StringTokenzier 이용 불가
 * 		=> (O) 그냥 charAt(i) - '1' + 1을 쓰는게 편할듯..
 * 	2-2. 필요한 사람 수 int employee 
 * 	2-3. 현재 박수를 치는 사람을 세는 변수 int applause
 * 		---pointer가 끝을 지날 때까지---
 * 		(1) 현재 pointer가 n일 때, applause >= n-1이면 applause += array[n-1]
 * 		(2) 현재 pointer가 n일 때, applause < n이면 이후 진행이 안됨
 * 			-> 어차피 필요 인원은 더 커지니까!
 * 		=> 따라서 applause < n 인 경우를 마주하면, employee += applause-n;
 */

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// using scanner instead of bufferedReader
		int T = Integer.parseInt(sc.nextLine());
		
		String line;
		int lineLength;
		
		for (int tc = 1; tc <= T; tc++) {
			
			line = sc.nextLine().trim();
			lineLength = line.length();
			
			int applause = 0;
			int chainedApplause = 0;
			int employee = 0;
			
			// index equals leastApplause
			for (int index = 0; index < lineLength; index++) {
				chainedApplause = line.charAt(index)-'1'+1;
				if (applause < index) {
					employee += index-applause;
					applause += index-applause;
				}
				applause += chainedApplause;
			}
			
			System.out.printf("#%d %d\n", tc, employee);
		}
		
	}
}
