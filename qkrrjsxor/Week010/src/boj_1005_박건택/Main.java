package boj_1005_박건택;
/*
 * 노드 간에 선후 관계까 있으므로 위상정렬을 사용하자
 * 
 * n번 노드를 건설하기 위해 걸리는 최소 시간은 그 아래 레벨들에서 걸리는 시간의 최대값의 합 + n번 노드 건설시간
 * ex) 예시 문제에서의 경우, 4번 노드를 건설하기 위해 걸리는 최소 시간은
 * 레벨 1 : 1번 노드 - 10초
 * 레벨 2 : 레벨 1 + Math.max(2번 노드, 3번노드) - 110초
 * 레벨 3 : 레벨 2 + 4번노드 - 120초
 * 
 * 이렇듯 그 하위 레벨까지 건설 시간의 최대 + 본인 노드이다
 * 이를 배열로 만들어서 각 n번 노드를 건설하는데 걸리는 시간을 저장해보자
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int test = 0; test<tc; test++) {
			
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			//간선 배열을이용하자
			List<Integer>[] adjList = new List[V+1];
			for(int i =1 ; i <V+1; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			
			//건물들 건설 시간 저장
			int[] time = new int[V+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= V; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			//위상정렬 큐와 차수 배열
			Queue<Integer> queue = new LinkedList<>();
			int[] degree = new int[V+1];
			
			//간선 연결 정보 입력
			for(int i =0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				adjList[start].add(end);
				degree[end]++;
			}
			
			//목적 건물 입력
			int target = Integer.parseInt(br.readLine());
			
			//진입차수 0인 놈들 큐에 넣기
			int[] maxTime = new int[V+1];	//각 건물 지을 때 걸리는 최대 시간 저장할 배열
			for(int i = 1; i < V+1; i++) {
				if(degree[i] == 0) {
					queue.offer(i);
					
					maxTime[i] = time[i];
				}
			}
			
			while(!queue.isEmpty()) {
				int node = queue.poll();
				
				//큐에서 뽑고 후위 노드의 진입차수 줄이기
				for(int i = 0; i< adjList[node].size(); i++) {
					
					int afterNode = adjList[node].get(i);
					degree[afterNode]--;
					
					//큐에서 뽑은 다음에 후위 노드에 걸리는 시간 최대값을 비교하며 넣자
					maxTime[afterNode] = Math.max(maxTime[node] + time[afterNode], maxTime[afterNode]);
					
					//후위차수 진입차수가 0이 되면
					if(degree[afterNode] == 0) {
						queue.offer(afterNode);
						//큐에 넣는 다는것은... 건물을 짓는다는 것 이므로 최대 시간을 구해서 maxTime에 넣어주자 <--- 아니야 이거 아니야 지워
					}
				}
				
			}
//			System.out.println(Arrays.toString(maxTime));
			
			System.out.println(maxTime[target]);
			
		}
	}
}
