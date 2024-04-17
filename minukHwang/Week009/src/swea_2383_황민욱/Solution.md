# SWEA 2383. 점심 식사시간

## ✓ Difficult Point
- 문제 접근과 로직까지는 괜찮았으나, 사람들을 하나씩 줄세우고 시뮬레이션 하는 과정에 있어서 어려움을 느꼈다.
- 초기에는 우선 순위 큐에 넣은 사람들을 시간이 되면 한명씩 빼서 처리를 하려고 하였지만, 대기하는 사람들을 처리하는 과정이 원활하게 진행되지 않았고 이를 위해서 계단 큐를 만들어서 계단을 내려가고 있는 사람들을 확인했다.

<br/>

## ✓ Logic
1. 계단은 두개라고 했으니 각각의 계단에 어떠한 사람들을 보내냐에 따라서 달라지게 되므로, 모든 경우의 수를 따져서 비교하기 위해서 부분 집합을 활용하여 계단 2개로 나누어서 보낸다.

2. 계단으로 보내는 사람들의 집단은 Priority Queue를 활용하여 구성한다. Priority Queue에 들어갈 사람들의 클래스를 만들어서 넣고, 우선 순위 큐의 정렬 조건을 계단으로부터의 거리로 설정한다. (시간이 적게 걸릴 수록 더 빨리 나올 수 있기 때문이다.)

3. 2개로 나누어진 사람들의 Priority Queue에서 조건에 맞게 사람들을 꺼내면서, 문제에서 제시한 방식에 따라서 시뮬레이션을 진행한다. 

   1. 반복문을 통해서 시간을 증가시킨다. 
   
   2. 우선 순위 큐 제일 처음 나올 사람의 시간이 만약 지금 시간과 같거나 작다면, 사람들을 큐에서 뺀다.
   
      - 작다면의 이유: 계단이 꽉차서 사람들이 계단으로 못 넘어갈 수 있기 때문에 본인이 갈 수 있는 시간보다 지났다면 가능하다.

   3.  계단 우선 순위 큐를 만들어서 계단으로 갈 수 있는 사람들을 우선 순위 큐에 넣는다. 
   
       - 여기서 유의할 부분은 계단 큐에 넣을 때, 사람들의 시간을 계단 진입 시간으로 바꾼다. 만약 현재 시간과 동일한 사람들은 +1 (1분 후에 이동할 수 있다고 했기에), 만약 현재 시간보다 적은 사람은 현재 시간(계단에 들어가는 시점)으로 사람들의 시간을 변경해줘야한다.

        - 또한 계단에 3명만 내려갈 수 있다고 했기에, 계단 우선 순위 큐의 크기가 3보다 작아야만 사람들을 넣을 수 있다.

    4. 계단 큐의 사람들은 각각의 사람들의 시간 (계단 진입 시간)과 현재 시간의 차이가 계단의 길이와 같다면 계단에서 빼준다. 
   
        - 우선 순위 큐에 넣은 이유는 사람들의 계단 진입 시간에 따라서 먼저 내려가기 때문이다.

        - 계단의 길이만큼 시간이 걸린다고 했으니 해당 조건을 활용한다.

    5. 계단 우선 순위 큐, 사람들 우선 순위 큐 모두 비어진다면, 반복문을 끝내고 최종 시간을 리턴한다.
    
4. 사람들 우선 순위 큐가 두 개가 있기 때문에 각각 시뮬레이션을 진행하고, 각각의 결과인 최종 시간 중 최대의 값이 하나의 구해진 부분 집합의 최종 결과이다.

5. 모든 부분 집합 케이스를 위의 시뮬레이션을 진행하면서 최종 시간을 구하고 최소 시간을 갱신한다. 

<br/>

