package boj_2252_줄세우기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, A, B;
	static List<Integer>[] adjList;
	static int[] connected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		connected = new int[N+1];
		
		for (int i=0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			adjList[A].add(B);
			connected[B]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i=1; i<=N; i++) {
			if (connected[i] == 0) {
				queue.add(i);
			}
		}
		
		int curr;
		
		while (!queue.isEmpty()) {
			
			curr = queue.poll();
			sb.append(curr).append(' ');
			
			for (int directed: adjList[curr]) {
				connected[directed]--;
				if (connected[directed] == 0) {
					queue.add(directed);
					
				}
				
			}
			
		}
		
		bw.write(sb.toString());
		bw.close();
		
	}
	
}