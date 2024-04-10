# 백준 14503 로봇청소기

## 어려웠던 점
- 이미 한 번 풀이에 실패했던 문제였는데, 로봇 청소기가 바라보는 방향을 유지해야 한다는 조건을 어떻게 구현해야 할지가 떠오르지 않았다.
- 재도전 시 메서드의 매개변수로 좌표와 바라보는 방향의 델타 인덱스 값까지 넣어주면 되겠다는 아이디어를 떠올리고 풀이를 시작했다.

## 풀이
1. `clean` 메서드를 재귀적으로 이용해 바라보는 방향을 유지한다.
   - 문제의 조건에 맞추어 델타탐색을 하며 청소하는 clean메서드를 작성한다.
   - 90도 회전 뒤 전진하거나, 벽이 아닐 경우 후진하는 경우 등에서 바라보는 방향을 메서드의 매개변수로 넣어주어 유지할 수 있도록 한다.


2. 청소 할 시 `count++`해주어 추후 count를 출력한다.
   - 어느 칸에 방문하건, 그 칸이 벽이 아니고 방문한 적 없던 곳이라면 무조건 청소한다.

3. `return`문을 써서 과하게 탐색하지 않도록 해준다.
   - 벽에 도달하면 작동을 멈추어야 하므로 return 해준다.
   - 사방 델타탐색으로 청소했을 경우, 너무 많이 탐색하면 안되므로 return 해준다.

### clean 메서드

```java
	private static void clean(int r, int c, int d) {

		if (room[r][c] == 0 && !visited[r][c]) {
			count++;
			visited[r][c] = true; // 청소
		}

		// 상하좌우 4칸 탐색
		  for (int i = 0; i < 4; i++) {
	            d = (d + 3) % 4;
	            int nr = r + dr[d];
	            int nc = c + dc[d];

			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				if (room[nr][nc] == 0 && !visited[nr][nc]) {
					clean(nr, nc, d);
					return;
				}
			}
		}

		// 청소 안된 칸이 4칸 중에 없으면
		int br = r + dr[(d + 2) % 4];
		int bc = c + dc[(d + 2) % 4];
		if (0 <= br && br < N && 0 <= bc && bc < M) {
			if (room[br][bc] == 0) {
				// 벽이아니면 뒤로가서 청소
				clean(br, bc, d);
			} else {
				// 벽이면 멈추기
				return;
			}
		}

	}
```
- 델타 탐색으로 문제의 조건에 맞추어 clean횟수를 카운트하는 메서드
- 자세한 설명은 주석과 위 항목 참고

## 막혔었던 부분
- [백준 14503번 로봇청소기 문제 이해에 도움이 필요합니다.](https://github.com/Glenn-syj/just-do-algorithms/issues/137) 이슈를 발행해서 이해에 도움을 받았다.

처음 구현했던 사방탐색

```java
		// 상하좌우 4칸 탐색
		for (int d = startD + 3; d < startD + 7; d++) {
			int nr = r + dr[d % 4];
			int nc = c + dc[d % 4];

			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				if (room[nr][nc] == 0 && !visited[nr][nc]) {
					clean(nr, nc, d);
				}
			}
		}
```

수정한 사방탐색
```java
		// 상하좌우 4칸 탐색
		  for (int i = 0; i < 4; i++) {
	            d = (d + 3) % 4;
	            int nr = r + dr[d];
	            int nc = c + dc[d];

			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				if (room[nr][nc] == 0 && !visited[nr][nc]) {
					clean(nr, nc, d);
					return;
				}
			}
		}
```

- `i`는 단순 4번을 반복하는 용도로 사용하고, 그 반복문 안에서 `d`값을 업데이트 해 준다.
- 업데이트된 `d`값을 매개변수로 넘겨준다.
- 수정 전 코드에서는 업데이트되지 않은 `d`값을 넘겨주므로 방향 설정이 제대로 되지 않았다. 


