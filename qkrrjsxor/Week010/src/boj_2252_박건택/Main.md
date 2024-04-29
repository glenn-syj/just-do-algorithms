# 문제 정보
난이도: Gold III
분류 : 위상정렬
url: https://www.acmicpc.net/problem/2252

# 고찰
문제를 보고 위상 정렬을 이용하면 되겠다는 생각이 들었습니다.
위상 정렬이란, 대학교 커리큘럼의 선수과목처럼 선후관계가 있는 노드들을 순서를 이어주는 알고리즘입니다.
위상 정렬에는 큐와 스택을 이용한 두가지 방법이 있습니다.
저는 큐를 사용하는 방법이 더 편하다고 생각하여 큐를 이용하여 위상 정렬을 하였습니다.

# 로직
**[위상 정렬]**
1. 선후관계를 정의하고 위상 정렬을 하기 위해서는 진입 차수와 진출 차수가 필요하다. 진입 차수의 개수가 선행 노드이므로 진입 차수를 저장해주기 위한 배열을 생성한다.
2. 진입 차수가 0인 노드는 선행 노드가 없기 때문에 진입 차수가 0인 모든 노드를 큐에 삽입을 한다.
3. 큐에 담긴 노드들을 하나씩 빼내며 해당 노드의 후행 노드의 진입 차수를 감소시킨다. 
4. 후행 노드의 진입 차수가 0이 되면 다시 큐에 넣는다.
5. 큐가 빌 때 까지 반복을 한다.

# What I Learned
위상 정렬에 관한 문제를 풀며 위상 정렬의 개념과 로직을 복습하였고, 인접 리스트를 이용한 그래프 구현에 대한 연습을 하였습니다.

# Code
```java
package boj_2252_박건택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	//사람(노드) 수
		int E = Integer.parseInt(st.nextToken());	//간선 수
		
		List<Integer>[] adjList = new List[V+1];	//1번 부터 시작 ( 0번은 버려)
		int[] degree = new int[V+1];		//진입 차수 저장
		
		for(int i =1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}//리스트 배열 초기화
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			adjList[start].add(end);
			degree[end]++;
		}	//인접 리스트와 진입 차수 배열 입력
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i<=V; i++) {
			if(degree[i]==0) {
				queue.offer(i);
			}
		}	//진입 차수가 0인 것들 큐에 삽입
		
		
		while(!queue.isEmpty()) {
			
			int node = queue.poll();
			sb.append(node + " ");
			
			for(int i = 0; i < adjList[node].size(); i++) {
				
				degree[adjList[node].get(i)]--;
				
				if(degree[adjList[node].get(i)] == 0) {
					queue.offer(adjList[node].get(i));
				}
			}
			
		}
		
		System.out.println(sb.toString());
	}
}
```

