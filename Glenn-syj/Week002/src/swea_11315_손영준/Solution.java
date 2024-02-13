package swea_11315_오목판정;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/* S11315. 오목판정
 * 
 * 1. 조건
 * 	1-1. 정수 5 <= N <= 20
 * 	1-2. 다섯 개 '이상' 연속한 부분이 있는지 없는지 판정
 * 		-> 대각선: 좌측 아래로 향하는 대각선, 우측 아래로 향하는 대각선에 대해서만 체크
 * 		-> 세로: 아래로만 체크
 * 		-> 가로: 우측으로만 체크
 * 2. 풀이
 * 	2-1. 가로는 col N-5까지만 체크
 * 		-> 세로는 row N-5까지만 체크
 * 		-> 좌에서 우로 가는 대각선은: row 0~N-5, col 0~N-5 까지만 체크
 * 		-> 우에서 좌로 가는 대각선은: row 0~4; col 4~N-1 까지만 체크
 */

public class Solution {
	
	static char[][] points;
	static StringTokenizer st;
	static int N;
	static int[][] diagDeltas = { {1, 1}, {-1, -1} };
	static Scanner sc;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/swea_11315_오목판정/input.txt");
//		sc = new Scanner(file);
		
		sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		
		tc:
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(sc.nextLine());
			
			points = new char[N][N];
			for (int i=0; i<N; i++) {
				String line = sc.nextLine().trim();
				for (int j=0; j<N; j++) {
					points[i][j] = line.charAt(j);
				}
			}
			
			
			horizontal:
			for (int i=0; i<N; i++) {
				for (int j=0; j<N-4; j++) {
					boolean isFiveStreaked = true;
					for (int k=0; k<5; k++) {
						if (points[i][j+k] != 'o') {
							isFiveStreaked = false;
							break;
						}
					}
					if (isFiveStreaked) {
						System.out.printf("#%d %s\n", tc, "YES");
						continue tc;
					}
				}
			}
			
			vertical:
			for (int i=0; i<N-4; i++) {
				for (int j=0; j<N; j++) {
					boolean isFiveStreaked = true;
					for (int k=0; k<5; k++) {
						if (points[i+k][j] != 'o') {
							isFiveStreaked = false;
							break;
						}
					}
					if (isFiveStreaked) {
						System.out.printf("#%d %s\n", tc, "YES");
						continue tc;
					}
				}
			}
			
			rightDiag:
			for (int i=0; i<N-4; i++) {
				for (int j=0; j<N-4; j++) {
					boolean isFiveStreaked = true;
					for (int k=0; k<5; k++) {
						if (points[i+k][j+k] != 'o') {
							isFiveStreaked = false;
							break;
						}
					}
					if (isFiveStreaked) {
						System.out.printf("#%d %s\n", tc, "YES");
						continue tc;
					}
				}
			}
			
			leftDiag:
			for (int i=0; i<N-4; i++) {
				for (int j=4; j<N; j++) {
					boolean isFiveStreaked = true;
					for (int k=0; k<5; k++) {
						if (points[i+k][j-k] != 'o') {
							isFiveStreaked = false;
							break;
						}
					}
					if (isFiveStreaked) {
						System.out.printf("#%d %s\n", tc, "YES");
						continue tc;
					}
				}
			}
			
			System.out.printf("#%d %s\n", tc, "NO");
			
			
		}
		
		
		
	}
}