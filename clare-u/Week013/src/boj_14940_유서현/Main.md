## 백준 14940 쉬운 최단거리

**문제**

지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구하여라.

문제를 쉽게 만들기 위해 오직 가로와 세로로만 움직일 수 있다고 하자.

**입력**

지도의 크기 n과 m이 주어진다. n은 세로의 크기, m은 가로의 크기다.(2 ≤ n ≤ 1000, 2 ≤ m ≤ 1000)

다음 n개의 줄에 m개의 숫자가 주어진다. 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점이다. 입력에서 2는 단 한개이다.

**출력**

각 지점에서 목표지점까지의 거리를 출력한다. 원래 갈 수 없는 땅인 위치는 0을 출력하고, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.

### 내 풀이

**탐색 시**
- 문제에서 설명하는 대로 모든 지점에 대해 목표지점까지의 거리를 구하려 하는 것은 비효율적이다.
	- 도착 지점을 시작점으로 잡고 bfs 탐색을 한다.
- bfs를 하며 최소 거리를 각 지점마다 저장한다.
	- `distance[][]` 배열을 선언해 시작점으로부터의 거리를 저장하도록 한다.
- queue가 빌 때까지 사방탐색을 하며, 아직 방문하지 않았으며 갈 수 있는 곳(`1`)으로 이동한다.

**입력받을 때**
- 지도를 입력받을 때 목표지점의 좌표를 저장해두고 그 좌표에서 bfs를 시작한다.
- 갈 수 없는 땅은 미리 방문한 것으로 표시하여 추후 방문하지 않도록 한다.

**출력 시**
- 도달이 불가한 위치는 `!visited[i][j]` 인 곳이다. (false였던 곳) 그 위치를 `-1`로 바꾸어 출력한다.

**의의**
- 부끄럽지만 bfs와 dfs를 거의 잊어가고 있었다.
- bfs와 구현을 연습해볼 수 있는 나에게 필요한 문제였다.

### 전체 코드
```java
package boj_14940_쉬운최단거리;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int n, m;
	static int[][] board, distance;
	static boolean[][] visited;

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		board = new int[n][m];
		distance = new int[n][m]; // 거리 저장
		visited = new boolean[n][m];

		int vr = 0;
		int vc = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = sc.nextInt();
				if (board[i][j] == 2) { // 목표지점
					vr = i;
					vc = j;
				} else if (board[i][j] == 0) {
					visited[i][j] = true; // 갈 수 없는 땅
				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(board[i][j]);
//			}
//			System.out.println();
//		}

		bfs(vr, vc);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) { // 도달 불가한 위치라면
					distance[i][j] = -1;
				}
				System.out.print(distance[i][j] + " ");
			}
			System.out.println();
		}

	} // main

	static void bfs(int vr, int vc) {
		// 큐 생성
		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { vr, vc }); // 큐에 넣고
		visited[vr][vc] = true; // 방문처리

		while (!queue.isEmpty()) { // bfs탐색
			int[] curr = queue.poll();

			for (int d = 0; d < 4; d++) {
				int r = curr[0] + dr[d];
				int c = curr[1] + dc[d];
				if (0 <= r && r < n && 0 <= c && c < m) {
					if (!visited[r][c] && board[r][c] == 1) {
						visited[r][c] = true; // 방문처리
						distance[r][c] = distance[curr[0]][curr[1]] + 1; // 현 위치에서 +1
						queue.add(new int[] { r, c }); // 큐에 넣기
					}
				}
			}
		}

	}

}

```



