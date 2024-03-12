package swea_2930_힙;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
	static Scanner sc;
	static int T;
	static int N;
	static int input;
	static MaxHeap mHeap;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("./src/swea_2930_힙/input.txt");
		sc = new Scanner(file);
		
//		sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for (int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			mHeap = new MaxHeap(N*2);
			
			sb.append('#').append(tc);
			for (int i=0; i<N; i++) {
				input = sc.nextInt();
				if (input == 2) {
					sb.append(' ').append(mHeap.popMax());
				} else {
					mHeap.insert(sc.nextInt());
				}
			}
			
			System.out.println(sb.toString());
			sb.setLength(0);
		}
		
		
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
			return -1;
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