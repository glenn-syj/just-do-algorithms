package boj_16946_유서현;

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
