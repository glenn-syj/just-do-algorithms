package boj_1520_내리막길;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// dfs를 구현하여 예제에 맞는 값을 출력할 수 있었지만,
// dp를 혼자서 구현하지 못해 시간초과가 나왔다.

public class Main {
	
	static int ans;
	
	static int[][] map;
	static int[][] dp;
	
	static int[] dr = new int[] {0, 1, 0, -1};
	static int[] dc = new int[] {1, 0, -1, 0};
	
	static int row;
	static int column;
	
	public static void main(String[] args)throws IOException {
		ans = 0;
		
		StringBuilder sb;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 지도의 세로 크기
		row = Integer.parseInt(st.nextToken());
		// 지도의 가로 크기
		column = Integer.parseInt(st.nextToken());
		
		// 지도 생성
		map = new int[row][column];
		// dp
		dp = new int[row][column];
		
		// 맵에 값 넣어주기
		for(int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < column; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		sb = new StringBuilder();
		
		sb.append(dfs(0, 0));

		// 값 출력
		System.out.println(sb);
		
		
	}
	
	// dfs 및 dp
	public static int dfs(int r, int c) {
		
		// 결승점에 도착하면 1을 반환해준다.
		if(r == row - 1 && c == column - 1) {
			return 1;
		}
		
//		if(dp[r][c] != 0) {
//			return dp[r][c];
//		}
		// 나는 처음에 dp 배열을 디폴트 값인 '0'으로만 초기화가 되도록 했다.
		// 그리고 기저조건으로 주석처리한 위의 if문을 설정해주었는데,
		// 내가 했던 이 방식은, 경로가 생성되지 않는 곳에서의 탐색은 처음부터 다시 탐색하게 되는 것이다.

		// dp의 값들을 -1로 초기화해주고, 아래처럼 기저조건을 설정해줘야,
		// 한 번이라도 탐색이 이루어진 곳에서는, 
			//비록 경로가 생성이 되지 않더라도,
		// 곧바로 0을 반환하도록 해준다.
		
		// dp가 -1값이 아니라면, 즉 한 번이라도 탐색이 이루어졌다면, 해당 값을 반환한다.
		if(dp[r][c] != -1) {
			return dp[r][c];
		}
		
		// 한 번이라도 탐색했음을 나타내기 위해, 0값을 할당해준다.
		dp[r][c] = 0;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 영역 밖으로 나가거나 현재 위치보다 다음 위치의 값이 같거나 높다면 continue
			if(nr < 0 || nr >= row || nc < 0 || nc >= column || map[r][c] <= map[nr][nc]) {
				continue;
			}
			
			// 골인지점까지 도착하는 경로에 그 경우의 수를 누적시켜주는 코드.
			// 이로인해 dp[0][0]은 모든 경로와 겹치기 때문에 골인지점까지 도착하는 경우의 수들이 누적된다.
			dp[r][c] += dfs(nr, nc);
		}
		
		// 그 dp[0][0]을 반환해준다.
		return dp[r][c];
		
	}
	
	
}