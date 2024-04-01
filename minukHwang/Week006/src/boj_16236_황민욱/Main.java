package boj_16236_황민욱;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Shark {
	int size;
	int r;
	int c;

	Shark() {
	}

	public Shark(int r, int c) {
		this.size = 2;
		this.r = r;
		this.c = c;
	}

}

class Fish {
	int size;
	int r;
	int c;

	Fish() {

	}

	public Fish(int size, int r, int c) {
		super();
		this.size = size;
		this.r = r;
		this.c = c;
	}

	public int getDistance(int r, int c) {
		int distance = Math.abs(this.r - r) + Math.abs(this.c - c);

		return distance;
	}

	@Override
	public String toString() {
		return "#" + size + "[" + r + ", " + c + "]";
	}
}

public class Main {
	static int N;
	static int map[][];
	static boolean visited[][];
	static List<Fish> fishes;
	static Shark shark;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		fishes = new LinkedList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] > 0 && map[r][c] != 9) {
					Fish fish = new Fish(map[r][c], r, c);
					fishes.add(fish);
				}

				if (map[r][c] == 9) {
					shark = new Shark(r, c);
				}
			}
		}

		int time = eatFish();

		System.out.println(time);
	}

	private static int eatFish() {
		int time = 0;

		// 가까운 생선 찾기
		int minIndex = 0;
		int minLength = Integer.MAX_VALUE;

		for (int i = 0; i < fishes.size(); i++) {
			Fish fish = fishes.get(i);
			int distance = fish.getDistance(shark.r, shark.c);

			if (minLength < distance) {
				continue;
			}

			bfs(fish.r, fish.c, shark.r, shark.c);
		}

		System.out.println(minIndex + " " + minLength);

		return time;
	}

	private static void bfs(int fishR, int fishC, int sharkR, int sharkC) {
		int[] deltaR = { -1, 1, 0, 0 };
		int[] deltaC = { 0, 0, -1, 1 };
		
		Queue<int[]> queue = new LinkedList<>();
		int[] coord = { fishR, fishC };
		queue.add(coord);
		
		int r = 0; 
		int c = 0;
		isNotOutBound(r, c, fishR, fishC, sharkR, sharkC);

	}

	private static void isNotOutBound(int r, int c, int fishR, int fishC, int sharkR, int sharkC) {
		
	}

}
