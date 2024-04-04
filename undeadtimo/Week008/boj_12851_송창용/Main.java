package boj_12851_숨바꼭질2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 
 * 뱀과 사다리 게임 문제와 비슷하다고 할 수 있는 문제다.
 * 
 * 그러나 뱀과 사다리 게임 문제를 풀었으면서 해당 문제를 보고 같은 bfs 방식으로 풀 수 없을 것이라 단정짓고
 * 
 * 재귀방식으로 문제를 풀려고 하다가 계속해서 시간초과를 일으켰다.
 * 
 * 결국 문제를 푸는 시간이 너무 오래 소모되어 타인의 코드를 참고하였다.
 * 
 * 
 */

/*
 * 
 * 해당 문제는 bfs로 푸는 것이다.
 * 
 * 최소 횟수로 목적지에 도달해야 하는데,
 * 
 * 최소 횟수로 도달하는 방식의 수 또한 출력해야 한다.
 * 
 * 이를 bfs 방식으로 생각해본다면,
 * 
 * 한 차례마다 bfs를 이용해서 퍼져나가며,
 * 
 * 같은 차례에 동시에 목적지에 도달하는 경우의 수들을 측정하는 것이다.
 * 
 * 
 */



public class Main {
	
	static int start;
	static int end;
	static int[] nums;
	
	static int ans;
	
	static List<Integer> end_cnts;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 시작점을 할당해주기 위한 변수 start
		start = Integer.parseInt(st.nextToken());
		
		// 목적지를 할당해주기 위한 변수 end
		end = Integer.parseInt(st.nextToken());
		
		// 각 초가 지났을 때 어떤 위치에 도달할 수 있는지 기록하기 위한 배열 nums.
		nums = new int[end * 2];
		
		// bfs 방식을 사용하기 위해 queue처럼 사용하기 위한 List 타입의 자료구조.
		List<Integer> queue = new ArrayList<>();
		
		// 우선 List 타입으로 만들었지만 Queue 자료구조처럼 사용할 queue에 시작점을 넣어준다.
		queue.add(start);
		
		// 초를 나타낼 cnt 변수.
		// 처음 움직이는 순간은 1초가 지났을 때이기 때문에 1초를 할당해주었다.
		int cnt = 1;
		
		// 각각 이동했을 때의 위치를 표시해줄 doub, plus, minus 변수.
		int doub = 0;
		int plus = 0;
		int minus = 0;
		
		// 목적지에 도달했음을 알기 위한 boolean 타입의 flag변수.
		boolean flag = false;
		
		// 같은 시간에 목적지에 도달하는 방식의 수를 세기 위한 ans 변수.
		int ans = 0;
		
		// 처음에는 이 예외를 생각하지 못했다.
		// 문제에서는 시작 위치가 목적지보다 이전에 있다고 명시하지 않기 때문에,
		// 시작 위치가 목적지보다 이후에 있을 때를 생각해야한다.
		if(end <= start) {
			// 시작 위치가 목적지보다 이후에 있으면, 뒤로 한칸씩 이동하여,
			// 목적지에 도달하는 방법밖에 없으므로,
			// 목적지까지 도달하는 최소 시간은 시작점 - 목적지이며,
			// 그것은 한 가지 방식밖에 없으므로 1을 출력해주면 된다.
			System.out.println(start - end);
			System.out.println(1);
			
			// 밑에 추가로 구현한 기능들이 실행되지 않도록 return 해준다.
			return;
		}
		
