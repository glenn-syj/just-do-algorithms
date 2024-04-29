package boj_1005_유서현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K, W;
	static int[] buildTime;
	static int[][] adj;
	static int[] degree;
	static int[] buildCost;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			buildTime = new int[N + 1];
			buildCost = new int[N + 1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i < N + 1; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}

			adj = new int[N + 1][N + 1];
			degree = new int[N + 1];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				adj[a][b] = 1;
				degree[b]++;
			}

			W = Integer.parseInt(br.readLine());

//			System.out.println(Arrays.toString(degree));

			Queue<Integer> queue = new LinkedList<>();

			for (int i = 1; i < N + 1; i++) {
				if (degree[i] == 0) {
					buildCost[i] = buildTime[i]; // 초기 건설 비용 넣어주기
					queue.add(i);
				}
			}

			while (!queue.isEmpty()) {
				int curr = queue.poll();
//				System.out.println(curr);

				for (int i = 1; i < N + 1; i++) {
					if (adj[curr][i] == 1) {
						degree[i]--;
						// 건물 i를 짓기 시작할 수 있는 시간 (peek)
						buildCost[i] = Math.max(buildCost[i], buildCost[curr] + buildTime[i]);

						if (degree[i] == 0) {
							queue.add(i);
						}
					}
				}

			}
//			System.out.println(Arrays.toString(buildCost));

			System.out.println(buildCost[W]);
		}

	}

}
