# 1012 - 유기농 배추

[문제 링크](https://www.acmicpc.net/problem/1012)

## 로직

기본적인 bfs 문제

모든 점에서 1이면 사방으로 너비우선탐색을 진행한다. 더이상 나아가지 못할 때 까지 만나는 1을 visit 체크해준다.

이후, 다른 1을 찾아서 같은 로직을 반복하고, 로직이 반복된 횟수를 출력한다.

### 모든 점을 대상으로 bfs를 시행하는 구문

```java
for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(cabbage[i][j] == 1 && !visit[i][j]) {
						bfs(i, j);
						count++;
					}
				}
			}
```

visit 체크를 해주고 있기 때문에, 같은 영역에 있는 점에서 탐색은 이루어지지 않는다.


### 사방으로 뻗어나가는 bfs 코드

```java


public static void bfs(int r, int c) {
		Queue<int[]> qu = new LinkedList<>();
		qu.add(new int[] {r, c});
		
		
		while(!qu.isEmpty()) {
			r = qu.peek()[0];
			c = qu.peek()[1];
			
			visit[r][c] = true;
			
			qu.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				
				if(nr >= 0 && nc >= 0 && nr < M && nc < N) {
					if(!visit[nr][nc] && cabbage[nr][nc] == 1) {
						qu.add(new int[] {nr, nc});
						visit[nr][nc] = true;
					}
				}
			}
			
			
			
		}
		
	}


```

기초적인 수준의 문제였지만, 순간 각 영역에서의 시작점을 어떻게 골라야 할 지 생각해낼 수 없었다.

모든 점에 대해서 배추가 심어져있으면 탐색을 진행하고 배추가 심어져있지 않거나, 배추인데도 이미 한 번 탐색한 배추는 탐색을 진행하지 않도록 하면 되는 것이었다.


<br>