package boj_1005_ACMCraft;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* B1005. ACM Craft
 * 
 * 1. 조건
 * 	1-1. 위상 정렬을 이용하자
 * 	1-2. 해당 index에 도달하면 지금까지 계산된 모든 비용을 return
 *
 * 2. 풀이
 * 	2-1. 위상 정렬을 기본적으로 이용
 * 		-> 목적지가 queue에서 나온다면 목적지 건설에 걸리는 시간을 더하고 바로 종료
 * 		-> 처음 시작 정점을 모르기 때문에, 위상 정렬을 역으로 이용
 * 		=> 할 필요가 없다! (왜냐하면 모든 건물이 건설 가능하도록 주어지는 까닭)
 * 	2-2. A에서 B 방향을 B에서 A 방향으로 이용
 * 
 */

public class Main {

	static int T, N, K, X, Y, W;
	static int[] costs, connected, newCosts;
	static List<Integer>[] adjList;
	static List<Integer>[] parents;
	static boolean[] mandatory;
	static boolean[] visited;
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			costs = new int[N+1];
			newCosts = new int[N+1];
			connected = new int[N+1];
			adjList = new ArrayList[N+1];
			mandatory = new boolean[N+1];
			queue = new LinkedList<>();
			
			int ans = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i=0; i<=N; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for (int edgeId=0; edgeId<K; edgeId++) {
				st = new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				adjList[X].add(Y);
				connected[Y]++;
			}
			
			
			
			W = Integer.parseInt(br.readLine());
			mandatory[W] = true;
			findParents(W);
			
			for (int i=1; i<=N; i++) {
				newCosts[i] = costs[i];
				
				if (connected[i] == 0 && mandatory[i]) {
					queue.add(i);
					visited[i] = true;
				}
			}


			
			int curr;
			int costVal;
			
			while (!queue.isEmpty()) {
				curr = queue.poll();
				
				for (int next: adjList[curr]) {
					connected[next]--;

					costVal = Math.max(newCosts[next], newCosts[curr] + costs[next]);
					if (costVal > newCosts[next]) {
						newCosts[next] = costVal;
					}
					
					
					if (connected[next] == 0 && mandatory[next]) {
						queue.add(next);
					}
					
				}
				
				
			}
			
			ans = newCosts[W];
			sb.append(ans).append('\n');
			bw.write(sb.toString());
			sb.setLength(0);
			bw.flush();
			
		}
		
		bw.close();
	}
	
	public static void findParents(int x) {
		for (int parent: parents[x]) {
			if (!mandatory[parent]) {
				findParents(parent);
				mandatory[parent] =true;
			}
		}
	}


	
}