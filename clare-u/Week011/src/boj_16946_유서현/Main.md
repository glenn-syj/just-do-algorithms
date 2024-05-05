## 백준 16946 벽 부수고 이동하기 4

### 최초 접근

처음 문제를 접하고, 나는 이 문제를 단순한 BFS 문제라고 생각했었다.

처음의 로직

- 완전탐색 + bfs를 활용한다.
- 전체 맵을 돌면서 벽을 만날 때마다 각 벽에 대해 bfs를 한다.
- bfs 시 시작 칸을 포함해서 `count++` 해주고, 다 돌고나면 count 값을 1의 자리만 저장해주기 위해 `count % 10`을 벽에 저장해준다.
- 인풋이 붙어있는 값으로 들어오므로 `String` 으로 받은 뒤 `charAt` 메소드로 값을 분리해 저장한다.

사실 이 로직으로도 테스트 케이스들에서는 오류가 없었다. 다만 시간초과에 맞닥드리게 되어 어딘가 수정할 곳이 없을까 고민하다가 2가지 수정사항을 거쳤다.

1. BufferedReader와 StringTokenizer, StringBuilder 등을 사용해서 최대한 시간소요를 줄인다.
2. `visited`배열은 각 벽에 대해 bfs 시마다 초기화 해준다. (놓쳤던 논리적 오류)

그럼에도 불구하고 계속 시간초과가 나서 스터디원들에게 힌트를 구하고 깨달았다.

N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000) 이라는 입력범위 때문에 나의 로직으로는 시간초과가 날 수밖에 없는 슬픈 사실을…

문제에서 주어진 

각각의 벽에 대해서 다음을 구해보려고 한다.

- 벽을 부수고 이동할 수 있는 곳으로 변경한다.
- 그 위치에서 이동할 수 있는 칸의 개수를 세어본다.

 부분을 너무 철썩같이 수행해 버린 탓에, 논리적으로는 오류가 없지만 효율적이지 못한 코드가 된 것이다. 

**수정 전 코드**
```java
package boj_16946_벽부수고이동하기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] board;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];

		String input;
		for (int i = 0; i < N; i++) {
			input = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				board[i][j] = input.charAt(j) - '0';
			}

		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(board[i][j]);
//			}
//			System.out.println();
//		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					// 벽을 만나면 각 벽에서 bfs
					bfs(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < M; j++) {
				sb.append(board[i][j]);
			}
			System.out.println(sb);
		}

	} // main

	static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[N][M];
		int count = 0;

		int[] temp = { r, c };
		queue.add(temp); // 큐에 넣고
		visited[r][c] = true; // 방문처리
		count++;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currR = curr[0];
			int currC = curr[1];

			for (int d = 0; d < 4; d++) {
				int nr = currR + dr[d];
				int nc = currC + dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < M) {
					if (!visited[nr][nc] && board[nr][nc] == 0) {
						int[] temp2 = { nr, nc };
						queue.add(temp2);
						visited[nr][nc] = true;
						count++;
					}
				}
			}
		} // while
		// 다 돌고나면 count값을 1의 자리만 저장해주기
		board[r][c] = count % 10;
	}

}

```

### 수정된 풀이 (peek)

이 문제는 분리 집합개념의 이용이 필요했다. (BFS+분리집합)

벽을 제외하고 이어진 나머지를 그룹으로 치고, 각 그룹에 번호를 지정하고 해당 그룹의 개수를 센다.

그리고 그룹마다 몇 개의 0을 포함하고 있는지 Map에 저장한다.

이 때 Map에는 <그룹번호, 그룹 포함된 0 개수> 즉 <1,2><2,3> 이런 식으로 저장이 된다.

그 이후 각 벽에서는 그룹화해놓은 정보를 이용하여 벽에 직접 맞닿은 그룹만 세어주면 된다.

이러면 bfs로 각 벽마다 끝까지 탐색할 필요 없이, 최초 그룹화해둔 정보만으로 간단하게 탐색을 끝낼 수 있다.

```java
package boj_16946_벽부수고이동하기4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr, group;
	static int n;
	static int m;
	static Map<Integer, Integer> groupSize;

	public static void main(String[] args) throws Exception {

		// BFS/분리집합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		group = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}

		// 각각 그룹마다 다른 key값을 가지게해줌
		int groupCnt = 1;
		groupSize = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0 && group[i][j] == 0) {
					groupSize.put(groupCnt, bfs(i, j, groupCnt));
					groupCnt++;
				}
			}
		}
		// 답을 이중배열로 출력함
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (group[i][j] == 0) {
					sb.append(count(i, j) + "");
					continue;
				}
				sb.append(0 + "");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	// 벽에 맞닿은 그룹의 합을 구함
	static int count(int x, int y) {
		int cnt = 1;
		if (arr[x][y] == 0)
			return 0;
		Set<Integer> set = new HashSet<>();

		// 벽에 맞닿은 4방향만 구하면됨
		// 그 방향의 그룹의 0의 갯수 정보는 이미 구했기 때문
		for (int i = 0; i < 4; i++) {
			int[] dx = { 0, 1, 0, -1 };
			int[] dy = { 1, 0, -1, 0 };

			int sx = x + dx[i];
			int sy = y + dy[i];

			if (sx < 0 || sy < 0 || sx >= n || sy >= m || group[sx][sy] == 0)
				continue;
			// 맞닿은 그룹이 중복일 경우를 위해 set에 저장함
			set.add(group[sx][sy]);

		}
		for (int size : set) {
			cnt += groupSize.get(size);
		}

		return cnt % 10;
	}

	// 그룹마다 몇개의 0이 포함되었는지를 리턴해줌
	static int bfs(int x, int y, int groupCnt) {
		int cnt = 1;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		group[x][y] = groupCnt;
		while (!q.isEmpty()) {
			Point point = q.poll();
			int[] dx = { 0, 1, 0, -1 };
			int[] dy = { 1, 0, -1, 0 };
			for (int i = 0; i < 4; i++) {
				int sx = point.x + dx[i];
				int sy = point.y + dy[i];

				if (sx < 0 || sy < 0 || sx >= n || sy >= m)
					continue;
				// 아직 그룹에 속하지 않았고 && 벽이 아니라면 카운트해준다.
				if (group[sx][sy] == 0 && arr[sx][sy] == 0) {
					group[sx][sy] = groupCnt;
					cnt++;
					q.add(new Point(sx, sy));
				}
			}
		}
		return cnt;

	}

}

class Point{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

```