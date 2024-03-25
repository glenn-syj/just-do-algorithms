package boj_16236_황민욱;

// 1. 일단 상어는 물고리가 있는지 확인을 먼저한다.
// 2. 

import java.util.Queue;
import java.util.Scanner;

class Fish{
	
}

public class Main {
	static int N;
	static int map[][];
	static Queue<Fish> queue;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}
	}
}
