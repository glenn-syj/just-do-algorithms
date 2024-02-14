package swea_1231_중위순회;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	static int count = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		for(int test = 1; test <= 10; test++) {
			
			int N = Integer.parseInt(sc.nextLine());
			
			Node[] nodeArr = new Node[N+1];
			
			for(int i = 0; i < N+1; i++) {
				nodeArr[i] = new Node();
			}
			
			for(int i = 1; i <= N; i++) {
				String str = sc.nextLine();
				
				st = new StringTokenizer(str);
				
				nodeArr[i].index = Integer.parseInt(st.nextToken());
				nodeArr[i].data = st.nextToken();
				
				if(st.hasMoreTokens()) {
					nodeArr[i].left = nodeArr[Integer.parseInt(st.nextToken())];
				}
				if(st.hasMoreTokens()) {
					nodeArr[i].right = nodeArr[Integer.parseInt(st.nextToken())];
				}
			}
			
			System.out.print("#" + test + " ");
			inOrder(nodeArr[1]);
			System.out.println();
		}
		
		
	}
	
	public static void inOrder(Node node) {
		
//		count++;
		
		if(node == null  ) {
			return;
		}
		
//		System.out.println(count);
		inOrder(node.left);
		
		System.out.print(node.data);
		
		inOrder(node.right);
	}
}	

class Node{
	int index;
	String data;
	Node left;
	Node right;
	
	public Node() {}
	public Node(String data) {
		this.data = data;
	}
}

