package baekjun;

import java.util.Scanner;

public class 영식이와_친구들_1592 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int M = input.nextInt();
		int L = input.nextInt();
		int min = 0;	//최소공배수 변수
		
		//최소공배수 구하기
		for(int i = 1; i <= N*L; i++) {		//1~ N*L 까지 증가시키며 N과 L 동시에 나누어떨어지는 수 찾기
			if( (i%N == 0) && (i%L == 0)) {
				min = i;
				break;
			}
		}
		
		
		int result = (min/L) *(M-1);
		System.out.println(result);
	}

}
