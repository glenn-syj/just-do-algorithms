package boj_20040_황민욱;

import java.util.Scanner;

public class Main {
	static int n, m;
	static int[] p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		p = new int[n];

		for (int i = 0; i < n; i++) {
			p[i] = i;
		}

		int cycledTime = 0;

		out: for (int i = 1; i <= m; i++) {
			int px = findset(sc.nextInt());
			int py = findset(sc.nextInt());

			if (px == py) {
				cycledTime = i;
				break out;
			} else {
				union(px, py);
			}
		}
		
		System.out.println(cycledTime);

	}

	public static void union(int x, int y) {
		p[y] = x;
	}

	public static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}

		return p[x];
	}
}
