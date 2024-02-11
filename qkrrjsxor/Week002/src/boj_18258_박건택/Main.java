package boj_18258_박건택;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Queue를 구현하고 실행해보자
 * 크기조절이 가능한 ArrayList로 리스트를 만들고
 * push, pop을 하며 요소 넣기
 * 
 * 요소들을 넣고 뺄 때 활용하기
 */
public class Main {
	
	//큐를 구현할 ArrayList
	static int[] queue = new int[2000000];
	static int size = 0;	//Queue에 있는 요소의 개수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		int cmdNum = Integer.parseInt(sc.nextLine());
		String cmd;
		int num;
		
		//처음 입력받은 명령 수 만큼 반복
		for(int i = 0; i < cmdNum; i++) {
			
			//명령의 종류 입력
			cmd = sc.nextLine();
			st = new StringTokenizer(cmd);
			String s = st.nextToken().trim();
			
			//push 입력 시
			if(s.equals("push")) {
				num = Integer.parseInt(st.nextToken());
				push(num);
			}
			
			//pop 입력 시
			if(s.equals("pop")) {
				int temp = pop();
				System.out.println(temp);
			}
			
			//front 입력 시
			if(s.equals("front")) {
				front();
			}
			
			//back 입력 시
			if(s.equals("back")) {
				back();
			}
			
			//size 입력 시
			if(s.equals("size")) {
				size();
			}
			
			//empty 입력 시
			if(s.equals("empty")) {
				empty();
			}
			
		}
	}
	
	//push 메서드 구현
	public static void push(int num) {
		queue[size] = num;
		
		size++;

	}
	
	//pop 메서드 구현
	public static int pop() {
		if(size>0){
			size -= 1;
			int temp = queue[0];
			
			for(int i = 0; i < size ; i++) {
				queue[i] = queue[i+1];
			}
			

			return temp;	
		}else {

			return -1;
		}
	}

	//front 메서드
	public static void front() {
		System.out.println(queue[0]);
		
	}
	
	//back 메서드
	public static void back() {
		if(size > 0) {
			System.out.println(queue[size-1]);
		}
		else {
			System.out.println(-1);
		}
	}
	
	public static void size() {
		System.out.println(size);
	}
	
	public static void empty() {
		if (size == 0) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
}