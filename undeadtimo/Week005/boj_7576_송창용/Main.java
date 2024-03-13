package boj_7576_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int tomaCnt;
	static int cnt;
	static boolean flag;
	static int[][] basket;
	static int[][] tmp;
	static boolean[][] visited;
	static ArrayList<ArrayList<Integer>> startPoints;
	
	static int getTomato;
	static int exTomato;
	static int zeroCnt;
	static int oneCnt;
	
	static int row;
	static int column;
	
	static int[] dr;
	static int[] dc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		column = Integer.parseInt(st.nextToken());
		
		row = Integer.parseInt(st.nextToken());
		
		Queue<ArrayList<Integer>> queue = new LinkedList<>();
		
		basket = new int[row][column];
		visited = new boolean[row][column];
		
		startPoints = new ArrayList<ArrayList<Integer>>();
		
		oneCnt = 0;
		zeroCnt = 0;
		getTomato = 0;
		exTomato = 0;
		
		dr = new int[] {1, 0, -1, 0};
		dc = new int[] {0, 1, 0, -1};
		
		for(int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < column; j++) {
				basket[i][j] = Integer.parseInt(st.nextToken());
				if(basket[i][j] == 1) {
					ArrayList<Integer> tmp = new ArrayList<>();
					tmp.add(i);
					tmp.add(j);
					visited[i][j] = true;
					oneCnt++;
					startPoints.add(tmp);
				}else if(basket[i][j] == 0) {
					zeroCnt++;
				}
			}
		}
		
		tomaCnt = 0;
		cnt = 0;
		flag = false;
		
		if(oneCnt == 0) {
			System.out.println(-1);
			return;
		}else if(zeroCnt == 0 && oneCnt >= 1) {
			System.out.println(0);
			return;
		}
		
		
			// 큐에 처음 1인 것들을 하나씩 넣어준다.
			for(int i = 0; i < startPoints.size(); i++) {
				queue.offer(startPoints.get(i));
			}
			
			// 맨 앞에 있는 것부터 poll 하면서 그 위치에서의 델타 탐색을 돌면서 다음 위치가 0이라면 넣어준다.
			while(queue.size() != 0) {
				int qSize = queue.size();
				// for(int i = 0; i < queue.size(); i++)였음
				// 반복문이 돌아가면서 queue에 요소가 들어가고 삭제되니까, 반복횟수가 자꾸 변했던 거였음
				for(int i = 0; i < qSize; i++) {
					// 이건 대체...
					// queue.poll 해서 가져온 것에서 get하면 int로 형변환 해줘야하고,
					// queue.peek.get하면 형변환이 필요없음.
					ArrayList tmmp = queue.poll();
					int tr = (int)tmmp.get(0);
					int tc = (int)tmmp.get(1);
					for(int j = 0; j < 4; j++) {
						if(tr + dr[j] < 0 || tr + dr[j] >= row || tc + dc[j] < 0 || tc + dc[j] >= column) {
							continue;
						}
						if(visited[tr + dr[j]][tc + dc[j]]) {
							continue;
						}
						if(basket[tr + dr[j]][tc + dc[j]] == 0) {
							visited[tr + dr[j]][tc + dc[j]] = true;
							ArrayList<Integer> tmp = new ArrayList<>();
							tmp.add(tr + dr[j]);
							tmp.add(tc + dc[j]);
							queue.offer(tmp);
							tomaCnt++;
						}
					}
				}
				cnt++;
				if(tomaCnt == zeroCnt) {
					flag = true;
					break;
				}
				if(cnt >= row * column) {
					break;
				}
			}
			
			
			// visited를 이용해서 1을 지나치고, 0을 넣으면서 visited로 변경해준다.
			// -1은 무시한다.
			
			// 맨처음 1을 넣은 갯수만큼 반복한다. 이것이 하루,
			// 이후 새롭게 들어온 애들 전부를 대상으로 반복한다. 이것이 다시 하루.
			
			// 기저조건으로는 델타 탐색을 돌려서 익힐 토마토를 찾으면 큐에 넣는 것인데,
			// 싸이클이 끝난 이후, 큐에 들어있는 것이 없다면, 익힌 토마토가 없다는 것이고,
			// 바로 탐색을 끝내고 -1을 출력하면 된다.
		
		if(flag) {
			System.out.println(cnt);
		}else {
			System.out.println(-1);
		}
		
		
	}
	
}