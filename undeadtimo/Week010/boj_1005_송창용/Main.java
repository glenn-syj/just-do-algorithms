package boj_1005_ACMCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 위상정렬을 이용한 문제 ACM Craft.
 * 
 * 줄 세우기 또한 위상정렬을 사용하여 푸는 문제이지만,
 * 
 * 줄 세우기는 몇 명'을' 가리키고 있는지에 초점을 맞춰야 하는 문제이고,
 * 
 * ACM은 몇 명'에게' 지목받고 있는지에 초점을 맞춰야 한다.
 * 
 */

public class Main {

	/*
	 * 
	 * ACM 크래프트는 각 건물을 건설할 때, 먼저 만들어야 하는 기저 빌딩이 존재한다.
	 * 
	 * 따라서, 아무런 조건이 필요없는 건물부터 건설을 시작하여, 점점 빌드를 완성시켜야 한다.
	 * 
	 * 
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		// 테스트 케이스 시작
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			// 건물의 갯수를 할당할 변수 buildingCnt
			int buildingCnt = Integer.parseInt(st.nextToken());
			
			// 건물을 건설하기 위한 조건들의 갯수를 담은 ruleCnt 변수.
			int ruleCnt = Integer.parseInt(st.nextToken());
			
			// 각 건물을 건설하는데 걸리는 시간을 건물의 번호와 인덱스를 매칭하여,
			// time 배열에 저장해줄 것이다.
			
			// time 배열을 생성하였다.
			int[] time = new int[buildingCnt + 1];
			
			st = new StringTokenizer(br.readLine());
			
			// 배열에 각 건물을 짓는데 소모되는 시간을 할당하였다.
			for(int i = 1; i <= buildingCnt; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			// 각 건물을 짓기위해 필수적으로 건설해야하는 건물의 갯수를 알 수 없으니,
			// 2중 리스트를 생성하여, 각 건물을 짓기 위해 필요한 건물의 번호를 넣어줄 것이다.
			List<List<Integer>> link = new ArrayList<>();
			
			for(int i = 0; i <= buildingCnt; i++) {
				// 2중 리스트를 사용하기 위해, 리스트 내부에 건물의 갯수 + 1만큼 리스트를 넣어준다.
				link.add(new ArrayList<>());
			}
			
			// 각 빌딩이 몇 개의 필수 조건 건물을 가지고 있는지 저장할 degree 배열.
			// 이 배열을 이용하여, 건물이 가지고 있는 필수 조건이 충족되었는지 확인할 것이고,
			// 0값이 되면, queue에 넣어서, 건설한 것으로 취급할 것이다.
			int[] degree = new int[buildingCnt + 1];
			
 			// 건물을 건설하기 위한 조건들의 갯수만큼 반복한다.
			for(int i = 0; i < ruleCnt; i++) {
				st = new StringTokenizer(br.readLine());

				// 건설을 위한 '조건'이 되는 건물 번호를 start 변수에 할당하였다.
				int start = Integer.parseInt(st.nextToken());
				
				// 건설할 대상이 되는 건물 번호를 end 변수에 할당하였다.
				int end = Integer.parseInt(st.nextToken());
				
				link.get(start).add(end);

				// 조건이 되는 건물의 갯수를 카운트해준다.
				degree[end]++;
			}
			
			Queue<Integer> queue = new LinkedList<>();
			
			// 다른 조건 없이 바로 건설할 수 있는 건물들을 큐에 넣는다.
			for(int i = 1; i <= buildingCnt; i++) {
				if(degree[i] == 0) {
					queue.add(i);
				}
			}
			
			int[] ans = new int[buildingCnt + 1];
			
			for(int i = 1; i <= buildingCnt; i++) {
				ans[i] = time[i];
			}
			
			// 큐가 빌 때까지 반복한다.
			while(!queue.isEmpty()) {
				// 더 이상 필수적으로 건설해야 하는 건물이 없는 건물을 가져와 건설한다.
				int buildNow = queue.poll();
				
				// 해당 건물을 조건으로 가지고 있는 건물 목록을 가져온다.
				List<Integer> nextBuildings = link.get(buildNow);
				
				int size = nextBuildings.size();
				
				// 건물 목록을 순회하면서 건설에 걸리는 시간을 갱신해준다.
				for(int i = 0; i < size; i++) {
					int target = nextBuildings.get(i);
					
					// 해당 건물을 건설하는데 걸리는 시간.
					// 해당 건물을 짓기까지의 현재 비용과, 직전 정점에서 현재 정점까지의 비용중 더 큰 것.
					// 조건이 되는 건물들을 건설하는 비용들을 비교한다고 생각하면 이해가 쉬울 것이다.
					ans[target] = Math.max(ans[target], ans[buildNow] + time[target]);
					
					// 필수적으로 건설해야 하는 건물의 갯수를 하나 감소시킨다.
					degree[target]--;
					
					// 더 이상 필수적으로 건설해야 할 건물이 없다면, queue에 넣어서, 
					// 건설 차례를 기다리게 한다.
					if(degree[target] == 0) {
						queue.offer(target);
					}
					
				}
			}// while문 끝
			
			st = new StringTokenizer(br.readLine());
			
			// 지어야 하는 건물의 갯수를 담은 purpose 변수.
			int purpose = Integer.parseInt(st.nextToken());
			
			// 각 건물 번호별로 건설 시간이 담긴 배열에서, 마지막으로 건설해야하는 건물의 소요시간을 출력.
			System.out.println(ans[purpose]);
			
		}
		
	}
}