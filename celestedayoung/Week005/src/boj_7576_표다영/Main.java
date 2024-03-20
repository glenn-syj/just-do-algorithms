package boj_7576_토마토;

/*	[Point]
 *	- BFS를 이용하자.
 *  - 탐색의 시작점이 여러 개인 점을 고려하자.
 *  - 날짜를 count 하는 방법: 익힌 토마토에 접근할 때마다 이전 토마토 좌표의 값 +1을 넣어준다. 
 *  - 처음 토마토가 1로 주어지므로 최종 날짜는 -1을 해주어야 한다.
 *  
 *  [Logic]
 *  1. 익은 토마토가 있는 위치를 찾고 해당 토마토의 좌표를 Queue에 넣는다.
 *  2. sookSung 메소드를 이용해 토마토를 익히면서 날짜를 count 한다.
 *  	2-1. Queue에서 좌표를 꺼낸다.
 *  	2-2. 해당 좌표에서 네 방향에 대한 좌표를 만든다.
 *  	2-3. 새로 만든 좌표가 경계조건을 만족한다면 해당 좌표의 토마토를 익힌다.
 *  3. isSooksung 메소드를 이용해 토마토의 익힘 여부를 검사하고 날짜를 update 한다. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int row, col;
	static int[][] box;
	static Deque<int[]> myQueue = new ArrayDeque<>();
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		box = new int[row][col];
		
		for (int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < col; c++) {
				box[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (box[r][c] == 1) {
					myQueue.addLast(new int[] {r, c});
				}
			}
		}
		
		System.out.println(sooksung());

	}
	
	static boolean isSooksung() {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (box[r][c] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	static int sooksung() {
		
		while (!myQueue.isEmpty()) {			
			
			int[] now = (int[]) myQueue.pollFirst();
			int nowR = now[0];
			int nowC = now[1];
			
			for (int d = 0; d < 4; d++) {
				
				int nextR = now[0] + dr[d];
				int nextC = now[1] + dc[d];
				
				if (nextR >= 0 && nextR < row && nextC >= 0 & nextC < col && box[nextR][nextC] == 0) {
					box[nextR][nextC] = box[nowR][nowC] + 1;
					myQueue.addLast(new int[]{nextR, nextC});
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		if (isSooksung()) {
			return -1;
		} else {
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					if (box[r][c] > max) {
						max = box[r][c];
					}
				}
			}
			return max - 1;
		}
	}
}
