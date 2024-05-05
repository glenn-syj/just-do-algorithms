package boj_14938_서강그라운드;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* B14938. 서강 그라운드
 * 
 * 1. 조건
 * 	1-1. 다익스트라를 이용해 최단 거리를 구하기
 * 	1-2. 길이 서로 연결되어 있지 않은 곳도 있다고 가정해야 함
 * 		-> 각 점에 대해서 매번 최단거리 구하기 적용
 * 2. 풀이
 * 	2-1. N번 다익스트라를 반복
 * 		-> PriorityQueue를 이용해서 다익스트라를 만들자
 * 
 */

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	static int N, M, R, X, Y, T;
	static int[] itemCounts;
	static boolean[] visited;
	static PriorityQueue<Edge> pq;
	static List<Edge>[] adjList;
	
	static class Edge implements Comparable<Edge> {
		
		int start, end, weight;
		
		public Edge() {
			
		}
		
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 정점의 번호는 1번부터 시작한다
		itemCounts = new int[N+1];
		adjList = new ArrayList[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int v=1; v<=N; v++) {
			itemCounts[v] = Integer.parseInt(st.nextToken());
			adjList[v] = new ArrayList<>();
		}
		
		for (int e=0; e<R; e++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			
			adjList[X].add(new Edge(X, Y, T));
			adjList[Y].add(new Edge(Y, X, T));
		}
		
		
		// 위에서 초기화하고 아래에서는 값을 할당만 함
		int[] dists = new int[N+1];
		Edge curr;
		// 최대 아이템 개수를 저장하는 변수
		int maxVal = Integer.MIN_VALUE;
		int localMax;
		int pick;
		// 각 vertex에 대해서 최단 거리를 기반으로 해 접근가능한 모든 item 개수를 구한다
		for (int v=1; v<=N; v++) {
			
			localMax = 0;
			
			for (int i=1; i<=N; i++) {
				dists[i] = INF;
			}
			
			dists[v] = 0;
			
			pq = new PriorityQueue<Edge>();
			pq.addAll(adjList[v]);
			
			while (!pq.isEmpty()) {
				
				curr = pq.poll();
				
				for (Edge e: adjList[curr.start]) {

					if (dists[e.end] > dists[curr.start] + e.weight) {
						dists[e.end] = dists[curr.start] + e.weight;
						pq.addAll(adjList[e.end]);
					}
					
				}
				
			}
			
			for (int i=1; i<=N; i++) {
				if (dists[i] <= M) {
					localMax += itemCounts[i];
				}
			}
			
			if (maxVal < localMax) {
				maxVal = localMax;
			}
			
		}
		
		sb.append(maxVal);
		bw.write(sb.toString());
		bw.close();
		
		
	}

}