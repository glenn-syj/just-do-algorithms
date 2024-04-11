package swea_5644_박건택;
/*
 * 일단 충전 범위들을 나타내보자
 * 겹치는 범위를 표현하기 위해 int 배열로 각 범위에 미치는 범위의 수를 저장
 * 각 충전기가 영향 미치는 좌표는 따로 리스트로 저장해보자
 * 
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2 {
	static int[][] map;
	static List<int[]> chargers; // 충전기들의 범위와 위치와 세기 저장
	static List<int[]>[] range;
	static int[][] pathA;
	static int[][] pathB;
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 }; // 멈춤, 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int test = 1; test <= tc; test++) {

			map = new int[10][10];

			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 이동 경로의 수
			int A = Integer.parseInt(st.nextToken()); // 충전기 수

			range = new List[A];
			for(int i = 0 ; i< A ; i++) {
				range[i] = new ArrayList<>();
			}
			pathA = new int[M + 1][2]; // A와 B의 이동경로
			pathB = new int[M + 1][2]; // A와 B의 이동경로

			int r = 0;
			int c = 0;
			int nr = r;
			int nc = c;
			int dir = 0;
			pathA[0][0] = r;
			pathA[0][1] = c;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				dir = Integer.parseInt(st.nextToken());
				nr = nr + dr[dir];
				nc = nc + dc[dir];

				pathA[i][0] = nr;
				pathA[i][1] = nc;
			}

			nr = 9;
			nc = 9;
			pathB[0][0] = nr;
			pathB[0][1] = nc;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				dir = Integer.parseInt(st.nextToken());
				nr = nr + dr[dir];
				nc = nc + dc[dir];

				pathB[i][0] = nr;
				pathB[i][1] = nc;
			}
			// --------이동경로 입력 완료-------

			// 충전기 정보 입력하고 map에 표시하기
			chargers = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int[] temp = new int[4];
				temp[1] = Integer.parseInt(st.nextToken()) -1;
				temp[0] = Integer.parseInt(st.nextToken()) -1;
				temp[2] = Integer.parseInt(st.nextToken());
				temp[3] = Integer.parseInt(st.nextToken());

				chargers.add(temp);
				mapping(temp);
			}
			
//			for(int i = 0; i < 10; i++) {
//				for(int t = 0; t < 10; t++) {
//					System.out.print(map[i][t] + " ");
//				}
//				System.out.println();
//			}

		}
	}

	
	//충전기 위치와 범위를 맵에 나타내는 메서드
	public static void mapping(int[] charger) {
		int row = charger[0];
		int col = charger[1];
		int range = charger[2];
		int power = charger[3];

		int temp = 0;
		int c = 0;
		for (int r = (row - range); r <= row; r++) {
			if (r < 0) {
				continue;
			}

			map[r][col]++;
			temp++;
			for (int t = 1; t < temp; t++) {
				c = col + t;
				if (c < 10) {
					map[r][c]++;
				}

				c = col - t;
				if (c >= 0) {
					map[r][c]++;
				}
			}
		}

		temp = 0;
		for (int r = (row + range); r > row; r--) {
			if (r >= 10) {
				continue;
			}

			map[r][col]++;
			temp++;
			for (int t = 1; t < temp; t++) {
				c = col + t;
				if (c < 10) {
					map[r][c]++;
				}

				c = col - t;
				if (c >= 0) {
					map[r][c]++;
				}
			}
		}

	}
}
