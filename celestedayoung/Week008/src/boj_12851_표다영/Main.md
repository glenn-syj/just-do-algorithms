# BOJ 12851. 숨바꼭질2

## ✓ Difficult Point
- 처음에 떠올린 방법은 주어진 세 가지 방법에 따라 각 경우의 수를 탐색하며 가장 가능성 있는 경우를 선택해 앞선 내용을 반복하는 것이다. 
- 그러나 '가장 가능성 있는 경우'를 고를 조건을 어떻게 구체적으로 설정해야 할지 아이디어가 떠오르지 않았다. 

## ✓ Key Point
### 1. `진행 시간`을 이용해 BFS를 진행한다.
Queue에는 시간이 0초일 때의 경우부터 쌓이다가, 동생을 찾으면 종료되도록 한다.


### 2. 조건에 따라 `중복 방문`을 허용해야 한다.
예를 들어 input으로 1, 4가 주어졌다면 아래와 같이 두 가지 경우가 가능하다.  

case 1) **1** → 1 + 1 = **2** → 2 * 2 = **4**   
case 2) **1** → 1 * 2 = **2** → 2 * 2 = **4**

- 만약 중복 방문을 허용하지 않는다면 case 1에서 2를 방문했으므로 case 2에서 2를 방문하지 못한다. 
- 결국 4까지 갈 수 있는 경우의 수가 2가지라는 것을 계산할 수 없다. 따라서 특정 조건일 때 중복 방문을 허용해야 한다.
- 현재에서 다음으로 진행했을 때의 시간, 즉 현재 시간 + 1초가 최소 시간보다 크다면 해당 경우는 이미 최소 시간이라는 조건을 만족할 수 없다. 따라서 현재 시간 + 1초가 최소 시간 이하일 때를 중복 방문을 허용해 탐색할 수 있도록 한다.
  
∴ 특정 조건: `현재 시간 + 1초 <= 최소 시간`

### 3. `visited check의 시점`을 Queue에 add할 때가 아니라, poll 할 때 check한다.
만약 Queue에 넣을 때 방문 체크를 해버린다면 위에서 언급한 중복 방문이 허용되는 경우들이 Queue에 들어가지 못한다. 따라서 허용되는 경우들이 모두 Queue에 들어갈 수 있도록 Queue에서 요소들을 꺼낼 때 방문 체크를 하도록 한다.

  
## ✓ Logic
### Class

```java
class Sister {
	
	int position, time;
	
	public Sister(int position, int time) {
		this.position = position;
		this.time = time;
	}
	
}
```
- 동생을 찾으러 가는 동안의 위치, 진행 시간을 속성으로 가지는 클래스이다.
- 위 클래스를 활용해 만든 객체를 Queue에 넣고 빼면서 BFS 진행했다.

### Method

```java
public static void findBrother(int start, int end) {
		
		while (!myQueue.isEmpty()) {
			
			Sister current = myQueue.pollFirst();
			
			if (current.position == end) {
				if (current.time < minTime) {
					minTime = current.time;
				} 
				if (current.time == minTime) {
					methodCnt++;
				} 
			} else {
				visited[current.position] = true;
			}
			
			if (current.time + 1 <= minTime) {
				if (current.position - 1 >= 0 && !visited[current.position - 1]) {
					myQueue.addLast(new Sister(current.position - 1, current.time + 1));
				}
				if (current.position + 1 <= 100000 && !visited[current.position + 1]) {
					myQueue.addLast(new Sister(current.position + 1, current.time + 1));
				}
				if (current.position * 2 <= 100000 && !visited[current.position * 2]) {
					myQueue.addLast(new Sister(current.position * 2, current.time + 1));
				}
			}
		}
	}
```
- BFS를 구현한 메소드
- `중복 체크`는 Queue에서 Sister 객체를 `poll할 때 처리`한다.
- Key Point에서 언급한 `중복을 허용해야 하는 조건`에 따라 조건식을 작성했다.

## ✓ What I realized
- 위 문제와 같이 좌표, 시간 등과 같이 한 번에 체크해야 할 속성들이 많은 경우 class를 만드는 것이 이해하기 직관적이고 깔끔한 것 같아 자주 활용하려고 한다.
- 위 문제를 포함해 유사한 문제들을 풀어보니, BFS가 특별하고 어려운 알고리즘이라기 보단 효율적인 탐색을 하기 위한 하나의 방법이라는 것을 체감했다.
- 막상 풀고나니 BFS를 구현한 메소드 내부 조건식이 길어 가독성이 떨어지는 것 같다고 느꼈다. 이 부분을 조금 더 깔끔하게 고쳐보는 시도를 해봐야겠다.

---
#### references
https://doompa.tistory.com/422

