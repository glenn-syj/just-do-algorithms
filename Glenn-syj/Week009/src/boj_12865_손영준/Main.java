package boj_12865_평범한배낭;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.StringTokenizer;

/* B12865. 평범한 배낭
 * 
 * 1. 조건
 * 	1-1. 0-1 Knapsack Problem
 * 
 */

public class Main {
	
	static int N, K;
	static List<int[]> things;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int W, V;
		int[][] items = new int[N+1][2];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			
			items[i][0] = W;
			items[i][1] = V;
		}
		
		int[][] dp = new int[N+1][K+1];
		
		int[] item;
		int valAdd, valNeglect;
		int weight, value;
		
		for (int index=1; index<=N; index++) {
			item = items[index];
			weight = item[0];
			value = item[1];
			
			for (int currWeight=1; currWeight<=K; currWeight++) {
				
				if (weight > currWeight) {
					dp[index][currWeight] = dp[index-1][currWeight];
					continue;
				} else {
					valAdd = dp[index-1][currWeight-weight] + value;
					valNeglect = dp[index-1][currWeight];
					
					dp[index][currWeight] = Math.max(valAdd, valNeglect);
				}
			}
		}
		
		sb.append(dp[N][K]);
		bw.write(sb.toString());
		bw.close();
		
	}
	
}