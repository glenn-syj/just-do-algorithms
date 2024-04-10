package boj_12851_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, K;
	static int[] time;
	static int minTime;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		time = new int[100001];
		minTime = Integer.MAX_VALUE;
		count = 0;

		bfs(N);
		
		System.out.println(minTime);
		System.out.println(count);
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(start);
		time[start] = 1;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			int[] next = { current + 1, current - 1, current * 2 };

			for (int i = 0; i < 3; i++) {
				if (isNotOutBound(next[i])) {
					if (next[i] == K && (time[next[i]] == 0 || time[current] + 1 <= minTime)) {
						minTime = time[current];
						count++;
					}

					// 다음 시간이 현재 시간 + 1이라면
					if (time[next[i]] == 0 || time[next[i]] > time[current] + 1) {
						queue.add(next[i]);
						time[next[i]] = time[current] + 1;
					}
				}
			}
		}
	}

	private static boolean isNotOutBound(int x) {
		return x >= 0 && x <= 100000;
	}
}
