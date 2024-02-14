package boj_11729_하노이의탑;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/* B11729. 하노이의 탑
 * 
 * 20분 / 25분
 * 
 * 1. 조건
 * 	1-1. 하노이의 탑을 구현해보자
 * 	1-2. 3개의 공간을 대신해서, Stack[]이나 ArrayList<Integer>[]를 이용해보자
 * 	1-3. 완벽한 하나의 알고리즘이 아니라, 재귀를 이용해서 최솟값보다 큰 값들은 쳐내는 방식으로 이용
 * 		=> 음 근데 그냥 재귀 이용하면 될듯
 * 2. 풀이
 * 	2-1. 개수가 1개인 경우	
 * 		-> 1: (1 3)
 * 		 개수가 2개인 경우
 * 		-> 1: (1 2) 2: (1 3) 1: (2 3)
 * 		 개수가 3개인 경우
 * 		-> ...
 * 	2-2. f(n) = f(n-1)*2 + 1
 * 		-> f(1) = 1, f(2) = f(1)*2 + 1, f(3) = f(2)*2 + 1
 *		=> 자기보다 수 하나 적은 크기의 탑을 2번으로 옮기고, 자신이 3번으로 가고, 다시 그 탑을 3번으로 옮겨야 함
 *	2-3. move(int dept, int dest)
 *	2-4. f(times, dept, dest)
 *		-> f(3, 1, 3)인 경우에는 f(2, 1, 2) -> 1번에서 3번으로 {times}이동 -> f(2, 2, 3);
 *		=> f(n, 1, 3)인 경우에는 f(n-1, 1, 2) -> 1번에서 3번으로 n 이동 -> f(n-1, 2, 3);
 *		=> f(n-1, 1, 2)인 경우에는 f(n-2, 1, 3) -> 1번에서 2번으로 n-1 이동 -> f(n-2, 3, 2);
 *		=> f(n-2, 3, 2)인 경우에는 f(n-3, 3, 1) -> 3번에서 2번으로 n-2 이동 -> f(n-3 1, 2);
 *		=> f(n-3, 1, 2)인 경우에는 f(n-4, 1, ...)
 *  2-5. 결국, (N, dept, dest)인 경우에 dept도 dest도 아닌 지역 checkPoint에 대해
 *  	-> f(N-1, dept, checkPoint) 하고, dept에서 dest로 가장 큰 원판을 옮기고, f(N-1, checkPoint, dest) 시행
 */

public class Main {
	
	static Scanner sc;
	static StringBuilder sb = new StringBuilder();
	static int count;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/boj_11729_하노이의탑/input.txt");
//		sc = new Scanner(file);
		
		sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.nextLine());
		
		hanoi(N, 1, 3);
		
		System.out.println(count);
		System.out.println(sb.toString());
		
		
	}
	
	public static void hanoi(int times, int dept, int dest) {
		
		count++;
		
		if (times == 1) {
			sb.append(dept).append(' ').append(dest).append('\n');
			return;
		}
		
		int checkPoint = 0;
		
		// checkPoint는 dest도 dept도 아닌 곳이어야 함
		for (int i=1; i<=3; i++) {
			if (i != dept && i != dest) {
				checkPoint = i;
				break;
			}
		}
		
		// 가장 큰 원반 위에 있는 원반들을 checkPoint로 옮기기
		hanoi(times-1, dept, checkPoint);
		// 가장 큰 원반을 dest로 옮기기
		sb.append(dept).append(' ').append(dest).append('\n');
		// checkPoint에 있던 원반들을 dest에 있는 가장 큰 원반 위로 옮기기
		hanoi(times-1, checkPoint, dest);
	}
}