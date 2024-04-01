package boj_16928_뱀과사다리게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* B16928. 뱀과 사다리 게임
 * 
 * 1. 조건
 * 	1-1. 게임 크기는 10*10
 * 		-> 보드판에는 1에서 100까지 수가 하나씩
 * 	1-2. 주사위를 굴려 나온 수 만큼 이동
 * 		-> 주사위를 굴린 결과가 100번을 넘어서면 이동할 수 없음
 * 	1-3. 사다리면: 타고 위로 올라감, 뱀이면: 타고 밑으로 내려감
 * 	1-4. 1번 칸에서 시작해 100번 칸에 도착
 * 
 * 2. 풀이
 * 	2-1. 최소 거리를 구하기 = BFS를 이용하면 편할듯?
 * 		-> 각 위치에 대해서 갈 수 있는 곳 = i+1~i+6
 * 		-> 해당 위치에 대해 bfs 돌림
 *  2-2. 같은 칸을 다시 방문하면, 같은 과정을 반복하므로 visited true를 추가하자마자 해줘야 함
 *  
 */

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static boolean[] visited;
	static int[] board;
	static int x, y;
	static Queue<Integer> que = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[101];
		visited = new boolean[101];
		
		for (int i=1; i<=100; i++) {
			board[i] = i;
		}
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			board[x] = y;
			
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			board[x] = y;
		}
		
		que.add(1);
		visited[1] = true;
		
		int curr;
		int size = que.size();
		int leftover = 0;
		int dist = 0;
		
		while (!que.isEmpty()) {
			curr = que.poll();
			leftover++;
			
			if (curr == 100) {
				break;
			}
			
			for (int i=1; i<=6; i++) {
				if (curr+i <= 100 
						&& !visited[curr+i]) {
					que.add(board[curr+i]);
					visited[curr+i] = true;
					visited[board[curr+i]] = true;
				}
			}
			
			if (leftover == size) {
				leftover = 0;
				size = que.size();
				dist++;
			}
			
		}
		
		sb.append(dist);
		bw.write(sb.toString());
		bw.close();
		
		
	}
	
	
}