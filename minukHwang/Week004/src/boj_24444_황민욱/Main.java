package boj_24444_황민욱;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, R;
	
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] order;
	static int orderNum;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		order = new int[N+1];
		
		for(int i = 1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			
			graph[node1].add(node2);
			graph[node2].add(node1);
		}
		
		bfs(R);
		
		for(int i = 1; i < N+1; i++) {
			sb.append(order[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	public static void bfs(int node) {
		Queue<Integer> queue = new LinkedList<>();
		visited[node] = true;
		queue.add(node);
		
		while(!queue.isEmpty()){
			int current = queue.poll();
			order[current] = ++orderNum;
			Collections.sort(graph[current]);
			
			for(int i = 0; i < graph[current].size(); i++) {
				int child = graph[current].get(i);
				if(!visited[child]) {
					queue.add(child);
					visited[child] = true;
				}
			}
		}
	}
}
