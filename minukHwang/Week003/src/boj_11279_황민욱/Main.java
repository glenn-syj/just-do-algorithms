package boj_11279_황민욱;

class MaxHeap {
	int[] heap; // 배열
	int size;
	int maxSize;

	public MaxHeap() {

	}

	public MaxHeap(int maxSize) {
		this.maxSize = maxSize;
		this.size = 0;
		heap = new int[this.maxSize + 1];
		heap[0] = Integer.MAX_VALUE;
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private int leftChild(int pos) {
		return 2 * pos;
	}

	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private boolean isLeaf(int pos) {
		return pos > (size / 2) && pos <= size;
	}
	
	private void swap(int fpos, int spos) {
		int tmp;
		tmp = heap[fpos];
		heap[fpos] = heap[spos];
		heap[spos] = tmp;
	}
	
	private void maxHeapify(int pos) {
		
	}

}

public class Main {

}
