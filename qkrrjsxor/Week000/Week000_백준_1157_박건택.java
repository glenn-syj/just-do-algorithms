package baekjun;

import java.util.Scanner;

public class 단어공부_1157 {
	public static void main(String[] args) {
		/*
		 * 문자열 입력 받고 대문자로 변환
		 * 문자 하나하나씩 배열에 입력
		 * A~Z : 0~25 인덱스를 설정하고 
		 * 크기 26인 배열에 인덱스에 매칭되는 곳에 카운트 +1  
		 */
		Scanner input = new Scanner(System.in);	
		String word = input.nextLine();
		word = word.toUpperCase();
		int l = word.length();
		char[] c = new char[l]; 	//문자열을 한 글자씩 넣을 배열
		int[] ascii = new int[l];
		int[] arr = new int[26];		//문자에 맞는 인덱스에 카운트 +1 할 배열
		int max = -1000;
		

		for(int i = 0 ; i < l; i++) {
			c[i] = word.charAt(i);	// .charAt(index) 메서드로 문자열을 char[] c 배열에 한 글자씩 입력
			ascii[i] = c[i];		// char[] c 배열의 문자열을 아스키코드 정수로 int[] ascii 배열에 입력
		}
		
		// A : 65, B : 66, ~ Z : 90
		for(int i = 0; i < l; i++) {
			int idx = ascii[i] - 65;
			arr[idx] += 1 ;			//문자의 아스키 코드에 따라 arr 배열에 카운트 +1
		}
		
		int index = 0;
		//int[] arr 내의 최대값 찾기 => 문자의 최빈값
		for(int i =0; i < 26; i++) {
			if(arr[i] > max) {
				max = arr[i];
				index = i;
			}
			else if(arr[i] == max) {
				index = -2;			// max 값이 여러개인 경우 물음표 출력
			}
		}
		
		
		//최빈값을 다시 문자열로 출력
		int temp = index + 65;
		char freq = (char)temp;
		
		
		System.out.println(freq);
		
		input.close();
	}
	
}
