package Week001;

import java.util.Scanner;

public class Week001_SWEA_6485_박건택 {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		 * 입력 순서에 따라서 생성한다
		 * N을 입력 받아 버스 노선의 수를 입력
		 * Ai, Bi 를 노선 수 만큼 입력받아야 하므로 course[N][2]  생성 
		 * 각 행은 노선 번호이고 열은 A, B -> for 문으로 입력 형식에 맞게 Ai,Bi 입력
		 * P를 입력 받아 정류장의 수 입력
		 * result[N][P] (행 = 노선 번호, 열 = 정류장 번호)
		 * i 행에 Ai ~ Bi 열 까지 1 입력
		 * 
		 * 오답노트 ? P 입력 뒤에 입력받는 수자가 무슨 의미인지 몰랐었는데 조회할 정류장 번호였다.
		 * 하지만 이클립스랑 테스트에선 됐는데 제출하면 fail........................................ 
		 * 
		 */

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[][] course = new int[N][2];
			
			for(int i=0; i<N; i++) {
				course[i][0] = sc.nextInt() -1;	//Ai
				course[i][1] = sc.nextInt() -1; //Bi     정류장 번호-1 = 인덱스값 
			}
			
			int P = sc.nextInt();
			int[][] result = new int[N][5000];	// 노선 번호 - 정류장 번호 배열
			int[] cj = new int[P];		
			int[] count = new int[5000];	//열의 합계를 구하여 각 정류장에 지나는 버스 수를 담을 배열
			
			for(int i=0; i<P; i++) {
				cj[i] = sc.nextInt();
			}
			
			//행 : 노선번호, 열 : 정류장 번호
			//각 i 행마다 해당하는 노선 번호가 지나가는 정류장 Ai ~ Bi 열에 1 대입
			for(int r=0; r<N; r++) {
				for(int c=course[r][0]; c<=course[r][1]; c++) {
					result[r][c] = 1;
				}
			}
			
			
			
			//열 순회 하며 각 열의 합을 구한다
			for(int c =0; c<5000; c++) {
				for(int r=0; r<N; r++) {
					count[c] += result[r][c];
				}
			}
			
			
			System.out.print("#"+test_case);
			for(int i : cj) {
				System.out.print(" " + count[i-1]);
			}
		}

	} 

}
