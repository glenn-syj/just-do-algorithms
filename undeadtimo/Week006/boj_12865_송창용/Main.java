package boj_12865_평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine());
		
		int itemCnt = Integer.parseInt(st.nextToken());
		int canWeight = Integer.parseInt(st.nextToken());
		
		int[] weight = new int[itemCnt + 1];
		int[] value = new int[itemCnt + 1];
		int[][] dp = new int[itemCnt + 1][canWeight + 1];
		
		for(int i = 1; i <= itemCnt; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= itemCnt; i++) {
			for(int j = 1; j <= canWeight; j++) {
				
				// 물건의 무게 자체가 제한을 넘어서서 고려가 불가능할 경우에는
				if(weight[i] > j) {
					// 해당 인덱스의 dp에 이전 물건까지의 dp 값을 넣어준다.
					dp[i][j] = dp[i - 1][j];
				}else {
					
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
				}
				
			}
		}
		
		System.out.println(dp[itemCnt][canWeight]);
		
	}
}