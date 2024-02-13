package swea_1234_박건택;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		for (int test = 1; test <= 10; test++) {
			String input = sc.nextLine();	//한줄 입력
			st = new StringTokenizer(input);
			
			int N = Integer.parseInt(st.nextToken());	
			

			Stack myStack = new Stack(N);	//입력받은 N의 크기를 갖는 스택(빼열) 생성
			
			char[] numChar = st.nextToken().toCharArray(); //입력받은 숫자문자열을 char array로
			
//			System.out.println(Arrays.toString(numChar));
			
			for(int i =0; i<N; i++) {
				if(myStack.peek() == numChar[i]) {
					myStack.pop();
				}else {
					myStack.push(numChar[i]);
				}
//				System.out.println(Arrays.toString(myStack.stack));
			}
			
			
			//출력
			System.out.print("#"+test + " ");
			for(int i = 0; i <= myStack.top; i++) {
				System.out.print(myStack.stack[i]);
			}
			System.out.println();
			
		}
	}
}


class Stack {
	char[] stack;
	int top = -1;

	public Stack() {
	}
	//멤버 변수로 빈 스택 선언 한 후 생성자를 통해 사이즈 전달받고 객체 생성하기
	public Stack(int l) {
		stack = new char[l];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == stack.length - 1;
	}

	public void push(char data) {
		if (isFull())
			return;

		stack[++top] = data;
		return;
	}
	
	
	public void pop() {
		if (isEmpty())
			return ;

		stack[top] = 0;

		top--;
		return ;
	}

	public char peek() {
		if (isEmpty())
			return 0;
		return stack[top];
	}
}