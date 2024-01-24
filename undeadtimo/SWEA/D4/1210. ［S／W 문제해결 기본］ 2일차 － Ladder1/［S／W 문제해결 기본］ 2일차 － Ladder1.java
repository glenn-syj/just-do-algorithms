import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		


		for(int test_case = 1; test_case <= 10; test_case++)
		{
		
        int T;
		T=sc.nextInt();
		
		
		// 100 * 100의 2차원 배열 생성
		int[][] ladder = new int[100][100];
		
		// 사다리 숫자 입력
		for(int r = 0; r < 100; r++) {
			for(int c = 0; c < 100; c++) {
				ladder[r][c] = sc.nextInt();
			}
		}
		
		// 위치를 저장하기 위한 nr과 nc값
		int nr = 99;
		int nc = 0;
		
		// 마지막 열에서 2의 위치 파악
		for(int c = nc; c < 100; c++) {
			if(ladder[nr][c] == 2) {
				nc = c;
				break;
			}
		}
		
		
		// 왼쪽 또는 오른쪽에 1이 있다면 움직이는 로직
		// 왼쪽 또는 오른쪽에 1이 없다면 위로 올라가는 로직
		total:
		while(nr != 0) {
			// 첫 번째 행이 될 때까지 올라가도록 r != 0 조건 설정
			
			
			// 첫 번째 열이 아닐 때, 왼쪽으로의 탐색
			// 첫 번째 열이라면 두 번째 조건은 시행되지 않으므로 if 절에서 오류가 발생하지는 않는다.
			if((nc != 0) && ladder[nr][nc - 1] == 1 ){
				while(nc != 0 && ladder[nr][nc - 1] == 1) {
					nc--;
					
					if(nc == 0) {
						nr--;
						continue total;
					}
					
					if(ladder[nr][nc - 1] == 0) {
						nr--;
						continue total;
					}
				}
			}
			
			if((nc != 99) && ladder[nr][nc + 1] == 1) {
				while(nc != 99 && ladder[nr][nc + 1] == 1) {
					nc++;
					if(nc == 99) {
						nr--;
						continue total;
					}
					
					if(ladder[nr][nc + 1] == 0) {
						nr--;
						continue total;
					}
				}
			}
			
			nr--;
			
		}
		
		System.out.printf("#%d %d%n", T, nc);

		}
	}
}