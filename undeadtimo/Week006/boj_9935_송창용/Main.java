package boj_9935_문자열폭발;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 
 * 이전에 stack에 대해서 배울 때,
 * (와 )가 연속으로 존재할 때 무언가 이벤트가 일어나는 문제들을 풀었었다.
 * 그 때의 기억들을 떠올리며 문제를 풀어보았다.
 * 
 */

/*
 * 
 * stack에 주어진 문자열의 문자들을 하나씩 집어넣을 것이다.
 * 만약 폭발 문자열의 마지막 문자가 stack에 들어온다면,
 * 폭발 점검을 시작한다.
 * 
 * 첫 번째 예제의 경우 C4이기 때문에
 * 
 * 4가 들어왔을 경우, 그 다음으로 존재하는 문자가 C인지 확인하고,
 * C라면 C와 4를 제거,
 * 
 * C가 아니라면, 4를 넣고, 다음 문자를 추가해준다. 
 * 
 * 
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		String str = br.readLine();
		
		String bomb = br.readLine();
		
		List<Character> stack = new ArrayList<>();
		
		for(int i = 0; i < str.length(); i++) {
			
			/*
			 * 구현 순서를 잘못 설정한 것과,
			 * List의 범위를 넘어서는 탐색이 일어났을 때의 처리를 하지 않은 것으로,
			 * 
			 * 오류가 발생했었다.
			 * 
			 */
			
			// 문자를 하나씩 List에 추가한다.
			stack.add(str.charAt(i));
			
			// 추가한 문자가 폭발 문자의 마지막 문자와 일치한다면, 
			if(str.charAt(i) == bomb.charAt(bomb.length() - 1)) {
				
				// 우선 list를 탐색하다가 범위를 넘어서는 일이 없도록 처리해준다.
				for(int j = 0; j < bomb.length(); j++) {
					if(stack.size() - 1 - j < 0 || bomb.length() - 1 - j < 0) {
						continue;
					}
					
					// 문자열 폭발의 길이만큼 탐색하면서 전부 폭탄과 일치한다면, 폭발 문자들을 전부 제거해준다.
					if(stack.get(stack.size() - 1 -  j) == bomb.charAt(bomb.length() - 1 - j)){
						if(j == bomb.length() - 1) {
							
							// 조건 체크 후, 폭발 문자들 전부 제거
							for(int k = 0; k < bomb.length(); k++) {
								stack.remove(stack.size() - 1);
							}
						}
					// 폭발 문자열이 아님을 알게 되면 반복문에서 바로 빠져나온다.
					}else {
						break;
					}
				}
			}
			
		}
		
		
		if(stack.size() == 0) {
			System.out.println("FRULA");
		}else {
			// BufferedWriter를 통한 속도 향상이 없다면 시간초과가 나온다.
			for(int i = 0; i < stack.size(); i++) {
				bw.append(stack.get(i));
			}
			bw.flush();
		}
		
		
	}
}