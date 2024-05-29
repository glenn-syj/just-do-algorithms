# BOJ 2573. 빙산

## ✓ Difficult Point

- 문제에서 주어진 상황에 맞추어서하면 되는 시뮬레이션 문제이지만, 몇 가지 고려할 부분들이 있었다.
  
  - 얼음을 녹일 때 바로 반영하다보면 녹은 얼음이 바다가 될 수 있다. 이는 다른 얼음을 녹일 때 같은 회차이지만 영향을 미쳐 얼음이 한 번 더 녹을 수 있다는 문제가 발생할 수 있다. 따라서, 모든 얼음은 동시에 녹는다는 것을 유의해야한다.
  
  - BFS를 활용하여 얼음들이 연결되어있는지 확인하는 과정에 다양한 방법이 있겠지만, visited 배열에 연결되어 있으면 count 숫자 값을 넣어서 연결되어있는 빙산들의 갯수를 파악하고자 하였다.
  
  - 마지막으로 모두가 녹을 때까지 빙산이 분리가 안되는 경우에 대한 예외 처리를 꼭! 까먹지 않도록 주의하자. 

<br/>

## ✓ Logic

1. 2차원 배열에 빙산 정보를 입력 받고, 이와 똑같은 배열을 만들어준다.
2. 시간을 증가시키면서 얼음을 녹이는 과정을 진행한다.

	- 여기서 주의할 점은 주변에 바다가 있는지는 복사한 배열을 통해서 확인하고 녹이는 것은 원본 배열에서 진행한다. 
	- 복사 배열은 원본 배열에서 모든 얼음 녹이기 과정이 끝나고 다시 복사해서 업데이트한다.

3. 녹이고 나서 만약 모든 얼음이 다 녹았다면, 반복문을 중지시킨다.
4. 얼음이 다 녹지 않았다면, BFS를 통해서 빙산이 연결되어있는지 확인한다.
   
   - BFS를 통해 만약 지도에 0이 아니면서 visited에 0 (방문, 연결처리 하지 않았다면) count를 넣어서 연결된 섬의 번호를 visited에 표시하는 방법을 활용하였다.

5. 만약 count가 2보다 크다면 섬이 분리된 것이므로 반복문을 종료시키고 시간을 출력한다.

<br/>

## ✓ Code
```java
package boj_2573_황민욱;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] temp;
	static int[][] visited;
	static int count;
	static int[] deltaR = { -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		temp = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
				temp[r][c] = map[r][c];
			}
		}

		int time = 0;
		boolean isAvailable = false;

		out: while (true) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (temp[r][c] == 0) {
						continue;
					}
					
					for(int d = 0; d < 4; d++) {
						int nr = r + deltaR[d];
						int nc = c + deltaC[d];
						
						if(isNotOutBound(nr, nc) && temp[nr][nc] == 0 && map[r][c] > 0) {
							map[r][c]--;
						}
					}
				}
			}
			
			int water = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == 0) {
						water++;
					}
					temp[r][c] = map[r][c];
				}
			}
			
			
			time++;
			
			if(water == N * M) {
				break out;
			}
			
			visited = new int[N][M];
			count = 1;
			
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == 0) {
						continue;
					}
					
					if(map[r][c] != 0 && visited[r][c] == 0) {						
						bfs(r, c);
						count++;
					}
				}
			}
			
			if(count > 2) {
				isAvailable = true;
				break out;
			}
		}
		
		System.out.println(isAvailable? time : 0);
	}
	

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		
		int[] start = {r, c};
		visited[r][c] = count;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current[0] + deltaR[d];
				int nc = current[1] + deltaC[d];
				
				if(isNotOutBound(nr, nc) && map[nr][nc] != 0 && visited[nr][nc] == 0) {
					visited[nr][nc] = count;
					
					int[] next = {nr, nc};
					queue.add(next);
				}
			}
		}
		
	}
	
	private static boolean isNotOutBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	public static void printMap(int[][] list) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				System.out.print(list[r][c] + " ");
			}
			System.out.println();
		}
	}
}

```