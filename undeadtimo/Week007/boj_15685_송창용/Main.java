package boj_15685_드래곤커브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int ans;
	
	// x값과 y값에 변화를 주기 위해 dr과 dc 배열을 만들어주겠다.
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	// 우, 상, 좌, 하. 반시계방향 형태로 만들어주었다.
	// 반대 방향으로 방향을 전환할 것이라면 idx를 + 2 % 4 해주면 되고,
	// 반시계 방향으로 방향을 전환할 것이라면, idx를 + 1 % 4 해주면 될 것이고,
	// 시계 방향으로 방향을 전환할 것이라면, idx를 - 1 해주다가 -1이라면 + 4해주면 될 것이다.
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		boolean[][] map = new boolean[100 + 1][100 + 1];
		
		ans = 0;
		
		int dragonCnt = Integer.parseInt(st.nextToken());
		
		int[][] dragons = new int[dragonCnt][4];
		
		for(int i = 0; i < dragonCnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 이 부분에서 실수를 저질렀다.
			// 문제에서는 x좌표와 y좌표를 입력값으로 제공하는데, 나는 첫 번째 입력값을 row로 취급해 다루었고,
			// 두 번째 입력값을 column으로 취급해 다루어서 오류가 발생했었다.
			dragons[i][1] = Integer.parseInt(st.nextToken());
			dragons[i][0] = Integer.parseInt(st.nextToken());
			dragons[i][2] = Integer.parseInt(st.nextToken());
			dragons[i][3] = Integer.parseInt(st.nextToken());

			// 또한 좌표 (0, 0)을 기준으로 세로는 위로, 가로는 오른쪽으로 뻗어나가는 좌표지도를 생각했는데,
			// 문제를 잘 읽어보지 않아서 생긴 오해였다.
			// 이 문제에서는 가로는 오르쪽, 세로는 아래쪽으로 뻗어나가는 좌표지도이기 때문에,
			// 입력값을 그대로 2차원 배열에 적용해야 한다.

		}
		
		// 드래곤들을 선택할 for문, 이것은 dragonCnt 범위일 것이고,
		// 세대만큼 반복하는 for문, 0부터 시작해서, 0일 때만 예외를 두고, 1일 때부터 stack을 이용하도록.
		// 이게 말이 stack이지 list로 구현한다음, 마지막 요소부터 첫 번째 요소까지 가져오도록 구현해야함.
		// 단순히 가져오도록 하고 list에서 삭제하지 않아야, 이어서 다음 세대를 만들어낼 수 있는 것이지.
		
		List<Integer> drs;
		
		// 존재하는 드래곤만큼 반복하는 for문이다.
		for(int i = 0; i < dragonCnt; i++) {
			int startX = dragons[i][0];
			int startY = dragons[i][1];
			
			map[startX][startY] = true;
			
			int direction = dragons[i][2];
			
			int gen = dragons[i][3];
			
			drs = new ArrayList<>();
			
			// 드래곤을 선택했으니, 세대 만큼 반복하며 로직을 구현하는 for문이다.
			for(int j = 0; j <= gen; j++) {
				if(j == 0) {
					// 0번째 세대는 특별한 로직 없이, 바라보는 방향으로 1칸 이동하면 된다.
					startX += dr[direction];
					startY += dc[direction];
					
					map[startX][startY] = true;
					
					// 움직인 방향을 list 에 넣어준다.
					drs.add(direction);
					
				}
				
				if(j >= 1) { 
					// 1세대 이상부터는 특별한 로직이 필요하다.
					for(int k = drs.size() -1; k >= 0; k--) {
						// 바라보고 있던 방향을 가져와 exdirection 변수에 넣어주었다.
						int exdirection = drs.get(k);
						
						// 드래곤이 다음 세대로 넘어갈 때의 패턴을 살펴보면,
						// 이전에 가졌던 방향들을 역순으로 가져와,
						// 해당 방향의 반대방향, 그리고 시계방향으로 90도 회전한 이후,
						// 한 칸 이동하고 있다.
						exdirection = (exdirection + 2) % 4;
						exdirection -= 1;
						
						// -1 번째 배열을 가리키게 될 수 있으므로,
						// 방향이 음수라면 4를 더해준다.
						if(exdirection < 0) {
							exdirection += 4;
						}
						
						startX += dr[exdirection];
						startY += dc[exdirection];
						
						// 후에, 정사각형을 이루는지 판단하기 위해 방문체크를 해준다.
						map[startX][startY] = true;
						
						// 이 방향 또한 drs 리스트에 넣어준다.
						drs.add(exdirection);
						
					}
				}
				
			}
		}// 그리기 끝.
		
		// 현재 칸, 현재 오른쪽 한 칸, 현재 위치에서 아래쪽 한 칸, 현재 위치에서 오른쪽 한 칸 그리고 아래쪽 한 칸 이동한 위치.
		// 이곳이 전부 드래곤으로 방문한 곳이라면 카운트 횟수를 추가한다.
		for(int i = 0; i <= 99; i++) {
			for(int j = 0; j <= 99; j++) {
				if(map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	
	}
}