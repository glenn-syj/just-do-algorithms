package boj_24511_황민욱;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * [문제 풀이 과정]
 * - 각각의 자료구조는 배열에 원소가 1개만 들어가 있는 자료구조이다.
 * - 패턴을 구해서 풀면 쉬움
 * 	- 0 -> 큐 -> Xn-1과 배열의 해당 원소 swap
 *  - 1 -> 스택 -> 애초에 push pop 해도 x 값도 안변하기게 continue로 건너뛰기.
 *  -> 이러면 시간 초과남.
 * 
 * [문제 풀이 과정2]
 * - 어짜피 큐에 있는 요소들이 pop해서 나와서 리턴이 된다.
 * - 따라서 가장 뒤에 있는 큐의 요소가 pop해서 먼저 나오게 된다.
 * - 0에 위치한 숫자들을 stack에 넣고 stack에 있는 아이들 먼저 pop하고 stack이 비면 
 *   새롭게 추가된 숫자들을 순차적으로 출력 한다.
 */

import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(st.nextToken());

		StringTokenizer isQueueStack = new StringTokenizer(br.readLine());
		StringTokenizer QueueStackArr = new StringTokenizer(br.readLine());
		
		String[] stack = new String[N];
		int top = -1;
		
		for(int i = 0; i < N; i++) {
			if(isQueueStack.nextToken().equals("0")) {
				stack[++top] = QueueStackArr.nextToken();
			} else {
				QueueStackArr.nextToken();
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int M =  Integer.parseInt(st.nextToken());

		StringTokenizer Insert = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {
			if(top == -1) {
				if(i==M-1) {
					bw.write(Insert.nextToken() + "\n");
					break;
				}
				bw.write(Insert.nextToken() + " ");
			} else {
				if(i==M-1) {
					bw.write(stack[top--] + "\n");
					break;
				}
				bw.write(stack[top--] + " ");
			}
		}
		
		
		bw.flush();
	}
}
