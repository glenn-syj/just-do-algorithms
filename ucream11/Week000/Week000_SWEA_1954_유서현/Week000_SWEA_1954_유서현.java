import java.util.Scanner;

public class Week000_SWEA_1954_유서현 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// 정수 T 테케갯수 입력받기
		int T = scanner.nextInt();
		
		// 정수 N 각 테케의 달팽이사이즈 입력받기
		for (int i=1; i<=T; i++) {
			int N = scanner.nextInt();
			
			// 달팽이 1 ~ N*N 사이즈까지 (N=4면 1~16이겟지)
			// 사이즈 N*N의 배열 생성 (1부터 숫자넣는용)
			int[] numArr = new int[N*N]; // 0으로 초기화된애들
			
			// 출력 테케번호별로 출력
			System.out.println("#"+i);
			
			int startnum = 1;
			for (int j=0; j<numArr.length; j++) {
				numArr[j] = startnum++; // 1부터 쭉 넣기
			}
			
			int[][] snailArr = new int[N][N];
				// 2차원이니까 for 두번,,?
				for (int row=0; row<N; row++) { // 00 01 02 03 ,   10 11 12 13 이런식인가,,?
					for(int col=0; col<N; col++) {
						int count = 0;
						snailArr[row][col] = numArr[count++];
						}
						
					}
				
				

				// 배열 출력하기
				 for(int row=0; row<N; row++) {
			            for(int col=0; col<N; col++) {
			                System.out.print(snailArr[row][col] + "\s"); // 한칸씩 띄우기.
			            }
			            System.out.println(); //줄바꿈.
			        } 
						
					

				}

			 // 배열은 생성함
			 // 출력이 제대로 안됨
			// 
			
			
	


		}
		
		
	}



