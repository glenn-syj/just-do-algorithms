	package boj_14938_서강그라운드;
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.StringTokenizer;
	
	public class Main_Floyd {
	
		// 각 노드에서 각 노드까지의 최단 거리를 기록해나가기 위한 2차원 배열에 초기값으로 지정할 INF 정적 변수이다.
		public static final int INF = 987654321;
		
		public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			StringTokenizer st;
			
			st = new StringTokenizer(br.readLine());
			
			// 지역의 갯수를 나타내는 nodeCnt 변수이다.
			int nodeCnt = Integer.parseInt(st.nextToken());
	
			// 탐색 범위 제한을 나타내는 limit 변수이다.
			int limit = Integer.parseInt(st.nextToken());
			
			// 지역과 지역을 이어주는 길의 갯수를 나타내는 roadCnt 변수이다.
			int roadCnt = Integer.parseInt(st.nextToken());
			
			// 각 지역의 번호와 인덱스를 매칭시켜서 지역이 갖고있는 아이템을 표시한,
			// itemNum 배열이다.
			int[] itemNum = new int[nodeCnt + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= nodeCnt; i++) {
				// 각 지역이 갖고있는 아이템의 갯수를 배열에 넣어준다.
				itemNum[i] = Integer.parseInt(st.nextToken());
			}
				
			// 각 지역에서 각 지역까지 갈 수 있는 최소 거리를 저장할 2차원 배열 map이다.
			int[][] map = new int[nodeCnt + 1][nodeCnt + 1];
	
			// 2차원 배열 map의 요소들에 큰 정수값인 INF를 할당한다. 
			for(int i = 1; i <= nodeCnt; i++) {
				for(int j = 1; j <= nodeCnt; j++) {
					if(i == j) {
						continue;
					}
					map[i][j] = INF;
				}
			}
			
			// 바로 이어져있는 두 지역에 대해 우선 해당 거리를 임의로 두 거리 간의 최소 거리로 지정한다.
			for(int i = 0; i < roadCnt; i++) {
				st = new StringTokenizer(br.readLine());
				
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				int dist = Integer.parseInt(st.nextToken());
				
				// 양방향이기에 두 지역에 모두 최소 거리임을 표시한다.
				map[start][end] = dist;
				map[end][start] = dist;
			}
			
			// 추가로 거쳐서 지나쳐야할 지역을 가리키는 k를 고르는 for문이다.
			// 만일 k가 i또는 j와 값이 같다면 continue를 해도 상관없다.
			for(int k = 1; k <= nodeCnt; k++) {
				
				// 시작지점 지역 번호 i를 고르는 for문이다.
				for(int i = 1; i <= nodeCnt; i++) {
					
					// 도착지점 지역 번호 j를 고르는 for문이다.
					for(int j = 1; j <= nodeCnt; j++) {
						
						// 이전에 탐색해서 최소 거리인줄 알았던 것이, 다른 곳을 경유했을 때보다 거리가 멀다면,
						// i에서 j까지 도달하는 최소 거리를 k를 경유한 거리로 바꿔준다.
						
						// 예를들어, 1지역에서 2지역까지 100만큼 떨어져있다.
						// 1지역에서 3지역까지는 1, 3지역에서 2지역까지 1만큼 떨어져 있다고 할 때,
						// 맨 처음 map을 만들었을 때 임의의 최소거리로 1에서 2까지의 최소거리를 100으로 할당해주었다.
						// 그러나 k는 3 i는 1, j는 2가 선정되면서,
						// 1번 지역에서 2번 지역까지 3을 경유해서 가게되는 거리는 총 2가된다.
						// 따라서 100보다 2가 작으므로, 최소거리는 2로 갱신된다.
						
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
						
					}
				}
			}
			
			int ans = 0;
			int total = 0;
			
			// 모든 지역에서 출발하는 경우의 수를 살펴보아야 하기 때문에,
			// 모든 지역에 대해 탐색하는 for문을 작성했다..
			for(int i = 1; i <= nodeCnt; i++) {
				total = 0;
				// 정해진 출발지점에서 다른 모든 지역까지의 거리를 볼 것이다.
				for(int j = 1; j <= nodeCnt; j++) {
					
					if(map[i][j] <= limit) {
						total += itemNum[j];
					}
					
				}
				
				// 모든 지역에서 구할 수 있는 아이템의 갯수 중 최대를 구하는 구문이다.
				ans = Math.max(ans, total);
				
			}
			
			// 모든 지역에서 출발하는 경우의 수를 살펴보았을 때, 가장 많은 아이템을 가지게 될 때의 아이템 갯수를 출력한다.
			System.out.println(ans);
		}
	}
	
	
