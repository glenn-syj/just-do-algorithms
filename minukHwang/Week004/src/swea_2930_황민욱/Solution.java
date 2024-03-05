package swea_2930_황민욱;

/*
 * [문제 풀이 과정]
 * 1. 힙 배열을 만들고, 힙의 최대 크기를 포인터 변수로 만든다.
 * 2. heapPush와 heapPop을 구현한다.
 * 	1) heapPush
 * 		- 추가된 숫자를 힙 가장 끝으로 넣기
 * 		- 가장 자식 노드에서 부모 노드와 비교하면서 크기가 자식이 크다면 스왑한다.
 * 		- 노드가 루트까지 갈 때까지 계속 스왑한다.
 * 	2) heapPop
 * 		- 루트 노드의 값을 빼서 리턴한다.
 * 		- 가장 마지막 노드를 루트 노드로 넣고 최상단부터 자식과 크기를 비교하여 자식이 크다면 스왑한다.
 * 		- 자식 노드 왼쪽 오른쪽을 비교하여 더 큰 쪽 자식으로 스왑하도록 한다.
 * 3. 연산 번호를 입력받고 해당 연산에 따라서 결과값을 출력한다. (StringBuilder 활용)
 */

import java.util.Scanner;

public class Solution {

	// heap배열 선언하기
	static int[] heap;
	static int heapSize;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			// StringBuilder로 출력할 문자열 append
			sb.append("#").append(t);

			// 연산 몇번 할 것인지.
			int n = sc.nextInt();

			// heap 초기화
			// 연산을 n번하면 n번 추가될 가능성이 있으니 n+1
			// heapSize도 매번 초기화 해주기.
			heap = new int[n+1];
			heapSize = 0;

			for (int i = 0; i < n; i++) {
				int cal = sc.nextInt();

				// 1이면 push
				if (cal == 1) {
					int num = sc.nextInt();
					heapPush(num);

					// 2면 pop
				} else if (cal == 2) {
					int returnNum = heapPop();
					sb.append(" ").append(returnNum);
				}
			}
			
			System.out.println(sb.toString());
			sb.setLength(0);
		}
		
	}

	public static void swap(int a, int b) {
		int temp = heap[a];
		heap[a] = heap[b];
		heap[b] = temp;
	}

	public static void heapPush(int num) {
		heap[++heapSize] = num;

		int child = heapSize;
		int parent = heapSize / 2;

		while (parent > 0 && heap[parent] < heap[child]) {
			swap(parent, child);

			child = parent;
			parent = child / 2;
		}
	}

	public static int heapPop() {
		if (heapSize == 0) {
			return -1;
		}

		int popNum = heap[1];
		heap[1] = heap[heapSize--];

		int parent = 1;
		int child = parent * 2;

		if (child + 1 <= heapSize && heap[child] < heap[child + 1]) {
			child++;
		}

		// 리프 노드일 경우 종료 && 자식이 작다면 종료
		while (child <= heapSize && heap[parent] < heap[child]) {
			
			swap(parent, child);

			parent = child;
			child = parent * 2;
			
			if (child + 1 <= heapSize && heap[child] < heap[child + 1]) {
				child++;
			}
		}

		return popNum;
	}
}
