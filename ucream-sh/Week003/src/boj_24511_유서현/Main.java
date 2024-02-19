package boj_24511_유서현;

import java.util.Scanner;

public class Main {
	// boj_24511_queuestack
	// 구현 실패인채로 끝

	static int N; // 스태틱 해두는거랑 아래에서 쓰는거랑의 차이를 잘 모르겠지만..
	static int[] A;
	static int M;

	static int idx = 0;

	static SinglyLinkedList B;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 입력받기

		N = sc.nextInt(); // int N : 큐스택 자료구조 개수

		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		} // int[] A : 반복문으로 입력받고 사이즈는 N, 큐는 0 스택은 1

		B = new SinglyLinkedList();

		for (int i = 0; i < N; i++) {
			B.add(i, sc.nextInt());
		} // B : 반복문으로 입력받고 사이즈는 N, 큐스택

//		B.printList(); // 1 -> 2 -> 3 -> 4 -> 
//		System.out.println(B.listIdx(0)); // 1
//		System.out.println(B.listIdx(1)); // 2
//		System.out.println(B.listIdx(2)); // 3
//		System.out.println(B.listIdx(3)); // 4

		M = sc.nextInt(); // int M : 삽입할 애들 개수

		SinglyLinkedList C = new SinglyLinkedList();
		for (int i = 0; i < M; i++) {
			C.add(i, sc.nextInt());
		}
		// C : 반복문으로 입력받고 사이즈는 M, B에다가 삽입할 거임
		// 삽입해도 B는 N사이즈 그대로임 주의

		for (int i = 0; i < M; i++) {
//			C.add(i, sc.nextInt());
		}

		// add메소드를 수정하거나, 암튼 스택인지 큐인지에 따라 다를듯

		// 0이면 새거남기고 본인 팝
		// 1이면 팝된애 끌고가는식으로 그냥 지나치게.

	} // main

}

// Node랑 SinglyLinkedList는 실습거를 복습하면서 복붙해옴
class Node {
	int data; // 값
	Node link; // 노드의 주소값을 저장하는 느낌

	Node() {
	}

	Node(int data) {
		this.data = data;
	}
} // class Node

class SinglyLinkedList {
	// 동적으로 써먹을 수 있는 싱글리 링크드리스트
	Node head; // '헤드'라는 노드가 맨앞을 쳐다본다.
	int size;

	SinglyLinkedList() {
		head = new Node(); // 맨 처음 노드의 주소를 가리킬 '헤드'
	} // 이땐 null을 가리킴

	// 삽입
	void add(int idx, int data) {

		Node curr = head; // 헤드부터 탐색 시작
		for (int i = 0; i < idx; i++) { // idx까지
			curr = curr.link;
		}

		Node newNode = new Node(data);

		newNode.link = curr.link;
		curr.link = newNode;
		size++;
	}

	// 뒤틀린 popcorn
	void pop() {

	} // pop

	// 조회
	// 모든 데이터 조회
	void printList() {
		Node curr = head.link; // 헤드가 가리키는애부터 탐색 시작
		while (curr != null) {
			System.out.print(curr.data + " -> ");
			curr = curr.link;
		}
		System.out.println();
	} // printList()

	// 인덱스조회-> 리턴
	int listIdx(int idx) {
		Node curr = head.link; // 헤드가 가리키는애부터 탐색 시작

		int data;

		for (int i = 0; i < 10; i++) {
			if (idx == i) {
				data = curr.data;
				return data;
			}
			curr = curr.link;
		}

		return Integer.MIN_VALUE;
	} // printList()

} // class SinglyLinkedList