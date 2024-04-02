package boj_17404_박건택;
/*
 * 조합으로 풀어 보자
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int first;
	static int sum;
	static int N;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int t = 0; t < 3; t++) {
				arr[i][t] = Integer.parseInt(st.nextToken());
			}
		}
		
		sum += arr[0][0];
		first = 0;
		sumCount(1,0,0);
		
		sum += arr[0][1];
		first = 1;
		sumCount(1,0,1);
		
		sum += arr[0][2];
		first = 2;
		sumCount(1,0,2);
		System.out.println(ans);
	}

	public static void sumCount(int r, int c, int before) {
		//base case
		if(r==N-1) {
			if(before==first) {
				sum = Math.max(sum+arr[N-1][(first+1)%3], sum+arr[N-1][(first+2)%3]);
			}
			else {
				sum = sum+ arr[N-1][0]+ arr[N-1][1]+ arr[N-1][2] - arr[N-1][first] - arr[N-1][before];
			}
			
			if(sum < ans) {
				ans = sum;
			}
			return;
		}
		
		//recursive
		
		for(int i = 0; i < 3; i++) {
			if(i == before)	continue;
			sum += arr[r][c];
			sumCount(r+1, i, c);
			sum -= arr[r][c];
		}
	}
}
