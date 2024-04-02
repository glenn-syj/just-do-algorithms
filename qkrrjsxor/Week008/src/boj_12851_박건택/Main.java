package boj_12851_박건택;
/*
 * DP를 이용해 풀려고 하였지만, 틀린 접근이었다.
 * BFS 카테고리라는 정보를 얻어 다시 풀어보았다.
 * 
 * 최단 시간은 bfs로 한칸 앞, 한칸 뒤, 두배 점프 각각 탐색하며 목적지(동생위치)가 나올때 까지 탐색
 * 최단 시간의 가짓수도 구해야 한다
 * 
 * 시작 : x = 0
 * x-1, x+1, x*2 = 반복 횟수만큼 증가
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] time = new int[100001];
		boolean[] visited = new boolean[100001];

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		if (N >= K) {
			System.out.println(N - K);
			System.out.println(1);
			return;
		}

		Queue<Integer> queue = new LinkedList<>();

		queue.offer(N);
		int now = 0;
		int next = 0;
		int count = 0;
		int min = Integer.MAX_VALUE;
		time[N] = 0;
		while (!queue.isEmpty()) {
			now = queue.poll();

			for (int i = 0; i < 3; i++) {
				if (i == 0)
					next = now - 1;
				else if (i == 1)
					next = now + 1;
				else if (i == 2)
					next = now * 2;

				if (next >= 0 && next < 100001) {
					//숨바꼭질 1과 다르게 최단 경로의 수를 구해야 한다. 그러기 위해서 time[next]가 time[now]+1 과 같은 경우에도 탐색하기 위해 등호를 붙인다.
					if (time[next] == 0 || time[next] >= time[now] + 1) {
						time[next] = time[now] + 1;
						queue.offer(next);

						//그리고 min 변수와 아래 코드를 추가하여 K에 도착했을 때 min 값을 업데이트하며 최소 시간이면 count ++
						if (next == K && time[next] <= min) {
							min = time[next];
							count++;
						}
					}
				}
			}
		}

		System.out.println(time[K]);
		System.out.println(count);

	}
}
