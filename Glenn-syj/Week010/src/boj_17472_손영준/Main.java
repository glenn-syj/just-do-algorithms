package boj_17472_다리만들기2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* B17472. 다리만들기2
 * 
 * 1. 조건
 * 	1-1. 각 영역들이 직간접적으로 모두 연결될 수 있도록
 * 	1-2. 그러한 상황에서의 다리 길이의 합
 * 	1-3. 영역들은 서로 떨어져 있음
 * 	1-4. 다리는 가로 방향은 가로 방향으로, 세로 방향은 세로 방향으로
 * 		-> 그냥 두 점 사이의 맨하탄 거리를 이용하면 된다!
 * 		=> 꺾일 수 없기 때문에, 완전한 맨하탄 거리는 아니고..
 * 		=> 즉 x값과 y값 중 하나는 일치할 때 거리의 최솟값을 간선으로 삼아야 한다
 * 		=> Integer.MAX_VALUE로 심어두고, 이 간선이 이용되면 -1 출력!
 * 2. 풀이
 * 	2-1. BFS를 이용해서 1을 만나면 해당 구역을 areaPtr 번에 저장 (0에서 시작)
 * 	2-2. 각 구역 내에 있는 모든 점들에 대해서 다른 모든 구역의 모든 점들에 대해
 * 		-> row값 호은 col값이 일치하는 점에 대해서만 거리구하기 수행
 * 	2-3. 최소 거리가 구해지면 간선 집합에 넣어두자!
 * 
 */

public class Main {
	
	static int[] parents;
	static int N, M, ptr;
	static int[][] board;
	static Queue<int[]> que;
	static boolean[][] visited;
	static List<List<int[]>> areas;
	static int[][] deltas = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	
	static class Edge implements Comparable<Edge> {
		
		int A, B, W;
		
		public Edge(int A, int B, int W) {
			this.A = A;
			this.B = B;
			this.W = W;
		}

		
		@Override
		public String toString() {
			return "Edge [A=" + A + ", B=" + B + ", W=" + W + "]";
		}



		@Override
		public int compareTo(Edge o) {
			return this.W - o.W;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));;
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		ptr = 0;
		areas = new ArrayList<List<int[]>>();
		que = new LinkedList<>();
		
		List<Edge> edges = new ArrayList<>();
		
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<M; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// ptr번째 구역마다 해당하는 좌표들 추가
		for (int row=0; row<N; row++) {
			for (int col=0; col<M; col++) {
				if (posCheck(row, col)) {
					bfs(new int[] {row, col});
					ptr++;
				}
			}
		}
		parents = new int[ptr];
		
		for (int i=0; i<ptr; i++) {
			parents[i] = i;
		}
		
		// 각 구역에서마다 다른 구역까지의 최소 거리를 구하는 구간
		int defaultDist, currentDist;
		int leftRow, leftCol, rightRow, rightCol;
		for (int left=0; left<ptr; left++) {
			for (int right=left+1; right<ptr; right++) {
				defaultDist = Integer.MAX_VALUE;
				currentDist = Integer.MAX_VALUE;
				for (int[] leftSpot: areas.get(left)) {
					loop:
					for (int[] rightSpot: areas.get(right)) {
						
						leftRow = leftSpot[0];
						leftCol = leftSpot[1];
						rightRow = rightSpot[0];
						rightCol = rightSpot[1];
						
						if (leftRow == rightRow) {
							
							for (int c=Math.min(leftCol, rightCol)+1;
									c<Math.max(leftCol, rightCol); c++) {
								if (board[leftRow][c]==1) {
									continue loop;
								}
							}
							
							currentDist = Math.abs(leftCol-rightCol) - 1;
							
						} else if (leftCol == rightCol) {
							
							for (int r=Math.min(leftRow, rightRow)+1;
									r<Math.max(leftRow, rightRow); r++) {
								if (board[r][leftCol]==1) {
									continue loop;
								}
							}
							
							currentDist = Math.abs(leftRow-rightRow) - 1;
						}
						
						if (currentDist <= 1) {
							continue;
						}
						defaultDist = Math.min(defaultDist, currentDist);
						
					}
				}
				
				edges.add(new Edge(left, right, defaultDist));
			}
		}
		
		Collections.sort(edges);
		
		// 이제 크루스칼을 돌리면 됨
		// 돌리는 과정에서 Integer.MAX_VALUE를 만나면 바로 끝내고 -1!
		
		int pick = 0;
		int ans = 0;
		int edgePtr = 0;
		Edge edge;
		int A, B, px, py;
		while (edgePtr < edges.size()) {
			edge = edges.get(edgePtr);
			px = findset(edge.A);
			py = findset(edge.B);
			if (px != py) {
				if (edge.W == Integer.MAX_VALUE) {
					ans = -1;
					break;
				}
				ans += edge.W;
				union(px, py);
				pick++;
			}
			edgePtr++;
			
			if (pick == ptr-1) {
				break;
			}
		}
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.close();
		
		
	
	}

	public static void bfs(int[] spot) {
		
		que.add(spot);
		ArrayList<int[]> spots = new ArrayList<>();
		
		int row, col, nextRow, nextCol;
		int[] curr;
		while (!que.isEmpty()) {
			curr = que.poll();
			spots.add(curr);
			row = curr[0];
			col = curr[1];
			
			for (int[] delta: deltas) {
				nextRow = row + delta[0];
				nextCol = col + delta[1];
				
				if (posCheck(nextRow, nextCol)) {
					que.add(new int[] {nextRow, nextCol});
					visited[nextRow][nextCol] = true;
				}
			}
			
		}
		
		areas.add(spots);
		
		
	}
	
	// 대표자만 들어온다고 가정
	public static void union(int x, int y) {
		parents[y] = x;
	}
	
	public static int findset(int x) {
		if (parents[x] != x) {
			parents[x] = findset(parents[x]);
		}
		
		return parents[x];
	}
	
	public static boolean posCheck(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < M
					&& board[row][col] == 1 && !visited[row][col]);
	}
}