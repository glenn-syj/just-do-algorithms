package boj_15828_박건택;

import java.util.Scanner;

/*
 * 버퍼에 데이터들이 들어오고 입력이 0이면 먼저 들어온 데이터 순서대로 빠져나간다.
 * Queue를 이용해서 입력을 받고 출력하자.
 * 
 * 
 * 먼저 사이즈를 입력받으므로 사이즈가 정의되지 않은 Queue 클래스 만들고,
 * 생성자로 int를 받아서 큐의 크기 정하기. --> 취소, 20만개로 만들어보기
 * 
 * 큐 pop을 했을 떄 순회하며 한칸씩 땡기기에는 반복횟수가 너무 많을 것 같아서
 * 최대 입력 개수인 20만으로 크기를 정하고,
 * 입력받은 버퍼의 크기는 rear - front해서 최대값을 제한하자.
 * 
 * 버퍼 최대값을 넘어가면 입력 안하고 버려진다.
 * 
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int size = sc.nextInt();
		queue newQ = new queue(size); // 입력받은 size로 제한되는 큐 생성

		// 반복 횟수는 어떻게 정하지? Large case에서 큐의 사이즈와 무관하게 20만까지 나오는데,,
		// 반복은 20만번으로 하고 입력으로 -1이 나오면 break 하자
		for (int i = 0; i < 200000; i++) {
			int num = sc.nextInt();

			if (num == 0) {
				newQ.pop(); // 문제에서 비어있는 경우에 0이 입력되지 않는다 하였으니
			} else if (num == -1) {
				break;
			} else {
				newQ.push(num);
			}
		}

		// 입력이 완료되고 출력하기
		if (newQ.isEmpty()) {
			System.out.println("empty");
		} else {
			
			int t = newQ.size();
			for (int i = 0; i < t; i++) {	//이 부분에서 i <= newQ.size() 로 경계조건 걸었다가 pop 할때마다 size가 줄어서 원하던 결과가 안나와서 수정.
				System.out.print(newQ.pop() + " ");
			}
		}
	}

}

//-------------------------------------------------------------------
//큐 구현
class queue {
	int[] q = new int[200000];
	int front = -1;
	int rear = -1;
	int size;

	public queue(int n) {
		this.size = n; // 생성자에 정수 입력받아서 최대값을 설정
	}

	public void push(int item) {
		if (isFull()) {
			return; // 가득 찼으면 그냥 넘기기
		}
		rear++;
		q[rear] = item;
//		System.out.println("push : " + q[rear]);	//디버깅용 출력
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("큐가 비었습니다.");
		}
		front++;
//		System.out.println("pop : " + q[front]);	//디버깅용 출력
		return q[front];
	}

	public boolean isEmpty() {
		return front == rear;
	}

	public boolean isFull() {
		return (rear - front) == this.size;
	}

	public int size() {
		System.out.println("size : " + (rear-front)); 	//디버깅용 출력
		return rear - front;
	}
}