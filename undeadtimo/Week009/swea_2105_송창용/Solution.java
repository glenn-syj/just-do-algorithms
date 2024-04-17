package swea_2105_디저트카페.copy;

import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 
 * 디저트 카페 문제를 풀면서 배운 것.
 * 
 * 1) 방향을 조정해주는 방법
 * 재귀함수를 사용할 때, 매개변수로 사방탐색을 돌리는 반복문의 시작을 지정해준다.
 * 
 * 예를들어, dfs(int dir)이 있을 때,
 * for(int d = dir; d < 4; d++){
 * 		dfs(d)
 *  }
 *  
 *  를 해주면, 우상 방향을 마치고, 좌하 방향 탐색이 이루어질 때, 
 *  다시 우상 방향부터 탐색하지 않도록 잡아준다.
 *  
 *  2) 처음 위치에 돌아오는 것을 체크하기 위한 방법.
 *  
 *  시작 위치를 static 변수에 담아주고, 방문 체크로 continue를 하기 이전에
 *  현재 위치가 시작 위치와 동일한지 체크해주면 된다.
 *  
 *  
 *  3) dfs 함수를 만드는 법.
 *  
 *  상황과 조건에 따라서, 재귀함수와, 반복문을 적당하게 사용해주면 된다.
 *  
 *  
 * 
 */


/*
 * 
 * dfs에 익숙하지 않아서 풀지 못했다.
 * 
 * 핵심은 반복문과 재귀를 같이 사용한다는 것.
 * 
 * dfs의 원리를 생각한다면 구현할 수 있을 것이다.
 * 
 * 
 */

public class Solution {
	
	/*
	 * 
	 * 디저트 카페가 시작될 수 있는 위치들을 반복문으로 하나씩 지정후, 해당 위치에서 사방탐색을 돌릴 것이다.
	 * 
	 * 단, dfs가 되기 위해서는 함수 내부의 반복문을 통해 사방 탐색을 하고,
	 * 
	 * 그 안에서 재귀를 통해 가는 방향으로 한 번 더 이동하도록 하는 것이다.
	 * 
	 * 
	 */
	
	/*
	 * 
	 * 문제의 디테일한 부분을 살펴보자면, 한 번 먹었던 종류의 디저트를 다시 먹으면 안되기 때문에
	 * 
	 * 디저트 종류별 섭취 여부를 알 수 있게 해줄 불리언 배열을 추가로 만들어서 사용해야 한다.
	 * 
	 */
	
	
	// 시작지점을 저장해주기 위한 변수 startR과 startC.
	static int startR;
	static int startC;
	
	// 대각선 탐색을 위한 dr 배열과 dc 배열.
	static int[] dr = {-1, 1, 1, -1};
	static int[] dc = {1, 1, -1, -1};
	
	// 재귀함수에서 map 정보를 사용하기 위해 static으로 선언한 2차원 배열.
	static int[][] map;
	
	// 디저트의 섭취 여부와, 방문 여부를 체크하기 위한 불리언 배열들.
	static boolean[] dessert;
	static boolean[][] visited;
	
	// 함수에서 map 바깥으로 나갔는지 체크하기위해, map 크기를 static 으로 선언.
	static int mapSize;
	
	// 함수에서 사용하기 위해 정답 도출 변수를 static 으로 선언.
	static int ans;
	
	// main 함수 시작
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		StringTokenizer st;
		
		st = new StringTokenizer(sc.nextLine());
		
		// 테스트 케이스의 수.
		int T = Integer.parseInt(st.nextToken());
		
		// 테스트 케이스 시작
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// 카페를 순회하여 돌아오지 못할 경우, -1을 출력하기 위해,
			// ans 변수를 -1로 초기화해주었다.
			ans = -1;
			
			st = new StringTokenizer(sc.nextLine());

			mapSize = Integer.parseInt(st.nextToken());
			
