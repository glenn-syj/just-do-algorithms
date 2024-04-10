package boj_1197_최소스패닝트리_크루스칼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * 크루스칼 방식으로 풀었다.
 * 
 * 프림과 다익스트라 방식으로도 풀어보겠다.
 * 
 */

public class Main {
	
	// 크루스칼은 유니온 파인드 방식을 사용하여, 그룹화 되었는지 체크한다.
	static int[] parent;
	
	// Comparable<Node>를 상속받고, toCompare 메서드를 오버라이드 해주면,
	// priority queue를 사용했을 때, 자동으로 정렬된다.
	public static class Node implements Comparable<Node>{
		// 각 간선의 정보를 담아주기 위해, start, end, weight 변수를 설정해주었다.
		public int start;
		public int end;
		public int weight;
		
		public Node(int start, int end, int weight){
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 정점의 개수 v
		int v = Integer.parseInt(st.nextToken());
		
		// 간선의 개수 e
		int e = Integer.parseInt(st.nextToken());
		
		// 처음 크루스칼을 구현할 때, 유니온 파인드 방식을 아예 떠올리지 못했다.
		// parent 배열도 처음에는 만들어주지 않고, visited 배열로 체크하려 했다.
		parent = new int[v + 1];
		
		// 처음 각 그룹을 초기화해주는 과정
		for(int i = 1; i <= v; i++) {
			// 각 요소의 부모는 자기자신이다.
			parent[i] = i;
		}
		
		// priority queue를 생성한다.
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 처음 유니온 파인드로 체크해주지 않았을 때, 메모리 초과 문제를 해결하기 위해,
		// for문 밖에서 변수를 생성하고, 이것을 재활용하려 했다.
		// 사실상 굳이 필요하지 않은 부분.
		int start = 0;
		int end = 0;
		int weight = 0;
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());

			// 주어지는 입력값을 받아서,
			start = Integer.parseInt(st.nextToken());
			
			end = Integer.parseInt(st.nextToken());
			
			weight = Integer.parseInt(st.nextToken());
			
			// Node 객체를 생성해준 이후, 가중치 비교를 통한 정렬을 위해
			// priority queue에 넣어주었다.
			pq.add(new Node(start, end, weight));
			
		}
		
		// 예전에도 했던 실수, 
		// for 반복문의 범위를 설정해줄 때 i < pq.size()를 했다가.
		// 반복문 내에서 pq.poll()로 pq의 크기가 줄어들기 때문에,
		// pq 전체 요소를 탐색하지 못하고 중간에 반복문이 중단되었다.
		int pqSize = pq.size();
		
		int sum = 0;
		
		Node no;
		
		for(int i = 0; i < pqSize; i++) {
			no = pq.poll();
			// 시작지점과 끝지점을 가져와, 둘의 최고 리더를 변수에 할당해주었다.
			int to = find_set(no.start);
			int from = find_set(no.end);
			
			// 두 최고리더가 같지 않는 경우,
			// 즉 같은 그룹에 들어있지 않으면 이 둘을 부모 자식 관계로 만들어서 그룹화해준다.
			if(to != from) {
				// 선을 이어준 것이므로, 가중치를 더해준다.
				sum += no.weight;
				// 실제로 둘을 부모 자식 관계로 만드는 union을 실시해준다.
				union(to, from);
			}
			
		}
		
		System.out.println(sum);
		
	}
	
	// 자기자신이 부모인지 확인하는 메서드
	public static boolean isParent(int x) {
		if(parent[x] == x) {
			return true;
		}else {
			return false;
		}
	}
	
	// 부모를 찾는 메서드 find-set
	public static int find_set(int x){
		if(x == parent[x]) {
			return x;
		}else {
			return parent[x] = find_set(parent[x]);
		}
		
	}
	// 유니온 하는 메서드 union
	public static void union(int x, int y) {
		parent[find_set(y)] = find_set(x);
	}
	
	
}