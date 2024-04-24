# 문제 정보
난이도: 골드 II
분류 : BFS
url: https://www.acmicpc.net/problem/16946

# 고찰
처음에 BFS로 풀면 될 것이라고 예상하고 풀었다.
처음 생각한 방법은, 각 벽에 대해서 BFS로 연결되어있는 0의 개수를 세고, 해당 벽을 연결된 칸의 개수로 대치하는 것이었다.
하지만 역시 시간 초과가 발생하였다.
각 벽에 대해서 BFS를 실행 시킬 때 마다 visit 처리를 위해 원본 map을 복사하는 것과 print를 쓴 것이 문제인것인가 싶어서 방식을 바꾸어봤다.
원본 map 그대로 bfs를 실시하고,visit 처리를 위해 0인 부분을 탐색하며 -1로 바꾸었고, 탐색이 끝난 뒤 그 부분만 다시 0으로 바꾸는 방식으로 변경하였지만
마찬가지로 시간초과 발생.

생각을 한 뒤 1에서 bfs를 하지말고, 0에서 bfs를 하여 미리 연결되어있는 부분을 전부 연결되어 있는 칸 수로 바꾸고 1인 부분들은 4방 탐색을 하며 단순히 더하기만 하자, 라고 생각을 하였다.
하지만 구현하다가 그룹화 하는 방법과, 칸 수를 어떻게 저장하고 계산할지 하는지 막혀서 Peek 하였다.

아이디어까지는 생각해 내었는데 구현을 못해서 아쉽다.

# Logic
1. 0에서 부터 bfs를 시작하여 이어진 칸들의 개수를 세고, visited 배열에 연결된 칸들에 대해 그룹id를 저장하고, countMap에 그룹 id에 해당하는 칸의 수를 저장한다.
2. 벽에서 사방 탐색을 하여 사방에 어떤 그룹 id가 있는지 visited 배열을 통해 탐색하고, 인접한 그룹 id를 Set으로 저장한다.
3. set에 저장한 그룹 id에 대해서 countMap에 해당 그룹 id의 칸 수를 찾아서 더한다.
4. 해당 벽 칸에 칸 수 정보를 대치한다.


# What I learned
- 발상의 전환으로 벽에서 bfs를 시작하는 것이 아니라 0에서 bfs를 한다.
- bfs 방법에 대해 더욱 단단히 익혔습니다.
- Map을 이용해서 해당 칸의 id와 연결된 칸의 개수로 이어진 부분들을 하나의 그룹으로 묶는 방법을 배웠습니다.

# Java Code
```java

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



```