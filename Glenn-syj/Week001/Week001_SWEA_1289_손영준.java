import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* 1289. 원재의 메모리 복구하기
 * 
 * 5분 / 5분
 * 
 * 1. 조건
 * 	1-1. 메모리 bit 중 하나를 골라 결정하면 해당 값이 메모리의 끝까지 덮어씌워짐
 * 		-> 뒤로 모두 0이되거나, 뒤로 모두 1이 되거나
 * 2. 풀이
 * 	2-1. 현재 인덱스가 이전 값 (초기값: 0)과 다르다면 count값에 + 1
 * 	2-2. 굳이 1과 0을 분기할 필요없이, value를 변수로 설정하고 비교하면 됨
 */

public class Solution {
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/swea_1289_원재의메모리복구하기/input.txt");
//		Scanner sc = new Scanner(file);

		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			char[] memory = sc.nextLine().trim().toCharArray();
			
			int count = 0;
			// 초기에 설정되어 있는 값 (0으로 덧씌워진 상태)
			char value = '0';
			
			for (char bit : memory) {
				if (value != bit) {
					value = bit;
					count++;
				}
			}
			
			System.out.printf("#%d %d\n", tc, count);
			
		}
		
	}
}
