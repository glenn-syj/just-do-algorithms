package boj_12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Sister {
	
	int position, time;
	
	public Sister(int position, int time) {
		this.position = position;
		this.time = time;
	}
}

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static Deque<Sister> myQueue;
	
	static boolean[] visited = new boolean[100001];
	static int minTime = Integer.MAX_VALUE;
	static int methodCnt = 0;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		myQueue = new ArrayDeque<Sister>();
		myQueue.addLast(new Sister(N, 0));
		
		findBrother(N, K);
		System.out.printf("%d%n%d", minTime, methodCnt);
		
	}
	
	public static void findBrother(int start, int end) {
		
		
		while (!myQueue.isEmpty()) {
			
			Sister current = myQueue.pollFirst();
			
			if (current.position == end) {
				if (current.time < minTime) {
					minTime = current.time;
				} 
				if (current.time == minTime) {
					methodCnt++;
				} 
			} else {
				visited[current.position] = true;
			}
			
			if (current.time + 1 <= minTime) {
				if (current.position - 1 >= 0 && !visited[current.position - 1]) {
					myQueue.addLast(new Sister(current.position - 1, current.time + 1));
				}
				if (current.position + 1 <= 100000 && !visited[current.position + 1]) {
					myQueue.addLast(new Sister(current.position + 1, current.time + 1));
				}
				if (current.position * 2 <= 100000 && !visited[current.position * 2]) {
					myQueue.addLast(new Sister(current.position * 2, current.time + 1));
				}
			}
		}
	}
}
