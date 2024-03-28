package boj_16236_아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * bfs 형식을 이용해서 문제를 풀어보겠다.
 * 
 * 매 초마다 사방 탐색을 하면서 queue에 좌표값을 넣을 것이다.
 * 
 * 그러다가 상어가 먹을 수 있는 물고기를 해당 초에 발견하게 될 것이다.
 * 
 * 그러면 그곳으로 이동하여 수치에 따라서 상어의 크기를 키워주고,
 * 
 * 만약 해당 초에 먹을 수 있는 물고기가 여러마리라면, 문제에서 제시한 조건에 따라서 물고기를 먹으러 간다.
 * 
 * 상어가 이동(물고기에 다가가서 식사)할 때마다 visited를 초기화해주는 것을 유념해야 한다.
 * 
 * 문제를 풀면서 실수들을 저질렀다. 그리고 이 실수들은 대부분 문제를 제대로 읽지 않아서 발생한 것이다.
 * 1) 상어의 크기 미만의 물고기를 먹을 수 있는 것인데, 상어의 크기 이하의 물고기를 먹을 수 있는 것으로 착각했다.
 * 2) 상어보다 큰 물고기는 지나갈 수 없는데 지나갈 수 있다고 생각했다.
 * 3) 2크기의 물고기를 먹으면 2만큼 수치가 쌓인다고 생각했다. 4크기 상어가 2물고기 두 마리를 먹으면 성장한다고 생각했다.
 * 
 * 이 외에도,
 * 3) 상어의 시작 위치 9를 0으로 만들어주지 않았다.
 * 4) 상어보다 큰 물고기들 때문에 작은 물고기에게 접근이 불가능한 경우를 생각하지 못했다.
 * 
 * 
 */

public class Main {
	
	static int[][] sea;
	static boolean[][] visited;

	// 사방 탐색
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		// 바다의 크기
		sea = new int[n][n];
		// 이동한 곳 체크
		visited = new boolean[n][n];
		
		int sharkR = -1;
		int sharkC = -1;
		
		// 처음 샤크의 크기
		int shark = 2;
		// 레벨업을 위한 포인트
		int point = 0;
		
		// bfs 탐색을 위한 queue. r, c 좌표를 담기 위해 이중 리스트 형태를 취했다.
		List<List<Integer>> queue = new ArrayList<>();
		
		// 같은 거리에 있는 물고기들을 담기 위한 이중 리스트.
		List<List<Integer>> fishes;
		
		// 각 크기의 물고기가 몇마리 있는지 세기 위한 배열
		int[] countingArr = new int[7];
		
		// 바다의 상태를 형성하기 위한 for문.
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				sea[i][j] = Integer.parseInt(st.nextToken());
				
				if(sea[i][j] == 9) {
					List<Integer> tmp = new ArrayList<>();
					
					// 처음 상어의 위치
					tmp.add(i);
					tmp.add(j);
					
					queue.add(tmp);
					// 방문체크
					visited[i][j] = true;
					sea[i][j] = 0;
					
				}
				
