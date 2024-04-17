package swea_2117_황민욱;

import java.util.Scanner;

public class Solution {
	static int T, N, M;
	static int[][] map;
	static int maxCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = sc.nextInt();
			M = sc.nextInt();

			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
			maxCount = 0;

			inspect();
			
			sb.append(maxCount).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void inspect() {

		for (int k = 1; k <= N + 1; k++) {
			int cost = k * k + (k - 1) * (k - 1);

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int count = service(k - 1, r, c);

					if ((M * count - cost) >= 0) {
						maxCount = Math.max(count, maxCount);
					}
				}
			}
		}
	}

	private static int service(int k, int centerR, int centerC) {
		int total = 0;
		int left = centerC;
		int right = centerC;

		for (int r = centerR - k; r <= centerR + k; r++) {
			for (int c = left; c <= right; c++) {
				if (isNotOutBound(r, c) && map[r][c] == 1) {
					total++;
				}
			}

			if (r < centerR) {
				left--;
				right++;
			} else {
				left++;
				right--;
			}
		}

		return total;
	}

	private static boolean isNotOutBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
