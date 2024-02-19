package swea_1234_비밀번호;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/* S1234. 비밀번호
 * 
 * 5분 / 15분 (스택 구현 10분)
 * 
 * 1. 조건
 * 	1-1. 0~9로 이루어진 번호 문자열에서 같은 번호로 붙어있는 쌍들 소거
 * 		-> 남은 번호를 비밀번호로 만듦
 * 	1-2. 문자의 총 수가 주어지며, 이후 공백을 둔 다음 번호 문자열이 공백없이 주어짐
 * 		-> 수의 크기를 갖는 char 스택, 번호 문자열 하나씩 입력받기
 * 2. 풀이
 * 	2-1. Character 스택을 구현해서, 입력값이 peek()값과 같으면 pop() 시켜버리기
 * 		-> 음.. 근데 그냥 main내에서 sb에 집어넣는 게 나을듯
 * 	2-2. 스택 내부에 print 관련 method 구현해서 바로 이용
 * 	2-3. 근데 스택을 썼는데... 만약 문자열 길이가 1_000_000이라면?
 * 		-> 나중에 고민해보자
 */

public class Solution {
	
	static int T = 10;
	static int N;
	static String line;
	static CharStack stack;
	static StringTokenizer st;
	static Scanner sc;
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("./src/swea_1234_비밀번호/input.txt");
		sc = new Scanner(file);
		
//		sc = new Scanner(System.in);
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(sc.nextLine());
			N = Integer.parseInt(st.nextToken());
			line = st.nextToken();
			stack = new CharStack(N);
			
			System.out.printf("#%d ", tc);
			stack.push(line.charAt(0));
			for (int i=1; i<N; i++) {
				if (stack.peek() == line.charAt(i)) {
					stack.pop();
				} else {
					stack.push(line.charAt(i));
				}
			}
			
			stack.print();
			
		}
		
		
		
		
	}
}

class CharStack {
	
	private final int DEFAULT_CAPACITY = 100;
	private char[] elements;
	private int top;
	private StringBuilder sb;
	
	CharStack () {
		this.elements = new char[DEFAULT_CAPACITY];
		this.top = -1;
		this.sb = new StringBuilder();
	}
	
	CharStack(int initialCapacity) {
		this.elements = new char[initialCapacity];
		this.top = -1;
		this.sb = new StringBuilder();
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public boolean isFull() {
		return top == elements.length-1;
	}
	
	public void push(char theElement) {
		if (isFull()) {
//			혹시 모르니 주석 처리
//			System.out.println("Stack is full");
			return;
		}
		
		elements[++top] = theElement;
	}
	
	public char pop() {
		if (isEmpty()) {
//			혹시 모르니 주석 처리			
//			System.out.println("Stack is empty");
			return ' ';
		}
		
		char removedElement = elements[top];
		elements[top--] = ' ';
		return removedElement;
	}
	
	public char peek() {
		if (isEmpty()) {
//			혹시 모르니 주석 처리			
//			System.out.println("Stack is empty");
			return ' ';
		}
		return elements[top];
	}
	
	public void print() {
		
		if (isEmpty()) {
//			혹시 모르니 주석 처리			
//			System.out.println("Stack is empty");
			return;
		}
		
		for (int i=0; i<=top; i++) {
			sb.append(elements[i]);
		}
		
		System.out.println(sb.toString());
		sb.setLength(0);
	}
	
	
}