package Week002_백준_12789_황민욱;

/*
 * [문제 풀이 과정]
 * 1. 스택을 구현한다.
 * 2. 대기열에 있는 확인해야할 사람을 기준으로 해당 배열을 모두 탐색할 때까지 다음의 과정을 반복한다.
 * 	- 만약 순번대로 입장이 가능하다면, 다음 순번의 번호를 증가 시키고, 대기열의 확인해야할 사람의 인덱스 또한 증가시킨다.
 *  - 만약 순번대로 입장이 불가하다면, 스택에 다음 사람의 순번이 있는지 확인하고 없다면 스택에 대기열 사람을 추가한다. (대기열 사람의 인덱스 증가)
 * 3. 위와 같은 과정이 끝나고, 스택에 혹시라도 사람이 남아있다면 순번대로 들어올 수 있는지 다시 한번 확인한다.
 * 4. 만약 스택이 다 비어진다면 "Nice" 출력 아니라면 "Sad"
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		stack = new int[n - 1];
		String[] arr = br.readLine().split(" ");

		int start = 0;
		int next = 1;

		while (start < n) {
			int student = Integer.parseInt(arr[start]);

			if (student == next) {
				start++;
				next++;
			} else {
				if (!isEmpty() && peek() == next) {
					pop();
					next++;
				} else {
					push(student);
					start++;
				}
			}
		}

		int left = n - next + 1;

		for (int i = 0; i < left; i++) {
			if (peek() == next) {
				pop();
				next++;
			}
		}

		boolean isAvailable = isEmpty() ? true : false;

		bw.write(isAvailable ? "Nice" : "Sad");
		bw.flush();

	}

	// 스택
	static int[] stack;
	static int top = -1;

	public static void push(int a) {
		if (isFull()) {
			return;
		}

		stack[++top] = a;
	}

	public static int pop() {
		if (isEmpty()) {
			return 0;
		}

		int result = stack[top];
		stack[top--] = 0;
		return result;
	}

	public static int peek() {
		if (isEmpty()) {
			return -1;
		}

		return stack[top];
	}

	public static boolean isFull() {
		return top == stack.length - 1;
	}

	public static boolean isEmpty() {
		return top == -1;
	}
}
