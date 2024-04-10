package boj_1197_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
	int A;
	int B;
	int W;
	
	Edge(){
		
	}

	Edge(int a, int b, int w) {
		A = a;
		B = b;
		W = w;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.W, o.W);
	}
}

public class Main {
	static int V, E;
	static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();
		
		
		Edge[] edges = new Edge[E];
		
		for(int i = 0; i < E; i++) {
			edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		
		Arrays.sort(edges);
		
		parent = new int[V + 1];
		
		for(int i = 0; i < V + 1; i++) {
			parent[i] = i;
		}
		
		
		int answer = 0;
		int pick = 0;
		
		for(int i = 0; i < E; i++) {
			int px = findSet(edges[i].A);
			int py = findSet(edges[i].B);
			
			if(findSet(px) != findSet(py)) {
				union(px, py);
				answer += edges[i].W;
				pick++;
			}
			
			if(pick == (V-1)) break;
		}
		
		System.out.println(answer);
	}

	private static void union(int px, int py) {
		parent[py] = px;
	}

	private static int findSet(int x) {
		if(x != parent[x]) {
			parent[x] = findSet(parent[x]);
		}
		
		return parent[x];
	}
	
	
}
