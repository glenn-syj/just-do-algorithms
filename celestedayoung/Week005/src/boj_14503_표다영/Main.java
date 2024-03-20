package boj_14503_로봇청소기;

/*	[Point]
 *	- 후진할 때 방향을 유지해야 한다.
 *  
 *  [Logic]
 *  1. 반시계 방향으로 90°씩 회전하며 경계조건 및 청소조건을 만족하는지 검사한다.
 *  2. 만족한다면 robot 메소드를 계속해서 수행한다.
 *  3. 만족한다면 robot 메소드를 계속해서 수행한다.
 *  4. 경계조건 및 청소조건을 만족하는지 검사한다.
 *  5. 만족한다면 robot 메소드를 계속해서 수행한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int row, col, startR, startC, startDirection;
	static int count = 1;
	static int[][] room;
	static int[] dr = {-1, 0, 1, 0 };
	static int[] dc = {0, 1, 0, -1};
	
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		startDirection = Integer.parseInt(st.nextToken());
		
		room = new int[row][col];
		for (int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < col; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		robot(startR, startC, startDirection);
		
		System.out.println(count);
		
	}
	
	static void robot(int r, int c, int dir) {
		
		room[r][c] = -1;
		
		for (int d = 0; d < 4; d++) {
			
			dir = (dir + 3) % 4;
			
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (nr >= 0 && nr < row && nc >= 0 && nc < col && room[nr][nc] == 0) {
				count++;
				robot(nr, nc, dir);
				return;
			}
		}
		
		// 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우: 방향을 유지한 채 한 칸 후진
		int backDirection =(dir + 2) % 4;
		
		int br = r + dr[backDirection];
		int bc = c + dc[backDirection];
		
		if (br >= 0 && br < row && bc >= 0 && bc < col && room[br][bc] != 1) {
			robot(br, bc, dir);
		}
	}
}
