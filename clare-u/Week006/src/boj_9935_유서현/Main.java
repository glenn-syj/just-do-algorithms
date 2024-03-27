package boj_9935_유서현;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	// 처음에 폭파시킨 이후 다시 반복문(재귀식)으로 풀려다가 시간초과가 날 것 같아서 다른 문제를 피크
	// 새로운 임시문자열(스택)을 이용하는 게 더 낫다는 점 확인
	// 스택에 넣어주면서 이 문자열의 길이가 폭발문자열의 길이와 같거나 커지면,
	// 문자열의 뒤에서 폭발 문자열 길이 만큼을 비교해서 같으면 폭발시키고
	// 아니면 문자열을 추가시키기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String boom = sc.next();

		// 스택 선언
		Stack<Character> stack = new Stack<>();

		// 처음에 while문으로 했다가 for문으로 변경
		for (int idx = 0; idx < str.length(); idx++) {
			stack.push(str.charAt(idx));
			boolean a = true;

			if (stack.size() >= boom.length()) {
				for (int i = 0; i < boom.length(); i++) {
					if (stack.get(stack.size() - boom.length() + i) != boom.charAt(i)) {
						a = false;
						break;
					}
				}

				if (a) {
					for (int i = 0; i < boom.length(); i++) {
						stack.pop();
					}
				}
			}

		}

		if (stack.size() > 0) {
			for (int i = 0; i < stack.size(); i++) {
				System.out.print(stack.get(i));
			}
		} else {
			System.out.println("FRULA");
		}

	}
}
