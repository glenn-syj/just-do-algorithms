package swea_1221_박건택;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * if문과 counting 정렬을 이용해보자..
 * Map은 키 값을 비교하는게 오래 걸릴 것 같고
 * 우선순위 큐는 아직 생소하고 삽입하는게 오래 걸릴 것 같습니다.
 * 
 * 입력이 띄어쓰기로 구분되어있으니 StringTokenizer 이용
 * 입력 수가 많으니 BufferedWriter 이용
 * 
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(sc.nextLine());
		
		for(int test = 1; test <= tc; test++) {
			
			String input = sc.nextLine();
			st = new StringTokenizer(input);
			st.nextToken(); // # 붙은 케이스 입력 버리기..
			
			int[] counts = new int[10];
			String[] str = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
			int num = Integer.parseInt(st.nextToken());
			
			input = sc.nextLine();	//문자열들 입력
			st = new StringTokenizer(input);
			
			for(int i = 0; i < num; i++) {
				String element = st.nextToken();
				
				if(element.equals("ZRO")) {
					counts[0] += 1;
				}else if(element.equals("ONE")){
					counts[1] += 1;
				}else if(element.equals("TWO")){
					counts[2] += 1;
				}else if(element.equals("THR")){
					counts[3] += 1;
				}else if(element.equals("FOR")){
					counts[4] += 1;
				}else if(element.equals("FIV")){
					counts[5] += 1;
				}else if(element.equals("SIX")){
					counts[6] += 1;
				}else if(element.equals("SVN")){
					counts[7] += 1;
				}else if(element.equals("EGT")){
					counts[8] += 1;
				}else if(element.equals("NIN")){
					counts[9] += 1;
				}
				
			}
			
//			System.out.println(Arrays.toString(counts));
			System.out.println("#" + test);
			for(int i = 0; i < 10; i++) {
				for(int t = 0; t < counts[i]; t++) {
					bw.write(str[i] + " ");
				}
			}
			
			bw.flush();
			System.out.println();
		}
		
	}
}
