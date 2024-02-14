package swea_1231_중위순회;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/* S1231. 중위순회
 * 
 * 5분
 * 
 * 1. 조건
 * 	1-1. in-order형식으로 읽기
 * 	1-2. 위 트리를 in-order 형식으로 순회했을 때 나오는 단어
 * 		-> char data와, Node left, Node right를 갖는 노드의 배열 이용
 * 2. 풀이
 * 	2-1. Node[] 이용 -> 해당 index에 대해서 입력받으면 data값만 바꿔주자!
 * 	2-2. root찾기도 해보자! 현재: 루트를 찾아낸 버전
 */

public class Solution {
	
	static Scanner sc;
	static int N;
	static Node[] nodes;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] countingArr;
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("./src/swea_1231_중위순회/input.txt");
		sc = new Scanner(file);
		
//		sc = new Scanner(System.in);
		
		for (int tc=1; tc<=10; tc++) {
			
			sb.append('#').append(tc).append(' ');
			N = Integer.parseInt(sc.nextLine());
			nodes = new Node[N+1];
			countingArr = new int[N+1];
			
			for (int i=1; i<=N; i++) {
				st = new StringTokenizer(sc.nextLine());
				st.nextToken();
				nodes[i] = new Node(st.nextToken().charAt(0));
				int left, right;
				switch (st.countTokens()) {
				case 2:
					left = Integer.parseInt(st.nextToken());
					right = Integer.parseInt(st.nextToken());
					nodes[i].left = nodes[left];
					nodes[i].right = nodes[right];
					countingArr[left]++;
					countingArr[right]++;
					break;
				case 1:
					left = Integer.parseInt(st.nextToken());
					nodes[i].left = nodes[left];
					countingArr[left]++;
					break;
				default:
					break;
				}
			}
			
			int root = 0;
			for (int i=1; i<=N; i++) {
				if (countingArr[i] == 0) {
					root = i;
				}
			}
			
			inorder(root);
			System.out.println(sb.toString());
			sb.setLength(0);

			
		}
			
		
	}
	
	public static void inorder(int i) {
		
		if (i > nodes.length-1 || i <= 0) {
			return;
		}

		inorder(2*i);
		sb.append(nodes[i].data);
		inorder(2*i+1);
		
	}
	
	
}

class Node {
	
	char data;
	Node left;
	Node right;
	
	Node() {
		
	}
	
	Node(char data) {
		this.data = data;
	}
	
}