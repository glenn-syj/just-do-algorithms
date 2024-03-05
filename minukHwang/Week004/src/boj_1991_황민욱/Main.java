package boj_1991_황민욱;

import java.util.Arrays;
import java.util.Scanner;

class Node {
	char data;
	Node left;
	Node right;

	Node() {

	}

	Node(char data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "[#" + data + left + right + "]";
	}

}

public class Main {
	static Node[] nodes;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.nextLine();

		nodes = new Node[n];

		for (int i = 0; i < n; i++) {
			nodes[i] = new Node();
		}

		for (int i = 0; i < n; i++) {
			String str = sc.nextLine();
			nodes[str.charAt(0) - 'A'].data = str.charAt(0);

			nodes[str.charAt(0) - 'A'].left = str.charAt(2) == '.' ? null : nodes[str.charAt(2) - 'A'];
			nodes[str.charAt(0) - 'A'].right = str.charAt(4) == '.' ? null : nodes[str.charAt(4) - 'A'];
		}

		preorder(nodes[0]);
		sb.append("\n");
		inorder(nodes[0]);
		sb.append("\n");
		postorder(nodes[0]);

		System.out.println(sb);

	}

	// VLR
	public static void preorder(Node node) {
		if (node == null) {
			return;
		}
		
		sb.append(node.data);
		preorder(node.left);
		preorder(node.right);
	}

	// LVR
	public static void inorder(Node node) {
		if (node == null) {
			return;
		}
		

		inorder(node.left);
		sb.append(node.data);
		inorder(node.right);
	}

	// LRV
	public static void postorder(Node node) {
		if (node == null) {
			return;
		}

		postorder(node.left);
		postorder(node.right);
		sb.append(node.data);
	}
}
