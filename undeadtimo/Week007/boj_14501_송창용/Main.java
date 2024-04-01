package boj_14501_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp 문제일 것이겠지만,
 * 
 * dp 방식을 사용하는 것이 아직 익숙치 않아 재귀로 문제를 풀었다.
 * 
 * 
 */

public class Main {
	
	// dp를 위한 배열
	static int[] dp;
	
	// 출력을 위한 변수
	static int ans;
	
	// 총 일하는 날짜 D
	static int D;
	
	// 각 상담 정보를 담을 days 2차원 배열.
	static int days[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		D = Integer.parseInt(st.nextToken());
		
		days = new int[D + 1][2];
		
		for(int i = 1; i <= D; i++) {
			st = new StringTokenizer(br.readLine());
			days[i][0] = Integer.parseInt(st.nextToken());
			days[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 날짜 일수와 인덱스를 연결하기 위해 D + 1 크기의 배열을 만들었다.
		dp = new int[D + 1];
		
		// 최대값을 찾기 위해, ans 값을 가장 작은 정수값으로 만들어주었다.
		ans = Integer.MIN_VALUE;
		
		// 1일부터 시작하는 것이기에 첫 번째 매개변수로 1을 넣어주고,
		// 0원부터 시작하는 것이기에 두 번째 매개변수로 0을 넣어주었다.
		recur(1, 0);
		
		System.out.println(ans);
		
	}
	
	public static void recur(int day, int money) {
		// D일이 지난 이후에 상담이 끝난다면,
		// 이것은 고려 대상이 아니기 때문에 ans 값과 비교하지 않고,
		// 바로 return한다.
		// 여기서는 값을 계산하기 전에 조건을 확인하고 있으므로,
		// D + 1을 초과해야 마지막 날에 1일짜리 상담을 무시하지 않을 수 있다.
		if(day > D + 1) {
			return;
		}
		
		// D일이 지났다면, 여기서는 값을 계산하기 전에 조건을 확인하고 있으므로,
		// D + 1인지를 확인해줘야, 마지막 날에 1일짜리 상담을 계산할 수 있다.
		if(day == D + 1) {
			if(ans < money) {
				ans = money;
			}
			return;
		}
		
		// 상담을 했을 때의 재귀.
		// 상담에 걸리는 일수를 day에 더해주고, 상담료를 money에 더해준다.
		recur(day + days[day][0], money + days[day][1]);
		
		// 상담을 하지 않았을 때의 재귀
		// day에 1을 더해주고, money에는 아무것도 더해주지 않는다.
		recur(day + 1, money);
	}
	
	
}