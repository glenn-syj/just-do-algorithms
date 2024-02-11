package Week002_백준_12789_황민욱;

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
