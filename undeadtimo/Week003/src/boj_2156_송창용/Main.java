package boj_2156_송창용;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		/*
		 * 포도주 시식,
		 * 연속으로 세 개의 포도주를 고를 수 없다.
		 * 주어지는 포도주를 최대한 많이 마실 때의 양을 출력하는 문제.
		 * 
		 * 패턴을 고려했을 때,
		 * 각 요소의 위치에서 가질 수 있는 최대 포도주량의 배열을
		 * dp의 이름으로 생성한다.
		 * 
		 * 인덱스를 편하게 지정할 수 있도록, dp의 크기는 포도주의 개수보다 하나 더 크게 지정한다.
		 * 따라서 dp[0]은 고정적으로 0이된다.
		 * 다만, dp[0]의 0값이 실제로 입력받은 포도주의 양이 0일 때와 겹쳐서 예외를 발생시킬 수 있으므로,
		 * dp배열을 Integer 타입으로 생성하여, dp[0]를 null 값으로 만들어줘도 된다.
		 * 
		 * dp[1]은 고정적으로 첫 번째 포도주의 양일 것이고,
		 * dp[2]는 고정적으로 첫 번째 포도주의 양 + 두 번째 포도주의 양이다.
		 * 
		 * dp[3]부터는 패턴을 적용할 수 있다.
		 * dp[3]은
		 * dp[3 - 1],
		 * dp[3 - 2] + 세 번째 포도주의 양, 
		 * dp[3 - 3] + 두 번째 포도주의 양 + 세 번째 포도주의 양 
		 * 이 셋 중에서 가장 큰 값이다.
		 * 
		 * dp[3] 이후의 모든 dp 값들은 위의 패턴으로 구할 수 있다.
		 * dp를 저장하고 가져와 사용하는 방식으로는 top-down과 bottom-up이 있다.
		 * 
		 * 
		 */
		Scanner sc = new Scanner(System.in);
		
		StringTokenizer st;
		
		st = new StringTokenizer(sc.nextLine());
		
		// 처음 입력값으로 주어지는 와인잔의 개수를 wineGlass 변수에 할당하였다.
		int wineGlass = Integer.parseInt(st.nextToken());
		
		// 편하게 값을 가져올 수 있도록 와인잔의 개수보다 하나 더 크게 만들어주었다.
		int[] wineAmount = new int[wineGlass + 1];
		
		
		// for 반복문을 통해 입력으로 들어오는 와인양을 전부 배열에 할당해주었다.
		for(int i = 1; i <= wineGlass; i++) {
			st = new StringTokenizer(sc.nextLine());
			
			wineAmount[i] = Integer.parseInt(st.nextToken()); 
		}
		
		// dp 배열도 위의 와인양 배열과 마찬가지로 편하게 사용할 수 있도록,
		// 와인잔의 개수보다 하나 더 큰 크기로 생성해주었다.
		int[] dp = new int[wineGlass + 1];
		
		dp[1] = wineAmount[1];
		
		if(wineGlass >= 2) {
			dp[2] = wineAmount[1] + wineAmount[2];
		}
		
		for(int i = 3; i <= wineGlass; i++) {
			
			if(dp[i] == 0) {
				dp[i] = Math.max(Math.max(dp[i - 2] + wineAmount[i], dp[i - 3] + wineAmount[i - 1] + wineAmount[i]), dp[i - 1]);
			}
		}
		
		System.out.println(dp[wineGlass]);
		
	}
}