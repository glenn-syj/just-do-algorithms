package boj_9935_황민욱;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Character> stack = new Stack<>();
		String str = sc.next();
		String explode = sc.next();

		int pointer = 0;

		for (int i = 0; i < str.length(); i++) {
			stack.add(str.charAt(i));

			if(stack.size() >= explode.length()) {
				int start = stack.size() - explode.length();
				for(int p = start; p < stack.size(); p++) {
					
				}
			}

			
		}
	}
}
