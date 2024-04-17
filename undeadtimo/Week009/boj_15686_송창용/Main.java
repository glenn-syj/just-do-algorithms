package boj_15686_치킨배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int ans;
	
	static List<List<Integer>> aliveChicken;
	static boolean[] flags;
	
	static List<List<Integer>> chickens;
    static List<List<Integer>> people;
    
    static int chickenCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		
		ans = Integer.MAX_VALUE;
		
		int mapSize = Integer.parseInt(st.nextToken());
		
		chickenCnt = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[mapSize][mapSize];
		
		people = new ArrayList<>();
		chickens = new ArrayList<>();
		
		
		for(int i = 0; i < mapSize; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(i);
					tmp.add(j);
					
					people.add(tmp);
				}else if(map[i][j] == 2) {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(i);
					tmp.add(j);
					
					chickens.add(tmp);
				}
			}
			
			
		}
		
		flags = new boolean[chickens.size()];
		
		// 살아남을 치킨집을 골라줄 조합
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	public static void comb(int idx, int sidx) {
		
		
		// 조합이 결정될 경우, 해당 조합을 바탕으로 최소 거리 구하기.
		if(sidx == chickenCnt) {
			aliveChicken = new ArrayList<>();
			
			for(int i = 0; i < flags.length; i++) {
				if(flags[i]) {
					aliveChicken.add(chickens.get(i));
				}
			}
			
			
			dist();
			return;
		}
		
		if(idx == chickens.size()) {
			return;
		}
		
		flags[idx] = true;
		comb(idx + 1, sidx + 1);
		flags[idx] = false;
		comb(idx + 1, sidx);
		
	}
	
	public static void dist() {
		int total = 0;
		
		
		
		for(int i = 0; i < people.size(); i++) {
			int mini = Integer.MAX_VALUE;
			int peopleR = people.get(i).get(0);
			int peopleC = people.get(i).get(1);
			
			for(int j = 0; j < aliveChicken.size(); j++) {
				int chickenR = aliveChicken.get(j).get(0);
				int chickenC = aliveChicken.get(j).get(1);
				
				int distance = Math.abs(peopleR - chickenR) + Math.abs(peopleC - chickenC);
				
				if(mini > distance) {
					mini = distance;
				}
				
			}
			
			total += mini;
			
		}
		
		if(ans > total) {
			ans = total;
		}
		
	}
	
}