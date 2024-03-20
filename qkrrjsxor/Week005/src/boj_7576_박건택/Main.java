package boj_7576_박건택;
/*
 * 처음 문제를 봤을 떈 2차원배열 완전 탐색으로 할까 했다.
 * 하지만 영준이가 그런 문제를 내줬을 리가 없어 하고 분류를 보니까 그래프와 BFS..
 * 바킹독을 보며 BFS에 대해 공부를 하고 c++ 코드 Peek 하여 풀었습니다
 * 
 * c++에서는 원본 배열과 dist 배열, 좌표쌍을 저장할 수 있는 큐를 사용하였는데
 * 자바에서는 좌표쌍을 어떻게 큐에 저장할것인가?
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str = br.readLine();
		st = new StringTokenizer(str);
		int col = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());
		int[][] box = new int[row][col]; // 토마토 배열
		int[][] dist = new int[row][col]; // 거리 배열
		Queue<int[]> queue = new LinkedList<int[]>();
		int[] coordinate = new int[2];
		int temp;

		// box : 1은 익은 토마토 0은 안익은 토마토 -1은 빈칸
		// dist :
		for (int i = 0; i < row; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for (int t = 0; t < col; t++) {
				temp = Integer.parseInt(st.nextToken());
				box[i][t] = temp;

				if (temp == 1) { // 토마토가 있으면 좌표를 큐에 넣기
					coordinate = new int[2];
					coordinate[0] = i;
					coordinate[1] = t;
					queue.add(coordinate);
				}
				if (temp == 0) { // 토마토가 없으면 거리 -1로 초기화
					dist[i][t] = -1;
				}
			}
		}

//		for(int i = 0; i< row; i++) {
//			for(int t =0 ;t <col; t++) {
//				System.out.print(box[i][t]);
//			}
//			System.out.println();
//		}
//		
//		for(int i = 0; i< row; i++) {
//			for(int t =0 ;t <col; t++) {
//				System.out.print(dist[i][t] + " ");
//			}
//			System.out.println();
//		}
//		for(int i = queue.size(); i>0; i--) {
//			coordinate = queue.peek();
//			System.out.println(Arrays.toString(coordinate));
//		}
		int curR = 0;
		int curC = 0;
		int nr = 0;
		int nc = 0;
		// BFS 시작
		while (!queue.isEmpty()) {
			coordinate = new int[2];
			coordinate = queue.poll();
			curR = coordinate[0];
			curC = coordinate[1];

			for (int i = 0; i < 4; i++) {
				nr = curR + dr[i];
				nc = curC + dc[i];

				if (nr < 0 || nr >= row || nc < 0 || nc >= col)
					continue;
				if (dist[nr][nc] >= 0)
					continue; // dist가 0이상 : 빈칸이거나 익은 토마토가 있다

				dist[nr][nc] = dist[curR][curC] + 1;

				coordinate = new int[2];
				coordinate[0] = nr;
				coordinate[1] = nc;
				queue.add(coordinate);
				
//				System.out.println(queue.toString());
//				for(int k = 0; k< row; k++) {
//					for(int t =0 ;t <col; t++) {
//						System.out.print(dist[k][t] + " ");
//					}
//					System.out.println();
//				}
			}
		}

		int max = -10;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (dist[r][c] == -1) {
					System.out.println(-1);
					return;
				}

				if (dist[r][c] > max) {
					max = dist[r][c];
				}
			}
		}

		System.out.println(max);

	}
}
