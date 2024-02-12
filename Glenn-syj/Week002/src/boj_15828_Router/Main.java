package boj_15828_Router;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* B15828. Router
 * 
 * 5분 / 20분 (큐 구현 15분..)
 * 
 * 1. 조건
 * 	1-1. (문제는 길지만) 사실은 그냥 큐를 잘 이용하는 문제
 * 	1-2. 버퍼의 크기를 나타내는 자연수 N = 큐의 크기
 * 		-> 가득차면 못 들어오게 처리
 * 	1-3. 0을 입력으로 받을 시, dequeue
 * 	1-4. 1 <= N <= 100_000, 1 <= 정보의 수 <= 200_000
 * 2. 풀이
 * 	2-1. 큐를 구현해서 풀자!
 * 	2-2. sc.nextInt()가 -1인 경우에 종료
 * 	2-3. 종료 후에는 남아있는 element들을 dequeue하며 출력
 * 		-> 비어있을 경우에는 empty 
 * 3. 정답 이후
 * 	3-1. Array를 이용한 원형큐로 다시 풀어봐도 될듯!
 */

public class Main {
	
	static Scanner sc;
	static StringBuilder sb;
	static int N;
	static int input;
	static LinkedQueue queue;
	
	
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/boj_15828_Router/input.txt");
//		sc = new Scanner(file);
		
		sc = new Scanner(System.in);
		
		N = sc.nextInt();
		queue = new LinkedQueue(N);
		sb = new StringBuilder();
		
		
		while ((input = sc.nextInt()) != -1) {
//			System.out.print(input);
			if (input != 0) {
				queue.enqueue(input);
			} else {
				queue.dequeue();
			}
		}
		
		// 큐가 비어있는 경우
		if (queue.isEmpty()) {
			sb.append("empty");
			System.out.println(sb.toString());
			return;
		}
		
		sb.append(queue.dequeue());
		while (!queue.isEmpty()) {
			sb.append(' ').append(queue.dequeue());
		}
		
		System.out.println(sb.toString());
		
	}
}

class LinkedQueue {
	
	private int data;
	private Node front;
	private Node rear;
	private int capacity;
	private int size;

	class Node {
		int data;
		Node next;
		
		Node() { }
		
		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	LinkedQueue() {
		this.capacity = 100_000;
	}
	
	LinkedQueue(int initialCapacity) {
		this.capacity = initialCapacity;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isFull() {
		return size == capacity;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enqueue(int data) {
		if (isFull()) {
			// 아무것도 출력해서는 안됨
			return;
		} else if (isEmpty()) {
			front = new Node(data);
			rear = front;
			size++;
			return;
		} 
		size++;
		rear.next = new Node(data);
		rear = rear.next;
	}
	
	public int dequeue() {
		if (isEmpty()) {
			// 아무것도 출력해서는 안됨
			return -1;
		}
		
		int removedElement = front.data;
		front = front.next;
		size--;
		
		return removedElement;
	}
	
	public int peek() {
		if (isEmpty()) {
			// 아무것도 출력해서는 안됨
			return -1;
		}
		
		return front.data;
	}
	
}