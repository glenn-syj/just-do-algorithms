package boj_9935_황민욱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		String str = br.readLine();
		String explode = br.readLine();
		int str_length = str.length();
		int explode_length = explode.length();
		

		for (int i = 0; i < str_length; i++) {
			stack.add(str.charAt(i));

			if (stack.size() >= explode_length) {
				int count = 0;
				int start = stack.size() - explode_length;
				
				for(int j = start; j < stack.size(); j++) {
					if(stack.get(j) == explode.charAt(j - start)) {
						count++;
					} else {
						break;
					}
					
					if(count == explode_length) {
						for(int k = 0; k < explode_length; k++) {
							stack.pop();
						}
					}
				}
			}
		}
		
		
		if(stack.isEmpty()) {
			sb.append("FRULA");
		} else {
			for(int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
		}
		
		System.out.println(sb);
	}
}
