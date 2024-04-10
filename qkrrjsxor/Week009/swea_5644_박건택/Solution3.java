package swea_5644_무선충전;
/*
 * 일단 충전 범위들을 나타내보자
 * 겹치는 범위를 표현하기 위해 List 배열로 각 범위에 미치는 범위의 수를 저장
 * 각 충전기가 영향 미치는 좌표는 따로 리스트로 저장해보자
 * 
 * Map을 List 2차원 배열로 만들어서, 각 칸에 충전기에 따른 파워를 저장
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution3 {
	static ArrayList<Integer>[][] map;
	static List<int[]> chargers; // 충전기들의 범위와 위치와 세기 저장
	static int[][] pathA;
	static int[][] pathB;
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 }; // 멈춤, 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int test = 1; test <= tc; test++) {

			map = new ArrayList[10][10];

			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 이동 경로의 수
			int N = Integer.parseInt(st.nextToken()); // 충전기 수

//			range = new List[A];
//			for(int i = 0 ; i< A ; i++) {
//				range[i] = new ArrayList<>();
//			}
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
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int[] temp = new int[4];
				temp[1] = Integer.parseInt(st.nextToken()) - 1;
				temp[0] = Integer.parseInt(st.nextToken()) - 1;
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

			int[] A = new int[2];
			int[] B = new int[2];
			sum = 0;
			for (int i = 0; i < M; i++) {
				A[0] = pathA[i][0];
				A[1] = pathA[i][1];
				B[0] = pathB[i][0];
				B[1] = pathB[i][1];

				cal(A, B);
			}

			System.out.println("#" + test + " " + sum);
		}
	}

	static int sum;

	public static void cal(int[] A, int[] B) {

		if (map[A[0]][A[1]] != null && map[B[0]][B[1]] != null) {
			// 두개 다 null 이 아니고 같은 충전기 영역에 있을 때
			for (int i = 0; i < map[A[0]][A[1]].size(); i++) {
				for (int t = 0; t < map[B[0]][B[1]].size(); t++) {
					if (map[A[0]][A[1]].get(i) == map[B[0]][B[1]].get(t)) {
						isMax(map[A[0]][A[1]], map[B[0]][B[1]]);
						return;
					}
				}
			}

			// 두개 다 null 이 아니지만 충전기 영역이 다를 때
			int max = -1;
			for (int k = 0; k < map[A[0]][A[1]].size(); k++) {
				if (map[A[0]][A[1]].get(k) > max) {
					max = map[A[0]][A[1]].get(k);
				}
			}
			sum += max;

			max = -1;
			for (int k = 0; k < map[B[0]][B[1]].size(); k++) {
				if (map[B[0]][B[1]].get(k) > max) {
					max = map[B[0]][B[1]].get(k);
				}
			}
			sum += max;
		} else {

			if (map[A[0]][A[1]] != null) {
				int max = -1;
				for (int k = 0; k < map[A[0]][A[1]].size(); k++) {
					if (map[A[0]][A[1]].get(k) > max) {
						max = map[A[0]][A[1]].get(k);
					}
				}
				sum += max;
			} else if (map[B[0]][B[1]] != null) {
				int max = -1;
				for (int k = 0; k < map[B[0]][B[1]].size(); k++) {
					if (map[B[0]][B[1]].get(k) > max) {
						max = map[B[0]][B[1]].get(k);
					}
				}
			}

		}

	}

	private static void isMax(ArrayList<Integer> List1, ArrayList<Integer> List2) {
		// TODO Auto-generated method stub
		if (List1.size() == List2.size() && List1.size() == 1) {
			sum += List1.get(0);
			return;
		}

		int[] arr1 = new int[List1.size()];
		int[] arr2 = new int[List2.size()];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = List1.get(i);
		}
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = List2.get(i);
		}

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		if (List1.size() != List2.size()) {
			if (List1.size() == 1) {
				sum += arr1[0];
				if (arr2[0] != arr1[0]) {
					sum += arr2[0];
				} else {
					sum += arr2[1];
				}
				return;
			} else if (List2.size() == 1) {
				sum += arr2[0];
				if (arr2[0] != arr1[0]) {
					sum += arr1[0];
				} else {
					sum += arr1[1];
				}
				return;
			}

		}

		int max1 = 0;
		int max2 = 0;
		if (arr1[0] > arr2[0]) {
			max1 = arr1[0];
			if (arr1[1] > arr2[0]) {
				max2 = arr1[1];
			} else {
				max2 = arr2[0];
			}

		} else {
			max1 = arr2[0];
			if (arr2[1] > arr1[0]) {
				max2 = arr2[1];
			} else {
				max2 = arr1[0];
			}
		}

		if (max1 / 2 > max2) {
			sum += max1;
		} else {
			sum += max1;
			sum += max2;
		}
	}

	// 충전기 위치와 범위, 파워를 맵에 나타내는 메서드
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

			if (map[r][col] == null) {
				ArrayList<Integer> newArr = new ArrayList<>();
				newArr.add(power);
				map[r][col] = newArr;
			} else {
				map[r][col].add(power);
			}

			temp++;
			for (int t = 1; t < temp; t++) {
				c = col + t;
				if (c < 10) {
					if (map[r][c] == null) {
						ArrayList<Integer> newArr = new ArrayList<>();
						newArr.add(power);
						map[r][c] = newArr;
					} else {
						map[r][c].add(power);
					}
				}

				c = col - t;
				if (c >= 0) {
					if (map[r][c] == null) {
						ArrayList<Integer> newArr = new ArrayList<>();
						newArr.add(power);
						map[r][c] = newArr;
					} else {
						map[r][c].add(power);
					}
				}
			}
		}

		temp = 0;
		for (int r = (row + range); r > row; r--) {
			if (r >= 10) {
				continue;
			}

			if (map[r][col] == null) {
				ArrayList<Integer> newArr = new ArrayList<>();
				newArr.add(power);
				map[r][col] = newArr;
			} else {
				map[r][col].add(power);
			}
			temp++;
			for (int t = 1; t < temp; t++) {
				c = col + t;
				if (c < 10) {
					if (map[r][c] == null) {
						ArrayList<Integer> newArr = new ArrayList<>();
						newArr.add(power);
						map[r][c] = newArr;
					} else {
						map[r][c].add(power);
					}
				}

				c = col - t;
				if (c >= 0) {
					if (map[r][c] == null) {
						ArrayList<Integer> newArr = new ArrayList<>();
						newArr.add(power);
						map[r][c] = newArr;
					} else {
						map[r][c].add(power);
					}
				}
			}
		}

	}
}
