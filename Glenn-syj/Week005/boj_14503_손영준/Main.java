package boj_14503_로봇청소기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* B14503. 로봇 청소기
 * 
 * 1. 조건
 * 	1-1. 로봇 청소기가 있는 방은 N*M 크기의 직사각형
 * 	1-2. 청소기는 동 서 남 북 중 하나 바라봄
 * 	1-3. 처음에 빈 칸은 전부 청소되지 않은 상태이다
 * 	1-4. if (현재 칸 청소 X) - 현재 칸 청소
 * 		if ( 주변 4칸이 모두 청소됨)
 * 			if ( 방향 그대로 후진 가능): 한 칸 후진 후 1번으로
 * 			else: 작동을 멈춘다
 * 		else if ( 청소되지 않은 빈 칸 O)
 * 			if (반 시계 방향 90도가 청소 X): 전진
 * 	1-5. d=0~3, (-1,0) (0, 1), (1, 0) (0, -1) 
 * 2. 풀이
 * 	2-1. 로봇 청소기 클래스를 이용 -> 좌표값에 대해서 검사 후 원본 배열 변경
 * 	2-2. 1이 벽이고, 0이 청소되지 않은 빈칸
 * 		-> 청소되었다면 값을 -1로 바꿔주자.
 * 	2-3. 가장자리가 모두 1로 막혀있으므로, rangeCheck의 필요가 없다!
 * 
 */


public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;
	
	static int[][] board;
	static int N, M;
	static int R, C, D;
	static int[][] deltas = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	
	static class Cleaner {
		
		int row;
		int col;
		int dir;
		int cleaned = 0;
		boolean isOn = true;
		
		public Cleaner() {
			
		}
		
		public Cleaner(int row, int col, int dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		
		public void clean() {
			if (board[row][col] == 0) {
				board[row][col] = -1;
				cleaned++;
			}
		}
		
		public boolean isOn() {
			return isOn;
		}
		
		public int getCleaned() {
			return cleaned;
		}
		
		public void check() {
			int nextRow, nextCol;
			for (int ptr=1; ptr<=4; ptr++) {
				nextRow = row + deltas[(dir-ptr+4)%4][0];
				nextCol = col + deltas[(dir-ptr+4)%4][1];
				if (board[nextRow][nextCol] == 0) {
					dir = (dir-ptr+4)%4;
					row = nextRow;
					col = nextCol;
					return;
				}
			}
			
			// 이미 주변에 청소되지 않는 빈 칸이 없다면 후진 할 수 있는지 체크
			if (board[row-deltas[dir][0]][col-deltas[dir][1]] == 1) {
				isOn = false;
			} else {
				row = row-deltas[dir][0];
				col = col-deltas[dir][1];
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
	
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		Cleaner cleaner = new Cleaner(R, C, D);
		
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<M; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (cleaner.isOn()) {
			cleaner.clean();
			cleaner.check();
		}
		
		
		
		sb.append(cleaner.getCleaned());
		bw.write(sb.toString());
		bw.close();
		
	}
}

