## boj_12581_숨바꼭질2 풀이 과정

### 선행 문제

**boj_1697_숨바꼭질**

숨바꼭질 2에서 어떻게 최소 시간을 체크할건지에 대한 아이디어가 떠오르지 않아서, 1697 숨바꼭질 문제를 먼저 풀이했다.

소요 시간: 총 2시간

설계 : 20분
```
// 1차원 배열에서 BFS 탐색을 진행한다.
// N이 갈 수 있는 곳은 3곳이다. 현재위치에서 -1, +1, *2 
// BFS로 이 3개를 for문 돌려서 탐색한다
// 방문체크, 카운트용으로 visited 배열을 사용
```

설계 시 잘했다고 여긴 점은 visited를 int[]로 선언하여 최소 시간과 방문체크를 한 배열에서 구한다는 점 이었다.

구현 : 1시간 40분
```java
package boj_1697_숨바꼭질;

import java.util.*;

public class Main {
	static int N, K;
	static int visited[] = new int[100001]; // 1번 인덱스부터

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		if (N == K) {
			System.out.println(0); // 같은 위치에서 시작 시 0을 출력
		} else {
			BFS();
		}
	}

	static void BFS() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);
		visited[N] = 1;

		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int i = 0; i < 3; i++) {
				int next;
				if (i == 0) {
					next = now + 1;
				} else if (i == 1) {
					next = now - 1;
				} else {
					next = now * 2;
				}

				if (next == K) {
					System.out.println(visited[now]);
					return;
				}

				if (next < 0 || next > 100000) {
					continue;
				}
				if (visited[next] != 0) {
					continue;
				}

				queue.add(next);
				visited[next] = visited[now] + 1;
			}
		}
	}

}
```

### 본 문제

**boj_12851_숨바꼭질2**

위 코드를 잘 바꿔 풀이하려 했으나 반례가 존재하여 '틀렸습니다' 판정을 계속 받았다.

**반례**
```
입력: 0 3

출력 :
3
1

정답:
3
2
```
이 때 [이슈](https://github.com/Glenn-syj/just-do-algorithms/issues/127)를 발행하여 도움을 받았었다.

(틀렸습니다 판정을 받았던 코드도 해당 링크에 게시되어 있어 재게시하지 않겠다)

중점은 한번 방문한 노드를 다 스킵하지 않고, 어떤 경우 중복 방문을 할 수도 있다는 점이었다.

처음부터 설계하는게 가장 나아 보였으나 처음부터 설계하기에는 다시 time을 어떻게 구할지 아이디어가 떠오르지 않아, 

조언을 듣고, 최대한 비슷한 로직으로 풀이했던 풀이를 peek하였다.

https://lottodangchum.tistory.com/85 에서 가장 중요한 점은

```java
	// next가 범위 밖이거나, 이미 방문한 지점인데 기존 소요 시간보다 오래 걸린다면 X
	if (next<0 || next>100_000 || (time[next] != 0 && time[next] < time[now]+1)) continue;
	time[next] = time[now]+1; // 소요 시간 저장
	que.add(next);
```
나는 `이미 방문한 지점인데 기존 소요 시간보다 오래 걸린다면 continue;`라는 조건을 고려하지 않고, 단순 `이미 방문한 지점을 모두 continue`하고 있었다.

본 코드에서 조금만 더 신경써서 로직을 개선했다면 solve가 가능한 부분이라 아쉽기도 했지만, 이미 시간이 너무 오래 소요되어 (체감 3시간 이상..?) peek로 배워가는 것도 많았으니 만족하기로 했다.

**풀이한 코드에서 개선된 부분**
```java

				if (next < 0 || next > 100000 || (visited[next] != 0 && visited[next] < visited[now] + 1)) {
					// next가 범위 밖이거나, 이미 방문한 지점인데 기존 소요 시간보다 오래 걸린다면 X
					continue;
				}
```

**풀이한 코드에서 삭제한 부분**
```java
                if (visited[now] > time && visited[next] != 0) {
					// 방문한 노드는 스킵
					continue;
				}
```


풀이를 하고 코드를 게시하고 나니, while 안의 for문 (i 0~3)을 더 가독성이 좋도록 메서드로 분리하는 등 개선할 방법은 없을지 고민하게 되었다.

추후 숨바꼭질 3을 개인적으로 풀이하게 된다면 개선해 보아야 겠다.


### 느낀 점

방문체크가 필요할 때 BFS는 정말 편리한 알고리즘이다.


### 추가로 공부가 필요한 점

방문체크가 필요한 때 DFS보다는 BFS가 적절한 이유(왜 DFS로는 사용하지 않는지에 대한.. DFS 이해 부족)