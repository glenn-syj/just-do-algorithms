package swea_2930_박건택;

import java.util.Scanner;

public class Solution {

	static int[] tree;
	static int size;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int tc = sc.nextInt();
		for (int test = 1; test <= tc; test++) {

			sb.append("#").append(test);

			// 매 테스트 케이스마다 tree 배열, size 초기화
			tree = new int[100000];
			size = 0;

			int times = sc.nextInt();

			for (int i = 0; i < times; i++) {

				int oper = sc.nextInt();

				if (oper == 1) {
					int num = sc.nextInt();

					heapPush(num);
				} else if (oper == 2) {

					sb.append(" ").append(heapPop());
				}
			}

			System.out.println(sb.toString());

			sb.setLength(0);
		}

	}

	// 최대 힙 push
	// 1.완전 이진 트리 맞게 leaf node 생성
	// 2.새로운 데이터 거기에 입력
	// 3. 부모 노드와 비교하며 swap -> 루트 까지 or 부모 노드가 더 클 때까지
	static void heapPush(int n) {

		tree[++size] = n;

		int child = size;
		int parent = size / 2;

		//
		while (parent > 0 && tree[parent] < tree[child]) {

			int temp = tree[parent];
			tree[parent] = tree[child];
			tree[child] = temp;

			child = parent;
			parent = child / 2;
		}

	}

	// 최대 힙 pop
	// 1. 루트 삭제
	// 2. 마지막 노드를 루트로
	// 3. 자식 노드와 비교하며 자식 노드중 더 큰 값과 swap -> leaf 노드일 때 까지 or 자식 노드가 더 작을 떄 까지
	static int heapPop() {

		if (size == 0) {
			return -1;
		}

		int popItem = tree[1];
		tree[1] = tree[size--];

		int parent = 1;
		int child = parent * 2;
		
		
		if (tree[child] < tree[child + 1]) {
			child = child + 1;
		}
		while ((child <= size && tree[child] > tree[parent])) {

			int temp = tree[child];
			tree[child] = tree[parent];
			tree[parent] = temp;

			parent = child;
			child = parent * 2;
			
			if (tree[child] < tree[child + 1]) {
				child = child + 1;
			}
		}

		return popItem;
	}
}