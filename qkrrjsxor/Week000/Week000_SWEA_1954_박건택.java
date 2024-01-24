import java.util.Arrays;
import java.util.Scanner;

public class 달팽이_숫자 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][N];
		
		int r = 0;
		int c = 0;
		int nr = 0;
		int nc = 0;
		int idx = 0;
		int[] dr = {0, 1, 0, -1};  //우 하 좌 상
		int[] dc = {1, 0, -1, 0};
		int cnt = 1;
		
		for(int i = 0; i < N*N; i++) {
			arr[r][c] = cnt++;
			nr = r + dr[idx];
			nc = c + dc[idx];
			if( nr >= 0 && nr < N && nc >= 0 && nc < N && arr[nc][nr] ==0) {
				r = nr;
				c = nc;
			}
			else {
				idx = (idx + 1) % 4;    // 위 조건을 만족하지 않는다면 방향 변경
                r += dr[idx];
                c += dc[idx];
			}
			
			System.out.println("r :" + r + " c : " + c + " idx : " + idx);
		}
		
		System.out.println(Arrays.toString(arr[0]));
		System.out.println(Arrays.toString(arr[1]));
		System.out.println(Arrays.toString(arr[2]));
		for(int k = 0; k < N; k++) {
			for(int t = 0; t < N; t++) {
				System.out.print(arr[k][t] + " ");
			}
			System.out.println();
		}

	}

}