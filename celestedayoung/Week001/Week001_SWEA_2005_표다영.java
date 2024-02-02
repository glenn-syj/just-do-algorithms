/*
 * SWEA_2005. 파스칼의 삼각형
 * 	- Logic
 * 		0. 출력 값을 보면 삼각형 모양을 유지하진 않으며, 행이 증가할 때마다 열의 수가 하나씩 늘어난다.
 * 		1. r = 0, 1일 때는 요소가 항상 1이다.
 * 		2. r > 2일 때는 가장 첫 번재 열과 마지막 열의 요소가 1이고, 나머지 요소는 이전 행에서 현재 위치한 요소의 열-1의 값 + 열의 값이다.
 * 		   즉, answer = [r - 1][c - 1] + [r - 1][c]	
 * 
 *  - Point
 * 		- 2차원 배열 초기화 시 행의 수만 지정하고 열의 수는 지정하지 않음으로써 주어진 N(삼각형의 크기)에 맞게 열의 수를 지정할 수 있다.
 * 			ex) N=4일 경우: r=0일 때 c=1, r=1일 때 c=2, r=2일 때 c=3, r=3일 때 c=4 
 * 				즉, for문을 통해 r을 N-1까지 돌릴 때 c의 값을 r+1로 설정하여 배열의 크기를 계속해서 늘릴 수 있다.
 */
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for (int t = 1; t <= tc; t++) {
			
			int row = sc.nextInt();

			// 2차원 배열 초기화 시, 행의 길이만 지정: 행이 증가함에 따라 열의 길이를 가변적으로 추가하기 위함이다.
			int[][] triangle = new int[row][];
			
			for (int r = 0; r < row; r++) {
				triangle[r] = new int[r + 1];
			}
			
			for (int r = 0; r < row; r++) {
				for (int c = 0; c <= r; c++) {
					
					// r < 2일 때는 요소가 항상 1
					if (r < 2) {
						triangle[r][c] = 1;
					// 가장 첫 번째 열과 마지막 열의 요소는 항상 1
					} else if (c == 0 || c == r) {
						triangle[r][c] = 1;
					// r >= 2이면서 1과 1사이에 위치한 열의 요소는 [이전 행][현재 위치한 요소의 열 - 1] + [이전 행][현재 위치한 요소의 열]
					} else if (c > 0 && c < r) {
						triangle[r][c] = triangle[r - 1][c - 1] + triangle[r - 1][c];
					}
				}
			}
			System.out.println("#" + t);
			for (int i = 0; i < row; i++) {
				for (int j = 0; j <= i; j++) {
					System.out.print(triangle[i][j] + " ");
				}
				System.out.println();
			} 
		}
	}
}
