package boj_16928_뱀과사다리게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * map을 나타내는 배열과, 턴의 횟수를 나타내는 배열 두 가지를 사용해야 한다는 아이디어를 떠올리지 못했다.
 * 
 * 이 아이디어를 타인의 코드를 보고 알아내 다시 시도해보았고,
 * 
 * 1번째에서 시작해야 하는데 0번째에서 시작하는 실수 (문제를 제대로 읽지 않은 유형)
 * 
 * 1째 턴에 갔던 곳에서 시작해서 도착한 곳은 2번째가 되어야 하는데,
 * 
 * queue 내부의 값을 꺼낼 때마다 하나씩 증가하는 수치를 넣어줘버렸다. (코드 작성을 잘못한 유형)
 * 
 * 이 두 가지 실수를 바로잡으니 문제를 해결할 수 있었다.
 * 
 * 추가) queue를 이용하여 탐색을 진행했으니 bfs라고 해야하겠다.
 * 
 * 문제를 제출하고 성공 판정을 받을 때까지만해도 이 문제가 bfs 라는 것을 이해하지 못했다.
 * 
 * 
 */


public class Main {
	
	static int[] map;
	static int[] cnt;
	
	static boolean[] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		// 뱀과 사다리를 반영할 게임판을 구현한 map 배열
		map = new int[100 + 1];
		
		// 각 칸에 몇 번 째 차례에 도달할 수 있는지 작성할 cnt 배열
		cnt = new int[100 + 1];
		
		// 좌표에 따라 사다리나 뱀을 표현하기 위해, 각 칸에 인덱스 값을 부여해주었다.
		for(int i = 1; i <= 100; i++) {
			map[i] = i;
		}
		
		// 사다리의 갯수와 뱀의 갯수가 주어지는데, 구별없이 map에 할당해주면 된다.
		int ladder = Integer.parseInt(st.nextToken());
		
		int snake = Integer.parseInt(st.nextToken());
		
		
		// 앞에 있는 값의 인덱스에 도달하면 뒤에 있는 값의 인덱스에 도착한다는 것을 표현하기 위해
		// map[a][b] 로 값을 할당해주었다.
 		for(int i = 0; i < ladder + snake; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start] = end;
		}
		
		// bfs에 사용할 queue를 생성해주었다.
		Queue<Integer> q = new LinkedList<>();
		
		// 방문한 곳은 또 탐색하지 않도록 방문체크 배열을 만들어주었다.
		visited = new boolean[100 + 1];
		
		// 이 부분에서 실수를 했었다.
		// 1번 칸에서 시작하기 때문에 1을 넣어줘야 하는데,
		// q.add(0)을 작성하여 0번째 칸부터 시작해 오류가 발생했다.
		q.add(1);
		visited[1] = true;

		// bfs 탐색 중에 출발 점의 값을 담아줄 turn 변수.
		int turn = 0;
		
		// 문제 답을 제출하기 위한 ans 변수.
		int ans = -1;
		
		// q가 빌 때 까지 반복한다.
		while(!q.isEmpty()) {
			// bfs의 정석대로 첫 번째 요소를 추출한다. (조회 및 제거)
			int player = q.poll();

			// 추출한 좌표값을 인덱스로하여 cnt 배열에 저장되어 있는 '횟수'값을 가져와 turn 변수에 담아준다.
			turn = cnt[map[player]];
			
			// 사실 필요없는 if문이다.
			// 이것은 뱀을 밟고 뒤로 갔을 때를 표현한 if 문인데,
			// 이 경우에는 값을 계산해서 해당 좌표로 진짜 돌아가는 의미가 없다.
			// 뒤로 가면 횟수가 더욱 멀어지기 때문에, 이 경우에서 최소 횟수를 찾을 가능성은 없기 때문이다.
			// 따라서 뱀을 밟는 경우는 continue로 아예 신경을 쓰지 않아도 된다.
			if(player > map[player]) {
				continue;
			}
			
			// 주사위를 굴려서 최소 한 칸 최대 여섯 칸을 갈 수 있는 것을 구현했다.
			for(int i = 1; i <= 6; i++) {
				
				// 100번 칸을 넘어가거나, 이미 방문했던 칸을 다시 가게 되면 넘기도록 만들었다.
				if(player + i > 100 || visited[map[player + i]] == true) {
					continue;
				}
				
				// 만약 한 번도 방문하지 않은 곳이라면,
				if(cnt[map[player + i]] == 0) {
					// 해당 칸에 이어서 bfs 탐색을 돌아야 하기 때문에 queue에 넣어준다.
					q.offer(map[player + i]);
					// 또한 방문체크도 해준다.
					visited[map[player + i]] = true;
					
					// 그리고 사다리까지 고려해서 이동한 칸을,
					// cnt에서 인덱스로 가져와, 해당 위치에 출발한 곳의 값 + 1을 넣어준다.
					cnt[map[player + i]] = turn + 1;
				}
				
				
				
			}
			
			// 100번째 칸에 0이 아닌 값이 들어가게 되면 출력을 위한 변수 ans에 해당 값을 넣어준다.
			if(cnt[100] != 0) {
				ans = cnt[100];
			}
			
		}
		
		System.out.println(ans);
		
	}
	
	
	
}