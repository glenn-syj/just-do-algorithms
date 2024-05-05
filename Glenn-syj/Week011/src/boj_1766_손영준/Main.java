package boj_1766_문제집;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* B1766. 문제집
 * 
 * 1. 조건
 * 	1-1. 1번부터 N번까지 총 N개의 문제로 되어있는 문제집 (1이 가장 쉽고 N이 가장 어려움)
 * 	1-2. '먼저 푸는 것이 좋은 문제' <- 위상 정렬 이용
 * 	1-3. (1) N 개의 문제는 모두 풀어야 한다
 * 		 (2) 먼저 푸는 것이 좋은 문제가 있는 문제는, 그 문제를 먼저 품
 * 		 (3) 가능하면 쉬운 문제부터 풀어야 한다
 * 2. 풀이
 * 	2-1. 위상정렬 구현
 */

public class Main {
	
	static int N, M;
	static List<Integer>[] adjList;
	static int[] depths;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		depths = new int[N+1];
		
		
		for (int i=0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		int X, Y;
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			
			adjList[X].add(Y);
			depths[Y]++;
		}
		
		for (int i=1; i<=N; i++) {
			Collections.sort(adjList[i]);
		}
		
		for (int i=1; i<=N; i++) {
			if (depths[i] == 0) {
				queue.add(i);
			}
		}
		
		int curr;
		while (!queue.isEmpty()) {
			
			curr = queue.poll();
			sb.append(curr).append(' ');
			for (int next: adjList[curr]) {
				depths[next]--;
				
				if (depths[next] == 0) {
					queue.add(next);
				}
			}
		}
		
		bw.write(sb.toString());
		bw.close();
		
	}
}