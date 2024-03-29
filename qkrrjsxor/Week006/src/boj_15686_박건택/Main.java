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

public class Main {
	
	static int N;	//입력 줄 수
	static int M;	//치킨집 개수
	static int[][] map;
	static boolean[] visit;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1}; 	//위 오른 아래 왼 순ㅓ
	static List<int[]> chicken;
	static List<int[]> home;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String str;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		int[] location;		//좌표값 저장을 위한 길이 2 배열
		chicken = new ArrayList<>();	//치킨집들의 위치를 저장할 리스트
		home = new ArrayList<>();		//집
		Queue<int[]> q = new LinkedList<>();		//bfs를 위한 큐
		
		int tmp;
		for(int i = 0; i< N; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			
			for(int t = 0; t< N; t++) {
				tmp = Integer.parseInt(st.nextToken()) ; 	
//				map[i][t] = tmp;
				
				
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
					home.add(location);
				}
			}
		}
		
		ans = Integer.MAX_VALUE;
		visit = new boolean[chicken.size()];
		
		DFS(0,0);
		System.out.println(ans);
	}
	public static void DFS(int start, int cnt) {
		
		if(cnt == M) {
			int res=0;
			
			for(int i=0; i< home.size(); i++) {
				int temp = Integer.MAX_VALUE;
				for(int j = 0; j< chicken.size(); j++) {
					if(visit[j]) {
						int[] tempHome = home.get(i);
						int[] tempChicken = chicken.get(j);
						int distance = Math.abs(tempHome[0] - tempChicken[0]) + Math.abs(tempHome[1] - tempChicken[1]);
						
						temp = Math.min(temp, distance);
					}
				}
				res += temp;
			}
			ans = Math.min(ans,  res);
			return;
		}
		
		//백트래킹
		for (int i = start; i<chicken.size(); i++) {
			visit[i] = true;
			DFS(i+1, cnt+1);
			visit[i] = false;
		}
		
	}
}
