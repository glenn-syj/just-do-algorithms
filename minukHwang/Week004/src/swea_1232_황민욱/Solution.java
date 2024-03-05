package swea_1232_황민욱;

/*
 * [문제 풀이 과정]
 * 1. 노드 만들기
 * 
 * 2. 노드 배열 만들어서 이진 트리 구현하기
 * 	- 입력 값을 받으면 노드의 left, right 설정해주어 연결하기
 * 	- 노드의 idx가 배열의 인덱스랑 같다.
 * 
 * 3. 재귀로 연산 구현하기
 * 	- 기저 조건으로 만약 노드의 left right 둘 다 없다면, 노드의 데이터(숫자) 반환
 * 	- 최하단 자식 노드를 탐색할 수 있도록 left 재귀, right 재귀
 * 	- 자식 노드를 확인하면 left right 부모(본인)으로 연산하기
 * 
 */

import java.util.Scanner;

class Node {
	int idx;
	String data;
	Node left;
	Node right;

	Node() {

	}

	Node(String data) {
		this.data = data;
	}

	// 확인용 toString
	@Override
	public String toString() {
		return "[#" + idx + ", " + data + ", " + left + ", " + right + "]";
	}
}

public class Solution {
	static Node[] nodes;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int n = sc.nextInt();
			sc.nextLine();

			nodes = new Node[n + 1];

			for (int i = 1; i <= n; i++) {
				nodes[i] = new Node();
			}

			for (int i = 1; i <= n; i++) {
				String[] str = sc.nextLine().split(" ");
				
				nodes[i].idx = Integer.parseInt(str[0]);
				nodes[i].data = str[1];

				if (str.length > 2) {
					nodes[i].left = nodes[Integer.parseInt(str[2])];
					nodes[i].right = nodes[Integer.parseInt(str[3])];
				}
			}
			
			Double answer = eval(nodes[1]);
			
			System.out.printf("#%d %.0f\n", t, answer);

		}
	}
	
	// LVR
	public static double eval(Node node) {
		if(node.left == null && node.right == null) {
			return Double.parseDouble(node.data);
		}
		
		double left = eval(node.left);
		double right = eval(node.right);
		
		if(node.data.equals("+")) {
			return left + right;
		} else if(node.data.equals("-")) {
			return left - right;
		} else if(node.data.equals("*")) {
			return left * right;
		} else {
			return left / right;
		}
	}
}
