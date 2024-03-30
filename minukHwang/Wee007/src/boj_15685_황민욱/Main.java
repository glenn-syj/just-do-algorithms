package boj_15685_황민욱;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;
	static int[] deltaX = { 1, 0, -1, 0 };
	static int[] deltaY = { 0, -1, 0, 1 };

	static List<Integer> directions;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		map = new int[101][101];

		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();

			directions = new ArrayList<>();

			// 일단 0세대 만들기
			map[y][x] = 1;
			map[y + deltaY[d]][x + deltaX[d]] = 1;

			directions.add(d);

			dragonCurve(x + deltaX[d], y + deltaY[d], g);
		}

		int count = 0;

		for (int y = 0; y < 100; y++) {
			for (int x = 0; x < 100; x++) {
				if (map[y][x] == 1 && map[y + 1][x] == 1 && map[y][x + 1] == 1 && map[y + 1][x + 1] == 1) {
					count++;
				}
			}
		}

		System.out.println(count);
	}

	private static void dragonCurve(int endX, int endY, int g) {
		if (g == 0) {
			return;
		}

		for (int i = directions.size() - 1; i >= 0; i--) {
			int d = directions.get(i);

			int nextd = (d + 1) % 4;

			endX += deltaX[nextd];
			endY += deltaY[nextd];

			map[endY][endX] = 1;

			directions.add(nextd);
		}

		dragonCurve(endX, endY, g - 1);
	}

	public static void printMap() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
