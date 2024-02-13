package boj_12789_도키도키간식드리미;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* B12789. 도키도키간식드리미
 * 
 * 1. 조건
 * 	1-1. 맨 앞의 사람만 이동이 가능하고, 번호표 순서대로만 통과가 가능하다
 * 	1-2. 무사히 간식을 받을 수 있으면 Nice, 그렇지 않다면 Sad 출력
 * 	1-3. 1 <= N <= 1000
 * 	1-4. 원래 줄 에서 옆 공간으로 들어올 는 있지만, 반대는 불가능하다
 * 		-> 옆으로 빠지는 스택 내 peek()보다 더 큰 값이 들어오면 안됨
 * 2. 풀이
 * 	2-0. test case
 * 		(1) 5 4 1 3 2 -> Nice
 * 		(2) 5 3 4 1 2 -> Sad
 * 	2-1. primitive int 배열 이용과 Integer Stack 이용
 * 		-> Stack내에 peek() 값보다 더 큰 수가 들어오면 "sad" 출력
 * 		-> 배열 내는 삭제할 필요 없이, pointer를 통해서 length-1까지 체크하면 끝
 */


public class Main {
	
	static ArrayStack<Integer> intStack;
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] line = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			line[i] = Integer.parseInt(st.nextToken());
		}
		
		int ptr = 1;
		int count = 1;
		intStack = new ArrayStack<>();
		boolean isPossible = true;
		
		while (ptr < line.length) {

			if (line[ptr] == count) {
				ptr++;
				count++;
				// 어차피 return null이므로 비어있을 때 고려 필요 X;
			} else if (!intStack.isEmpty() && intStack.peek() == count) {
				intStack.pop();
				count++;
			} else if (!intStack.isEmpty() && intStack.peek() < line[ptr]) {
				isPossible = false;
				System.out.println("Sad");
				break;
			} else {
				intStack.push(line[ptr]);
				ptr++;
			}
			
			
		}
		
		if (isPossible) {
			System.out.println("Nice");
		}
		
	}
}

class ArrayStack<E> {
	
	// 문제 내 주어지는 N의 최댓값
	private final int DEFAULT_CAPACITY = 1_000;
	private E[] elements;
	private int top;
	
	ArrayStack() {
		this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		this.top = -1;
	}
	
	public int size() {
		return elements.length;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public boolean isFull() {
		return top == elements.length-1;
	}
	
	public void push(E theElement) {
		if (isFull()) {
			System.out.println("Stack is full");
			return;
		}
		elements[++top] = theElement;
	}
	
	public E pop() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		E removedElement = elements[top];
		elements[top--] = null;
		return removedElement;
	}
	
	public E peek() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		return elements[top];
	}
	
}