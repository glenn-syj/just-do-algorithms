package SWEA;

import java.util.Arrays;
import java.util.Scanner;

public class Week001_SWEA_1289_박건택 {

	public static void main(String[] args) {
		/*
		 * 1. int로 초기 입력값 받기 
		 * 		-->정수로 받기로 변경, 최대 50자리면 정수면 10^50 이니까,,
		 * 2. 정수를 각 자리마다 char 배열에받고 정수로 변환
		 * 3. zero 배열과 왼쪽부터 자리수 비교하며 다르면 그 지점부터 오른쪽 끝까지 1 또는 0으로 바꾸기
		 * 4. 위 3번에서 "다르면" 일때마다 count++  -> count 출력하면 최소 횟수
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			String original_memory = sc.next(); //원래의 메모리값

			int len = original_memory.length(); 
			int[] bitArr = new int[len];
			int[] zero = new int[len];
			int count = 0;
			
			//입력받은 비트값들을 각 자리별로 정수로 변환하여 bitArr에 저장
			for(int i=0; i<len; i++) {
				bitArr[i] = Integer.parseInt(original_memory.substring(i, i+1));
				zero[i] = 0;		//zero 배열 모두 0으로 초기화
			}
			
			for(int i=0; i<len; i++) {	
				if(bitArr[i] != zero[i]) {
					if(zero[i] ==0) {
						for(int t=i; t<len; t++) zero[t]=1;
					}else if(zero[i] == 1) {
						for(int t=i; t<len; t++) zero[t]=0;
					}
					count++;
				}
			}
			
			System.out.print("#"+test_case + " ");
			System.out.println(count);

			
		}
	}

}
