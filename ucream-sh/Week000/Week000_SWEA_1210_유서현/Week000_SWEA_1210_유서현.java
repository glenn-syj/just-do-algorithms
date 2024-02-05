import java.util.Scanner;

public class Week000_SWEA_1210_유서현 { // LADDER 1
	
	public static void main(String[] args) {
		// 전체 테케 갯수 = 10
		
		// 델타탐색용
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int cnt = 0;
		
		
		for (int i=0; i<10; i++) {
			// 당첨에 도착하게 되는 출발점의 x좌표
			int ans = 0;
			
			
			// 테케번호(testCase) 입력받기
			Scanner scanner = new Scanner(System.in);
			int testCase = scanner.nextInt();
			System.out.println("#"+ testCase +" " + ans );
			
			// 사이즈 100
			int size = 100;
			int[][] ladder = new int[size][size];
			
			// 배열 입력받기
			for(int row= 0; row <size; row++) { 
	            for(int col = 0; col <size; col++) {            
	                ladder[row][col] = scanner.nextInt();
	            }
	        }

			
			// 배열 출력하기
			 for(int row=0; row<size; row++) {
		            for(int col=0; col<size; col++) {
		                System.out.print(ladder[row][col] + "\s"); // 한칸씩 띄우기.
		            }
		            System.out.println(); //줄바꿈.
		        } // 오오 잘 입력됐구나
		
			// 이제 ladder[size-1] 에서 =99
			// 2를 찾아서 거길 시작점으로..
			 
			// 미로탐색 알고리즘 -> ?? 델타탐색인건 알겠는데 ㅠ 여기서마무리
		

		
		
		
		} // 여기까지 큰for문
	}

}
