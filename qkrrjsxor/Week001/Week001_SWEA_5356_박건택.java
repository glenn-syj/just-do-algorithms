package SWEA;

import java.util.Scanner;

public class Week001_SWEA_5356_박건택 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		 * 1. 5개의 문자열을 받으므로 String[5] 길이의 배열을 만들어서 각각 담는다
		 * 2. 각 문자열당 최대 15글자이므로 char[5][15] 2차원 배열을 만든다
		 * 3. 각 문자열의 한 글자씩 char 배열에 담기
		 * 4. for문으로 세로로 읽기
		 */

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String[] words = new String[5];
			char[][] c = new char[5][15];
			
			for(int i=0; i<5; i++)	// String[] words에 5개 문자열 입력
				words[i] = sc.next();
			
			for(int i=0; i<5; i++) { //c[i][15]에 한 글자씩 넣어보자	i : 행, t : 열
				for(int t=0; t<words[i].length(); t++) {//각 행의 문자열의 길이만큼씩 반복
					c[i][t] = words[i].charAt(t);
				}
			}
			
			
			System.out.print("#" + test_case + " ");	//#test_case
			
			//세로로 읽기
			//
			for(int col=0; col<15; col++) {
				for(int row=0; row<5; row++) {
					if(c[row][col] != 0) {
						System.out.print(c[row][col]);
					}
				}
				
			}
			System.out.println();
			
		}

	}

}
