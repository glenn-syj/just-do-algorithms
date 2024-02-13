package boj_11729_황민욱;

import java.util.Scanner;

public class Main {
	
	// 어떠한 변수...........
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		hanoiTower(1, 3, N);
	}
	
	public static int hanoiTower(int from, int to, int count) {
		if(count == 1) {
			System.out.printf("%d %d\n", from, to);
		}
		
		to = 6 - from -  to;
		return hanoiTower(from , to, --count);
	}
}
