package boj_4803_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int[] p;
	static List<Integer> cycle;

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
			cycle = new ArrayList<>();

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

				if (px == py) {
					cycle.add(px);
				}

				union(px, py);
				System.out.println(Arrays.toString(p));
				System.out.println(cycle);
			}

			for (int i = 0; i < cycle.size(); i++) {
				for (int j = 0; j < V + 1; j++) {
					if (p[j] == cycle.get(i)) {
						p[j] = 0;
					}
				}
			}

			int[] count = new int[V + 1];
			int total = 0;

			for (int i = 0; i < V + 1; i++) {
				if (p[i] == 0) {
					continue;
				}

				count[p[i]]++;
			}

			for (int i = 0; i < V + 1; i++) {
				if (count[i] > 0) {
					total++;
				}
			}

			sb.append("Case ").append(t).append(": ");

			if (total > 1) {
				sb.append("A forest of ").append(total).append(" trees.").append("\n");
			} else if (total == 1) {
				sb.append("There is one tree.").append("\n");
			} else {
				sb.append("No trees.").append("\n");
			}

			t++;
		}

		System.out.println(sb);
	}

	private static void union(int x, int y) {
		p[y] = x;
	}

	private static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}

		return p[x];
	}
}
