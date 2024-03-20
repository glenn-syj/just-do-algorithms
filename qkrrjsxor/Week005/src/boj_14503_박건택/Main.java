package boj_14503_박건택;

/*
 * d = 0 : 북, 1 : 동, 2 : 남, 3 : 서
 *
 * 주변 4칸 중 청소되지 않은 칸 있으면 반시계 방향으로
 * -> 델타배열 : 위 오른 아래 왼 순서
 * direction -1 % 4
 * 
 * 청소 안한 칸 : 0 , 벽 : 1, 청소 한 칸 : -1로 설정하자
 * 현재 칸 확인 -> 청소 안됐으면 청소 -> 주변 4칸 청소 안돼있는 칸 있으면(0이면) d=(d-1)%4 반시계 회전
 * -> 주변 4칸 다 청소 되어있으면 후진 = d는 그대로 놔둔채... nr = r - dr[d] , nc = c - dc[d] -> nr, nc가 벽이면 끝
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { -1, 0, 1, 0 }; // 위 오른 아래 왼 순서
	static int[] dc = { 0, 1, 0, -1 };
	static int d; // 반시계 회전
	static int row;
	static int col;
	static int[] coordinate;
	static int[][] board;
	static int curR;
	static int curC;
//	static int nr;
//	static int nc;
//	static int count;
	static Stack<int[]> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str = br.readLine();
		st = new StringTokenizer(str);
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		stack = new Stack<int[]>();
		coordinate = new int[2];
		board = new int[row][col];

		str = br.readLine();
		st = new StringTokenizer(str);

		curR = Integer.parseInt(st.nextToken());
		curC = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		coordinate = new int[2];
		coordinate[0] = curR;
		coordinate[1] = curC;
		stack.push(coordinate);

		for (int r = 0; r < row; r++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for (int c = 0; c < col; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int nr = 0;
		int nc = 0;
		int count = 0;
		int blank = 0;
		
		one: while (true) {

			
//			for(int i = 0; i< row; i++) {
//				for(int t = 0; t< col; t++) {
//					System.out.print(board[i][t] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			blank = 0;
			
			// 현재칸이 0이면
			if (board[curR][curC] == 0) {
				board[curR][curC] = -1;
				count++;
			}
			// 주변 4칸 탐색
			for (int i = 0; i < 4; i++) {
				nr = curR + dr[i];
				nc = curC + dc[i];

				if (board[nr][nc] == 0) { // 빈칸이 있으면
					blank ++;

				}
			}
			
			if (blank >0) { //주변 4칸에 빈칸이 있으면
				d = (d + 3) % 4; // 반시계 회전
				if(board[curR+dr[d]][curC+dc[d]] == 0) {
					
					curR = curR+ dr[d];
					curC = curC+ dc[d];
				}


				continue one;
			}
			else {	//주변 4칸에 빈칸이 없으면
				if (board[curR - dr[d]][curC - dc[d]] == 1) {	//뒤가 벽이면
					break one;
				}
				else {
					curR = curR- dr[d];
					curC = curC- dc[d];
					
				}
			}

		}
		
		System.out.println(count);

	}
}
