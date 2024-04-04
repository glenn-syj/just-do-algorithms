package boj_12851_숨바꼭질2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1차원 배열에서 BFS 탐색을 진행한다.
// N이 갈 수 있는 곳은 3곳이다. 현재위치에서 -1, +1, *2 
// BFS로 이 3개를 for문 돌려서 탐색한다
// 방문체크, 카운트용으로 visited 배열을 사용

public class Main {
	static int N, K, count, time;
	static int visited[];
	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 한줄에 들어온 문자열을 나눈다
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

//		System.out.println(N);
//		System.out.println(K);

		if (N == K) {
			System.out.printf("0\n1"); // 같은 위치에서 시작 시 0 1을 출력
		} else {
			visited = new int[100001];
			time = 0; // 최단 소요 시간
			count = 0; // 방법 몇개인지
			BFS();

			System.out.printf("%d\n%d", time, count);
		}
	}

	static void BFS() {
		queue = new LinkedList<>();
		queue.add(N);
		visited[N] = 1;

		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int i = 0; i < 3; i++) {
				int next;
				if (i == 0) {
					next = now + 1;
				} else if (i == 1) {
					next = now - 1;
				} else {
					next = now * 2;
				}

				if (next == K && time == 0) {
					time = visited[now]; // time 구하기 (1회만)
				}

				if (next == K && visited[now] == time) {
					// 동생을 찾은 경우
					count++;
				}

				if (next < 0 || next > 100000 || (visited[next] != 0 && visited[next] < visited[now] + 1)) {
					// next가 범위 밖이거나, 이미 방문한 지점인데 기존 소요 시간보다 오래 걸린다면 X
					continue;
				}

				queue.add(next);
				visited[next] = visited[now] + 1; // 소요 시간 저장
			}

		}
	}

}
