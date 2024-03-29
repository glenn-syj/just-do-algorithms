package boj_15686_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, M;
	static List<int[]> home;
	static List<int[]> chicken;
	static int chickenCount;
	static boolean visited[];
	static int[][] selected;
	static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		home = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int num = sc.nextInt();
				if (num == 1) {
					int[] coord = { r, c };
					home.add(coord);
				}

				if (num == 2) {
					int[] coord = { r, c };
					chicken.add(coord);
				}
			}
		}

		chickenCount = chicken.size();
		visited = new boolean[chickenCount];
		selected = new int[M][2];
		min = Integer.MAX_VALUE;
		
		com(0,0);
		
		System.out.println(min);
	}

	public static void com(int start, int depth) {
		if (depth == M) {
			int total = 0;
			
			for(int i = 0; i < home.size(); i++) {
				int minLength = Integer.MAX_VALUE;
				int homeR = home.get(i)[0];
				int homeC = home.get(i)[1];
				
				for(int j = 0; j < M; j++) {
					int chickenR = selected[j][0];
					int chickenC = selected[j][1];
					
					int length = Math.abs(homeR - chickenR) + Math.abs(homeC - chickenC);
					
					minLength = Math.min(minLength, length);
				}
				
				total += minLength;
			}
			
			min = Math.min(min, total);
			
			return;
		}

		for (int i = start; i < chickenCount; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[depth] = chicken.get(i);
				com(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}

}