## ✓ Code
```java
package swea_2383_황민욱;

import java.util.ArrayList;
import java.util.Arrays;

// 부분 집합 문제!

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
	static int T;
	static int N;
	static int[][] map;

	static int[][] start;
	static int[] stairL;

	static List<Person> people;
	static PriorityQueue<Person> pq1;
	static PriorityQueue<Person> pq2;

	static boolean[] visited;

	static int min;

	static class Person implements Comparable<Person> {
		int r;
		int c;
		int t;

		Person() {

		}

		Person(int r, int c) {
			this.r = r;
			this.c = c;
		}

		private int getTime(int startR, int startC) {
			return Math.abs(this.r - startR) + Math.abs(this.c - startC);
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.t, o.t);
		}

		@Override
		public String toString() {
			return "Person [r=" + r + ", c=" + c + ", t=" + t + "]";
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = sc.nextInt();
			map = new int[N][N];
			people = new ArrayList<>();
			start = new int[2][2];
			stairL = new int[2];

			int idx = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();

					if (map[r][c] == 1) {
						people.add(new Person(r, c));
					}

					if (map[r][c] > 1) {
						start[idx][0] = r;
						start[idx][1] = c;
						stairL[idx] = map[r][c];
						idx++;
					}
				}
			}

			visited = new boolean[people.size()];
			min = Integer.MAX_VALUE;

			subset(0);

			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}

	public static void subset(int idx) {
		if (idx == people.size()) {
			pq1 = new PriorityQueue<>();
			pq2 = new PriorityQueue<>();

			// 부분 집합으로 판별해서 넣어주기
			for (int i = 0; i < visited.length; i++) {
				Person person = people.get(i);

				if (visited[i]) {
					person.t = person.getTime(start[0][0], start[0][1]);
					pq1.add(person);
				} else {
					person.t = person.getTime(start[1][0], start[1][1]);
					pq2.add(person);
				}
			}

			int time = movePeople(pq1, stairL[0]);
			time = Math.max(movePeople(pq2, stairL[1]), time);

			min = Math.min(min, time);
			return;
		}

		visited[idx] = true;
		subset(idx + 1);
		visited[idx] = false;
		subset(idx + 1);
	}

	private static int movePeople(PriorityQueue<Person> pq, int length) {
		if (pq.isEmpty()) {
			return 0;
		}

		int time = pq.peek().t;

		PriorityQueue<Person> stair = new PriorityQueue<>();

		while (!pq.isEmpty() || !stair.isEmpty()) {
			while (!stair.isEmpty() && time - stair.peek().t == length) {
				stair.poll();
			}

			while (!pq.isEmpty() && pq.peek().t <= time && stair.size() < 3) {
				Person person = pq.poll();

				if (person.t == time) {
					person.t++;
				} else {
					person.t = time;
				}

				stair.add(person);
			}

			if (pq.isEmpty() && stair.isEmpty()) {
				break;
			}

			time++;
		}
		return time;
	}

}

```

## ✓ What I realized
- 우선 순위 큐를 사용하는 이유, 그리고 정렬을 위한 Comparable 활용법에 대해서 다시 생각해볼 수 있었다. (만약 단순히 배열 혹은, 큐를 활용했다면 매번 반복문으로 탐색을 진행했어야 할 것이다.)
  
- 다양한 알고리즘, 자료구조, 시뮬레이션을 유기적으로 연결해서 코드를 짤 수 있어야 한다. (특히, SWEA 모의 문제들은) 백준 문제들을 풀다보면, 하나의 알고리즘에 편향되는 경우가 많은데 이 문제는 부분 집합, 우선 순위 큐, 시뮬레이션 모두 활용해야했다.

- 시뮬레이션할 때 가장 중요한 부분은, 문제 조건에 맞게 진행하되, 조건을 명확하게 하지 않으면 안된다. 따라서 문제를 확실하게 읽고 모든 조건들을 놓치지 않도록 해야한다. 반복문을 돌릴 때, 계단에 도착하고 1분 뒤에 계단을 내려갈 수 있다는 부분을 놓쳐서 난항이 있었다.