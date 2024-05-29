## 백준 9663번 N-Queen

**문제**

크기가 N x N 인 체스판 위에 퀸 N 개가 서로를 공격 할 수 없게 놓는 경우의 수를 구하는 문제이다.  


### 최초 풀이
최초 풀이 시, 체스판의 형태를 떠올리며 2차원 배열에 나타내면 되겠다고 생각했다.

그래서 체스판으로 사용할 배열과 방문을 확인할 배열을 각각 2차원 배열에 나타내고자 했다.

그러나 체스판에 퀸을 새롭게 놓을 때마다 상하좌우 같은 줄에 이미 위치한 퀸이 있는지, 대각선에 퀸이 있는지 체크해야해서일까? 바로 시간초과를 마주하게 되었다.

### 수정된 풀이
나와 같은 과정을 겪은 [블로그](https://tear94fall.github.io/lecture/2020/09/16/n-queen-problem.html)의 설명을 힌트 삼아, 1차원 배열을 통해 문제를 풀이하였다.

배열의 index가 퀸의 열이 되고, 각 index의 원소 값이 퀸의 행이 되는 것이다.

0번 인덱스인 첫 번째 열부터 한 줄씩 퀸을 채워나가면서 놓을 수 있는 위치라면 재귀호출을 해 채워나가는 방식으로 풀이했다.

이 방법은 메모리도 아낌은 물론, 같은 열에 대해서는 검사할 필요가 없어져서 (하나를 놓으면 즉시 다음 열로 넘어가니까) 탐색 시간도 크게 줄일 수 있는 장점이 있었다.

우선 같은 행인 부분에 대해서는 
```java
    for (int i = 0; i < col; i++) {
            if (arr[col] == arr[i]) {
				return false;
			}

```
이렇게 검사할 수 있었는데, 대각선인 부분에 대해서 검사를 어떻게 깔끔하게 할 수 있을지 수학적 아이디어를 떠올리지 못하다가 [다른 블로그](https://st-lab.tistory.com/118)의 설명을 보고 이해하게 되었다.

```java
        else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
			return false;
		}
```
위 코드처럼, 열의 차와 행의 차가 같을 경우가 대각선에 놓여있는 경우이며 열은 col과 i를, 행은 각 배열의 값을 사용함에 주의하여 구할 수 있었다.


**전체 코드**

```java
package boj_9663_N_Queen;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static int[] arr;
	public static int N, count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 체스판의 크기
		arr = new int[N]; // 퀸이 몇행인지 위치 표시할 배열 생성

		count = 0;
		findQueen(0);
		System.out.println(count);

	} // main

	public static void findQueen(int depth) {
		if (depth == N) {
			count++;
//			System.out.println(Arrays.toString(arr));
			return;
		}

		for (int i = 0; i < N; i++) {
			arr[depth] = i;
			
			// 해당 depth에서 i에 놓을 수 있는지 검사
			if (can(depth)) {
				findQueen(depth + 1);
			}
		}
	}

	public static boolean can(int col) {
		for (int i = 0; i < col; i++) {

			// 같은 행일 경우
			if (arr[col] == arr[i]) {
				return false;
			}

			// 대각선인 경우
			// 열의 차와 행의 차가 같을 경우가 대각선인 경우
			else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
				return false;
			}

		}
		return true;
	}

}

```





