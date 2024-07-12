package boj_2606_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, M;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		graph = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();

			graph[node1].add(node2);
			graph[node2].add(node1);
		}

//		System.out.println(Arrays.toString(graph));

		visited = new boolean[N + 1];
		
		count = 0;

		dfs(1);
		
		System.out.println(count - 1);
	}

	private static void dfs(int current) {
		count++;
//		System.out.println(current);
		visited[current] = true;
		
		for(int node: graph[current]) {
			if(!visited[node]) {
				dfs(node);
			}
		}
		
	}
	
	
}
