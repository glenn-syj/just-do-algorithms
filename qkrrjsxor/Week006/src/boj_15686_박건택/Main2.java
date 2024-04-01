package boj_15686_박건택;
/*
 * BFS를 이용해서 풀어보겟습니다
 * 치킨집들의 좌표들을 저장(리스트에 {r,c} 배열 형태로)
 * M개만 남겨놓는 경우의 수 돌면서 탐색
 * 
 * 치킨집들의 위치들을 리스트에 저장하고, M개만 남겨놓고 BFS탐색에 쓰자
 * 거리 배열을 위해 NxN 배열 모든 요소가 -1으로 초기화, 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	
	static int N;	//입력 줄 수
	static int M;	//치킨집 개수
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1}; 	//위 오른 아래 왼 순서 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String str;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		
		int[] location;		//좌표값 저장을 위한 길이 2 배열
		List<int[]> chicken = new ArrayList<>();	//치킨집들의 위치를 저장할 리스트
//		List<int[]> home = new ArrayList<>();		//집
		Queue<int[]> q = new LinkedList<>();		//bfs를 위한 큐
		
		int tmp;
		for(int i = 0; i< N; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			
			for(int t = 0; t< N; t++) {
				tmp = Integer.parseInt(st.nextToken()) ; 	
				map[i][t] = tmp;
				
				
				if(tmp == 2) {
					location = new int[2];
					location[0] = i;
					location[1] = t;
					chicken.add(location);
				}
				else if(tmp == 1) {
					location = new int[2];
					location[0] = i;
					location[1] = t;
					q.offer(location);
				}
			}
		}
		
		//BFS 시작
		
	}
}
