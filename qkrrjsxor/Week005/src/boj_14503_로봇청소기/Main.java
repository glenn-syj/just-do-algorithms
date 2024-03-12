package boj_14503_로봇청소기;

/*
 * d = 0 : 북, 1 : 동, 2 : 남, 3 : 서
 *
 * 주변 4칸 중 청소되지 않은 칸 있으면 반시계 방향으로
 * -> 델타배열 : 위 오른 아래 왼 순서
 * direction -1 % 4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int d; 	//반시계 회전
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str = br.readLine();
		st = new StringTokenizer(str);
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());

		Queue<int[]> queue = new LinkedList<>();
		int[] coordinate = new int[2];
		int[][] board = new int[row][col];
		
		str = br.readLine();
		st = new StringTokenizer(str);
				
		int curR = Integer.parseInt(st.nextToken());
		int curC = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		coordinate = new int[2];
		coordinate[0] = curR;
		coordinate[1] = curC;
		queue.add(coordinate);

		for (int r = 0; r < row; r++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for (int c = 0; c < col; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(!queue.isEmpty()) {
			
			//현재칸이 -1이면
		}
		
		
		
		
	}
}
