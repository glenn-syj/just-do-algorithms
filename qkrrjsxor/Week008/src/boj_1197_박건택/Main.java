package boj_1197_박건택;
/*
 * 인접 행렬을 사용해서 메모리 초과가 발생
 * -> 인접리스트 이용해보자
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<Edge>[] adjList = new ArrayList[V+1];
		// adjList 배열 내부의 List들 초기화
		for (int i = 0; i < V+1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			adjList[start].add(new Edge(start, end, value));
		}

		// ---------여기까지 인접리스트 입력----------
		// ---------아래부터 프림 알고리즘----------

		// 필요한것 : visited, dist
		boolean[] visited = new boolean[V + 1];
		int[] dist = new int[V + 1];

		// dist를 최대값으로 초기화
		int INF = Integer.MAX_VALUE;
		Arrays.fill(dist, INF);

		// 1번 노드부터 시작
		dist[1] = 0;

		// 1번 노드부터 V번 노드까지 돌면서 방문하지 않고, dist가 제일 작은 놈 찾기
		for (int i = 1; i < V + 1; i++) {
			int min = INF;
			int idx = 0;
			if (!visited[i] && dist[i] < min) {
				min = dist[i];
				idx = i;
			}

			// 해당 노드 방문처리
			visited[i] = true;

			// 해당 노드에 인접하면서, 방문하지 않았고, 현재 dist 값보다 작은 가중치를 가진놈 찾아서 갱신하기
			for (int j = 1; j < V + 1; j++) {
				if (adjList[j].get() && !visited[j] && adjList[idx].get(INF)) {

				}
			}
		}

//		System.out.println(Arrays.toString(dist));
		System.out.println(ans);
	}
}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int value;

	public Edge(int start, int end, int value) {
		super();
		this.start = start;
		this.end = end;
		this.value = value;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.value, o.value);
	}

}
