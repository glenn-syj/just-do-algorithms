import java.util.Scanner;

/*
 * 풀이 방법
 * 1. 2차원 배열을 만든다.
 * 2. 대각선으로 2차원 배열에 숫자들을 넣는다. -> 이 또한 참조 배열로 만드는 것이 시간에 효율적. (100*100)
 * 	2-1. 대각선으로 탐색할 때 참조할 수 있는 2차원 배열을 만든다. 
 * 	     예를 들어 (2,2)에 3이 들어간다면 3의 인덱스에 [2,2]를 넣는 배열.
 * 3. 연산자를 구현한다.
 * 	  - #(r,c) = 2차원 배열에 해당 인덱스를 넣으면 나오는 값
 *    - &(p) = 참조 배열에 해당 인덱스를 넣으면 나오는 값
 *    - 덧셈
 * 4. 결과값 출력!
 */

public class Week001_SWEA_1493_황민욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[][] coord = new int[300][300];
		int[][] num = new int[45151][2];

		int count = 0;

		for (int y = 1; y < 300; y++) {
			for (int n = 0; n < y; n++) {
				coord[y - n][n + 1] = ++count;
				num[count][0] = n + 1;
				num[count][1] = y - n;
			}
		}

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int p = sc.nextInt();
			int q = sc.nextInt();

			int x = num[p][0] + num[q][0];
			int y = num[p][1] + num[q][1];

			int answer = coord[y][x];

			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
