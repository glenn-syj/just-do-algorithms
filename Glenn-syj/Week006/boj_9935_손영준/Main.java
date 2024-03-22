package boj_9935_문자열폭발;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/* B9935. 문자열 폭발
 * 
 * 1. 조건
 * 	1-1. Deque에 기반한 스택을 이용한다
 * 	1-2. 한 번 순회하며 폭발하면, 다음에 또 폭발을 검사한다
 * 		-> 폭발이 진행되지 않으면 종료
 * 	1-3. 진행되지 않았을 때, 스택이 empty라면 FRULA 출력
 * 		-> 아니라면 deque에서 앞에서부터 빼면서 출력
 * 2. 풀이
 * 	2-0. deqA에 모든 char을 넣고 시작, curDeq = deqB
 * 	2-1. Deque 2개를 이용해서
 * 		-> curDeq이 deqA면 A->B: first부터 넣으면서 문자열이 완성되면 문자열 길이 만큼 pop()
 * 		-> curDeq이 deqB면 반대로
 * 	2-2. count가 1~문자열의 length일 때에 체크를 위해 필요한 배열 char[]: 폭발 문자열 저장
 * 3. 디버깅
 * 	3-1. 시간초과가 뜨는데... 언제 시간초과가 뜰까?
 *
 * 
 */

public class Main {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br;
	static BufferedWriter bw;
	
	static Deque<Character> deq = new ArrayDeque<>();
	static Deque<Integer> deqCount = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine().trim();
		char[] booms = br.readLine().trim().toCharArray();
		int count = 0;
		boolean boom = true;
		
		int sPtr = 0;
		deq.addLast(input.charAt(sPtr));
		if (deq.peekLast() == booms[0]) {
			deqCount.addLast(1);
			count = 1;
		} else {
			deqCount.addLast(0);
		}
		
		
		while (sPtr < input.length()) {
			
			
			if (count == booms.length) {
				for (int i=0; i<booms.length; i++) {
					deq.pollLast();
					deqCount.pollLast();
				}
				if (deq.isEmpty() || deqCount.peekLast() == 0) {
					count = 0;
				} else if (deq.peekLast() == booms[deqCount.peekLast()-1]) {
					count = deqCount.peekLast();
				}
				continue;
			}
			
			sPtr++;
			if (sPtr >= input.length()) {
				break;
			}
			deq.addLast(input.charAt(sPtr));
			
			if (deq.peekLast() == booms[0]) {
				count = 1;
				deqCount.addLast(count);
				continue;
			}

			if (deq.peekLast() != booms[count]) {
				count = 0;
				deqCount.addLast(count);
				continue;
			}

			count++;
			deqCount.addLast(count);
			
		}
		
		if (deq.isEmpty()) {
			sb.append("FRULA");
		}
		
		while (!deq.isEmpty()) {
			sb.append(deq.pollFirst());
		}			
		
		bw.write(sb.toString());
		bw.close();
		
	}
	
	
	
}