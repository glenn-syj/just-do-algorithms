package swea_4008_황민욱;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int T, N;
	static int[] visited;
	static int[] numbers;
	
	static int max, min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			N = sc.nextInt();
			visited = new int[4];
			numbers = new int[N];
			
			for(int i = 0; i < 4; i++) {
				visited[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; i++) {
				numbers[i] = sc.nextInt();
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			perm(0, numbers[0]);
			
			sb.append(max - min).append("\n");
		}
		System.out.println(sb);
	}

	private static void perm(int depth, int answer) {
		if(depth == N-1) {
			max = Math.max(max, answer);
			min = Math.min(min, answer);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(visited[i] > 0) {
				visited[i]--;
				int temp = answer;
				
				switch(i) {
				case 0:
					temp += numbers[depth+1];
					break;
				case 1:
					temp -= numbers[depth+1];
					break;
				case 2:
					temp *= numbers[depth+1];
					break;
				case 3:
					temp /= numbers[depth+1];
					break;
				}
				
				perm(depth + 1, temp);
				visited[i]++;
			}
		}
		
	}
	
}
