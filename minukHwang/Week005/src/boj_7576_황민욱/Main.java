package boj_7576_황민욱;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Tomato {
	int r;
	int c;
	boolean isEmpty;
	boolean isRipe;
	int date;
	
	Tomato(){
		
	}
	
	Tomato(int r, int c){
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Tomato [isEmpty=" + isEmpty + ", isRipe=" + isRipe + ", date=" + date + "]";
	}
}

public class Main {
	static int M, N;
	static Tomato[][] map;
	static boolean[][] visited;
	static Queue<Tomato> queue;
	static int[] deltaR = { -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();
		N = sc.nextInt();
		map = new Tomato[N][M];
		visited = new boolean[N][M];
		queue = new LinkedList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				// 0은 토마토, 1은 익은 토마토, -1은 토마토 없음
				int status = sc.nextInt();
				// 디폴트 안 익은 토마토
				map[r][c] = new Tomato(r, c);

				if (status == 1) {
					// 익은 토마토
					map[r][c].isRipe = true;
					queue.add(map[r][c]);
					visited[r][c] = true;
				} else if (status == -1) {
					// 비어있음
					map[r][c].isEmpty = true;
				}
			}
		}
		
		// bfs 방식으로 완전 탐색
		while(!queue.isEmpty()) {
			Tomato currentTomato = queue.poll();
			int currentR = currentTomato.r;
			int currentC = currentTomato.c;
			int currentDate = currentTomato.date;
			
			for(int i = 0; i < 4; i++) {
				int nextR = currentR + deltaR[i];
				int nextC = currentC + deltaC[i];
				
				// 만약에 범위가 나가지 않는다면, 방문하지 않았다면
				if(isNotOutBound(nextR, nextC) && !visited[nextR][nextC]) {
					Tomato nextTomato = map[nextR][nextC];
					visited[nextR][nextC] = true; // 다시 못가게 막아주고
					
					if(nextTomato.isEmpty) {
						// 만약 비어 있다면
						continue;
					}
					
					// 일반 토마토라면?
					nextTomato.isRipe = true;
					nextTomato.date = currentDate + 1;
					
					queue.add(nextTomato);
				}
			}
		}
		
		
		// 전체 체크 후 날짜 계산
		int answer = 0;
		
		out: for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				Tomato tomato = map[r][c];
				answer = Math.max(answer, tomato.date);
				
				// 안 익은 토마토가 있다면
				if(!tomato.isRipe && !tomato.isEmpty) {
					answer = -1;
					break out;
				}
			}
		}
	
		System.out.println(answer);
	}
	
	// 범위 체크
	public static boolean isNotOutBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	
	// 디버깅용
	public static void printMap() {
		for (int r = 0; r < N; r++) {
			System.out.print("| ");
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " | ");
			}
			System.out.println();
		}
	}

	
}
