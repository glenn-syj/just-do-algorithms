package boj_16946_박건택;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2{
	static int N, M;
	static int[][] map;
	static Queue<int[]> wall = new LinkedList<>();
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();

			for (int t = 0; t < M; t++) {
				map[i][t] = str.charAt(t) - '0';

			}
		} // map 정보 입력완

		for(int i =0 ; i < N; i++) {
			for(int t = 0; t < M; t++) {
				if(map[i][t] ==1) {
					int tmp = bfs(i,t);
					sb.append(tmp);
				}else {
					sb.append(0);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

	public static int bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();

		int[] coordinate = new int[2];
		coordinate[0] = r;
		coordinate[1] = c;
		
		queue.offer(coordinate);
		int[] now;
		int nc, nr;
		int count = 0;
		
		int[][] visited = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int t = 0; t < M ; t++) {
				visited[i][t]= map[i][t];
			}
		}
		
		while (!queue.isEmpty()) {
			now = queue.poll();

			for (int d = 0; d < 4; d++) {
				nr = now[0] + dr[d];
				nc = now[1] + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (visited[nr][nc] == 0) {
						visited[nr][nc]=1;
						coordinate = new int[2];
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
