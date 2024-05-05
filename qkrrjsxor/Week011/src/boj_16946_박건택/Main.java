package boj_16946_박건택;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map, visited;
	static Queue<int[]> wall = new LinkedList<>();
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Map<Integer, Integer> countMap = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];	//맵 정보
		visited = new int[N][M];	//0인 칸들의 그룹 번호

		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();

			for (int t = 0; t < M; t++) {
				map[i][t] = str.charAt(t) - '0';
			}
		} // map 정보 입력완

		int groupId = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (visited[i][j] == 0 && map[i][j] == 0) {
					groupId++;
					bfs(i, j, groupId);
				}
			}
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == 1) {
					int c = 1;
					Set<Integer> set = new HashSet<>();
					for (int d = 0; d < 4; ++d) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nr >= 0 && nr < N && nc >= 0 && nc < M && visited[nr][nc] != 0)
							set.add(visited[nr][nc]);	//그룹 번호 입력
					}
					for (int key : set) {	//그룹 번호에 따라 그 칸의 개수 countMap에서 가져와서 더하기
						c += countMap.get(key);
					}
					sb.append(c%10);
				} else {
					sb.append("0");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}


	public static int bfs(int r, int c, int groupId) {
		Queue<int[]> queue = new LinkedList<int[]>();

		int[] coordinate = new int[2];
		coordinate[0] = r;
		coordinate[1] = c;
		
		queue.add(coordinate);
		int[] now;
		int nc, nr;
		int count = 0;
		
		visited[r][c] = groupId;
		
		while (!queue.isEmpty()) {
			now = queue.poll();
			count++;
			
			for (int d = 0; d < 4; d++) {
				nr = now[0] + dr[d];
				nc = now[1] + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (visited[nr][nc] == 0 && map[nr][nc] == 0) {
						visited[nr][nc]= groupId;
						coordinate = new int[2];
						coordinate[0] = nr;
						coordinate[1] = nc;
						queue.offer(coordinate);
					}
				}
			}

		}
		
		countMap.put(groupId, count);
		
		return count + 1;
	}
}