			map = new int[mapSize][mapSize];
			

			
			// 도시의 카페 정보를 받아왔다.
			for(int i = 0; i < mapSize; i++) {
				st = new StringTokenizer(sc.nextLine());
				for(int j = 0; j < mapSize; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 시작 위치를 잡기 위한 이중 for문
			// 대각선으로만 이동해서 원래 위치로 돌아오려면,
			// 가로로는 0인덱스에서 n - 3 인덱스까지 가능하고,
			// 세로로는 1인덱스에서 n - 2 인덱스까지 가능하다.
			
			// 세로를 지정해줄 반복문
			for(int i = 1; i <= mapSize - 2; i++) {
				// 가로를 지정해줄 반복문
				for(int j = 0; j <= mapSize - 3; j++) {
					startR = i;
					startC = j;
					
					// 방문체크를위한 배열
					visited = new boolean[mapSize][mapSize];
					// 디저트 체크를 위한 배열
					dessert = new boolean[101];
					
					// 시작 위치를 방문 체크해주고, 디저트도 섭취한 것으로 취급한다.
					visited[i][j] = true;
					dessert[map[i][j]] = true;
					// 세로위치, 가로위치, 먹은 디저트의 갯수, 사방탐색 방향을 조정해줄 변수
					dfs(i, j, 1, 0);
					
					// 해당 위치에서의 dfs 탐색이 완료되면, 다른 곳에서 시작할 것이므로,
					// 방문체크와 디저트 여부를 거짓으로 바꾸어준다.
					visited[i][j] = false;
					dessert[map[i][j]] = false;
				}
			}
			
			System.out.printf("#%d %d%n", test_case, ans);
			
		}// 테스트 케이스 끝
		
	}
	
	public static void dfs(int r, int c, int dessertCnt, int d) {
		
		
		// 사방탐색을 위한 반복문
		// 사방탐색의 시작을 d로 지정해주었다.
		// 이러면 한 번 탐색했던 방향을 다시 탐색하지 않도록 해주어,
		// 시간을 절약할 수 있게 해준다.
		for(int dir = d; dir < 4; dir++) {
			
			// 기존의 위치에서 사방탐색으로 이동한 위치 nr, nc.
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			// 첫 위치로 돌아왔는지 체크.
			// 단 첫 위치에서 움직이자마자 바로 돌아오는 것이 아닌, 
			// 대각선을 통해서 돌아올 수 있도록 하기 위해
			// dessertCnt > 2가 필요하다.
			if(nr == startR && nc == startC && dessertCnt > 2) {
				if(ans < dessertCnt) {
					ans = dessertCnt;
				}
				return;
			}
			
			// 방문했던 곳이면 continue, 단 처음 위치에 돌아오는 것을 확인해야 하므로,
			// 처음 위치에 도달할 때의 체크 뒤에 존재해야한다.
			if(outCheck(nr, nc)) {
				continue;
			}
			
			// 한 번 먹었던 디저트이거나 방문했던 곳이라면 방문하지 않는다.
			if(dessert[map[nr][nc]] || visited[nr][nc]) {
				continue;
			}
			
			// 두 개의 체크를 마쳤으니 재귀를 넣어보자
			visited[nr][nc] = true;
			dessert[map[nr][nc]] = true;
			dfs(nr, nc, dessertCnt + 1, dir);
			// 재귀를 통해 같은 방향으로 뻗어나가다가,
			// 조건에 걸려서 돌아오면 방향을 바꾸도록 한다.
			visited[nr][nc] = false;
			dessert[map[nr][nc]] = false;

		
		}
		
	}
	
	// 도시 바깥으로 나갔는지 체크하기 위한 기능을 메서드로 만들어보았다.
	public static boolean outCheck(int r, int c) {
		if(r < 0 || r >= mapSize || c < 0 | c >= mapSize) {
			return true;
		}else {
			return false;
		}
	}
	
}