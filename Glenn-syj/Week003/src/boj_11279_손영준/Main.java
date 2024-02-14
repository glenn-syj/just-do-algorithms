package boj_11279_최대힙;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/* B11279. 최대힙
 * 
 * 1. 조건
 * 	1-1. 연산의 개수 1 <= N <= 100_000
 * 	1-2. 일단 MaxHeap의 크기는 N*2면 충분
 * 2. 풀이
 * 	2-1. MaxHeap을 구현해서 풀어보자
 * 		-> Heapify에 대해서 초점을 두자!
 * 
 */


public class Main {

	static Scanner sc;
	static int N;
	static int input;
	static MaxHeap mHeap;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
//		File file = new File("./src/boj_11279_최대힙/input.txt");
//		sc = new Scanner(file);
		
		sc = new Scanner(System.in);
		N = sc.nextInt();
		mHeap = new MaxHeap(N*2);
		
		for (int i=0; i<N; i++) {
			input = sc.nextInt();
			if (input == 0) {
				sb.append(mHeap.popMax()).append('\n');
			} else {
				mHeap.insert(input);
			}
		}
		
		System.out.println(sb.toString());
		
		sc.close();
		
	}
	
	
}

class MaxHeap {
	
	private final int DEFAULT_CAPACITY = 1024 * 1024;
	private int[] elements;
	private int size;
	private int capacity;
	
	public MaxHeap() {
		
		this.size = 0;
		this.capacity = DEFAULT_CAPACITY;
		this.elements = new int[capacity+1];
	}
	
	public MaxHeap(int initialCapacity) {
		
		this.size = 0;
		this.capacity = initialCapacity;
		this.elements = new int[capacity+1];
	}
	
	private int getLeftChildIndex(int i) {
		return 2 * i;
	}
	
	private int getRightChildIndex(int i) {
		return 2 * i + 1;
	}
	
	private int getParentIndex(int i) {
		return i / 2;
	}
	
	private boolean isLeaf(int i) {
		
		return i > (size / 2) && i <= size;
	}
	
	private void swap(int i, int j) {
		
		int tmp;
		tmp = elements[i];
		elements[i] = elements[j];
		elements[j] = tmp;
		
	}
	
	private void maxHeapify(int i) {
		
		if (isLeaf(i)) {
			return;
		}
		
		int idx = i;
		
		
		// 재귀가 아니라 반복문으로 풀어보기
		while (elements[idx] < elements[getLeftChildIndex(idx)]
				|| elements[idx] < elements[getRightChildIndex(idx)]) {
			if (elements[getLeftChildIndex(idx)] > elements[getRightChildIndex(idx)]) {
				swap(idx, getLeftChildIndex(idx));
				idx = getLeftChildIndex(idx);
			} else {
				swap(idx, getRightChildIndex(idx));
				idx = getRightChildIndex(idx);
			}
		}
		
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void insert(int theElement) {
		
		elements[++size] = theElement;
		int current = size;
		// 0을 쓰지 않기 때문에 getParentIndex(current)가 0이어서는 안됨
		while (elements[current] > elements[getParentIndex(current)] && getParentIndex(current) != 0)  {
			swap(current, getParentIndex(current));
			current = getParentIndex(current);
		}
		
	}
	
	public void print() {
		
		for (int i=1; i <= size / 2; i++) {
			System.out.println("Parent Node : " + elements[i]);
			if (getLeftChildIndex(i) <= size) {
				System.out.println(" Left Child Node: " + elements[getLeftChildIndex(i)]);
			}
			if (getLeftChildIndex(i) <= size) {
				System.out.println(" Right Child Node: " + elements[getRightChildIndex(i)]);
			}
		}
		
		System.out.println();
		
	}
	
	public int popMax() {
		if (isEmpty()) {
			return 0;
		}
		
//		System.out.println("before: " + Arrays.toString(elements));
		int popped = elements[1];
		elements[1] = elements[size];
		//초기화를 안시켜줬었다!!
		elements[size] = 0;
		size--;
//		System.out.println("after: " + Arrays.toString(elements));
		
		maxHeapify(1);
		
		return popped;
	}
	
}