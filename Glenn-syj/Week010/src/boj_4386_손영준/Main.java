package boj_4386_별자리만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static double X, Y;
	static double[][] stars;
	static double ans;
	static boolean[] visited;
	
	static class Edge implements Comparable<Edge> {
		
		int start;
		int end;
		double dist;
		
		public Edge() {
			
		}
		
		public Edge(int start, int end, double dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Edge o) {
			return (int) Double.compare(this.dist, o.dist);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		stars = new double[N][2];
		ans = 0d;
		visited = new boolean[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			X = Double.parseDouble(st.nextToken());
			Y = Double.parseDouble(st.nextToken());
			
			stars[i] = new double[] {X, Y};			
		}
		
		List<Edge>[] adjList = new ArrayList[N];
		PriorityQueue<Edge> pqEdge = new PriorityQueue<>();

		for (int i=0; i<N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				
				double dist = calculateDist(stars[i], stars[j]);
				
				adjList[i].add(new Edge(i, j, dist));
				adjList[j].add(new Edge(j, i, dist));
				
			}
		}
		
		pqEdge.addAll(adjList[0]);
		visited[0] = true;
		Edge currEdge;
		
		int peek = 1;
		double ans = 0d;
		
		while (peek != N) {
			
			currEdge = pqEdge.poll();
			
			if (visited[currEdge.end]) continue;
			
			visited[currEdge.end] = true;
			peek++;
			ans += currEdge.dist;
			
			pqEdge.addAll(adjList[currEdge.end]);
			
			
		}
		
		sb.append(Math.round((ans*100d))/100d);
		bw.write(sb.toString());
		bw.close();
		sb.setLength(0);
		
	}
	
	public static double calculateDist(double[] start, double[] end) {
		return Math.sqrt(Math.pow(Math.abs(start[0]-end[0]), 2) 
				+ Math.pow(Math.abs(start[1]-end[1]), 2));
	}
	
}