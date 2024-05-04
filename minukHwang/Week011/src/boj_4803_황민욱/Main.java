package boj_4803_황민욱;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static int[] p;
	static Set<Integer> tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = 1;

		while (true) {
			int V = sc.nextInt();
			int E = sc.nextInt();

			if (V == 0 && E == 0)
				break;

			p = new int[V + 1];
			tree = new HashSet<>();

			for (int i = 0; i < V + 1; i++) {
				p[i] = i;
			}

			int[][] edges = new int[E][2];

			for (int i = 0; i < E; i++) {
				edges[i][0] = sc.nextInt();
				edges[i][1] = sc.nextInt();
			}

			for (int i = 0; i < E; i++) {
				int px = findset(edges[i][0]);
				int py = findset(edges[i][1]);

				union(px, py);
				System.out.println(Arrays.toString(p));
			}

			for (int i = 1; i < V + 1; i++) {
				int parent = findset(i);
				if(parent > 0) {
					tree.add(parent);
				}
			}

			sb.append("Case ").append(t).append(": ");

			if (tree.isEmpty()) {
				sb.append("No trees.").append("\n");
			} else if (tree.size() == 1) {
				sb.append("There is one tree.").append("\n");
			} else {
				sb.append("A forest of ").append(tree.size()).append(" trees.").append("\n");
			}

			t++;
		}

		System.out.println(sb);
	}

	private static void union(int x, int y) {
		if (x == y) {
			p[y] = x;
			p[x] = 0;
		} else if (x < y) {
			p[y] = x;
		} else {
			p[x] = y;
		}
	}

	private static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}

		return p[x];
	}
}
