package swea_2383_황민욱;

import java.util.ArrayList;
import java.util.Arrays;

// 부분 집합 문제!

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
	static int T;
	static int N;
	static int[][] map;

	static int[][] start;
	static int[] stairL;

	static List<Person> people;
	static PriorityQueue<Person> pq1;
	static PriorityQueue<Person> pq2;

	static boolean[] visited;

	static int min;

	static class Person implements Comparable<Person> {
		int r;
		int c;
		int t;

		Person() {

		}

		Person(int r, int c) {
			this.r = r;
			this.c = c;
		}

		private int getTime(int startR, int startC) {
			return Math.abs(this.r - startR) + Math.abs(this.c - startC);
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.t, o.t);
		}

		@Override
		public String toString() {
			return "Person [r=" + r + ", c=" + c + ", t=" + t + "]";
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = sc.nextInt();
			map = new int[N][N];
			people = new ArrayList<>();
			start = new int[2][2];
			stairL = new int[2];

			int idx = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();

					if (map[r][c] == 1) {
						people.add(new Person(r, c));
					}

					if (map[r][c] > 1) {
						start[idx][0] = r;
						start[idx][1] = c;
						stairL[idx] = map[r][c];
						idx++;
					}
				}
			}

			visited = new boolean[people.size()];
			min = Integer.MAX_VALUE;

			subset(0);

			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}

	public static void subset(int idx) {
		if (idx == people.size()) {
			pq1 = new PriorityQueue<>();
			pq2 = new PriorityQueue<>();

			// 부분 집합으로 판별해서 넣어주기
			for (int i = 0; i < visited.length; i++) {
				Person person = people.get(i);

				if (visited[i]) {
					person.t = person.getTime(start[0][0], start[0][1]);
					pq1.add(person);
				} else {
					person.t = person.getTime(start[1][0], start[1][1]);
					pq2.add(person);
				}
			}
//			System.out.println();
//			System.out.println("#1" + pq1);
//			System.out.println("#2" + pq2);
//			System.out.println();

			int time = movePeople(pq1, stairL[0]);
			time = Math.max(movePeople(pq2, stairL[1]), time);

			min = Math.min(min, time);
			return;
		}

		visited[idx] = true;
		subset(idx + 1);
		visited[idx] = false;
		subset(idx + 1);
	}

	private static int movePeople(PriorityQueue<Person> pq, int length) {
//		System.out.println(length);
		if (pq.isEmpty()) {
			return 0;
		}

		int time = pq.peek().t;

		PriorityQueue<Person> stair = new PriorityQueue<>();

		while (!pq.isEmpty() || !stair.isEmpty()) {
//			System.out.println(pq);
			while (!stair.isEmpty() && time - stair.peek().t == length) {
//				System.out.println(time + " : stair " + stair);
				stair.poll();
//				System.out.println(time + " : stair " + stair);
			}

			while (!pq.isEmpty() && pq.peek().t <= time && stair.size() < 3) {
//				System.out.println(time + " : pq " + pq);
				Person person = pq.poll();

				if (person.t == time) {
					person.t++;
				} else {
					person.t = time;
				}

				stair.add(person);
//				System.out.println(time + " : pq " + pq);
			}

			if (pq.isEmpty() && stair.isEmpty()) {
				break;
			}

			time++;
		}

//		System.out.println("time" + time);
		return time;
	}

}
