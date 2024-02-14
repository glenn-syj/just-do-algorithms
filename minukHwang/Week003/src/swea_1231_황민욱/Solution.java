package swea_1231_황민욱;

/*
 * [문제 풀이 과정]
 * 1. node 클래스를 만들고 배열을 만들어서 트리를 구현하자.
 * 	- node 인스턴스는 data, idx, left(자식), right(자식)을 멤버 변수로 가진다.
 *  - node 배열을 만들고 해당 배열은 index가 node의 idx와 같다.
 *  
 * 2. 입력을 받아서 node의 정보를 삽입한다.
 * 	- node 배열을 node 인스턴스로 초기화
 *  - node 배열 각각의 요소에 data 할당
 *  - node 배열 각각의 요소에 left, right 할당 -> node간 연결
 * 
 * 3. 중위순회를 재귀로 구현한다.
 * 	- 중위순회는 LVR
 * 	- Left 노드를 재귀로 넘겨주어서 탐색
 * 	- 만약 더 이상 자식이 없다면 return
 * 	- 더 이상 자식이 없다면 본인 data 출력하고 오른쪽 순회.
 *  - 반복
 */

import java.util.Scanner;

class Node {
	String data;
	int idx;
	Node left;
	Node right;
	
	Node(){
		
	}
}

public class Solution {
	static Node[] nodes = new Node[100+1];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for(int t = 1; t <= 10; t++) {			
			int N = sc.nextInt();
			sc.nextLine();
			
			for(int i=1; i < N+1; i++) {
				nodes[i] = new Node();
			}
			
			for (int i = 1; i < N+1; i++) {
				String[] str = sc.nextLine().split(" ");
				
				nodes[i].idx = Integer.parseInt(str[0]);
				nodes[i].data = str[1];
				
				for(int j = 2; j < str.length; j++) {
					if(nodes[i].left == null) {
						nodes[i].left = nodes[Integer.parseInt(str[j])];
					} else {
						nodes[i].right = nodes[Integer.parseInt(str[j])];
					}
				}
			}
			
			System.out.printf("#%d ", t);
			inorder(nodes[1]);
			System.out.println();
			
		}
	}
	
	//LVR
	public static void inorder(Node node) {
		if(node == null) {
			return;
		}
		
		inorder(node.left);
		System.out.print(node.data);
		inorder(node.right);
	}
}
