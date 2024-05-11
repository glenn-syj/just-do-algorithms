package boj_15684_사다리조작;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 세로 막대의 갯수를 나타내는 length
	private static int length;
	// 막대와 막대를 잇는 줄의 갯수를 나타내는 width
	private static int width;
	// 가로 막대가 놓일 수 있는 위치의 갯수를 나타내는 place 
	private static int place;
	
	// 정답을 출력하기 위한 ans 변수.
	private static int ans;
	
	// 사다리의 상태를 표시하기 위한 2차원 배열 ladder;
	private static int[][] ladder;
	
	// i번 사다리에서 출발하여 i번 사다리로 끝나는 조건이 충족되었는지 확인하는 flag 변수.
	private static boolean flag;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 세로 막대의 갯수, 세로 막대를 잇는 가로 막대의 갯수, 가로 막대를 놓을 수 있는 공간의 갯수,
		// 각각을 변수에 담아주었다.
		length = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		place = Integer.parseInt(st.nextToken());
		
		// 막대의 번호를 매칭해주기 위해, 가로와 세로의 길이에 각각 1을 더해준 2차원 배열을 생성하였다.
		ladder = new int[place + 1][length + 1];
		
		// 사다리 배열에 값을 담아주기 위해 생성한 x와 y변수.
		// for문 밖에서 생성할 경우, 메모리가 낭비되지만,
		// 알고리즘 문제를 풀 때는 메모리보단 속도가 더 중요하므로,
		// for문의 바깥에서 생성해보았다.
		int x;
		int y;
		
		for(int i = 0; i < width; i++) {
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			// 세로 막대를 잇는 가로막대의 왼쪽부분을 1로, 오른쪽부분을 2로 표시하였다.
			// 탐색하며 1을 만날 때는 오른쪽으로 가게되고, 2를 만날 때는 왼쪽으로 가게된다.
			ladder[x][y] = 1;
			ladder[x][y + 1] = 2;
		}
		
		// 0부터 3까지 허용하는 가로 막대의 갯수를 지정하고 dfs 함수를 호출한다.
		// 0을 포함하는 이유는, 이미 조건이 충족되어있는 경우가 존재하기 때문이다.
		for(int i = 0; i <= 3; i++) {
			ans = i;
			// 첫 번째 행부터 탐색하기 위해, dfs의 첫 번째 매개변수로 1을 넣었다.
			// 사용하는 가로 막대의 갯수를 저장하기 위해 두 번째 매개변수로 0을 넣었다.
			dfs(1, 0);
			
			// 만일 dfs를 시행한 이후, 조건이 충족되었다면,
			// flag 변수를 통해 반복문을 빠져나온다.
			if(flag) {
				break;
			}
		}
		
		// 만약 3개의 막대까지 사용하며 dfs를 사용했음에도,
		// 조건을 충족하지 못했다면 -1을 출력한다.
		System.out.println(flag ? ans : -1);
		
	}
	
	public static void dfs(int start, int cnt) {
		// 만일 조건이 충족되었다면 바로 dfs를 끝낸다.
		if(flag) return;
		
		// 지정한 가로 막대의 갯수만큼 사용했다면,
		if(ans == cnt) {
			// i번 세로 막대에서 출발하여, i번 세로 막대에서 끝나는지 확인한다.
			if(check()) {
				// 조건이 충족되면 flag 변수를 true로 바꾼다.
				flag = true;
			}
			return;
		}
		
		// 첫 번째 행부터 마지막 행까지 탐색하는 for문이다.
		for(int i = start; i < place + 1; i++) {
			
			// 첫 번째 열부터 마지막 열까지 탐색하는 for문이다.
			// 단, 마지막 세로막대는 가로막대를 이을 다음 세로막대가 없기 때문에,
			// j < length + 1이 아니다.
			for(int j = 1; j < length; j++) {
				// 만일 탐색하는 곳에 막대가 존재하지 않는다면,
				if(ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
					// 가로 막대를 하나 놓고,
					ladder[i][j] = 1;
					ladder[i][j + 1] = 2;
					// dfs를 호출한다.
					dfs(i, cnt + 1);
					// dfs후, 돌아오면서 원상복구를 해주기 위해,
					// 놓았던 가로막대를 다시 없던 것으로 해준다.
					ladder[i][j] = 0;
					ladder[i][j + 1] = 0;
					
				}
			}
		}
		
	}
	
	// 조건을 충족하고 있는지 확인하기 위한 check 메서드.
	public static boolean check() {
		// 모든 세로 막대에 대한 탐색을 하는 for문이다.
		for(int i = 1; i < length + 1; i++) {
			
			// 세로 막대의 번호를 column 변수에 할당하고,
			// 가로 막대를 놓을 수 있는 위치를 처음부터 탐색하기 위해,
			// row 변수에 1을 할당하였다.
			int row = 1;
			int column = i;
			
			// 가로 막대를 놓을 수 있는 위치들을 전부 탐색하는 for문이다.
			for(int j = 0; j < place; j++) {
				
				// 만약 탐색한 곳에 1이 있다면 오른쪽으로 가고,
				if(ladder[row][column] == 1) {
					column++;
				// 탐색한 곳에 2가 있다면 왼쪽으로 간다.
				}else if(ladder[row][column] == 2) {
					column--;
				}
				
				// 갔다가 돌아오는 것을 방지하고 또한,
				// 다음 가로 막대가 가능한 곳을 탐지하기 위해 row 변수에 1을 더해준다.
				row++;
			}
			
			// 만약 출발지점을 나타내는 i와 도착지점을 나타내는 column이 같지 않다면,
			// check 함수를 끝내고 false를 반환한다.
			if(i != column) {
				return false;
			}
		}
		// 모든 탐색을 성공적으로 마치면 true를 반환한다.
		return true;
	}
	
}