import java.util.*;
import java.io.*;


/* 1859. 백만 장자 프로젝트
 * 0. 조건
 * 	0-1. 연속된 N일 동안의 물건과 매매가
 * 	0-2. 한 번에 하나의 물건 밖에 못삼 
 * 	0-3. 최대 100만개의 자연수 (최대값 10000)
 * 		-> 1, 1, 1, ... , 1, 10000 => 100억 (int 범위 넘어섬) 
 * 1. 풀이
 * 	1-1. 앞에서부터 접근: 전 배열의 최댓값을 찾아도, 남은 index 들 사이에서의 최댓값을 또 찾아야함
 * 		-> (1) 그렇다 O(k^2)를 향하므로 비효율적
 * 		-> (2) 게다가 최악의 경우: 내림차순으로 진행될 때 O(N^2)가 됨 
 * 		=> 이걸 개선할 방법은 없을까?
 * 	1-2. 뒤에서부터 접근: 마지막 요소의 값을 최댓값 max로 두기
 * 		-> max보다 작을 시: max-array[i]
 * 		-> max보다 클 시: max를 재설정 (점수 계산 X)
 */


public class Week000_SWEA_1859_손영준 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] prices = new int[N];
			
			// 역순으로 price 초기화
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				prices[N-1-i] = Integer.parseInt(st.nextToken());
			}
			
			long profit = 0;
			int max = prices[0];
			
			for (int price: prices) {
				if (price < max) {
					profit += max-price;
				} else {
					max = price;
				}
			}
			
			bw.write("#" + tc + " " + profit + "\n");
			bw.flush();
		}
		bw.close();
		
	}
}
