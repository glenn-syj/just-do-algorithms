package boj_1916_박건택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static int N, M;
	static ArrayList<ArrayList<Node>> adjList;
	static int[] dist;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		   
		N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        adjList = new ArrayList<ArrayList<Node>>();
        dist = new int[N + 1];
        visit = new boolean[N + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }//초기화
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
 
            adjList.get(start).add(new Node(end, weight));
        }
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        System.out.println(dijkstra(start, end));
	}
	
	public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;
 
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.v;
 
            if (!check[cur]) {
                check[cur] = true;
 
                for (Node node : adjList.get(cur)) {
                    if (!check[node.v] && dist[node.v] > dist[cur] + node.w) {
                        dist[node.v] = dist[cur] + node.w;
                        pq.add(new Node(node.v, dist[node.v]));
                    }
                }
            }
        }
        return dist[end];
    }
}
