package boj_3109_빵집;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static char[][] board;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		String input;
		
		board = new char[R][C];
		visited = new boolean[R][C];
		
		Deque<int[]> deq = new ArrayDeque<>();
		
		for (int row=0; row<R; row++) {
			input = br.readLine().trim();
			for (int col=0; col<C; col++) {
				board[row][col] = input.charAt(col);
			}
		}
		
		int[] curr;
		int count = 0;
		for (int row=0; row<R; row++) {
			deq.add(new int[] {row, 0});
			
			while (!deq.isEmpty()) {
				curr = deq.pollLast();
				if (visited[curr[0]][curr[1]]) continue;
				visited[curr[0]][curr[1]] = true;
				if (curr[1] == C-1) {
					count++;
					break;
				}
				
				if (posCheck(curr[0]+1, curr[1]+1)) {
					deq.addLast(new int[] {curr[0]+1, curr[1]+1});
				}
				if (posCheck(curr[0], curr[1]+1)) {
					deq.addLast(new int[] {curr[0], curr[1]+1});
				}
				if (posCheck(curr[0]-1, curr[1]+1)) {
					deq.addLast(new int[] {curr[0]-1, curr[1]+1});
				}
				
			}
			
			deq.clear();
		}
		
		bw.write(String.valueOf(count));
		bw.close();
		
	}
	
	public static boolean posCheck(int row, int col) {
		return row >= 0 && row < R && col >=0 && col < C 
				&& board[row][col] != 'x'
				&& !visited[row][col];
	}
}