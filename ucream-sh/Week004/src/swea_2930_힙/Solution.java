package swea_2930_힙;

import java.util.Scanner;

// swea_2930_힙;
// 이전에 푼 문제를 다시 풀어보았는데, 아래 push와 pop 조건문을 제대로 작성하지 못해서 이전 코드 일부 참고
// int child와 int parent 를 설정하고 해야 하는 건 암기가 필요할듯 함

public class Solution {
	// 최대 힙을 구현
	// 연산 1, 2 구현

	static int N; // 총 연산 수
	static int[] heap;

	static int heapsize;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			heapsize = 0; // 초기화 해야하는거 까먹지 말기
			N = sc.nextInt();

			heap = new int[N + 1];

			System.out.print("#" + t + " ");

			for (int i = 1; i <= N; i++) {
				if (sc.nextInt() == 1) {
					// 힙에 그 뒤 숫자 삽입
					push(sc.nextInt());
				} else {
					// 삭제 (힙이 공백이면 -1 출력)
					System.out.print(pop() + " ");
				}
			}
			System.out.println();
		}

	} // main

	public static void swap(int a, int b) {
		int temp = heap[a];
		heap[a] = heap[b];
		heap[b] = temp;
	}

	public static void push(int data) {
		// 데이터를 받아서 삽입
		heap[++heapsize] = data;

		int child = heapsize;
		int parent = heapsize / 2;

		// 부모와 비교 (자식/2)
		while (parent > 0 && heap[parent] < heap[child]) {
			// 부모가 0이아니고 자식보다 작으면 아닐때까지 반복문
			swap(parent, child);
			child = parent; // 자식인덱스를 부모인덱스로 바꿔주고
			parent = child / 2;
		}
	}

	public static int pop() {
		// 최대 힙의 루트 노드의 키값을 출력하고 해당 노드를 삭제
		if (heapsize == 0) {
			return -1;
		}

		int data = heap[1];
		heap[1] = heap[heapsize--];

		int parent = 1;
		int child = parent * 2;

		// 리프노드로 가면 자식이 없을수도 있으니까 ch가 heapSize 이하인지 확인
		if (child + 1 <= heapsize && heap[child] < heap[child + 1]) {
			child++;
		}

		while (child <= heapsize && heap[parent] < heap[child]) {
			// 부모가 0이아니고 자식보다 작으면 아닐때까지 반복문
			swap(parent, child);
			parent = child;
			child = parent * 2;

			// if문 한번 더 (처음에 검증을 하고 들어오니까, 바뀐 애들을 다시 검증하는 방식)
			if (child + 1 <= heapsize && heap[child] < heap[child + 1]) {
				child++;
			}
		}
		return data;

	}

}