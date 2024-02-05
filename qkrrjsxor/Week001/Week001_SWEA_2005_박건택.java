package SWEA;

import java.util.Scanner;

public class Week001_SWEA_2005_박건택 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[][] triangle = new int[N][N];
			
			if(N==1) {
				triangle[0][0] = 1;
			}
			else if(N==2) {
				triangle[0][0] = 1;
				triangle[1][0] = 1;
				triangle[1][1] = 1;
			}
			else {
				triangle[0][0] = 1;
				triangle[1][0] = 1;
				triangle[1][1] = 1;
				
				for(int i=2; i<N; i++) {
					for(int t=1; t<i+1; t++) {
						triangle[i][t] = triangle[i-1][t-1] + triangle[i-1][t];
						triangle[i][0] = 1;
						triangle[i][i] = 1;
					}
					
				}
			}
			
			System.out.println("#" + test_case);
			for(int i=0; i<N; i++) {
				for(int t=0; t<N; t++) {
					if(triangle[i][t] !=0) {
						System.out.print(triangle[i][t]+" ");
					}
				}
				System.out.println();
			}
			
		}

	}

}
