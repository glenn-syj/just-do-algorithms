package boj_12865_유서현;

import java.util.Scanner;

public class Main {
	
// dp로 풀기 위해 knapsack 알고리즘 검색해서 peek

	static class Thing {
		int weight, value;

		public Thing(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 물건 개수
		int k = sc.nextInt(); // 가방 크기

		Thing[] things = new Thing[n + 1];
		for (int i = 1; i <= n; i++) {
			things[i] = new Thing(sc.nextInt(), sc.nextInt());
		}

		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j < things[i].weight) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - things[i].weight] + things[i].value);
				}
			}
		}

		System.out.println(dp[n][k]);
	}

}
