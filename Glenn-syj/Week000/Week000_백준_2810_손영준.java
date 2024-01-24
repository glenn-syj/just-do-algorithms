import java.io.*;
import java.util.*;

/* 2810. 컵홀더
 * 	1. 첫 시작과 끝은 어차피 무조건 들어감 (at least 2)
 * 	2. 컵홀더 자리: 좌측은 고민하지 않고, 우측만 고민하기 -> 마지막은 검증으로서만의 가치
 * 		2-1. S는 무조건 우측에 1개, L은 우측에 S가 올 때만 ++count
 * 			-> L은 2개가 되면 +1이 됨
 * 			-> 여기에서 스택도 이용 가능!
 *	3. 컵홀더 개수와 사람의 수 중 작은 것 출력 
 */

public class Week000_백준_2810_손영준 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		// at least 2개
		int count = 2;
		char ptrChar;
		char checkChar;
		boolean coupled = false;
		
		String line = br.readLine().trim();
		
		// can use StringTokenizer
		// StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N-1; i++) {
			ptrChar = line.charAt(i);
			checkChar = line.charAt(i+1);
			switch (ptrChar) {
			case 'S':
				// S인 경우에는 무조건 컵홀더 자리 추가
				count++;
				break;
			default:
				// L인 경우에는 시작L이냐 끝L이냐가 중요 
				if ((ptrChar != checkChar) || (coupled)) {
					count++;
					coupled=false;
				} else {
					coupled=true;
				}
			}
		}
		
		bw.write(String.valueOf(Math.min(N, count)));
		bw.close();
		
	}
}
