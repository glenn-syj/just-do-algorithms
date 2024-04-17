package 수영대회결승전;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

class Swimmer {
	
	int r, c, time;
	
	Swimmer(int r, int c, int time) {
		this.r = r;
		this.c = c;
		this.time = time;
	}
	
}

public class Solution {
	
	static Scanner sc;
	static StringTokenizer st;
	static int testNum, N;
	static int[] end;
	static int[][] sea;
	static boolean[][] visited;
	static Deque<Swimmer> myQueue;
	
	static int[] deltaR = {-1, 1, 0, 0};
	static int[] deltaC = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		
		testNum = sc.nextInt();
		
		for (int t = 1; t <= testNum; t++) {
			
			N = sc.nextInt();
			sc.nextLine();
			
			sea = new int[N][N];
			visited = new boolean[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(sc.nextLine());
				for (int c = 0; c < N; c++) {
					sea[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			myQueue = new ArrayDeque<>();
			
			st = new StringTokenizer(sc.nextLine());
			myQueue.addLast(new Swimmer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
			
			st = new StringTokenizer(sc.nextLine());
			end = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			System.out.printf("#%d ", t);
			swimming();
			
		}
		
	}
	
	public static void swimming() {
		
		while (!myQueue.isEmpty()) {
			
			Swimmer current = myQueue.pollFirst();
			visited[current.r][current.c] = true;
			
			if (current.r == end[0] && current.c == end[1]) {
				System.out.println(current.time);
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				
				int newR = current.r + deltaR[d];
				int newC = current.c + deltaC[d];
				
				if (newR >= 0 && newR < N && newC >= 0 && newC < N && !visited[newR][newC]) {
					
					if (sea[newR][newC] == 0) {
						myQueue.addLast(new Swimmer(newR, newC, current.time + 1));
						visited[newR][newC] = true;
					} else if (sea[newR][newC] == 2) {
						if (current.time % 3 == 2) {
							myQueue.addLast(new Swimmer(newR, newC, current.time + 1));
							visited[newR][newC] = true;
						} else {
							myQueue.addLast(new Swimmer(current.r, current.c, current.time + 1));
						}
					}
				}
			}
			
		}
		
		System.out.println(-1);
	}
	

}
