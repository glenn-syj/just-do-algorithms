package boj_15685_드래곤커브;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* B15685. 드래곤 커브
 * 
 * 1. 조건
 * 	1-1. 드래곤 커브는 시작점, 시작 방향, 세대로 이루어져 있음
 * 	1-2. N세대 드래곤 커브는 끝점에서 90도 시계방향 회전 시킨 다음 끝점으로
 * 		-> 다음 드래곤 커브는, 이미 있는 드래곤 커브 진행 방향의 역순으로 진행하는데,
 * 		-> 단순한 역순이 아니라 역순의 deltas 다음 배열을 방향으로 함
 * 	1-3. 이미 들른 점에 대해서는 visited를 true로 표기
 * 		-> 처음에 주어지는 정점과 이후에 진행한 방향도 true로
 * 		-> arrayList에는 0단계 드래곤커브 마지막 점만을 추가
 * 2. 풀이
 * 	2-1. 
 */

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, X, Y, D, G;
	static int[][] deltas = new int[][] { {1, 0}, {0, -1}, {-1, 0}, {0, 1} };
	static boolean[][] visited;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[101][101];
		
		List<int[]> points;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			points = new ArrayList<>();
			
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			
			points.add(new int[] {X+deltas[D][0], Y+deltas[D][1], D});
			visited[Y][X] = true;
			visited[Y+deltas[D][1]][X+deltas[D][0]] = true;
			int size;
			int[] curr;
			int newX, newY, newD;
			for (int step=1; step<=G; step++) {
				size = points.size();
				for (int idx=size-1; idx>=0; idx--) {
					curr = points.get(points.size()-1);
					newD = (points.get(idx)[2]+1)%4;
					newX = curr[0]+deltas[newD][0];
					newY = curr[1]+deltas[newD][1];
					points.add(new int[] {newX, newY, newD});
					visited[newY][newX] = true;
				}
			}
			
		}

		
		for (int y=0; y<100; y++) {
			for (int x=0; x<100; x++) {
				if (visited[y][x]
						&& visited[y][x+1]
						&& visited[y+1][x]
						&& visited[y+1][x+1]) {
					count++;
				}
			}
		}
		
		sb.append(count);
		bw.write(sb.toString());
		bw.close();
		
	}
	
}