package boj_1012_유기농배추;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N, K;
	
	static int[][] cabbage;
	static boolean[][] visit;
	static int count;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int c = 0; c < T; c++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cabbage = new int[M][N];
			visit = new boolean[M][N];
			count = 0;
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				cabbage[p1][p2] = 1;
			}
			
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(cabbage[i][j] == 1 && !visit[i][j]) {
						bfs(i, j);
						count++;
					}
				}
			}
			
			System.out.println(count);
			
		}
		
	}
	
	
	public static void bfs(int r, int c) {
		Queue<int[]> qu = new LinkedList<>();
		qu.add(new int[] {r, c});
		
		
		while(!qu.isEmpty()) {
			r = qu.peek()[0];
			c = qu.peek()[1];
			
			visit[r][c] = true;
			
			qu.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				
				if(nr >= 0 && nc >= 0 && nr < M && nc < N) {
					if(!visit[nr][nc] && cabbage[nr][nc] == 1) {
						qu.add(new int[] {nr, nc});
						visit[nr][nc] = true;
					}
				}
			}
			
			
			
		}
		
	}
}