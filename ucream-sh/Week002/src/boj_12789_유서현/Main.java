package boj_12789_유서현;

import java.util.Scanner;

public class Main {
//	boj_12789_도키도키간식드리미
	// 스택 메소드 구현
	// 새 줄 배열과 기존 줄 배열을 만들어두기
	// 옳게 된 순서의 줄도 배열로 만들어 두기 (배열 사이즈는 다 N으로)
	//
	// 임시 줄은 스택으로 만들어두기 (사이즈는 혹시모르니 N으로)
	// - 기존 줄 -> 임시 줄에 넣을건데
	// - 옳은 순서 줄의 i=0인지 아닌지 검사
	// - 일치하지 않으면 임시줄로 push() (이미 들어가있는 애보다 작은 애만 push() 가능)
	// - 만약 더 큰수면 순서 꼬이므로 Sad 출력
	// - 일치하면 -> pop() 해서 새 줄로 이동하고 i++
	//
	// 새 줄 == 옳은 순서 줄 이 되면 Nice 출력

	static int[] stack;
	static int top = -1;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		stack = new int[N]; // 임시 줄 스택 생성

		// 대기줄(기존줄) -> 인풋받은대로
		int[] wating = new int[N];
		for (int i = 0; i < N; i++) {
			wating[i] = sc.nextInt();
		}

		// 옳게된줄 (정렬된줄과 비교용도)
		int[] right = new int[N];
		for (int i = 0; i < N; i++) {
			right[i] = i + 1; // 1부터 N까지
		}

		// 정렬된줄 (새줄)
		int[] sorte = new int[N];

		int sorteIdx = 0; // 정렬된 줄 인덱스 체크용 (포인터느낌?)

		String ans = "Nice";

		// - 대기줄 -> 임시줄 에 넣을건데
		for (int i = 0; i < N; i++) {
			if (wating[i] == right[sorteIdx]) { // - 옳은 순서 줄의 i=0인지 아닌지 검사
				// - 일치하면 새 줄로 이동하고 i++
				sorte[sorteIdx++] = wating[i];
//				System.out.println(wating[i] + "새 줄에 넣음");
			} else { // - 일치하지 않으면 임시줄로 push() (이미 들어가있는 애보다 작은 애만 push() 가능)
				if (top == -1) {
					push(wating[i]);
//					System.out.println(wating[i] + "임시줄에 넣음");
				} else if (stack[top] > wating[i]) {
					push(wating[i]);
//					System.out.println(wating[i] + "임시줄에 넣음");
				} else if (right[sorteIdx] == stack[top]) {
					pop();
					sorteIdx++;
					i--; // 다시 그 i부터 검사하도록

				} else {
					if (wating[i] == right[sorteIdx]) {
						sorte[sorteIdx++] = wating[i];
//						System.out.println(wating[i]);
					} else {
						ans = "Sad"; // - 만약 더 큰수면 순서 꼬이므로 Sad 출력
//						System.out.println("슬픈데");
					}
				}
			}

		} // for문

		// top == -1되기전까지 pop하면 될듯

		System.out.println(ans);

	} // main

	// 스택 메소드
	// isEmpty isFull pop push
	// 다 구현하고 나서 생각났는데 이거 isEmpty랑 isFull은 안해도됐던거같다; 연습은된거같다

	public static boolean isEmpty() {
		return top == -1;
	} // isEmpty()

	public static boolean isFull() {
		return (top == stack.length - 1);
	} // isFull()

	public static int pop() {
		if (isEmpty()) {
			System.out.println("팝할게 없는데");
			return Integer.MIN_VALUE; // 오류처럼보이게 숫자 출력
		} else {
			return stack[top--];
		}
	} // pop()

	public static void push(int data) {
		if (isFull()) {
			System.out.println("꽉찼는데");
		} else {
			stack[++top] = data;
		}
	} // push()

}