package boj_2252_황민욱;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static List<Integer>[] nodes;
	static int[] inDegree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		N = sc.nextInt();
		M = sc.nextInt();

		nodes = new ArrayList[N + 1];
		inDegree = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();

			nodes[node1].add(node2);
			inDegree[node2]++;
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i < N + 1; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			sb.append(curr).append(" ");

			for (int node : nodes[curr]) {
				inDegree[node]--;
				if (inDegree[node] == 0) {
					queue.add(node);
				}
			}
		}
		
		System.out.println(sb);

	}
}
