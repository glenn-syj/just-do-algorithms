package boj_14502_박건택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * NxM 연구실, 0은 빈 칸, 1은 벽, 2는 바이러스
 * 
 * 설계 : 
 * 벽을 세개 세울 수 있는 모든 경우에 대해 bfs를 하자..!
 * 
 * bfs는 큐를 이용해서 하고
 * 벽을 세울 수 있는 경우를 어떻게 구할까?
 * 
 * 
 */

public class Main2 {
	static int[][] lab;
	static int[][] labcopy;
	static boolean[][] visited;
	static int safe;
	static int N, M;
//	static List<int[]> virus;	//바이러스 위치를 저장할 리스트

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 }; // 상 우 하 좌
	static Queue<int[]> queue; // bfs용 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str;
//		virus = new ArrayList<>();

		str = br.readLine();
		st = new StringTokenizer(str);
		int tmp;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];
		visited = new boolean[N][M];
		queue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);

			for (int t = 0; t < M; t++) { // 연구소 배열 입력
				tmp = Integer.parseInt(st.nextToken());
				lab[i][t] = tmp;

				if (tmp == 2) { // 바이러스의 위치를 길이 2 배열로 리스트에 저장
					int[] coor = new int[] { i, t };
					queue.offer(coor);
				}
			}
		}

		wall(0, 0, 0);
		System.out.println(safe);
	}

	//벽 세개 세우는 경우들..?
	public static void wall(int r, int c, int cnt) {
		
		for(int r1 = 0; r1< N; r1++) {
			for(int r2 = r1; r2<N; r2++) {
				for(int r3 = r2; r3<N; r3++) {
					
					
					for(int c1 = 0; c1 < M-2; c1++) {
						for(int c2 = 0; c2 < M-1; c2++) {
							for(int c3 = 0; c3 < M; c3++) {
								
								if(lab[r1][c1] != 0 || lab[r2][c2] != 0 || lab[r3][c3] != 0) continue;
								
								lab[r1][c1] = 1;
								lab[r2][c2] = 1;
								lab[r3][c3] = 1;
								
//								for (int i = 0; i < N; i++) {
//									for (int t = 0; t < M; t++) {
//										System.out.print(lab[i][t] + " ");
//									}
//									System.out.println();
//								}
//								System.out.println();
								
								countVirus();
								
								lab[r1][c1] = 0;
								lab[r2][c2] = 0;
								lab[r3][c3] = 0;
								
//								System.out.println("r : " + r + " c : " + c + " cnt : " + cnt);
								
							}
						}
					}
				}
			}
		}
		

	}

	public static void countVirus() {
		labcopy = new int[N][M];
		//벽 세워진 경우들에 대해 bfs 돌아보자, bfs 표시 & 경계용 labcopy
		for(int i = 0; i< N; i++) {
			for(int t= 0; t< M; t++) {
				labcopy[i][t] = lab[i][t];
			}
		}
		
		
		//BFS 시작
		while (!queue.isEmpty()) {
			int[] loc = queue.poll();
			int curR = loc[0];
			int curC = loc[1];

			for (int dir = 0; dir < 4; dir++) {
				int newR = curR + dr[dir];
				int newC = curC + dc[dir];

				if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
					continue;
				}
				
				if(labcopy[newR][newC] == 0) {
					labcopy[newR][newC] = 2;
					int[] l = new int[] {newR, newC};
					queue.offer(l);
				}

			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int t = 0; t < M; t++) {
				System.out.print(labcopy[i][t] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		int temp = 0;
		for (int i = 0; i < N; i++) {
			for (int t = 0; t < M; t++) {
				if (lab[i][t] == 0) {
					temp++;
				}
			}
		}
		
		safe = Math.max(safe, temp);
	}
}
