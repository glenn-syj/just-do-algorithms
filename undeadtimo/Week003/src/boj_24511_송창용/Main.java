package boj_24511_송창용;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		/*
		 * BufferedReader InputStreamReader StringTokenizer를 이용해 입력. 
		 * BufferedWriter 사용해보기.
		 *  
		 * 스택에 해당하는 자료구조는 전부 버린다. 
		 * 큐 자료구조가 같는 원소들을 배열 안에 넣고, 삽입할 수열의 길이 M만큼의 원소를 출력해준다.
		 * 출력해야할 요소의 개수가 남는다면, 입력값에서 하나씩 가져와 추가로 출력해준다.
		 * 
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int len = Integer.parseInt(st.nextToken());

		int[] queueStack = new int[len];

		int queueCnt = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			queueStack[i] = Integer.parseInt(st.nextToken());
			if (queueStack[i] == 0) {
				queueCnt++;
			}
		}

		int[] elements = new int[queueCnt];

		int tempCnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			if (queueStack[i] == 0) {
				elements[tempCnt++] = Integer.parseInt(st.nextToken());
			} else {
				st.nextToken();
			}
		}

		st = new StringTokenizer(br.readLine());
		int inputCnt = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < inputCnt; i++) {
			if (i < queueCnt) {
				bw.write(elements[queueCnt - 1 - i] + " ");
			} else {
				bw.write(st.nextToken() + " ");
			}
		}
		bw.flush();
	}
}