package boj_2206_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 보자마자 dfs라는 생각이 들었다.
// 하지만 가장 짧은 거리를 찾는 것이기 때문에 bfs 탐색이 이루어진 것일까?


public class Main {
	
	static int ans;
	
	static int[][] map;

	// 이 문제의 핵심인 3차원 배열의 visited
	// 주어진 2차원 배열과 똑같은 위치를 방문했는지 탐색하는 것을 넘어서서,
	// 벽을 부순 상태에서의 방문인지, 벽을 부수지 않은 상태에서의 방문인지를 체크해줘야 한다.
	// bfs란 넓게 퍼지는 것이기 때문에 벽을 부순상태에서의 방문이 벽을 부수지 않은 상태에서의 방문을 막아버릴 수 있기 때문이다. 
	static boolean[][][] visited; 
	static Queue<List<Integer>> queue;
	
	static int r;
	static int c;
	
	static int[] dr = new int[] {1, -1, 0, 0};
	static int[] dc = new int[] {0, 0, 1, -1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		visited = new boolean[r][c][2];
		ans = Integer.MAX_VALUE;
		

		// 주어진 입력값이 빈칸 없이 주어졌기 때문에 charAt을 사용했다.
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			for(int j = 0; j < c; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		
		
		queue = new LinkedList<>();
		
		// 최초 시작점을 넣어주기 위한 임시 list
		List<Integer> tmp = new ArrayList<>();
		
		// 0행, 0열, 벽을 부쉈는지 여부를 0과 1로 나타내기 위한 값, 거리
		// 처음 위치를 포함하여 거리를 세고 있기 때문에 거리는 1값을 넣어주었다.
		tmp.add(0);
		tmp.add(0);
		tmp.add(0);
		tmp.add(1);
		
		queue.add(tmp);
		
		// 첫 위치로 되돌아오는 것을 방지하기 위해 true 체크
		visited[0][0][0] = true;
		dfs(0, 0, 0, 1);
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
		
	}
	
	public static void dfs(int row, int column, int cnt, int d){
		
		// queue가 비어버릴 때까지 즉, 모든 탐색을 마칠 때까지 반복한다.
		while(!queue.isEmpty()) {
			// 첫 위치를 나타내는 list를 꺼낸다.
			List<Integer> start = queue.poll();
			

			int nr = (int) start.get(0);
			int nc = (int) start.get(1);
			int flag = (int) start.get(2);
			int dist = (int) start.get(3);

//			System.out.printf("%d %d %d %d%n", nr, nc, flag, dist);
			
			// 목적지에 도착했다면, ans값과 비교하여 그것보다 작다면 할당해준다.
			if(nr == r - 1 && nc == c - 1) {
				if(ans > dist) {
					ans = dist;
				}
			}
			
			// 델타탐색을 위한 for문
			for(int i = 0; i < 4; i++) {
				
				// 영역 밖으로 나가거나, 방문한 곳이라면 continue
				if(nr + dr[i] < 0 || nr + dr[i] >= r || nc + dc[i] < 0 || nc + dc[i] >= c || visited[nr + dr[i]][nc + dc[i]][flag] == true) {
					continue;
				}
				
				// 벽이 아니라면, 해당 위치로 이동하며 거리를 세준다.
				if(map[nr + dr[i]][nc + dc[i]] == 0) {
					List<Integer> tmp = new ArrayList<>();
					
					tmp.add(nr + dr[i]);
					tmp.add(nc + dc[i]);
					tmp.add(flag);
					tmp.add(dist + 1);
					
					queue.add(tmp);
					
					visited[nr + dr[i]][nc + dc[i]][flag] = true;
					continue;
				}
				
				// 벽을 부순 적이 없고, 해당 위치에 벽이 있다면,
				if(flag == 0 && map[nr + dr[i]][nc + dc[i]] == 1) {
					List<Integer> tmp = new ArrayList<>();
					
					
					tmp.add(nr + dr[i]);
					tmp.add(nc + dc[i]);
					// flag + 1로 벽을 부쉈음을 체크하고,
					tmp.add(flag + 1);
					// 거리를 세준 이후,
					tmp.add(dist + 1);
					
					// 이동한다.
					queue.add(tmp);
					
					visited[nr + dr[i]][nc + dc[i]][flag] = true;
				}
			}
		}
		
	}
	
}