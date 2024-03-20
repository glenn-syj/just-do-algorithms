package boj_7569_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 3차원 토마토

public class Main {
	
	static int ans;
	
	static int[] dz = new int[] {1, -1, 0, 0, 0, 0};
	static int[] dr = new int[] {0, 0, 1, -1, 0, 0};
	static int[] dc = new int[] {0, 0, 0, 0, 1, -1};
	
	// 3차원을 표현하기 위해, 3차원 배열을 선언하였다.
	static int[][][] box;
	static boolean[][][] visited;
	static List<List<Integer>> firstTomato = new ArrayList<>();
	
	static int c;
	static int r;
	static int z;
	
	static int zero;
	static int one;
	static int minus;
	static int makeTomato;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		c = Integer.parseInt(st.nextToken());
		
		r = Integer.parseInt(st.nextToken());
		
		z = Integer.parseInt(st.nextToken());
		
		box = new int[z][r][c];
		visited = new boolean[z][r][c];
		
		// 조건을 판단해주기 위해, 0의 갯수, 1의 갯수, -1의 갯수를 담을 변수를 선언하였다.
		zero = 0;
		one = 0;
		minus = 0;
		makeTomato = 0;
		
		ans = 0;
		
		
		for(int i = 0; i < z; i++) {
			for(int j = 0; j < r; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < c; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if(box[i][j][k] == 1) {
						one++;
						
						List<Integer> tmp = new ArrayList<>();
						
						tmp.add(i);
						tmp.add(j);
						tmp.add(k);

						// 첫 위치 좌표들을 리스트에 넣어주었다.
						firstTomato.add(tmp);
					}else if(box[i][j][k] == 0) {
						zero++;
					}else if(box[i][j][k] == -1) {
						minus++;
					}
					
				}
			}
		}
		
		
		if(zero == 0 && one >= 1) {
			System.out.println(0);
			return;
		}else if(one == 0) {
			System.out.println(-1);
			return;
		}
		
		// 모든 토마토를 익혔는지 판단하기 위한 flag 변수이다.
		boolean flag = true;
		
		
		Queue<List<Integer>> queue = new LinkedList<>();
		for(int i = 0; i < firstTomato.size(); i++) {
			queue.offer(firstTomato.get(i));
		}
		
		while(true) {
			int qSize = queue.size();
			
			for(int i = 0; i < qSize; i++) {
				List tmp = queue.poll();
				int nz = (int) tmp.get(0);
				int nr = (int) tmp.get(1);
				int nc = (int) tmp.get(2);
				
				for(int d = 0; d < 6; d++) {
					if(nz + dz[d] < 0 || nz + dz[d] >= z || nr + dr[d] < 0 || nr + dr[d] >= r || nc + dc[d] < 0 || nc + dc[d] >= c) {
						continue;
					}
					if(visited[nz + dz[d]][nr + dr[d]][nc + dc[d]] == false && box[nz + dz[d]][nr + dr[d]][nc + dc[d]] == 0) {
						visited[nz + dz[d]][nr + dr[d]][nc + dc[d]] = true;
						makeTomato++;
						List<Integer> tmmp = new ArrayList<>();
						tmmp.add(nz + dz[d]);
						tmmp.add(nr + dr[d]);
						tmmp.add(nc + dc[d]);
						
						queue.offer(tmmp);
					}
				}
			}
			
//			for(int i = 0; i < z; i++) {
//				for(int j = 0; j < r; j++) {
//					for(int k = 0; k < c; k++) {
//						if(visited[i][j][k] == false) {
//							System.out.printf("%d ", 0);
//						}else {
//							System.out.printf("%d ", 1);
//						}
//					}
//					System.out.println();
//				}
//			}
			
			ans++;
			
			if(makeTomato == zero) {
				flag = true;
				break;
			}
			
			if(queue.size() == 0) {
				flag = false;
				break;
			}
			
			
		}
		
		if(flag) {
			System.out.println(ans);
		}else {
			System.out.println(-1);
		}
		
		
	}
	
	
	
}