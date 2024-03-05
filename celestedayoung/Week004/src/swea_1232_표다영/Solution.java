package swea_1232_사칙연산;

import java.util.*;

class Node {
	
	int vertex;
	int data;
	String operator;
	Node left;
	Node right;

	Node() {}
}

public class Solution {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {

			int vertexNum = sc.nextInt();
			sc.nextLine();

			Node[] nodes = new Node[vertexNum + 1];

			for (int i = 1; i < nodes.length ; i++) {
				nodes[i] = new Node();
			}

			for (int i = 1; i < nodes.length; i++) {

				String[] input = sc.nextLine().split(" ");

				nodes[i].vertex = Integer.parseInt(input[0]);

				if (input[1].equals("+") || input[1].equals("-") || input[1].equals("*") || input[1].equals("/")) {

					nodes[i].operator = input[1];

					for (int j = 2; j < input.length; j++) {
						if (nodes[i].left == null) {
							nodes[i].left = nodes[Integer.parseInt(input[j])];
						} else {
							nodes[i].right = nodes[Integer.parseInt(input[j])];
						}
					}

				} else {
					nodes[i].data = Integer.parseInt(input[1]);
				}
			}
			
			double result = calculation(nodes[1]);
			System.out.printf("#%d %.0f%n", t, result);

		}
	}

	public static double calculation(Node node) {
		
		if (node.left == null || node.right == null) {
			return (double)node.data;
		}
		
		double left = calculation(node.left);
		double right = calculation(node.right);
		
		if(node.operator.equals("+")) {
			return left + right;
		} else if(node.operator.equals("-")) {
			return left - right;
		} else if(node.operator.equals("*")) {
			return left * right;
		} else {
			return left / right;
		}
		
	}

}


