import java.util.*;

/* 1210. Ladder1
 * 0. 입력
 * 	0-1. sc.nextLine()과 StringTokenizer 이용
 * 1. 초기
 * 	1-1. 출발점: data[0]에서, 값이 1인 곳들이 출발점
 * 	1-2. 도착점: data[99]에서, 값이 2인 곳이 도착점
 * 2. 진행
 * 	2-1. (1) 좌우 방향 체크 -> (2) 아래가 1인 곳 (가로지름 X)
 *	2-2. if (x==99) -> check the value if it is 2
 * 3. 출력
 * 	3-1. "#%d %d", tc, 위 진행 2-2를 만족하는 값
 * 
 */

public class Week000_SWEA_1210_손영준 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		int[][] pixels = new int[100][100];
		
		for (int tc=1; tc<=10; tc++) {
			sc.nextLine();
			for (int row=0; row <100; row++) {
				st = new StringTokenizer(sc.nextLine());
				for (int col=0; col < 100; col++) {
					pixels[row][col] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		for (int pixelRowZero: pixels[0]) {
			if (pixelRowZero==1) {
				if (movePointerToNext(pixels, pixelRowZero, 0)) {
					System.out.println(pixelRowZero);
				}
			}
		}
			
	}
	
	public static boolean movePointerToNext(int[][] pixels, int row, int col) {
		int[] ptr = new int[2];
		if (col < 99) {
			ptr = (findHorizontalPixel(pixels, row, col));
			if (ptr != null) {
				return movePointerToNext(pixels, ptr[0], ptr[1]);
			} else {
				return movePointerToNext(pixels, row+1, col);
			}
		} else {
			return pixels[row][col] == 2;
		}
	}
	
	public static int[] findHorizontalPixel(int[][] pixels, int row, int col) {
		// only left -> (-1, 0) / right -> (1,0)
		int[] dr = {-1, 1};
		int[] dc = {0, 0};
		int[] ptr = {row, col};
		
		for (int i=0; i<=1; i++) {
			if (isValidIndex(dr, dc, i, row, col) && pixels[row+dr[i]][col+dc[i]] == 1) {
				ptr[0] = row+dr[i];
				return ptr;
			}
		}
		return null;

	}
	
	public static boolean isValidIndex(int[] dr, int[] dc, int pos, int row, int col) {
		return (row+dr[pos] >= 0) && (row+dr[pos] < 100) && (col+dc[pos] >= 0) && (col+dc[pos] < 100);
	}
}
