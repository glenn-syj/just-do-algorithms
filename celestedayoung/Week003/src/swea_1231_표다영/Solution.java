package swea_1231_중위순회;
/*
 * 	[Logic]
 *	- Node를 사용해 Tree를 구현하고 중위순회를 수행하자.
 *	- Node Class에 `vertex`, `letter`, `left`, `right` 정보를 넣고, 각 노드를 하나의 배열로 관리한다.
 *
 *	A. Tree 구현
 *		0. vertex 개수+1개만큼의 Node 배열을 만들고 해당 배열에 초기화 된 노드들을 넣는다.
 *   		- root가 1이므로 vertext 개수에 1을 더해야한다.
 *		1. 노드에 들어갈 input을 배열로 받는다.
 *   		- 배열[0] : 해당 노드의 번호
 *   		- 배열[1] : 해당 노드에 들어갈 letter
 *   		- 배열[2] : 해당 노드의 left child 번호
 *   		- 배열[3] : 해당 노드의 right child 번호
 *		2. 배열의 2, 3번째 요소는 있을 수도, 없을 수도 있다. 따라서 있다면 2번째부터 배열의 길이-1까지 반복문을 돌며 child를 만든다.
 *
 *	B. 중위순회 구현
 *		0. 기저 조건 설정
 *		    - 노드의 번호가 노드의 길이보다 크거나 노드가 비어있다면 return
 *		1. 중위순회는 left child → vertex → right child 순으로 방문하므로 해당 순서에 따라 순회하며 vertex에서 출력한다.
*/
import java.util.Scanner;

class Node {
	int vertex;
	String letter;
	Node left;
	Node right;
	
	Node(){}
	
	Node(int vertex, String letter) {
		this.vertex = vertex;
		this.letter = letter;
	}

	@Override
	public String toString() {
		return "Node [num=" + vertex + ", letter=" + letter + ", left=" + left + ", right=" + right + "]";
	}
}

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for (int t = 1; t <= 10; t++) {
			
			int vertexNum = sc.nextInt();
			sc.nextLine();
			
			Node[] nodes = new Node[vertexNum + 1];
			for (int i = 1; i < nodes.length; i++) {
				nodes[i] = new Node();
			}
			
			for (int i = 1; i < nodes.length; i++) {
				
				String[] inputArr = sc.nextLine().split(" ");

				nodes[i].vertex = Integer.parseInt(inputArr[0]);
				nodes[i].letter = inputArr[1];
				
				for (int j = 2; j < inputArr.length; j++) {
					if (nodes[i].left == null) {
						nodes[i].left = nodes[j];
					}
					if (nodes[i].right == null) {
						nodes[i].right = nodes[inputArr.length - 1];
					}
				}
				
			}
			System.out.printf("#%d ", t);
			inorder(1, nodes);
			System.out.println();
		}		
		
	}
	
	static void inorder(int i, Node[] nodes) {
		// 기저 조건
		if (nodes.length <= i || nodes[i].equals(0)) {
			return;
		}
		
		// 왼쪽 자식 방문
		inorder(i * 2, nodes);
		
		// 부모 노드 방문
		System.out.print(nodes[i].letter);
		
		
		// 오른쪽 자식 방문
		inorder(i * 2 + 1, nodes);
	}
}