		// bfs를 하기 위한 핵심 로직 부분이다.
		// queue가 비어버리면 반복을 중단한다.
		// 하지만 목적지에 도달하는 것이 더 빠를 것이기 때문에,
		// 해당 조건으로 반복문이 멈추는 경우는 없을 것이다.
		while(queue.size() != 0) {
			
			// 1초마다 갈 수 있는 곳을 전부 탐색해야 하므로,
			// 퍼져나갈 수 있는 경우의 수들을 가져온다.
			// 즉, 현재 queue에 들어있는 위치값들의 수를 가져온다.
			int qSize = queue.size();
			
			// '지금' queue에 존재하는 위치값들을 대상으로 반복하는 for문이다.
			for(int i = 0; i < qSize; i++) {
				
				// queue에 들어있는 것 중, 첫 번째 요소, 즉 가장 먼저 들어왔던 요소를 가져오고,
				int point = queue.get(0);
				
				// 그 요소는 queue에서 제거해준다.
				queue.remove(0);
				
				// 수빈이는 현재 위치의 두 배에 해당하는 곳으로 순간이동하거나
				doub = point * 2;
				// 걸어서 한 칸 앞으로 가거나,
				plus = point + 1;
				// 걸어서 한 칸 뒤로 갈 수 있으니 전부 각각 변수에 할당해준다.
				minus = point - 1;
				
				// 배열을 벗어나 에러를 일으키지 않도록 범위지정을 해주었다.
				// 사실 double은 2를 곱하는 것이기 때문에 0보다 아래로 내려갈 일은 없어서,
				// 뒤쪽에 있는 조건은 사실 필요없다.
				if(doub < end * 2 && doub >= 0) {
					
					// 해당위치에 도달한 적이 없다면,
					// 현재 '몇 초'인지를 배열에 저장해준다.
					if(nums[doub] == 0) {
						nums[doub] = cnt;
						
						// 그리고 해당 좌표를 queue에 다시 넣어준다.
						// 이 점을 중심으로 다음 초에 다시 탐색을 퍼뜨려야하기 때문.
						queue.add(doub);
						
						// 근데, 현재 위치가 목적지라면,
						if(doub == end) {
							// 목적지에 도착했음을 인식하기 위해
							// flag 변수를 true로 바꾸고,
							flag = true;
							
							// 방식 하나로 도달에 성공했음을 카운트하기 위해
							// ans값을 1 더해준다.
							ans++;
						}
					}else {
						// else 즉, 해당 위치에 도달한 적은 있으나,
						// 이미 존재하는 값보다 더 일찍 도착했거나 같은 수치라면,
						if(nums[doub] >= cnt) {
							// 해당 위치에 존재하는 값을 현재 '초' 값으로 덮어씌워준다.
							nums[doub] = cnt;
							
							// 그리고 해당 위치와 해당 시간에서 탐색을 뻗어나갈 수 있도록,
							// queue에 넣어준다.
							queue.add(doub);
							
							// 해당 위치가 목적지라면,
							if(doub == end) {
								// 반복을 끝내기 위해 flag를 true로 바꾸고,
								flag = true;
								
								// 방식 하나로 도달했음을 카운트해준다.
								ans++;
							}
						}
					}
				}
				
				// 아래의 한 칸 뒤로 갔을 때와, 한 칸 앞으로 갔을 때의 로직은,
				// 위의 순간이동했을 때와 동일하다.
				
				if(minus >= 0 && minus < end * 2) {
					
					if(nums[minus] == 0) {
						nums[minus] = cnt;
						queue.add(minus);
						if(minus == end) {
							flag = true;
							ans++;
						}
					}else {
						if(nums[minus] >= cnt) {
							nums[minus] = cnt;
							queue.add(minus);
							if(minus == end) {
								flag = true;
								ans++;
							}
						}
					}
				}
				
				if(plus < end * 2 && plus >= 0) {
					
					if(nums[plus] == 0) {
						nums[plus] = cnt;
						queue.add(plus);
						if(plus == end) {
							flag = true;
							ans++;
						}
					}else {
						if(nums[plus] >= cnt) {
							nums[plus] = cnt;
							queue.add(plus);
							if(plus == end) {
								flag = true;
								ans++;
							}
						}
					}
				}

			}
			
			// 목적지에 도달한 순간이므로 반복문을 빠져나온다.
			if(flag) {
				break;
			}
			
			cnt++;
			
		}
		
		// 목적지에 도달하는 최소 시간을 출력하고,
		System.out.println(cnt);
		
		// 몇 가지 방식으로 도달할 수 있었는지를 출력한다.
		System.out.println(ans);
		
	}
	
	
}