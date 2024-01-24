import java.io.*;
import java.util.*;

/* 1592. 영식이와 친구들
 * 
 * 1. 조건
 *	1-1. N 명이므로 배열에 접근하는 index가 N보다 작아야 함
 * 		-> (position+L) % N 을 이용
 * 	1-2. M 번보다 적게 받을 때에만 지속되는 '조회' 및 '값 수정'
 * 		-> 조건식에서 M을 이용해야 함
 * 	1-3. array[position]이 짝수일 때는 (position+N-L) % N, 홀수일 때는 (position+L) 
 * 2. 풀이
 * 	2-1. while 반복문에서 공을 N번 '받았을 때' 바로 종료
 * 	2-2. 위 조건을 응용해서 구현
 */


public class Week000_백준_1592_손영준 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] players = new int[N];
		int position = 0;
		int passCount = 0;
		players[0] = 0;
		
		while (players[position] < M) {
			// position이 0일 때, 처음 받는 공도 받은 회수로 세어야 함
			players[position]++;
			if (players[position] == M) {
				break;
			}
			if (players[position] % 2 == 0) {
				// position에서 L만큼 반시계 방향으로 갈 때, 음수가 나오면 에러!
				position = (position+N-L) % N;
			} else {
				position = (position+L) % N;
			}
			// position이 이동하는 게 pass
			passCount++;
		}
		
		bw.write(String.valueOf(passCount));
		bw.close();
		
	}
}
