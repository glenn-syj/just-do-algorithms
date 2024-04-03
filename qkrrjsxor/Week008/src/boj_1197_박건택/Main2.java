package boj_1197_박건택;
/*
 * 그래프 인접 행렬로 받고 최소 신장 트리 복습을 해보자
 * 
 * 가중치가 음수인 것에 주의하자 -> 다익스트라 불가능
 * 
 * 프림을 이용해서 풀어보겠다.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int[][] adjArr = new int[V + 1][V + 1];
		boolean[] visited = new boolean[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			adjArr[from][to] = value;
		}

		// ---------여기까지 인접 행렬 입력----------
		// ---------아래부터 프림 알고리즘----------
		int INF = Integer.MAX_VALUE;
		int[] dist = new int[V + 1];
		int ans = 0;
		
		Arrays.fill(dist, INF);

		// 1번 노드부터 시작
		dist[1] = 0;	

		//
		for (int i = 1; i < V + 1; i++) {

			int min = INF;
			int idx = 0;

			// 방문하지 않은 노드중 가장 작은 노드 가져오기
			for (int j = 1; j < V + 1; j++) {
				if(!visited[j] && dist[j] < min) {
					min = dist[j];
					idx = j;
				}
			}
			//해당 노드 방문처리
			visited[idx] = true;
			
			//해당 노드의 인접 노드 중,방문하지 않았고, 현재 dist 값보다 작은 친구
			for(int j = 1; j < V+1; j ++) {
				if(!visited[j] && adjArr[idx][j] > 0 && adjArr[idx][j] < dist[j]) {
					dist[j] = adjArr[idx][j];
					
				}
			}
		}
		for(int i = 1 ; i<V+1; i++) {
				ans += dist[i];
			
		}
		
//		System.out.println(Arrays.toString(dist));
		System.out.println(ans);
	}
}
