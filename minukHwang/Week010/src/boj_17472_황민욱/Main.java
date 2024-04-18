package boj_17472_황민욱;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Coord {
	int r;
	int c;

	Coord() {

	}

	Coord(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "(" + r + ", " + c + ")";
	}
}

class Bridge implements Comparable<Bridge> {
	int st;
	int ed;
	int d;

	Bridge() {

	}

	Bridge(int st, int ed, int d) {
		this.st = st;
		this.ed = ed;
		this.d = d;
	}

	@Override
	public int compareTo(Bridge o) {
		return Integer.compare(this.d, o.d);
	}

	@Override
	public String toString() {
		return st + " -> " + ed + " d=" + d;
	}
}

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] temp;
	static int[][] info;

	static List<Bridge>[] bridges;
	static List<List<Coord>> island;

	static int[] deltaR = { -1, 1, 0, 0 };
	static int[] deltaC = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		island = new ArrayList<>();
		info = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		int num = 1;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0)
					continue;

				checkIsland(r, c, num);
				num++;
			}
		}
		
		bridges = new ArrayList[island.size() + 1];
		
		for(int i = 0; i < island.size() + 1; i++) {
			bridges[i] = new ArrayList<>(); // 초기화
		}
		
		for(int i = 0; i < island.size(); i++) {
			
			for(int j = 0; j < island.get(i).size(); j++) {
				Coord coord = island.get(i).get(j);
				
				checkBridge(coord.r, coord.c);
			}
		}
		
		boolean[] visited = new boolean[island.size() + 1];
		
		PriorityQueue<Bridge> pq = new PriorityQueue<>();
		
		visited[1] = true;
		
		pq.addAll(bridges[1]);
		
		int pick = 1;
		int min = 0;
		
		while(pick != island.size() && !pq.isEmpty()) {
			Bridge b = pq.poll();
			
			if(visited[b.ed]) {
				continue;
			}
		
			min += b.d;
			visited[b.ed] = true;
			pq.addAll(bridges[b.ed]);
			
			pick++;
		}
		
		if(pick != island.size()) {
			min = -1;
		}
		
		System.out.println(min);
	}

	private static void checkBridge(int r, int c) {
		int num = info[r][c];
		
		for(int d = 0; d < 4; d++) {
			int length = 0;
			int nr = r + deltaR[d];
			int nc = c + deltaC[d];
			
			while(isNotOutBound(nr, nc)){
				// 만약 바로 다음이 그냥 땅이라면, 혹은 거리가 1이라면
				if(length <= 1 && info[nr][nc] != 0) {
					break;
				}
				
				// 만약 다른 섬이라면 (간 거리가 1보다 크고, 다음 좌표가 0이 아니지만, 원래 섬이 아닌경우)
				if(length > 1 && info[nr][nc] != 0) {
					// 다리 추가 로직
					if(info[nr][nc] != num) {
						bridges[num].add(new Bridge(num, info[nr][nc], length));
					}
					
					break;
				}
				
				nr += deltaR[d];
				nc += deltaC[d];
				length++;
			}
		}
	}

	private static void checkIsland(int r, int c, int num) {
		boolean visited[][] = new boolean[N][M];
		Queue<Coord> queue = new LinkedList<>();
		List<Coord> list = new ArrayList<>();

		queue.add(new Coord(r, c));
		visited[r][c] = true;
		map[r][c] = 0;
		info[r][c] = num;

		while (!queue.isEmpty()) {
			Coord curr = queue.poll();
			list.add(curr);

			for (int d = 0; d < 4; d++) {
				int nextR = curr.r + deltaR[d];
				int nextC = curr.c + deltaC[d];

				// 밖으로 나가지 않으면서, 방문하지 않았고, 지도가 1이라면
				if (isNotOutBound(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
					queue.add(new Coord(nextR, nextC));
					visited[nextR][nextC] = true;
					map[nextR][nextC] = 0;
					info[nextR][nextC] = num;
				}
			}
		}
		
		island.add(list);
	}
	
	public static void printMap(int[][] m) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(m[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean isNotOutBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
