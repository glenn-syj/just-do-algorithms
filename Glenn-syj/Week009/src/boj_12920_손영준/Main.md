## BOJ 12920 평범한 배낭 2

### 들어가며

[평범한 배낭 2](https://www.acmicpc.net/problem/12920)는 기본적인 냅색 알고리즘에 대한 응용 문제로, "평범한 배낭"의 심화격인 문제라고 할 수 있다. (플래티넘 난이도는 골드 문제 알고리즘의 심화 버전으로도 보인다.)

이 문제는 dp를 이용할 때, 어떻게 더 효율적으로 이용할 수 있을지에 대한 문제이다. 즉, 어떻게 중복되는 연산을 깔끔하게 최소화할 수 있는지 묻고 있다.

### 설계 및 풀이

기본적으로 아래 주석과 같이 풀이했다.

```java
/* B12920. 평범한 배낭2
 * 
 * 1. 조건
 * 	1-1. 기본적으로 0-1 Knapsack 과 동일
 * 		-> 같은 V, C 가 나열되는 대신 K로 응축되어 있다고 생각하면 편함
 * 	1-2. 물건 종류가 N개 이지만, K 가 최대 10000개 있으므로 총 NK개의 개수
 * 		-> 기존처럼 모두 하나씩 할 시에는
 * 		-> O(K^2 N^2) 이므로 시간초과가 날 가능성이 높음
 *	1-3. '같은 종류'의 물건에 대해서는 한 row에 동시적으로 작업하면됨
 *
 * 2. 풀이
 * 	2-1. 풀이에서 가장 중요한 점은 같은 종류에 대해서는 한 행에만 적으면 된다는 것
 * 		(1) currWeight % V = 0 이고, 횟수를 추가한 count가 K보다 작거나 같을 때
 * 			-> V, C를 바로 사용하지 않음
 * 		(2)	전체 M에서 currWeight를 뺀 남은 자리 leftWeight에 대해서	
 * 			maxVal을 만들어둔다. 
 * 			-> dp[i][currWeight]는
 * 				A. dp[i-1][currWeight-(cnt*V)] + cnt*C '들' 중 최댓값 (cnt를 기준으로 반복문)
 * 				B. dp[i-1][currWeight]
 * 			-> A과 B중 더 큰 값을!
 */
```

**설계 및 풀이 과정**

1. 기본 냅색 알고리즘의 한계

    결국 '같은 무게'의 물건에 대해서는 최대 `M/V`개 까지만 이용할 수 있기에, `C`가 높은 순으로 정렬해 제한을 두고 이용하면 된다.

    그러나, 이에 대해서 일반적인 냅색 알고리즘으로 이용할 시 시간복잡도는 O(S^2N^2)가 된다. (여기에서 S는 K의 총합이다.)

    점근적으로 접근하자면, N은 최대 100이므로 실행에 큰 영향을 미치지 않는다. 하지만 K의 총합은 `V*K`의 값이 최대 10000이라는 제한에서, V=1인 경우 혹은 V가 작다면 최악의 경우 약 100만 정도로 추산할 수 있다.

    그렇다면 일반적인 냅색 알고리즘에 대해 약 100만 * 100만 = 1조번의 연산이 필요하게 된다.


2. 심화적인 냅색 알고리즘 이용

- `currentWeight/V` 이용

    결국 해당 item을 얼마나 이용할 수 있는지는 `현재 무게/물품 무게`에 달려 있다. 0개를 이용하는 경우(이용 X)부터 `currentWeight/V` 개를 이용하는 경우까지 반복문을 돌린다.

    여기까지 구현했을 때는 `1 1 10000`이 최대 100번 주어지는 경우에 `3xxx ms`가 걸렸다. 

- 한 item에 대해서 `M/V`개까지 `C`가 최대인 경우를 저장

    이를 위해서는 각 item들에 대해서 (1) `item.V`를 기준으로 우선 정렬하고 (2) `item.C`를 기준으로 한 번 더 정렬해야 했다.

    정렬한 뒤에는 `item.V` 값을 key로 갖고, `int[]`를 value로 갖는 HashMap에 저장해서 꺼내쓰기로 했다. (`keySet()` 메소드를 이용하면 간편하다.)

    그런데 해당 HashMap에 대해서 M/V개 까지만 M/V+1 개의 원소를 갖는 int[]에 저장하면, 이후 같은 `V`를 가진 아이템에 대해서는 무시할 수 있다.

    나아가, 변수가 i개 있다면 그 가치는 이전까지의 합 + 해당 배열의 i번째 인덱스 값이다. 따라서 누적합을 적용함으로써 소요 시간을 조금이라도 더 줄였다.

- 이후에는 원래의 냅색 알고리즘처럼 이용한다

3. 결과

- 시간

    측정 결과 `3xxx` ms가 나왔던 반례에 대해 `1xx` ms가 나왔다. 또한, 정답 코드 역시도 `1000ms`의 시간 제한 초과에서 `460 ms`로 줄어들어 문제를 통과할 수 있었다.

- 메모리

    `20000 kb` 이용으로 널널하게 통과했다.

### 코드

```java
package boj_12920_평범한배낭2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
    // 정렬할 수 있도록 Comparable 구현
	static class Item implements Comparable<Item> {
		
		int V, C, K;
		
		public Item(int V, int C, int K) {
			this.V = V;
			this.C = C;
			this.K = K;
		}

		@Override
		public int compareTo(Item o) {
			if (this.V > o.V) {
				return -1;
			} else if (this.V == o.V) {
				if (this.C > o.C) {
					return -1;
				} else if (this.C == o.C) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long startTime = System.currentTimeMillis();
//		System.out.println("startTime: " + startTime);
		
		int V, C, K;
		
		int[] counts = new int[M+1];
		ArrayList<Item> itemList = new ArrayList<>();
		Map<Integer, int[]> queueMap = new HashMap<>();
		
		for (int idx=1; idx<=N; idx++) {
			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			itemList.add(new Item(V, C, K));
			
		}
		// 우선적으로 같은 무게에서 가치가 높은 경우들만을 이용하기 위해 정렬
		Collections.sort(itemList);
		
		Item item;
		int initPtr = 1;
		int kCount = 0;
		for (int idx=0; idx<N; idx++) {
			item = itemList.get(idx);
			kCount = 0;
			
			if (queueMap.get(item.V) == null) {
				initPtr = 1;
				queueMap.put(item.V, new int[M/item.V+1]);
			}
			
			while (kCount < item.K && queueMap.get(item.V)[M/item.V] == 0) {
                // 누적합 이용
				queueMap.get(item.V)[initPtr] = queueMap.get(item.V)[initPtr-1] + item.C;
				initPtr++;
				kCount++;
			}
			
			
		}
		
		
		int[] currArr;
		int mapSize = queueMap.size();
		int[][] dp = new int[mapSize+1][M+1];
		
		int ptr = 1;
		int innerPtr;
		
		int possibleK, localMax, localVal;
		
		for (int key: queueMap.keySet()) {
			
			currArr = (int[]) queueMap.get(key);
			for (int currWeight=1; currWeight<=M; currWeight++) {
				
				possibleK = currWeight / key;
				localMax = Integer.MIN_VALUE;
				
				if (possibleK > currArr.length) {
					possibleK = currArr.length;
				}
				
                // 0개 ~ 이용가능한 최대까지 이용
                // localMax가 해당 key의 currWeight에서의 최댓값이 됨
				for (int cnt=0; cnt <= possibleK; cnt++) {
					localVal = dp[ptr-1][currWeight-(cnt*key)] + currArr[cnt];
					if (localMax < localVal) {
						localMax = localVal;
					}
				}
				
				dp[ptr][currWeight] = localMax;
			}
			
			ptr++;
		}
		
//		long endTime = System.currentTimeMillis();
//		System.out.println("endTime: " + endTime);
//		System.out.println("elapsedTime: " + (endTime-startTime));
		
		sb.append(dp[mapSize][M]);
		bw.write(sb.toString());
		bw.close();
		sb.setLength(0);
		
	}
	
}
```

### 느낀점

**문제 관련**

- 처음으로 플레티넘 문제를 풀었는데, 나름대로 활용 가능한 자원을 이용해 문제를 풀어냈다. 원래 문제가 의도했던 바와는 조금의 차이가 있는 것 같아, 원 의도를 파악할 필요가 있다.

- 기본적으로 로직에 대해서는 비슷하지만, 2차원 배열로도 충분히 문제를 해결할 수 있었을 것이다.

**자료구조 관련**

- 문제를 풀기 위해서 어떤 자료구조가 가장 필요하고 적절한지 고민하는 기회가 되었다. 동시에, 적절하지 않은 자료구조를 선택했을 때 로직을 구현하기 힘들어 어려움을 겪었다.

  - `O(1)`의 탐색 시간을 갖는 HashMap 이용
  - `Queue`, `List`, `int[]` 순으로 `HashMap`의 value type을 바궈 봄
    (최대 `M/V` 인덱스를 이용할 수 있으며, 해당 값은 이미 알고 있음)

- 직접 만든 클래스를 생성하고 이용하고, `Collections` 자료구조를 이용하면서 단순 이차원 배열보다는 결과적으로 실행 속도가 더욱 느렸던 것 같다.