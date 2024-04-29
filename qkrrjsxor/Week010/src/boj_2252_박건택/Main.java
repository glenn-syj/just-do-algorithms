package boj_2252_박건택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	//사람(노드) 수
		int E = Integer.parseInt(st.nextToken());	//간선 수
		
		List<Integer>[] adjList = new List[V+1];	//1번 부터 시작 ( 0번은 버려)
		int[] degree = new int[V+1];		//진입 차수 저장
		
		for(int i =1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}//리스트 배열 초기화
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			adjList[start].add(end);
			degree[end]++;
		}	//인접 리스트와 진입 차수 배열 입력
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i<=V; i++) {
			if(degree[i]==0) {
				queue.offer(i);
			}
		}	//진입 차수가 0인 것들 큐에 삽입
		
		
		while(!queue.isEmpty()) {
			
			int node = queue.poll();
			sb.append(node + " ");
			
			for(int i = 0; i < adjList[node].size(); i++) {
				
				degree[adjList[node].get(i)]--;
				
				if(degree[adjList[node].get(i)] == 0) {
					queue.offer(adjList[node].get(i));
				}
			}
			
		}
		
		System.out.println(sb.toString());
	}
}
