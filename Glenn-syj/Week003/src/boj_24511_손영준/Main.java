package boj_24511_큐스택;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/* B24511. 큐스택
 * 
 * 1. 조건
 * 	1-1. 1번, 2번, ..., N번의 자료구조 (queue 혹은 stack)이 나열
 * 	1-2. x0를 입력받음 
 * 		-> x0를 1번 자료구조에 삽입한 뒤, 1번 자료구조에서 원소를 pop
 * 		-> pop된 원소를 x1
 * 		x1을 입력받음
 * 		-> x1을 2번 자료구조에 삽입한 뒤, 2번 자료구조에서 원소를 pop ...
 * 	1-3. 길이 M의 수열 C를 가져와서 앞에서부터 차례대로 큐스택에 삽입	
 * 	1-4. 자료구조가 큐라면 0, 스택이라면 1
 * 2. 풀이
 * 	2-1. 사실 상
 * 		(1) queue가 있다면, 마지막 queue의 원소가 return
 * 		(2) queue가 없다면, 입력값 그대로 return
 * 	2-2. 전체 순회를 할 필요가 없음
 * 		-> 간단한 LinkedList를 이용해보자...
 * 	2-3. 효율
 * 		-> 다른 계산이 필요없으니까 바로 char로 이용하면 될듯?
 * 		=> 아니 다시 보니까 입력값이 10억까지네... 바보처럼 생각했다...
 */

public class Main {
	
	static ArrayList<Integer> queuePositions;
	static Scanner sc;
	static StringTokenizer st;
	static StringBuilder sb;
	static CustomLinkedList linkedList;
	static String input;
	
	static int N;
	static int M;
	
	
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/boj_24511_큐스택/input.txt");
//		sc = new Scanner(file);
		
		sc = new Scanner(System.in);
		
		sb = new StringBuilder();
		
		N = Integer.parseInt(sc.nextLine());
		queuePositions = new ArrayList<>();
		
		// 큐 포지션만 입력 받아 ArrayList에 넣기
		st = new StringTokenizer(sc.nextLine());
		
		for (int i=0; i<N; i++) {
			if (st.nextToken().equals("0")) {
				queuePositions.add(i);
			}
		}
		
		// 큐가 없다면 입력값 그대로 뱉으면 됨
		if (queuePositions.size() == 0) {
			sc.nextLine();
			sc.nextLine();
			System.out.println(sc.nextLine().trim());
			return;
		}
		
		// linkedList의 size에 맞게 잘 설정해주자...
		linkedList = new CustomLinkedList(queuePositions.size());
		
		
		st = new StringTokenizer(sc.nextLine());
		int queuePtr = 0;
		
		// 큐 포지션과 index가 일치하면 추가
		// add는 full되기 전까지는 rear을 쌓으므로 같은 add 이용 가능
		for (int i=0; i<=queuePositions.get(queuePositions.size()-1); i++) {
			input = st.nextToken();
			if (i == queuePositions.get(queuePtr)) {
				linkedList.add(input);
				queuePtr++;
			}
		}
		
		M = Integer.parseInt(sc.nextLine());
		st = new StringTokenizer(sc.nextLine());
		
		// add는 full이 된 이후에는 front쪽을 추가하고 rear을 밀어냄
		while (st.hasMoreTokens()) {
			
			sb.append(linkedList.add(st.nextToken())).append(' ');
		}
		
		// 공백문자 지워주기

		System.out.println(sb.toString());
		sb.setLength(0);
		sc.close();
		
		
	}
	
}

// 선입선출, 좌->우
class CustomLinkedList {
	
	private final int DEFAULT_CAPACITY = 100_000;
	private int size;
	private int capacity;
	private Node front;
	private Node rear;
	
	class Node {
		
		String data;
		Node next;
		Node before;
		
		public Node() {
			this.data = null;
			this.next = null;
			this.before = null;
		}
		
		public Node(String data) {
			this.data = data;
			this.next = null;
			this.before = null;
		}
		
	}
	
	public CustomLinkedList() {
		this.capacity = DEFAULT_CAPACITY;
		this.size = 0;
		this.front = null;
		this.rear = null;
	}
	
	public CustomLinkedList(int intialCapacity) {
		this.capacity = intialCapacity;
		this.size = 0;
		this.front = null;
		this.rear = null;
	}
	
	public boolean isFull() {
		return size == capacity;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	public String add(String data) {
		if (isFull()) {
			
			Node newNode = new Node(data);
			front.before = newNode;
			newNode.next = front;
			front = newNode;
			
			String removedElement = rear.data;
			rear = rear.before;
			rear.next = null;
			
			return removedElement;
			
		} else if (isEmpty()) {
			front = new Node(data);
			rear = front;
			size++;
			return null;
		}
		
		Node newNode = new Node(data);
		rear.next = newNode;
		newNode.before = rear;
		rear = newNode;
		size++;
		return null;
	}
	
	public void dump() {
		
		Node tmp = front;
		
		while(tmp != null) {
			System.out.print(tmp.data);
			System.out.print("->");
			tmp = tmp.next;
		}
		
		System.out.print("\n");
	}
	
}