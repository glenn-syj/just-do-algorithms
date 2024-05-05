package boj_16946_박건택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3 {
	static int N, M;
	static int[][] map;
	static Queue<int[]> wall = new LinkedList<>();
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();

			for (int t = 0; t < M; t++) {
				map[i][t] = str.charAt(t) - '0';

				if (map[i][t] == 1) { // map 좌표값이 1이면 벽으로 좌표 추가
					int[] coordinate = new int[2];
					coordinate[0] = i;
					coordinate[1] = t;
					wall.offer(coordinate);
				}
			}
		} // map 정보 입력완

		int[] now;
		while (!wall.isEmpty()) {
			now = new int[2];
			now = wall.poll();

			map[now[0]][now[1]] = bfs(now);
		}

		for (int i = 0; i < N; i++) {
			for (int t = 0; t < M; t++) {
				System.out.print(map[i][t]);
			}
			System.out.println();
		}

	}

	public static int bfs(int[] loc) {
		Queue<int[]> queue = new LinkedList<int[]>();

		queue.offer(loc);
		int[] now;
		int nc, nr;
		int count = 0;
		
		int[][] visited = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int t = 0; t < M ; t++) {
				visited[i][t]= map[i][t];
			}
		}// visit 처리를 위한 원본 map 복사
		
		while (!queue.isEmpty()) {
			now = queue.poll();

			for (int d = 0; d < 4; d++) {
				nr = now[0] + dr[d];
				nc = now[1] + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (visited[nr][nc] == 0) {
						visited[nr][nc]=1;
						int[] coordinate = new int[2];
						coordinate[0] = nr;
						coordinate[1] = nc;
						queue.offer(coordinate);
						count++;
					}
				}
			}

		}
		return count + 1;
	}
}
