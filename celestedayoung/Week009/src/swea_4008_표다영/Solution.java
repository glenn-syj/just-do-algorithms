package 숫자만들기;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	
	static Scanner sc;
	static StringTokenizer st;
	static int testNum, gameNum, tmp, pointer, max, min;
	static int[] nums, operators, result;
	
	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		
		testNum = sc.nextInt();
		
		for (int t = 1; t <= testNum; t++) {
			
			gameNum = sc.nextInt();
			sc.nextLine();
			
			operators = new int[4];
			
			st = new StringTokenizer(sc.nextLine());
			operators[0] = Integer.parseInt(st.nextToken());
			operators[1] = Integer.parseInt(st.nextToken());
			operators[2] = Integer.parseInt(st.nextToken());
			operators[3] = Integer.parseInt(st.nextToken());
			System.out.println(Arrays.toString(operators));

			
			nums = new int[gameNum];
			
			st = new StringTokenizer(sc.nextLine());
			for (int i = 0; i < gameNum; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			result = new int[gameNum - 1];
			
			makeNumbers(0);
			
			System.out.printf("#%d %d%n", t, max - min);
		}
		
		
	}
	
	static void makeNumbers(int depth) {
		
		if (depth == gameNum - 1) {
			
			tmp = nums[0];
			pointer = 1;
			
			for (int i = 0; i < result.length; i++) {
				if (result[i] == 0) {
					tmp += nums[pointer];
					pointer++;
				} else if (result[i] == 1) {
					tmp -= nums[pointer];
					pointer++;
				} else if (result[i] == 2) {
					tmp *= nums[pointer];
					pointer++;
				} else {
					tmp /= nums[pointer];
					pointer++;
				}
			}
			max = Math.max(tmp, max);
			min = Math.min(tmp, min);
			
			return;
		}
		
		for (int i = 0; i < operators.length; i++) {
			if (operators[i] != 0) {
				operators[i]--;
				result[depth] = i;
				makeNumbers(depth + 1);
				operators[i]++;
			}
		}
		
	}

}
