package swea_1493_수의새로운연산;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/* 1493. 수의 새로운 연산
 * 	
 * 20분 / 40분
 * 
 * 1. 조건
 * 	1-1. 점 (x,y)에 할당된 수 #(x, y)
 * 		-> (y=1)인 경우: sum(x) -- 1에서 x까지의 총합
 * 	1-2. 연산# 와 연산& 는 서로서로 변환
 * 	1-3. sumFromOne의 최댓값은 거의 50_000_000
 * 2. 풀이
 * 	2-1. 연산 #
 * 		-> (xi, yi)는 #(xi+yi-1, 1) = sum(xi+yi-1) 에서 yi-1 만큼을 뺀 값
 * 	2-2. 연산 &
 * 		-> &(p)에 대해서, sum(i)에 대해 p와 같거나 이를 넘어서는 i를 찾기
 * 		-> &(p) = (i, 1)에서 sum(i)-p만큼 좌상단으로 이동
 * 		-> &(p) = (i-sum(i)+p, 1+sum(i)-p)
 * 	2-3. 연산 star
 * 		-> #(&(p)+&(q))
 * 		-> p와 q를 파라미터로 받고, 두 점에 대해서 덧셈을 진행
 * 		-> 해당 점에 대한 # 연산
 */


public class Solution {
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/swea_1493_수의새로운연산/input.txt");
//		Scanner sc = new Scanner(file);
		
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		int T = Integer.parseInt(sc.nextLine());
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(sc.nextLine());
			
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			System.out.printf("#%d %d\n", tc, starInts(p, q));
		
		}
		
		
	}
	/** getSharp(int[] dot)
	 * 점에 대한 #연산을 구현한 메서드
	 * 
	 * @param dot
	 * @return #연산의 결과값
	 */
	public static int getSharp(int[] dot) {
		
		int p = dot[0];
		int q = dot[1];
		
		// 1부터 (p+q-1)까지의 합 - (p-1)
		return sumFromOne(dot) - (q-1);
	}
	
	/** getAnd(int n)
	 * 수에 대한 & 연산을 구현한 메서드
	 * 
	 * @param n &연산의 대상이 되는 피연산자
	 * @return
	 */
	public static int[] getAnd(int n) {
		
		int sum = 0;
		int xIntercept = 0;
		
		while (sum < n) {
			xIntercept++;
			sum += xIntercept;
		}
		
		int[] newDot = {xIntercept-sum+n, 1+sum-n};
		
		return newDot;
		
	}
	/** sumFromOne(int[] dot)
	 * 1에서 p+q-1까지의 합을 구하는 메서드
	 * 
	 * @param dot
	 * @return 1에서 p+q-1까지의 합
	 */
	public static int sumFromOne(int[] dot) {
		
		int p = dot[0];
		int q = dot[1];
		
		return ((p+q-1)*(p+q)/2);
	}
	
	/** addDots(int[] xy, int[] zw)
	 * 두 점에 대한 '+' 연산을 구현한 메서드
	 * 
	 * @param xy 연산의 좌측에 있는 점
	 * @param zw 연산의 우측에 있는 점
	 * @return 새로운 점에 대한 객체 반환
	 */
	public static int[] addDots(int[] xy, int[] zw) {
		
		int[] newDot = new int[2];
		newDot[0] = xy[0] + zw[0];
		newDot[1] = xy[1] + zw[1];
		
		return newDot;
				
	}
	
	/** starInts(int p, int q)
	 * 두 점에 대한 '별표' 연산을 구현한 메서드
	 * 
	 * @param p 점의 x좌표
	 * @param q 점의 y좌표
	 * @return starInt 연산에 대한 결과값
	 */
	public static int starInts(int p, int q) {
		
		return getSharp(
				(addDots(getAnd(p), getAnd(q)))
				);
				
		
	}
}
