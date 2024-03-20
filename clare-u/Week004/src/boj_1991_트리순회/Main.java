package boj_1991_트리순회;

import java.util.Scanner;

// boj_1991_트리순회

// Try 상태로 코드제출 (구현을 못하는중)

//트리의 노드부터 만들어보자.
class Node {
	String data;
	String left;
	String right;

	public Node() {

	}

	public Node(String data, String left, String right) {
		Node node = new Node();
		// 데이터와 왼,오른 노드를 받아서 할당
		node.data = data;
		node.left = left;
		node.right = right;
	}

} // 이진트리의 노드는 데이터, 왼차일드노드, 오른차일드노드 3가지를 가짐

class Tree {

	// inorder는 왼쪽, 자신, 오른쪽 이런순서로 출력
	public static void inorder(Node node) {
		// 노드가 null이 아닐 때까지 재귀호출을 반복
		if (node != null) {
//			inorder(node.left); // 왼쪽재귀호출을 다돌고오면
//			System.out.println(node.data); // 나자신을 출력하고
//			inorder(node.right); // 오른쪽재귀호출을 돌린다
		}
	}

	// 나 자신을 먼저 출력하는 preorder
	public static void preorder(Node node) {
		// 노드가 null이 아닐 때까지 재귀호출을 반복
		if (node != null) {
			System.out.println(node.data); // 자기자신 먼저 출력하고
//			preorder(node.left); // 왼쪽재귀호출을 다돌고오면
//			preorder(node.right); // 오른쪽재귀호출을 돌린다
		}
	}

	// 나 자신을 가장 나중에 출력하는 postorder
	public static void postorder(Node node) {
		// 노드가 null이 아닐 때까지 재귀호출을 반복
		if (node != null) {
//			postorder(node.left); // 왼쪽재귀호출을 다돌고오면
//			postorder(node.right); // 오른쪽재귀호출을 돌고
			System.out.println(node.data); // 맨마지막에 자기자신 출력
		}
	}

}

public class Main {
//	배열을 입력받는다
//	사이즈는 7*3
//	0 1 2   3 4 5   6 7 8   9 10 11   12 13 14   15 16 17   18 19 20

	static int N;
	static String[] tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		sc.nextLine(); // 개행문자 제거

		tree = new String[N * 3];
		for (int i = 0; i < tree.length; i += 3) {
			if (i == 0) {
				Node root = new Node(tree[i], tree[i + 1], tree[i + 2]);
			} else {
				Node n2 = new Node(tree[i], tree[i + 1], tree[i + 2]);
			}

		}

//		preorder();

	} // main

}