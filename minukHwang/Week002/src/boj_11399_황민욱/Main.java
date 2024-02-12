package boj_11399_황민욱;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 풀이방법
 * 1. 그리디 알고리즘 문제
 * 2. 일단 정렬한다.
 * 3. 앞에서 부터 더하고... 더하고... 누적해서 다 더한다.
 * 4. 최종 값 구하면 끝!
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] line = new int[n];
		
		for(int i = 0; i < n; i++) {
			line[i] = sc.nextInt();
		}
		
		Arrays.sort(line);
		
		int total = 0;
		int sum = 0;
		
		for(int i = 0; i < n; i++) {
			sum += line[i];
			total += sum;
		}
		
		System.out.println(total);
		
	}
}
