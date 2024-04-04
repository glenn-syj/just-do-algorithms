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
import java.util.PriorityQueue;
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
			adjList[end].add(new Edge(end, start, value));
		}

		// ---------여기까지 인접리스트 입력----------
		// ---------아래부터 프림 알고리즘----------

		// 필요한것 : visited, dist
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		//0번 노드부터 시작
		visited[1] = true;
		
		//0번 노드와 인접한 놈들 다 넣기
		for(Edge e : adjList[1]) {
			pq.add(e);
		}

		int pick = 1;
		int ans = 0;
		
		while(pick != V) {
			Edge e = pq.poll();
			
			if(visited[e.end]) continue;
			
			ans += e.value;
			
			visited[e.end] = true;
			pick++;
			
			pq.addAll(adjList[e.end]);
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
