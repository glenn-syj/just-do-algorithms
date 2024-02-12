package swea_7326_저수지의물의총깊이구하기;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/* S7326. 저수지의 물의 총 깊이 구하기
 * 
 * 5분 / 15분
 * 
 * 1. 조건
 * 	1-1. 완전탐색
 * 	1-2. 모서리의 경우에 대해서는 고려할 필요가 없는듯...?
 * 		-> 왜냐하면 위/아래/옆에 붙어있는 구역에 대해서는 
 * 		-> 바로 1칸 아래/위/옆에 대해서 물의 양이 같거나 더 크니까
 * 	1-3. 9 <= N <= 100
 * 
 * 2. 풀이
 * 	2-1. 완전 탐색
 * 		-> row: 1~N-2, col: 1~N-2만 탐색하면 됨
 * 	2-2. 최댓값이 0인 경우에는 마지막에만 1로 바꿔주면 됨
 * 
 */

public class Solution {
	
	static int T;
	static int N;
	static char[][] reservoir;
	static Scanner sc;
	static StringBuilder sb;
	static StringTokenizer st;
	static int max;
	static int[][] deltas = { {-1, -1}, {-1, 0}, {-1, 1},
								{0, -1}, 		{0, 1},
								{1, -1}, {1, 0}, {1, 1} };
	
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/swea_7326_저수지의물의총깊이구하기/input.txt");
//		sc = new Scanner(file);
		
		sc = new Scanner(System.in);
		sb = new StringBuilder();
		
		T = Integer.parseInt(sc.nextLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			sb.append('#').append(tc).append(' ');
			
			max = Integer.MIN_VALUE;
			N = Integer.parseInt(sc.nextLine());
			reservoir = new char[N][N];
			
			
			
			for (int row=0; row < N; row++) {
				st = new StringTokenizer(sc.nextLine());
				for (int col=0; col < N; col++) {
					reservoir[row][col] = st.nextToken().charAt(0);
				}
			}
			
			int val;
			for (int row=1; row <= N-2; row++) {
				for (int col=1; col <= N-2; col++) {
					if ( (val = searchWater(row, col)) > max) {
						max = val;
					}
				}
			}
			
			if (max == 0) {
				max++;
			}
			
			sb.append(max);
			System.out.println(sb.toString());
			sb.setLength(0);
			
		}
		
		
	}
	
	public static int searchWater(int row, int col) {
		
		int count = 0;
		
		for (int[] delta: deltas) {
			if (reservoir[row+delta[0]][col+delta[1]] == 'W') {
				count++;
			}
		}
		
		return count;
		
		
	}
}
