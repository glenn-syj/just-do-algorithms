package boj_17404_RGB거리2;

/*
 * 
 * 2차원 dp 배열을 이용한 RGB거리 2
 * 
 * dp 배열이 비록 2차원이지만 일반적인 dp와 큰 차이점은 없다.
 * 
 * dp에 값을 할당하는 패턴과, 메모이제이션 형태를 유의해서 문제를 파악해보도록 하자.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	// 집의 갯수를 나타내는 houseCnt 변수
	static int houseCnt;
	
	// 각 집별로 특정한 색깔을 칠할 때의 비용을 저장할 house 2차원 배열
	static int[][] house;
	
	// 각 색깔별로 최소 비용을 저장할 dp 2차원 배열
	static int[][] dp;
	
	// 출력을 위한 ans 변수
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 집의 갯수를 받아와서 할당해주었다.
		houseCnt = Integer.parseInt(st.nextToken());
		
		// 집을 색칠할 때의 비용을 저장할 house.
		house = new int[houseCnt][3];
		
		// 각 집을 색깔별로 칠했을 때의 최소 비용을 저장할 dp 배열.
		dp = new int[houseCnt][3];
		
		// 출력을 위한 ans 변수를 최대 정수 값으로 할당해주었다.
		ans = Integer.MAX_VALUE;
		
		// 집의 각 정보를 할당해주기 위해, 입력값을 받아오는 for문이다.
		for(int i = 0; i < houseCnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			house[i][0] = Integer.parseInt(st.nextToken());
			house[i][1] = Integer.parseInt(st.nextToken());
			house[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// 첫 번째 색깔을 고르는 for문으로 비즈니스 로직을 시작한다.
		// 첫 번째 색깔을 직접 골라주는 이유는,
		// 첫 번째 집에 대한 정보는 dp에 직접 넣어줘야 하며,
		// 첫 번째 집의 색깔 고르는 경우를 분리해주어, 마지막 집의 색깔과 겹치지 않게 하기 위해서이다.
		for(int firstColor = 0; firstColor < 3; firstColor++) {
			// 고르는 색깔은 그대로 dp에 넣어주고, 다른 색깔은 아예 배제해줘야 하기 때문에,
			// 집을 칠하는 비용의 최대 값인 1000 * 집의 갯수 최대값인 1000을 넣어주었다.
			// 나는 이것을 Integer.MAX_VALUE로 해주었다가, dp가 누적해서 계산되면서,
			// Integer.max_value에 특정 값이 더해지니,
			// Integer.min_value가 되어버렸다.
			dp[0][firstColor] = house[0][firstColor];
			dp[0][(firstColor + 1) % 3] = 1000 * 1000;
			dp[0][(firstColor + 2) % 3] = 1000 * 1000;
//			dp[0][(firstColor + 1) % 3] = Integer.MAX_VALUE;
//			dp[0][(firstColor + 2) % 3] = Integer.MAX_VALUE;
		
			// 2번째 집부터 마지막 집까지 dp를 계산하는 for문 이다.
			// 현재 집의 0번째 색깔에서의 최소비용을 알기 위해,
			// 바로 이전 집의 1번째 색깔의 최소비용과, 바로 이전 집의 2번째 색깔의 최소비용을 비교해서,
			// 더 작은 수치와 현재 집을 0번 색깔로 칠하는 비용을 더해주면
			// dp값이 된다.
			for(int j = 1; j < houseCnt; j++) {
				dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + house[j][0];
				dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + house[j][1];
				dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + house[j][2];
			}
			
			// 마지막 집을 기준으로 최종적인 최대값을 고르는 for문이다.
			for(int k = 0; k < 3; k++) {
				// 처음에는 이렇게 작성했었다.
				// 그러나 이렇게 하면, 첫 번째 집과 마지막 집의 색깔이 겹치는 경우도 고려하기 때문에,
				// 아래처럼 firstColor != k를 해주어 색깔이 겹치지 않도록 해줘야 한다.
//				if(ans > dp[houseCnt - 1][k]) {
//					ans = dp[houseCnt - 1][k];
//				}
				if(ans > dp[houseCnt - 1][k] && firstColor != k) {
					ans = dp[houseCnt - 1][k];
				}
			}
		
		}
		
		System.out.println(ans);
		
	}
	
	
}