## 백준 1107 리모컨

**문제**
수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.

리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.

수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오. 

수빈이가 지금 보고 있는 채널은 100번이다.

**입력**
첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다.  둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다. 고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.

**출력**
첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.

### 내 풀이
처음엔 간단한 문제인 줄 알고 접근했었으나, 반례모음의 도움도 받고 peek까지 해서 풀 수 있었다.

*반례 모음: https://www.acmicpc.net/board/view/46120*

해당 문제는 브루트 포스, 즉 완전 탐색 문제였다. 

최초 접근 시에는 완탐식으로 하지 않기 위해 분기도 만들어보고 조건문도 더 세분화해보고 여러 시도를 했었으나, 결국 문제가 요구하던 것은 0~ 999,999까지 전부다 탐색하며 맞는 조건을 찾는 것이 아니었을까 싶다...

문제를 요약해 보면 핵심은 완탐을 하되, 옮겨야 하는 채널로 고장난 버튼을 누르지 않고 가장 가깝게 이동해 버튼을 누르는 횟수를 최소화 해야한다는 것이였다.

나는 0~999,999까지의 반복문 안에서 고장난 버튼을 누를 경우 `boolean isBreak = false;` 변수를 통해 `if (!isBreak)` 조건문을 이용하도록 하여, 제대로 된 버튼만을 눌렀을 경우를 체크하도록 했다. 체크는 `i`를 `String.valueOf(i)` 메소드를 통해 문자열로 바꾸어 저장해서 `num.charAt(j) - '0'` 이렇게 각 자리수별로 체크할 수 있도록 했다.

그리고 고장난 버튼은 boolean 사이즈 10짜리 배열을 만들어서 입력 받을 수 있도록 했다.

다만 고민되는 부분이 두 가지 있었다.

우선 `if (N == curr) { System.out.println(0); }` 이렇게 목표 채널과 현재 채널 (100)이 같을 경우 바로 0을 출력하도록 했는데, 이 분기가 있는 것이 더 효율적인지 고민된다.

그리고 `curr` 변수는 100이라는 값만을 갖는 상수와 같은 존재인데, 굳이 변수에 값을 담는 게 나은지 가독성 측면에서 오히려 고민이 되기도 했다.

아직까지도 브루트포스와 절친은 아닌 듯 해서 꾸준한 연습이 필요할 듯 하다.

### 전체 코드
```java
package boj_1107_리모컨;

import java.util.Scanner;

public class Main {

	static int N, M;
	static boolean[] broken;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		broken = new boolean[10]; // 고장난 버튼 확인용도
		for (int i = 0; i < M; i++) {
			broken[sc.nextInt()] = true;
		}

		int curr = 100;

		if (N == curr) {
			System.out.println(0);
		} else {
			int result = Math.abs(N - curr);
			// 냅다 0부터 999999까지 완탐
			for (int i = 0; i <= 999999; i++) {
				String num = String.valueOf(i); // 숫자를 문자열 num에 저장

				boolean isBreak = false;
				for (int j = 0; j < num.length(); j++) {
					if (broken[num.charAt(j) - '0']) {
						// 고장난 버튼을 눌러야 하면 멈춘다.
						isBreak = true;
						break;
					}
				}
				if (!isBreak) {
					// 버튼을 누르는 횟수 중 가장 적은 횟수를 result에 담는다.
					int min = Math.abs(N - i) + num.length(); // 이 부분 peek
					result = Math.min(min, result);
				}
			}
			System.out.println(result);
		}

	}

}

```



