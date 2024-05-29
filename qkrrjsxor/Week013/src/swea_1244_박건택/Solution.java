package swea_1244_박건택;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	static int max = -1;
	static int[] numArray;
	static int len;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		int tc = Integer.parseInt(sc.nextLine());

		for (int test = 1; test <= tc; test++) {

			String input = sc.nextLine();
			st = new StringTokenizer(input);

			String num = st.nextToken();
			len = num.length();
			int swapCount = Integer.parseInt(st.nextToken());
			if(swapCount > len) {
				swapCount = len;
			}
			max = -1;
			numArray = new int[len];

			for (int i = 0; i < len; i++) {
				numArray[i] = num.charAt(i) - '0';
			}

			swap(0, swapCount);
			
			System.out.printf("#%d %d%n",test, max);
		}
	}

	public static void swap(int start, int swapCount) {
		// 경계조건
		if (swapCount == 0) {
			int temp = 0;
			for (int i = 1; i <= len; i++) {
				temp += numArray[len - i] * Math.pow(10, i - 1);
			}

			if (max < temp) {
				max = temp;
			}
			return;
		}

		for (int i = start; i < len - 1; i++) {
			for (int t = i + 1; t < len; t++) {
				int temp = numArray[i];
				numArray[i] = numArray[t];
				numArray[t] = temp;
				
				swap(i, swapCount-1);
				
				temp = numArray[i];
				numArray[i] = numArray[t];
				numArray[t] = temp;
			}
		}
	}
}
