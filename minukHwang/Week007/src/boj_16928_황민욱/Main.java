package boj_16928_황민욱;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] map;

	static Queue<Integer> queue;
	static int[] visited;
	static int count = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[101];
		visited = new int[101];

		for (int i = 0; i < N + M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();

			map[start] = end;
		}

		bfs(1);

		System.out.println(visited[100]);
	}

	private static void bfs(int node) {
		visited[node] = 0;
		queue = new LinkedList<>();

		queue.add(node);

		while (!queue.isEmpty()) {
			int current = queue.poll();

			if (current == 100) {
				break;
			}

			for (int i = 1; i <= 6; i++) {
				int next = current + i;

				if (next <= 100 && map[next] != 0) {
					next = map[next];
				}

				if (next <= 100 && visited[next] == 0) {
					visited[next] = visited[current] + 1;
					queue.add(next);
				}
			}
		}
	}
}
