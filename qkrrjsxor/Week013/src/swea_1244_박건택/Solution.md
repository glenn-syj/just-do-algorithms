# 문제 정보
문제번호 : 1244
난이도: D3
분류 : dfs, 조합
url: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD

# 고찰
처음엔 큰 수와 작은 수를 바꾸어 가는 것에 초점을 맞추어 생각하였지만 예외적인 상황이 많고 모든 상황을 다룰 수 없기에 모든 경우의 수를 탐색하는 것으로 방법을 바꾸었습니다.
따라서 조합의 모든 경우를 탐색하기 위해 재귀(dfs)를 이용하여 swapCount가 0이 될 때 까지 swap을 하는 모든 경우를 찾았습니다.

# Logic
1. dfs를 이용해 swapCount가 0이 될 때 까지 재귀 반복을 한다.
2. 반복문 이중 포인터를 사용해 배열에서 각 자리수 두개를 골라 swap을 한다.
    - 이중포인터 i, t 가 모든 경우를 조회할 수 있도록 dfs에 start 인덱스를 넘기고 그 위치부터 반복문을 한다.
3. dfs 호출을 한다.
4. swap한 것을 원상복구 한다.

# What I learned
- 오랜만의 알고리즘 재활훈련으로 dfs는 쉽지 않았지만 dfs 로직에 성공하였습니다.
- dfs를 이용한 조합의 경우들을 찾는 방법을 연습하였습니다.

# Java Code
```java

package swea_1244_박건택;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	static int max = -1;
	static int[] numArray;
	static int len;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		int tc = Integer.parseInt(sc.nextLine());

		for (int test = 1; test <= tc; test++) {

			String input = sc.nextLine();
			st = new StringTokenizer(input);

			String num = st.nextToken();
			len = num.length();
			int swapCount = Integer.parseInt(st.nextToken());
			if(swapCount > len) {
				swapCount = len;
			}
			max = -1;
			numArray = new int[len];

			for (int i = 0; i < len; i++) {
				numArray[i] = num.charAt(i) - '0';
			}

			swap(0, swapCount);
			
			System.out.printf("#%d %d%n",test, max);
		}
	}

	public static void swap(int start, int swapCount) {
		// 경계조건
		if (swapCount == 0) {
			int temp = 0;
			for (int i = 1; i <= len; i++) {
				temp += numArray[len - i] * Math.pow(10, i - 1);
			}

			if (max < temp) {
				max = temp;
			}
			return;
		}

		for (int i = start; i < len - 1; i++) {
			for (int t = i + 1; t < len; t++) {
				int temp = numArray[i];
				numArray[i] = numArray[t];
				numArray[t] = temp;
				
				swap(i, swapCount-1);
				
				temp = numArray[i];
				numArray[i] = numArray[t];
				numArray[t] = temp;
			}
		}
	}
}


```
