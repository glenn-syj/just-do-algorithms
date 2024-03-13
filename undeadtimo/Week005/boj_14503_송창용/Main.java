package boj_14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int ans;
	
	
	// 상, 우, 하, 좌
	// 인덱스를 반대로 이동하면 시계 반대방향이다.
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1}; 
	
	public static void main(String[] args) throws IOException {
		
		ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 행의 갯수
		int r = Integer.parseInt(st.nextToken());
		// 열의 갯수
		int c = Integer.parseInt(st.nextToken());
		
		// 방 생성
		int[][] room = new int[r][c];
		// 방 크기만큼의 방문체크 2차원 배열 생성
		boolean[][] visited = new boolean[r][c];
		
		st = new StringTokenizer(br.readLine());

		// 출발하는 행의 위치
		int startR = Integer.parseInt(st.nextToken());
		// 출발하는 열의 위치
		int startC = Integer.parseInt(st.nextToken());
		// 출발할 때 바라보는 방향
		int startD = Integer.parseInt(st.nextToken());
		
		// 생성한 방에 벽과 청소되지 않은 빈 공간을 입력해주었다.
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dirtyRoom;
		
		first:
		while(true) {
			dirtyRoom = 0;
			// 현재 위치가 청소되지 않은 곳이라면, 그곳을 청소한다.
			if(room[startR][startC] == 0 && visited[startR][startC] == false) {
				visited[startR][startC] = true;
				ans++;
			}
			
			
			
			
			// 현재 위치에서 상하좌우에 청소할 공간이 존재한지 확인한다.
			for(int i = 0; i < 4; i++) {
				if(room[startR + dr[i]][startC + dc[i]] == 0 && visited[startR + dr[i]][startC + dc[i]] == false) {
					dirtyRoom++;
				}
			}
			
			// 청소할 공간이 존재하지 않는다면, 바라보는 방향에서 후진하여 뒤로 한 칸 이동할 것이다.
			if(dirtyRoom == 0) {
				// 단 뒤가 벽이라면 작동을 멈춘다.
				if(room[startR + dr[(startD + 2) % 4]][startC + dc[(startD + 2) % 4]] == 1) {
					break first;
				// 뒤가 벽이 아니라면, 뒤로 한 칸 이동한다.
				}else {
					startR = startR + dr[(startD + 2) % 4];
					startC = startC + dc[(startD + 2) % 4];
					continue first;
				}
			// 청소할 공간이 존재한다면,
			}else {
				
				// 바라보는 방향으로 한 칸 앞이 청소할 공간이라면 그곳으로 이동한다.
					for(int i = 0; i < 4; i++) {
						// 바라보는 방향을 시계 반대방향으로 한 번 회전한다.
						startD = (startD + 3) % 4;
						// 바꾼 방향으로 한 칸 앞에 청소해야할 공간이 있다면,
						if(room[startR + dr[startD]][startC + dc[startD]] == 0 && visited[startR + dr[startD]][startC + dc[startD]] == false) {
							// 그곳으로 이동한다.
							startR = startR + dr[startD];
							startC = startC + dc[startD];
							continue first;
						}
						// 그렇지 않다면 방향 바꾸기를 반복한다.
					}
				
			}
			
		// while문이 끝나는 곳.	
		}
		
		
		
		System.out.println(ans);
		
	}
}