package swea_5644_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Charger {
	int idx;
	int power;

	Charger() {

	}

	Charger(int idx, int power) {
		this.idx = idx;
		this.power = power;
	}

	@Override
	public String toString() {
		return "#" + idx + ": " + power;
	}
}

public class Solution {
	static int T, time, bcCount;
	static List<Charger>[][] map;

	static int[] deltaY = { 0, -1, 0, 1, 0 };
	static int[] deltaX = { 0, 0, 1, 0, -1 };

	static int[] pathA;
	static int[] pathB;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			time = sc.nextInt();
			bcCount = sc.nextInt();

			map = new ArrayList[11][11];

			for (int y = 1; y < 11; y++) {
				for (int x = 1; x < 11; x++) {
					map[y][x] = new ArrayList<>();
				}
			}

			pathA = new int[time + 1];
			pathB = new int[time + 1];

			for (int i = 1; i < time + 1; i++) {
				pathA[i] = sc.nextInt();
			}

			for (int i = 1; i < time + 1; i++) {
				pathB[i] = sc.nextInt();
			}

			for (int i = 1; i < bcCount + 1; i++) {
				setBC(i, sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			}

//			printMap();

			int answer = movePeople(1, 1, 10, 10, 0);
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int movePeople(int aX, int aY, int bX, int bY, int t) {
		int total = 0;

		while (t <= time) {
			// A의 다음 좌표
			int dirA = pathA[t];
			aX += deltaX[dirA];
			aY += deltaY[dirA];
			
//			System.out.println("#"+t);
//			System.out.println("da: " + dirA + " (" + aX + ", " + aY +")" );

			List<Charger> powersA = map[aY][aX];
//			System.out.println(powersA);

			// B의 다음 좌표
			int dirB = pathB[t];
			bX += deltaX[dirB];
			bY += deltaY[dirB];
			
//			System.out.println("db: " + dirB + " (" + bX + ", " + bY +")" );
			
			List<Charger> powersB = map[bY][bX];
//			System.out.println(powersB);
//			System.out.println();

			int max = -1;
			
			if(powersA.size() == 0 && powersB.size() == 0) {
				t++;
				continue;
			} 
			
			if(powersA.size() == 0 && powersB.size() > 0) {
				for(int i = 0; i < powersB.size(); i++) {
					max = Math.max(max, powersB.get(i).power);
				}
			}
			
			if(powersA.size() > 0 && powersB.size() == 0) {
				for(int i = 0; i < powersA.size(); i++) {
					max = Math.max(max, powersA.get(i).power);
				}
			}
			
			if(powersA.size() > 0 && powersB.size() > 0) {		
				for (int i = 0; i < powersA.size(); i++) {
					for (int j = 0; j < powersB.size(); j++) {
						int power = 0;
						
						Charger powerA = powersA.get(i);
						Charger powerB = powersB.get(j);
						
						power = (powerA.power + powerB.power);
						
						if (powerA.idx == powerB.idx) {
							power /= 2;
						}
						
						max = Math.max(power, max);
					}
				}
			}
			
//			System.out.println(max);
			
			total += max;
			t++;
		}

		return total;
	}

	private static void setBC(int idx, int centerX, int centerY, int length, int power) {
		int left = centerX;
		int right = centerX;

		for (int y = centerY - length; y <= centerY + length; y++) {
			for (int x = left; x <= right; x++) {
				if (isNotOutBound(y, x)) {
					map[y][x].add(new Charger(idx, power));
				}
			}

			if (y < centerY) {
				left--;
				right++;
			} else {
				left++;
				right--;
			}
		}
	}

	public static void printMap() {
		for (int r = 1; r < 11; r++) {
			for (int c = 1; c < 11; c++) {
				System.out.printf("%-10s", map[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean isNotOutBound(int y, int x) {
		return y >= 1 && y < 11 && x >= 1 && x < 11;
	}
}
