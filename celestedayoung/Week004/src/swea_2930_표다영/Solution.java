import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
	
	static int[] heap;
	static int heapSize = 0;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
//		File file = new File("./swea/swea_2930_힙/input.txt");
//		Scanner sc = new Scanner(file);
		
		int testNum = sc.nextInt();
		
		for (int t = 1; t <= testNum; t++) {
			
			int calNum = sc.nextInt();
			heap = new int[1000000];
            heapSize = 0;
			List<Integer> result = new ArrayList<Integer>();
			
			for (int c = 0; c < calNum; c++) {
				
				if (sc.nextInt() == 1) {
					heapPush(sc.nextInt());
//					System.out.println("after add: " + Arrays.toString(heap));
				} else {
					if (heapSize == 0) {
						result.add(-1);
					} else {
						result.add(heapPop());
//						System.out.println("after pop: " + Arrays.toString(heap));
					}
					
				}
			}
			
			System.out.printf("#%d", t);
			for (int i = 0; i < result.size(); i++) {
				System.out.print(" " + result.get(i));
			}
			System.out.println();
		}
	}
	
	// [swap]
		static void swap(int a, int b) {
			int tmp = heap[a];
			heap[a] = heap[b];
			heap[b] = tmp;
		}
		
		// [삽입]
		static void heapPush(int data) {
			
			// 마지막 위치에 노드 추가
			heap[++heapSize] = data;
			
			// 부모와 비교하면서 swap
			int child = heapSize;
			int parent = heapSize / 2;
			
			while (parent > 0 && heap[parent] < heap[child]) {
				
				// swap
				swap(parent, child);
				
				child = parent;
				parent = child / 2;
			}
		}
		
		//[삭제]
		static int heapPop() {
			
			// 루트에 있는 원소 삭제
			int popItem = heap[1];
			
			// 마지막에 있는 원소를 루트로 옮기기
			heap[1] = heap[heapSize];
			heap[heapSize] = 0;
			heapSize--;
			
			int parent = 1;
			int child = parent * 2;
			
			// 자식끼리 비교
			if (child + 1 <= heapSize && heap[child] < heap[child + 1]) {
				child++;
			}
			
			while (child <= heapSize && heap[parent] < heap[child]) {
				
				
				// 자식이 더 크면 swap
				swap(parent, child);
				
				parent = child;
				child = parent * 2;
				
				// 자식끼리 비교
				if (child + 1 <= heapSize && heap[child] < heap[child + 1]) {
					child++;
				}

			}
			
			return popItem;
			
		}
}
