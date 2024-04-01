package boj_12851_박건택;
/*
 * DP를 이용해 풀려고 하였지만, 틀린 접근이었다.
 * BFS 카테고리라는 정보를 얻어 다시 풀어보았다.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] dist = new int[100001];
		boolean[] visited = new boolean[100001];

		st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(x);
		
		while
		
	}
}
