/*
 * SWEA_1289. 원제의 메모리 복구
 * 	- Logic
 * 		0. 초기 값은 요소가 0이고 길이가 length으로 주어진다. (1 <= length <= 50)
 * 		1. 따라서 복구해야 할 값과 초기 값을 비교하며 초기 값 요소와 복구해야할 값의 요소가 다를 때 초기 값을 바꿔주며 count한다.
 * 			1-1. 숫자는 0 또는 1이며 초기 값은 모두 0이므로 초기 값 != 복구해야할 값에서 요소를 바꾼다는 것은 0을 1로 바꾼다는 뜻이다.
 * 			1-2. 그러나 해당 숫자만 1로 바꾸는 것이 아니라, 해당 요소 뒤에 존재하는 모든 값을 1로 바꾸어야 한다.
 * 
 * 	- Point
 * 		- test case를 int type으로 받아버리면 '0011'과 같이 맨 앞에 0이 존재할 때 '11'로 받아버리게 된다.
 * 		  따라서 test case를 String으로 받아 형태를 유지하며 input을 받은 후, '0'(char type)을 빼준다.	
 * 		  즉, char - '0'을 하게 되면 ASCII Code 값끼리 계산되는 것이므로 형 변환 없이 바로 int type으로 바꿀 수 있다.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int testNum = sc.nextInt();
		
		for (int t = 1; t <= testNum; t++) {
			
			// test case 받기: int type으로 받으면 맨 앞에 0이 사라지므로 String type으로 받아서 형태를 유지한다. 
			String forLength = sc.next();
			
			int length = forLength.length();
			int[] testCase = new int[length];
			int[] initial = new int[length];
			
			// test case를 String으로 받았으므로 int로 바꿔주기: '0'은 char type이므로 char - '0'은 ASCII Code로 계산되어 int로 바꿀 수 있다.
			for (int i = 0; i < length; i++) {
				testCase[i] = forLength.charAt(i) - '0';
			}
			
			int cnt = 0;
			for (int i = 0; i < length; i++) {
				
				// 초기값과 test case 값이 다를 때 
				if (testCase[i] != initial[i]) {
					cnt++;
					// 값이 다른 index부터 끝까지 초기값과 똑같은 값으로 바꿔준다.
					for (int j = i; j < length; j++) {
						initial[j] = testCase[i];
					}
				}
			}
			System.out.printf("#%d %d%n",t, cnt);
		}
	}	
}