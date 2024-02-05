package SWEA;

import java.util.Scanner;

public class Week001_SWEA_1493_박건택 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		// 배열 만들기
		int[][] arr = new int[400][400];
		arr[0][0] = 1;
		
		for(int r=1; r<400; r++) {
			arr[r][0] = arr[r-1][0]+r;
		}
		
		for(int r=0; r<400; r++) {
			for(int c=1; c<400; c++) {
				arr[r][c] = arr[r][c-1] + c + r+1;
			}
		}
		
		//------
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int p = sc.nextInt();
			int q = sc.nextInt();
			int x1 = 0;
			int y1 = 0;
			int x2 = 0;
			int y2 = 0;
			
			
			// r,c = (0,0)부터 (200,200)까지 왼쪽으로 탐색하며 p값 찾기
			// p 값 찾으면 해당 r,c 값 +1 하여 저장 
			//왜 +1 하나? -> x,y 좌표에서는 1부터 시작하였으니까
			for(int r=0; r<200; r++) {
				for(int c=0; c<200; c++) {
					int temp = arr[r][c];
					if(temp == p) {
						x1 = c+1;
						y1 = r+1;
						break;
					}
				}
			}
			
			for(int r=0; r<200; r++) {
				for(int c=0; c<200; c++) {
					int temp = arr[r][c];
					if(temp == q) {
						x2 = c+1;
						y2 = r+1;
						break;
					}
					
				}
			}
			
			System.out.print("#" + test_case + " ");
			System.out.println(arr[y1+y2-1][x1+x2-1]);
			
		}

	}
}