				// 카운팅
				if(sea[i][j] >= 1 && sea[i][j] <= 6) {
					countingArr[sea[i][j]]++;
				}
				
			}
		}
		
		
		int sum = 0;
		
		// 1~6크기의 물고기들 수를 센 배열을 가져와서 몇마리를 먹을 수 있는지 센다.
		for(int i = 1; i <= 6; i++) {
			if(i >= 3) {
				if((i * (i + 1) / 2) - 1 > sum) {
					break;
				}
			}
			// sum 변수에 총 먹을 수 있는 물고기들의 수를 담아준다.
			sum += countingArr[i];
		}
		
		// 1크기의 물고기가 존재하지 않는다면, 0을 출력하고 main 메서드를 종료한다.
		if(countingArr[1] == 0) {
			System.out.println(0);
			return;
		}
		
		// 각 while문 반복횟수를 세기 위한, seconds 변수.
		int seconds = 0;
		
		// 먹을 수 있는 물고기들을 전부 먹으면 while문을 빠져나오도록 하기 위해,
		// 현재 먹은 물고기들을 세기 위한 totalPoint 변수.
		int totalPoint = 0;
		
		
		// 예외 발견.
		// 만약 상어보다 큰 물고기들로 사방이 막혀있다면 어떻게 할 것인가.
		// 심지어 움직일 공간이 조금 있는 상태라면? 
		// 큰 물고기 울타리 넘어서 먹을 물고기가 존재한다면??
		// visited 배열 때문에 결국에는 오도가도 못하고 제자리만을 지키며 끝없이 while문이 반복될 것이다.
		
		// 해결책.
		// 물고기를 먹은 순간의 시간만을 저장할 변수를 만들어준다. anotherSeconds
		// 상어가 돌아다니다가 물고기를 먹지 못하고 제자리에서 움직이지 못하는 경우를 지정해서,
		// 그 때 while문을 빠져나오도록 한다.
		
		// 물고기를 먹은 순간의 시간만을 담아줄 anotherSeconds 변수.
		int anotherSeconds = 0;
		
		// 바로 이전 위치를 담아줄 exR, exC 변수.
		int exR = -1;
		int exC = -1;
		
		
		whole:
		while(queue.size() != 0) {
			// 현재 queue에 존재하는 것들만 다루기 위해, queue의 size를 가져와 변수에 할당한다.
			int len = queue.size();
			
			// 같은 시간에 도달할 수 있는 물고기들만을 모아둬야 하기 때문에,
			// 1초가 흐를 때마다, fishes 이중 리스트를 초기화해준다.
			fishes = new ArrayList<>();
			
			for(int i = 0; i < len; i++) {
				// 이중 리스트의 첫 번째 요소를 가져온다. 
				List<Integer> position = queue.get(0);
				
				// 첫 번째 요소에서 행, 열 위치를 가져와서 r, c 변수에 담아준다.
				int r = position.get(0);
				int c = position.get(1);
				
				// 만약 물고기가 이전과 같은 위치라면, 즉 이동하지 못하는 상태라면 while문을 탈출한다.
				if(exR == r && exC == c) {
					break whole;
				}
		
				// 다음 반복 때 비교하기 위해, 현재 위치를 할당해준다.
				exR = r;
				exC = c;
				
				// 첫 번째 좌표 정보를 빼냈기 때문에 queue 에서는 지워준다.
				queue.remove(0);
				
				// 사방탐색을 위한 for문이다.
				for(int d = 0; d < 4; d++) {
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 배열을 벗어나면 continue
					if(nr < 0 || nr >= n || nc < 0 || nc >= n) {
						continue;
					}
					// 방문했던 곳이라면 continue
					if(visited[nr][nc] == true) {
						continue;
					}
					// 상어보다 큰 물고기가 있다면 continue
					if(sea[nr][nc] > shark) {
						continue;
					}
					
					// 만약 해당 좌표에 이동할 수 있다면,
					
					// 좌표를 담아줄 임시 리스트 NextPosition을 생성해주었다.
					List<Integer> NextPosition = new ArrayList<>();
					NextPosition.add(nr);
					NextPosition.add(nc);
					
					
					queue.add(NextPosition);
					
					// 방문 체크하여 되돌아오지 않도록 한다.
					visited[nr][nc] = true;
					
					// 상어가 먹을 수 있는 물고기가 있다면, 
					if(sea[nr][nc] < shark && sea[nr][nc] != 0) {
						// 물고기 리스트에 해당 좌표를 넣어준다.
						List<Integer> tmp = new ArrayList<>();
						tmp.add(nr);
						tmp.add(nc);
						
						fishes.add(tmp);
					}
				}
				
			}
			
			
			
			// 1초가 지났음을 계산해준다.
			seconds++;
			
			
			// 해당 시간동안 먹을 수 있는 물고기무리가 하나만 존재한다면
			if(fishes.size() == 1) {
				
				// 그 물고기가 있는 곳의 좌표를 가져온다.
				int nr = fishes.get(0).get(0);
				int nc = fishes.get(0).get(1);
				
				// 모든 물고기를 먹었는지 확인해줄 totalPoint와,
				// 상어가 성장하기 위한 point에 각각 1씩 더해준다.
				totalPoint++;
				point++;
				
				// point가 상어의 크기보다 같거나 크다면,
				if(point / shark >= 1) {
					// 상어의 크기를 하나 더해주고,
					shark += 1;
					// point는 상어의 이전 크기만큼 빼준다.
					point = point - (shark - 1);
				}
				
				// 상어가 물고기를 먹으면 다시 가장 가까이 있는 물고기를 탐색해야 하기 때문에,
				// 기존의 queue를 초기화해준다.
				queue.removeAll(queue);
				
				// 그리고 현재 위치를 queue에 담아준다.
				List<Integer> tmp = new ArrayList<>();
				tmp.add(nr);
				tmp.add(nc);
				
				queue.add(tmp);
				
				// 더불어 현재 위치를 방문체크 해주고,
				visited = new boolean[n][n];
				visited[nr][nc] = true;
			
				// 물고기를 먹었으니 0값을 그 자리에 넣어준다.
				sea[nr][nc] = 0;
				
				// 물고기를 먹은 순간의 시간을 anotherSeconds 변수에 넣어준다.
				anotherSeconds = seconds;
				
				
			}else if(fishes.size() > 1) {
				// 먹을 수 있는 물고기 무리의 갯수가 1보다 크다면,
				
				// 가장 위쪽에 있는 물고기.
				// 가장 위쪽에 있는 물고기가 여러마리 있다면, 가장 왼쪽에 있는 물고기를 고른다.
				int minR = Integer.MAX_VALUE;
				int minC = Integer.MAX_VALUE;
				
				// 가장 위쪽에 있는 물고기 확인.
				for(int j = 0; j < fishes.size(); j++) {
					List<Integer> fish = fishes.get(j);
					
					if(minR > fish.get(0)) {
						minR = fish.get(0);
						minC = fish.get(1);
					}
					
				}
				int cnt = 0;
				
				// 가장 위쪽에 있는 물고기가 여러마리 있는지 확인
				for(int j = 0; j < fishes.size(); j++) {
					
					List<Integer> fish = fishes.get(j);
					
					if(minR == fish.get(0)) {
						cnt++;
					}
					
					
				}
				
				// 여러마리 있다면 그중에서도 가장 왼쪽에 있는 물고기를 확인
				if(cnt > 1) {
					for(int j = 0; j < fishes.size(); j++) {
						List<Integer> fish = fishes.get(j);
						
						if(minC > fish.get(1) && fish.get(0) == minR) {
							minC = fish.get(1);
						}
						
					}
				}

				int nr = minR;
				int nc = minC;
				
				totalPoint++;
				point++;
				
				if(point / shark >= 1) {
					shark += point / shark;
					point = point - shark + 1;
				}
				
				
				queue.removeAll(queue);
				
				List<Integer> tmp = new ArrayList<>();
				tmp.add(nr);
				tmp.add(nc);
				
				queue.add(tmp);
				visited = new boolean[n][n];
				visited[nr][nc] = true;

				sea[nr][nc] = 0;
				

				anotherSeconds = seconds;
				
				
			} // 여러개의 물고기 무리가 있을 때 끝.
			
			// 먹을 수 있는 물고기들을 전부 먹었다면 while문을 빠져나온다.
			if(totalPoint >= sum) {
				break;
			}
			
		}
		
		System.out.println(anotherSeconds);
		
	}
	
}