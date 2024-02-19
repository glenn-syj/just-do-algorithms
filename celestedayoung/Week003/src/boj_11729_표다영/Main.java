package boj_11729_하노이탑이동순서;

import java.util.Scanner;

public class Main {
	
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		move(N, 1, 3, 2);
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}

	static void move(int N, int from, int to, int temp) {
		if (N == 1) {
			cnt++;
			sb.append(from + " " + to + "\n");
			return;
		} else {
			move(N - 1, from, temp, to);
			sb.append(from + " " + to + "\n");
			cnt++;
			move(N - 1, temp, to, from);
		}
	}

}
