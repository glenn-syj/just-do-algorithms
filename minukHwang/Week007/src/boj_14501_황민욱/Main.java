package boj_14501_황민욱;

import java.util.Scanner;

public class Main {
	static int[][] info;
	static int[] dp;
	static int N;
	static int max;
	static boolean[] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		info = new int[N][2];
		dp = new int[N];
		max = 0;
		selected = new boolean[N];

		for (int i = 0; i < N; i++) {
			info[i][0] = sc.nextInt();
			info[i][1] = sc.nextInt();
		}

		solve(0, 0);
		System.out.println(max);
	}

	public static void solve(int day, int value) {
		if (day == N) {
			max = Math.max(max, value);
			return;
		}

		int t = info[day][0];
		int p = info[day][1];

		if (day + t <= N) {
			solve(day + t, value + p);
		}

		solve(day + 1, value);
	}
}
