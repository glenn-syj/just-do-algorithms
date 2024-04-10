package boj_1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	
	int start, end, weight;
	
	public Edge() {}
	
	public Edge(int start, int end, int weight)  {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge edge) {
		return this.weight - edge.weight;
	}
}

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V, E, pick, result;
	static int[] parent;
	static Edge[] edges;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1];
		
		for (int i = 1; i <= V; i++) {
			makeSet(i);
		}

		edges = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edges);
		
		result = 0;
		pick = 0;
		
		for (int i = 0; i < E; i++) {
			
			int parent1 = findSet(edges[i].start);
			int parent2 = findSet(edges[i].end);
			
			if (parent1 != parent2) {
				union(parent1, parent2);
				result += edges[i].weight;
				pick++;
			}
			
			if (pick == (V - 1)) {
				break;
			}
		}
		
		System.out.println(result);
		
	}
	
	public static void makeSet(int x) {
		parent[x] = x;
	}
	
	public static int findSet(int x) {
		
		if (parent[x] != x) {
			parent[x] = findSet(parent[x]);
		}
		
		return parent[x];
	}
	
	public static void union(int x, int y) {
		parent[y] = x;
	}
}
