# SWEA 2383. 점심 식사시간

## ✓ Difficult Point
- DFS로 충분히 풀 수 있는 문제이긴 했으나, 유니온 파인드 개념을 다시 잡고 싶어서 도전했다가 오히려 더 어렵다고 생각이 들었다.
- 가장 큰 문제의 근원은 사이클을 이룬다는 것을 어떻게 표현할 것인지, 그리고 지속적으로 findset을 안하면 parent 배열 내의 정보들이 바뀌지 않기 때문에 문제 케이스에 따라서 틀릴 수 있다는 것을 캐치하지 못한 부분이다.

<br/>

## ✓ Logic
1. 정점의 수(V)와 간선의 수(E)를 입력받고, 각 정점에 대해 자기 자신을 부모로 하는 초기화 과정을 거친다.
2. 모든 간선에 대해 두 정점을 확인하고, 각 정점의 루트를 찾는다. 만약 루트가 같다면 사이클이 형성된 것이므로 루트의 부모 노드에 0으로 표지한다. 
3. 두 루트가 다르면 두 정점을 연결을 하는데, 여기서 중요한 부분은 parent 배열에 0에는 계속 0으로 유지가 되어야하기 때문에 전체적으로 노드가 번호 순서대로 위계를 가지며 연결될 수 있도록 조건을 지정한다. (추후에 무한 루프 방지)
4. 연결 과정이 끝나면 각각의 정점의 최종 루트를 찾고 만약에 0이 아니라면 해당 루트를 Set에 넣는다. (겹치지 않게 부모 노드만 찾기 위해서)
5. Set의 크기에 따라서 출력하면 문제 해결 

<br/>

## ✓ Code
```java
package boj_4803_황민욱;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static int[] p;
	static Set<Integer> tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = 1;

		while (true) {
			int V = sc.nextInt();
			int E = sc.nextInt();

			if (V == 0 && E == 0)
				break;

			p = new int[V + 1];
			tree = new HashSet<>();

			for (int i = 0; i < V + 1; i++) {
				p[i] = i;
			}

			int[][] edges = new int[E][2];

			for (int i = 0; i < E; i++) {
				edges[i][0] = sc.nextInt();
				edges[i][1] = sc.nextInt();
			}

			for (int i = 0; i < E; i++) {
				int px = findset(edges[i][0]);
				int py = findset(edges[i][1]);

				union(px, py);
				System.out.println(Arrays.toString(p));
			}

			for (int i = 1; i < V + 1; i++) {
				int parent = findset(i);
				if(parent > 0) {
					tree.add(parent);
				}
			}

			sb.append("Case ").append(t).append(": ");

			if (tree.isEmpty()) {
				sb.append("No trees.").append("\n");
			} else if (tree.size() == 1) {
				sb.append("There is one tree.").append("\n");
			} else {
				sb.append("A forest of ").append(tree.size()).append(" trees.").append("\n");
			}

			t++;
		}

		System.out.println(sb);
	}

	private static void union(int x, int y) {
		if (x == y) {
			p[y] = x;
			p[x] = 0;
		} else if (x < y) {
			p[y] = x;
		} else {
			p[x] = y;
		}
	}

	private static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}

		return p[x];
	}
}


```

## ✓ What I realized
- 해당 문제의 핵심은 일단 연결을 하고 다시 한번 더 부모 노드를 확인해서 사이클이 형성되어 있는지 아닌지를 파악해야하는 구조였다. 처음에 접근했던 방식은 입력을 받자마자 findset으로 부모 노드를 확인하고, 이 둘이 같으면 사이클 형성을 표지하여서 문제를 해결했다. 이렇게하면 입력 구조에 따라서 부모 노드 배열의 값이 업데이트되지 않은 상태에서 사이클 유무를 파악하다보니 놓치는 부분들이 생긴다.

- 또한 사이클 유무에 따라서 어떤 방식으로 트리의 수를 확인할 것인지도 중요하다. 만약 사이클을 이룬다면 부모 배열에서 0으로 표지를 하고, 전체 노드들의 부모를 확인하는 과정을 거쳤다.

- 처음에 단순히 0 표지만 하는 조건을 추가하였는데 스택 오버 플로우가 발생했다. 이는 0 표지로 인하여 parent 배열의 0번 인덱스에 값이 들어가게되면, union이 끝나고 모든 정점의 부모 노드를 찾을 때 무한 루프에 빠지게 된다. 해당 부분을 해결하기 위해서 무조건 번호가 작은 정점이 부모가 될 수 있도록 하였다.

- Set을 처음 사용해보았는데, 겹치지 않는 자료 구조를 만들어서 정답을 구해야할 때 유용하게 사용될 것 같다. (Set 인터페이스, HashSet 구현체를 사용했다.)