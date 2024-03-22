package boj_16236_아기상어;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* B16236. 아기 상어
 * 
 * 1. 조건
 * 	1-1. 가장 처음에 아기 상어의 크기는 2
 * 	1-2. 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 지나가기 가능
 * 	1-3. 자신의 크기보다 작은 물고기 먹기 가능, 같으면 통과는 가능
 * 	1-4. (1) 먹을 수 있는 물고기가 없으면 아기 상어는 엄마 상어에게 도움 요청
 * 		(2) 먹을 수 있는 물고기가 1마리면 바로
 * 			-> 이후 거리가 가장 가까운 순
 * 			-> 이후 위, 왼쪽
 * 	1-5. 크기와 같은 수의 물고기를 먹을 때 마다 증가
 * 2. 풀이
 * 	2-1. BFS 이용
 * 
 */

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
	
	static int[][] deltas = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	
	static int N;
	static int[][] board;
	static boolean[][] visited;
	static int[] current = new int[2];
	static int fishCount = 0;
	static int sharkSize = 2;
	static int eatenFish = 0;
	static int moved = 0;
	
	static PriorityQueue<int[]> queue;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];
		queue = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0]-o2[0] > 0) {
					return 1;
				} else if (o1[1] == o2[1]) {
					if (o1[0] - o2[0] > 0) {
						return 1;
					} else if (o1[0] - o2[0] == 0) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			}
			
		});
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					current[0] = i;
					current[1] = j;
				} else if (board[i][j] != 0) {
					fishCount++;
				}
			}
		}
		
		queue.add(new int[] {current[0], current[1], 0});
		int currentDist = 0;
		int[] ptr;
		int nextRow, nextCol;
		
		while (fishCount > 0 && !queue.isEmpty()) {
			ptr = queue.poll();
			System.out.println(Arrays.toString(ptr));
			for (int[] delta: deltas) {
				queue.add(new int[])
			}
			
			
		}
		
	}
	
	public static boolean posCheck(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < N && !visited[row][col] && board[row][col] <= sharkSize);
	}
}