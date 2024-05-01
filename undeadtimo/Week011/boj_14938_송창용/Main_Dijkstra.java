package boj_14938_서강그라운드;

// bfs를 이용한 서강그라운드 풀이.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	public int end;
	public int dist;
	
	Node(int end, int dist){
		this.end = end;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.dist - o.dist;
	}
	
}
public class Main_Dijkstra {
	
	static int ans = Integer.MIN_VALUE;
	static int nodeCnt;
	static int limit;
	static int roadCnt;
	
	static int[] itemNum;
	static List<Node>[] link;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		nodeCnt = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		roadCnt = Integer.parseInt(st.nextToken());
		
		itemNum = new int[nodeCnt + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= nodeCnt; i++) {
			itemNum[i] = Integer.parseInt(st.nextToken());
		}
		
		// List<Node>[] link = new ArrayList[nodeCnt + 1];
		// 이 형태로 자료구조를 만들 것이고, 각 node 위치에 해당하는 번호를,
		// 인덱스와 매칭시켜, 해당 node에 연결되어 있는 곳들을 Node 객체로 만들어 넣어줄 것이다.
		link = new ArrayList[nodeCnt + 1];
		
		for(int i = 0; i < link.length; i++) {
			link[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < roadCnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			link[start].add(new Node(end , dist));
			link[end].add(new Node(start, dist));
		}
		
		
		for(int i = 1; i <= nodeCnt; i++) {
			ans = Math.max(bfs(i), ans);
		}
		
		System.out.println(ans);
		
	}
	
	public static int bfs(int start) {
		int result = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[nodeCnt + 1];
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(visited[node.end]) {
				continue;
			}
			result += itemNum[node.end];
			visited[node.end] = true;
			
			for(Node next : link[node.end]) {
				if(!visited[next.end] && limit - (node.dist + next.dist) >= 0){
					pq.add(new Node(next.end, node.dist + next.dist));
				}
			}
			
		}
		
		return result;
	}
	
}